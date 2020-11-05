import React, { Fragment, useEffect } from 'react';
import { Breadcrumb, Spin, Row, Col, Typography, Tabs, Button, Tree } from 'antd';
import { HomeOutlined, EditOutlined, DeleteOutlined } from '@ant-design/icons';
import { NavLink } from 'react-router-dom';
import { useTranslation } from 'react-i18next';
import { useDispatch, useSelector } from 'react-redux';
import { USER_GROUP_DETAILS_LOAD, USER_GROUP_DETAILS_UNLOAD, ALL_MENUS, ALLOWED_MENUS } from '../../store/actionConstants';
import { UserGroups, System } from '../../agent/services';
import useBreakpoint from '../hooks/useBreakpoint';
import HeaderButtons from '../ui/HeaderButtons';
import { push } from 'connected-react-router';

const { Text } = Typography;
const { TabPane } = Tabs;

function UserGroupDetails(props) {
    const { t } = useTranslation();
    const dispatch = useDispatch();

    const point = useBreakpoint();
    const groupId = props.match.params.id;
    const group = useSelector(state => state.userGroupDetails.group);
    const fetching = useSelector(state => state.userGroupDetails.fetching);
    const allMenus = useSelector(state => state.userGroupDetails.allMenus);
    const allowedMenus = useSelector(state => state.userGroupDetails.allowedMenus);

    useEffect(() => {
        dispatch({ type: USER_GROUP_DETAILS_LOAD, payload: UserGroups.details(groupId) });
        dispatch({ type: ALL_MENUS, payload: System.allMenus() });
        dispatch({ type: ALLOWED_MENUS, payload: UserGroups.allowedMenus(groupId) });

        return function cleanup() {
            dispatch({ type: USER_GROUP_DETAILS_UNLOAD });
        }

    }, [dispatch, groupId]);

    const showConfirmDelete = () => {

    }


    const getGroupCheckedKeys = () => {
        if(allowedMenus){
            const keys = [];
            for(let i = 0; i < allowedMenus.length; i++){
                keys.push(allowedMenus[i].id);
            }
            return keys;
        }
        return [];
    }

    const groupCheckedKeys = getGroupCheckedKeys();

    const gotoEdit = () => {
        dispatch(push('/system/userGroups/' + groupId + '/edit'))
    }



    const addChildRecursive = (serverMenuItem) => {
        console.log("Menu Adding: " + serverMenuItem.code);
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
    }

    if (fetching) {
        return <Spin spinning />
    } else {
        let labelSpan = 8;
        let contentSpan = 8;
        const gutter = [8, 8];
        if (point === 'lg') {
            labelSpan = 4;
            contentSpan = 8;
        }
        if(group && allowedMenus){
            return (
                <Fragment>
                    {breadcrumb}
    
                    <HeaderButtons>
                        <Button icon={<EditOutlined />} type='primary' onClick={gotoEdit}>{t('edit')}</Button>
                        <Button className="ml-8"
                            icon={<DeleteOutlined />}
                            type='primary' danger
                            onClick={showConfirmDelete}>
                            {t('delete')}
                        </Button>
                    </HeaderButtons>
    
                    <Row gutter={gutter}>
                        <Col span={labelSpan}>
                            <Text strong>{t('Group name')}</Text>
                        </Col>
                        <Col span={contentSpan}>
                            <Text>{group.name}</Text>
                        </Col>
                    </Row>
                    <Row gutter={gutter}>
                        <Col span={labelSpan}>
                            <Text strong>{t('Description')}</Text>
                        </Col>
                        <Col span={contentSpan}>
                            <Text>{group.description}</Text>
                        </Col>
                    </Row>
    
                    <Tabs defaultActiveKey='1'
                        className="mt-16"
                        type='card'>
                        <TabPane tab={t('Allowed menus')} key={1} className="ml-16">
                            <Tree
                                checkable
                                defaultExpandedKeys={[]}
                                expandedKeys={expandedKeys}
                                checkedKeys={groupCheckedKeys}
                                treeData={menuTreeData} />
                        </TabPane>
                        <TabPane tab={t('Members')} key={2}>
                            Members
                        </TabPane>
                    </Tabs>
                </Fragment>
            );   
        } else {
            return <Spin spinning />
        }
    }

}

export default UserGroupDetails;