import React from 'react';
import {
    DashboardOutlined, UserOutlined
} from '@ant-design/icons';

const allMenuData = [
    {
        code: 'DashBoard',
        path: '/dashboard',
        icon: <DashboardOutlined />,
        title: 'Bảng điều khiển'
    },
    {
        code: 'System',
        path: null,
        icon: <DashboardOutlined />,
        title: 'Hệ thống'
    },
    {
        code: 'users',
        path: '/users',
        icon: <UserOutlined />,
        title: 'Users'
    },
    {
        code: 'userGroups',
        path: '/system/usergroups',
        icon: <UserOutlined />,
        title: 'User groups'
    },
    {
        code: 'Child2',
        path: '/child2',
        icon: <UserOutlined />,
        title: 'Menu con số 2'
    },
    {
        code: 'basetestlist',
        path: '/basetestlist',
        icon: <UserOutlined />,
        title: 'Base test list'
    },
]

export default allMenuData;