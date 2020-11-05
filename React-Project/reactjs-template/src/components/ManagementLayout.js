import React from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { NavLink, Switch, Route } from 'react-router-dom';
import { Layout, Menu } from 'antd';
import {
    UserOutlined, MenuUnfoldOutlined, MenuFoldOutlined,
} from '@ant-design/icons';
import { Avatar } from 'antd';
import UserList from './user/UserList';
import { SIDER_COLLAPSE } from '../store/actionConstants';
import logo from '../assets/images/logo.png';
import './ManagementLayout.css';
import UserDetails from './user/UserDetails';
import UserEditor from './user/UserEditor';
import { useTranslation } from 'react-i18next';
import MenuManagement from './system/MenuManagement';
import UserGroupList from './userGroup/UserGroupList';
import UserGroupDetails from './userGroup/UserGroupDetails';
import UserGroupEditor from './userGroup/UserGroupEditor';
import BaseTestList from './basetest/BaseTestList';
const { Header, Footer, Sider } = Layout;
const { SubMenu } = Menu;


function LoggedInView(props) {
    const { t } = useTranslation();
    const dispatch = useDispatch();
    const menuCollapsed = useSelector(state => state.common.menuCollapsed);
    const fetchedMenuData = useSelector(state => state.common.fetchedMenu);
    const allMenuData = useSelector(state => state.common.allMenuData);
    const currentUser = useSelector(state => state.auth.currentUser);

    const toggle = () => {
        const newCollapsed = !menuCollapsed;
        dispatch({ type: SIDER_COLLAPSE, collapsed: newCollapsed });
    }

    const cloneMenuItem = (localItem) => {
        return {
            ...localItem
        }
    }
    const mergeMenu = (fetched, all) => {
        const result = [];
        fetched.forEach(fetchedItem => {
            const fetchedCode = fetchedItem.code;
            for (let i = 0; i < all.length; i++) {
                const localItem = all[i];
                const localCode = localItem.code;
                if (fetchedCode === localCode) {
                    const newLevel1MenuItem = cloneMenuItem(localItem);

                    // Clone its children if any
                    if (fetchedItem.children && fetchedItem.children.length > 0) {
                        const newChildren = [];
                        fetchedItem.children.forEach(fetchedChildItem => {
                            for (let j = 0; j < allMenuData.length; j++) {
                                const localItem = allMenuData[j];
                                if (fetchedChildItem.code === localItem.code) {
                                    const newChildItem = cloneMenuItem(localItem);
                                    newChildItem['parent'] = newLevel1MenuItem;
                                    newChildren.push(newChildItem);
                                }
                            }

                        })
                        newLevel1MenuItem['children'] = newChildren;
                    }
                    result.push(newLevel1MenuItem);
                }
            }
        })
        return result;
    }

    const buildMenu = (items) => {
        return items.map(item => {
            if (!item.children || item.children.length === 0) {
                return (
                    <Menu.Item key={item.code}>
                        <NavLink to={item.path}>
                            {item.icon}
                            <span>{t(item.title)}</span>
                        </NavLink>
                    </Menu.Item>
                )
            } else {
                // console.log("Redering children: ", item.children);
                const childrenContent = item.children.map(childItem => {
                    return (
                        <Menu.Item key={childItem.code}>
                            <NavLink to={{ pathname: childItem.path }}>
                                {childItem.icon}
                                <span>{t(childItem.title)}</span>
                            </NavLink>
                        </Menu.Item>
                    )
                });
                return (
                    <SubMenu key={item.code} icon={item.icon} title={t(item.title)} >
                        {childrenContent}
                    </SubMenu>
                );
            }
        });
    }

    const mergedMenuData = mergeMenu(fetchedMenuData, allMenuData);

    const findMenuItemByPath = (path) => {
        // DFS style
        for (let i = 0; i < mergedMenuData.length; i++) {
            const menuItemLevel1 = mergedMenuData[i];
            if (menuItemLevel1.path === path) {
                return menuItemLevel1;
            } else {
                if (menuItemLevel1.children && menuItemLevel1.children.length > 0) {
                    for (let j = 0; j < menuItemLevel1.children.length; j++) {
                        const menuItemLevel2 = menuItemLevel1.children[j];
                        if (menuItemLevel2.path === path) {
                            return menuItemLevel2;
                        }
                    }
                }
            }
        }

        return null;
    }


    const layoutContent = (
        <Switch>
            <Route path="/users" exact
                render={(props) => (
                    <UserList {...props} findMenuItemByPath={findMenuItemByPath} />)} />
            <Route path="/users/addNew"
                render={(props) => (<UserEditor {...props} findMenuItemByPath={findMenuItemByPath} />)} />
            <Route path="/users/:id/edit"
                render={(props) => (<UserEditor {...props} findMenuItemByPath={findMenuItemByPath} />)} />
            <Route path="/users/:id"
                render={(props) => (<UserDetails {...props} findMenuItemByPath={findMenuItemByPath} />)} />
            <Route path="/system/menu" component={MenuManagement} />
            <Route path="/system/usergroups/addNew" component={UserGroupEditor} />
            <Route path="/system/usergroups/:id/edit" component={UserGroupEditor} />
            <Route path="/system/usergroups/:id" component={UserGroupDetails} />
            <Route path="/system/usergroups" component={UserGroupList} />
            <Route path="/basetestlist" component={BaseTestList} />
        </Switch>
    );

    return (
        <Layout style={{ minHeight: '100vh' }}>
            <Sider style={{ height: 'unset' }}
                trigger={<p>Trigger</p>}
                collapsed={menuCollapsed}>
                <div className='logo'>
                    <NavLink to="/">
                        <img src={logo} alt="LOGO" style={{ width: '150px', margin: '20px auto', display: 'block' }} />
                    </NavLink>
                </div>
                <Menu theme='dark' mode='inline'>
                    {buildMenu(mergedMenuData)}
                </Menu>
            </Sider>
            <Layout>
                {/* , display: 'flex', alignItems: 'baseline', justifyContent: 'flex-end' */}
                <Header style={{ backgroundColor: '#fff', padding: '0 16px' }}>
                    <div className='header-container'>
                        <div className="header-item">
                            <span className="sider-toggle" onClick={() => toggle()}>
                                {React.createElement(menuCollapsed ? MenuUnfoldOutlined : MenuFoldOutlined)}
                            </span>
                        </div>
                        <div className="header-divider"></div>
                        <p className="header-item" style={{ padding: '0 10px' }}>
                            {currentUser.givenName + ' ' + currentUser.surname}
                        </p>
                        <Avatar className="" icon={<UserOutlined />} style={{ backgroundColor: '#87d068' }} />
                    </div>

                </Header>
                <Layout>
                    {layoutContent}

                    <Footer style={{ textAlign: 'center' }}>Bản quyền 2020 - Zamio Việt Nam</Footer>
                </Layout>

            </Layout>
        </Layout>
    );
}

export default LoggedInView;