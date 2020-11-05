import React, { useEffect, useState, Fragment } from 'react';
import { Breadcrumb, Spin, Input, Form, Button, Tree, Result, notification } from 'antd';
import { HomeOutlined, SaveOutlined } from '@ant-design/icons';
import { NavLink } from 'react-router-dom';
import { useTranslation } from 'react-i18next';
import { useDispatch, useSelector } from 'react-redux';
import {
    USER_GROUP_EDITOR_LOAD,
    USER_GROUP_EDITOR_UNLOAD,
    CREATE_USER_GROUP, UPDATE_USER_GROUP,
    ALL_MENUS, ALLOWED_MENUS
} from '../../store/actionConstants';
import { UserGroups, System } from '../../agent/services';
import useBreakpoint from '../hooks/useBreakpoint';
import { push, goBack } from 'connected-react-router';

function UserGroupEditor(props) {
    const dispatch = useDispatch();
    const [checkedKeys, setCheckedKeys] = useState([]);
    const [form] = Form.useForm();
    const { t } = useTranslation();
    const groupId = props.match && props.match.params && props.match.params.id;
    const fetching = useSelector(state => state.userGroupEditor.fetching);
    const submitting = useSelector(state => state.userGroupEditor.submitting);
    const group = useSelector(state => state.userGroupEditor.group);
    const fetchError = useSelector(state => state.userGroupEditor.fetchError);
    const submitError = useSelector(state => state.userGroupEditor.submitError);
    const allMenus = useSelector(state => state.userGroupEditor.allMenus);
    const allowedMenus = useSelector(state => state.userGroupEditor.allowedMenus);
    const groupUpdated = useSelector(state => state.userGroupEditor.groupUpdated);
    const groupCreated = useSelector(state => state.userGroupEditor.groupCreated);
    const point = useBreakpoint();


    // console.log("UserGroupEditor rendering ........... ");
    useEffect(() => {
        dispatch({ type: ALL_MENUS, payload: System.allMenus() });
        if (groupId) {
            dispatch({ type: ALLOWED_MENUS, payload: UserGroups.allowedMenus(groupId) })
            dispatch({ type: USER_GROUP_EDITOR_LOAD, payload: UserGroups.details(groupId) })
        } else {
            dispatch({ type: USER_GROUP_EDITOR_LOAD, payload: null });
        }
        return function cleanup() {
            dispatch({ type: USER_GROUP_EDITOR_UNLOAD })
        }
    }, [dispatch, groupId]);

    const renderLoadingView = () => (
        <Spin spinning>
            <div style={{ width: '300px', height: '300px' }} />
        </Spin>
    )

    const renderBreadcrumb = () => {
        if (fetchError) {
            return null;
        }
        if (group && group.id) {
            return (
                <Breadcrumb className="mb-16">
                    <Breadcrumb.Item>
                        <NavLink to="/">
                            <HomeOutlined />
                        </NavLink>
                    </Breadcrumb.Item>
                    <Breadcrumb.Item>
                        {t('System settings')}
                    </Breadcrumb.Item>
                    <Breadcrumb.Item>
                        <NavLink to="/system/userGroups">
                            {t('User groups')}
                        </NavLink>
                    </Breadcrumb.Item>
                    <Breadcrumb.Item>
                        {group.name}
                    </Breadcrumb.Item>
                </Breadcrumb>
            )
        } else {
            return (
                <Breadcrumb className="mb-16">
                    <Breadcrumb.Item>
                        <NavLink to="/">
                            <HomeOutlined />
                        </NavLink>
                    </Breadcrumb.Item>
                    <Breadcrumb.Item>
                        {t('System settings')}
                    </Breadcrumb.Item>
                    <Breadcrumb.Item>
                        <NavLink to="/system/userGroups">
                            {t('User groups')}
                        </NavLink>
                    </Breadcrumb.Item>
                    <Breadcrumb.Item>
                        {t('Add new')}
                    </Breadcrumb.Item>
                </Breadcrumb>
            );
        }
    }

    const isFormValidationError = (error) => {
        return error && error.response && error.response.data.code === 'VALIDATION_ERROR';
    }

    const isBadRequestError = (error) => {
        return error && error.response && error.response.code === 'BAD_REQUEST';
    }

    const showErrorDialog = (error) => {
        console.log("Error: ", error);
    }

    const onFormFinish = (values) => {
        if (group && group.id) {
            const data = { ...values, allowedMenuIds: checkedKeys }
            dispatch({ type: UPDATE_USER_GROUP, payload: UserGroups.update(groupId, data) })
        } else {
            dispatch({ type: CREATE_USER_GROUP, payload: UserGroups.create(values) })
        }
    }

    const goToDetailsView = () => {
        dispatch(goBack());
    }

    const renderForm = () => {
        if (!isReady()) {
            return null;
        }
        let labelSpan = 8;
        let wrapperSpan = 16;
        if (point === 'lg') {
            labelSpan = 4;
            wrapperSpan = 8;
        }
        let layout = {
            labelCol: { span: labelSpan },
            wrapperCol: { span: wrapperSpan }
        }
        const tailLayout = {
            wrapperCol: { offset: labelSpan, span: wrapperSpan }
        }
        const initValues = {
            name: group ? group.name : '',
            description: group ? group.description : ''
        }
        const nameRules = [{ required: true, message: t('Name is required') }];

        const menuTreeData = buildMenuTreeData(allMenus);
        const expandedKeys = getParentKeys(menuTreeData);
        const initCheckedKeys = calculateInitCheckedKeys();
        const treeFormItem = groupId ?
            <Form.Item label="Allowed menus">
                <Tree
                    selectable={false}
                    checkable
                    onCheck={onMenuTreeChecked}
                    expandedKeys={expandedKeys}
                    defaultCheckedKeys={initCheckedKeys}
                    treeData={menuTreeData} />
            </Form.Item>
            : null
        return (
            <Form {...layout} form={form} initialValues={initValues} onFinish={onFormFinish}>
                <Form.Item label={t('Group name')} name="name" rules={nameRules}>
                    <Input />
                </Form.Item>
                <Form.Item label={t('Description')} name="description">
                    <Input />
                </Form.Item>
                {treeFormItem}
                <Form.Item {...tailLayout}>
                    <Button loading={submitting} type='primary' htmlType='submit' icon={<SaveOutlined />} >
                        {t('Save')}
                    </Button>
                    <Button onClick={goToDetailsView} className="ml-8">{t('Cancel')}</Button>
                </Form.Item>
            </Form>
        )
    }

    const renderWholeView = () => {
        return (
            <Fragment>
                {renderBreadcrumb()}
                {renderForm()}
            </Fragment>
        )
    }

    const placeErrorOnForm = () => {
        const formValues = form.getFieldsValue();
        const violations = submitError.response.data.violations;
        const fields = [];
        violations.forEach(v => {
            const errors = [];
            errors.push(v.errorMessage);
            fields.push({
                name: v.fieldName,
                value: formValues[v.fieldName],
                errors: errors
            });
        })
        form.setFields(fields);
    }

    const renderLoadErrorView = () => {
        return (
            <Result status="500"
                subTitle="Sorry, something went wrong!"
                title="Error" />
        )
    }

    const isReady = () => {
        if (groupId) {
            return !!allMenus && !!group && allowedMenus
        } else {
            return !!allMenus
        }
    }



    const onMenuTreeChecked = (checkedKeys, event) => {
        console.log("Checked keys ", checkedKeys);
        // console.log("Event: ", event);
        setCheckedKeys(checkedKeys)
    }

    const calculateInitCheckedKeys = () => {
        if (allowedMenus) {
            const keys = [];
            for (let i = 0; i < allowedMenus.length; i++) {
                keys.push(allowedMenus[i].id);
            }
            return keys;
        }
        return [];
    }

    const addChildRecursive = (serverMenuItem) => {
        const children = [];
        if (serverMenuItem.children && serverMenuItem.children.length > 0) {
            for (let i = 0; i < serverMenuItem.children.length; i++) {
                const child = addChildRecursive(serverMenuItem.children[i]);
                children.push(child);
            }
        }
        let item;
        if (children.length > 0) {
            item = {
                title: t('menu' + serverMenuItem.code),
                key: serverMenuItem.id,
                icon: <HomeOutlined />,
                children: children
            };
        } else {
            item = {
                title: t('menu' + serverMenuItem.code),
                key: serverMenuItem.id,
                icon: <HomeOutlined />
            };
        }

        return item;
    }

    const buildMenuTreeData = (allMenus) => {
        if (!allMenus || allMenus.length === 0) {
            return [];
        } else {
            const treeData = [];
            for (let i = 0; i < allMenus.length; i++) {
                const serverMenuItem = allMenus[i];
                const item = addChildRecursive(serverMenuItem);
                treeData.push(item);
            }
            return treeData;
        }
    }

    // Đệ quy
    const getParentKeysForNode = (node, keysHolder) => {
        if (node.children && node.children.length > 0) {
            keysHolder.push(node.key);
            for (let i = 0; i < node.children.length; i++) {
                const child = node.children[i];
                getParentKeysForNode(child, keysHolder);
            }
        }
    }

    // Lấy key của tất cả các node có con,
    // dùng để expand tất cả các nhánh của cây menu
    const getParentKeys = (menuTreeData) => {
        const keys = [];
        for (let i = 0; i < menuTreeData.length; i++) {
            getParentKeysForNode(menuTreeData[i], keys);
        }
        return keys;
    }

    const handleSubmitError = () => {
        if (isFormValidationError(submitError)) {
            const view = renderWholeView()
            placeErrorOnForm();
            return view;
        } else if (isBadRequestError(submitError)) {
            const view = renderWholeView()
            showErrorDialog(submitError);
            return view;
        }
    }

    const handleSubmittedOrLoaded = () => {
        if (groupUpdated) {
            notification.success({
                message: t('Success'),
                description: t('User group updated successfully!')
            })
            dispatch(push('/system/userGroups/' + groupId));
            dispatch({ type: USER_GROUP_EDITOR_UNLOAD })
            return null;
        } else {
            if (groupCreated) {
                notification.success({
                    message: t('Success'),
                    description: t('User group created successfully!')
                })
                dispatch(push('/system/userGroups/'));
                dispatch({ type: USER_GROUP_EDITOR_UNLOAD })
                return null;
            } else {
                if (isReady()) {
                    return renderWholeView();
                } else {
                    return renderLoadingView();
                }
            }

        }
    }
    const handleNotFetching = () => {
        if (submitError) {
            return handleSubmitError();
        } else {
            return handleSubmittedOrLoaded();
        }
    }


    // Main
    if (fetching) {
        return renderLoadingView();
    } else {
        if (fetchError) {
            return renderLoadErrorView();
        } else {
            return handleNotFetching();
        }
    }


}

export default React.memo(UserGroupEditor);