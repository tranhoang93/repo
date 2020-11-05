import React, { useEffect, useState, Fragment } from 'react';
import { Breadcrumb, Spin, Input, Form, Button, Tabs, Tree } from 'antd';
import { HomeOutlined } from '@ant-design/icons';
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
import { push } from 'connected-react-router';

function UserGroupEditor(props) {
    const dispatch = useDispatch();
    const [checkedKeys, setCheckedKeys] = useState([]);
    const { t } = useTranslation();
    const groupId = props.match && props.match.params && props.match.params.id;
    const fetching = useSelector(state => state.userGroupEditor.fetching);
    const group = useSelector(state => state.userGroupEditor.group);
    const allMenus = useSelector(state => state.userGroupEditor.allMenus);
    const allowedMenus = useSelector(state => state.userGroupEditor.allowedMenus);
    const error = useSelector(state => state.userGroupEditor.error);
    const submitting = useSelector(state => state.userGroupEditor.submitting);
    const groupUpdated = useSelector(state => state.userGroupEditor.groupUpdated);
    const [form] = Form.useForm();
    const point = useBreakpoint();


    // console.log("UserGroupEditor rendering ........... ");

    useEffect(() => {
        dispatch({ type: ALL_MENUS, payload: System.allMenus() });
        if (groupId) {
            dispatch({ type: USER_GROUP_EDITOR_LOAD, payload: UserGroups.details(groupId) })
            dispatch({ type: ALLOWED_MENUS, payload: UserGroups.allowedMenus(groupId) })
        } else {
            dispatch({ type: USER_GROUP_EDITOR_LOAD, payload: null });
        }
        return function cleanup() {
            dispatch({ type: USER_GROUP_EDITOR_UNLOAD })
        }
    }, [dispatch, groupId]);

    const onFormFinish = (values) => {
        if (group && group.id) {
            const data = { ...values, allowedMenuIds: checkedKeys }
            dispatch({ type: UPDATE_USER_GROUP, payload: UserGroups.update(groupId, data) })
        } else {
            dispatch({ type: CREATE_USER_GROUP, payload: UserGroups.create(values) })
        }
    }

    const getFormInitValues = () => {
        const a = {
            name: group ? group.name : '',
            description: group ? group.description : '',
        }
        return a;
    }

    const onMenuTreeChecked = (checkedKeys, event) => {
        console.log("Checked keys ", checkedKeys);
        // console.log("Event: ", event);
        setCheckedKeys(checkedKeys)
    }

    const getGroupCheckedKeys = () => {
        if (allowedMenus) {
            const keys = [];
            for (let i = 0; i < allowedMenus.length; i++) {
                keys.push(allowedMenus[i].id);
            }
            return keys;
        }
        return [];
    }

    const groupCheckedKeys = getGroupCheckedKeys();

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

    const menuTreeData = buildMenuTreeData(allMenus);
    const expandedKeys = getParentKeys(menuTreeData);

    let breadcrumb = null;
    if (!error) {
        if (group && group.id) {
            breadcrumb = (
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
            breadcrumb = (
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
            )
        }
    }

    if (fetching) {
        return <Spin spinning />
    } 
    else {
        if (error) {
            return (
                <React.Fragment>
                    {breadcrumb}
                    <p>Error</p>
                </React.Fragment>
            )
        } else {

            if(groupUpdated){
                console.log("Group updated")
                dispatch(push('/system/userGroups'));
                return <p>Group updated</p>;
            } else {
                console.log("There's no group updated")
                let labelColSpan = 8;
                let wrapperColSpan = 16;
                if (point === 'lg') {
                    labelColSpan = 4;
                    wrapperColSpan = 8;
                }
                const layout = {
                    labelCol: { span: labelColSpan },
                    wrapperCol: { span: wrapperColSpan }
                };
                const tailLayout = {
                    wrapperCol: { offset: labelColSpan, span: wrapperColSpan }
                };
    
                const nameRules = [{ required: true, message: t('Name is required') }]
                let saveBtnText = groupId ? t('Save changes') : t('Save');
                const initValues = getFormInitValues();
                let treeFormItem = null;
                if (group && group.id) {
                    treeFormItem = (
                        <Form.Item label="Allowed menus">
                            <Tree
                                selectable={false}
                                checkable
                                onCheck={onMenuTreeChecked}
                                expandedKeys={expandedKeys}
                                defaultCheckedKeys={groupCheckedKeys}
                                treeData={menuTreeData} />
                        </Form.Item>
                    );
                }
    
                const formItems = (
                    <Fragment>
                        <Form.Item
                            label={t('Group name')}
                            name="name"
                            rules={nameRules}>
                            <Input />
                        </Form.Item>
                        <Form.Item
                            label={t('Description')}
                            name="description">
                            <Input />
                        </Form.Item>
    
                        {treeFormItem}
    
                        <Form.Item {...tailLayout}>
                            <Button loading={submitting} type='primary'
                                htmlType='submit'>
                                {saveBtnText}
                            </Button>
                        </Form.Item>
                    </Fragment>
    
                );
    
                // In case of edit, wait for everything to be in place
                if (group && group.id) {
                    console.log("Case EDIT")
                    if (group && allowedMenus) {
                        console.log("Edit ready")
                        return (
                            <React.Fragment>
                                {breadcrumb}
                                <Form form={form} {...layout}
                                    initialValues={initValues}
                                    onFinish={onFormFinish}>
                                    {formItems}
                                </Form>
    
                            </React.Fragment>
                        )
                    } else {
                        console.log("Group and allowedMenus are not ready");
                        return <p>Group and allowedMenus are not ready</p>
                    }
                }
                // Case add new
                else if (group) {
                    return (
                        <React.Fragment>
                            {breadcrumb}
                            <Form form={form} {...layout}
                                initialValues={initValues}
                                onFinish={onFormFinish}>
                                {formItems}
                            </Form>
    
                        </React.Fragment>
                    );
                } else {
                    return <Spin spinning />
                }
            }
        }
    }

}

export default UserGroupEditor;