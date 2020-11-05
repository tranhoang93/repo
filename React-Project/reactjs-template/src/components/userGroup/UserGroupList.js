import React, { Fragment, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Breadcrumb, Button, Dropdown, Menu, Spin, Table } from 'antd';
import { HomeOutlined, PlusOutlined, DownOutlined } from '@ant-design/icons';
import { useTranslation } from 'react-i18next';
import { NavLink } from 'react-router-dom';
import HeaderButtons from '../ui/HeaderButtons';
import { push } from 'connected-react-router';
import { UserGroups } from '../../agent/services';
import { USER_GROUP_LIST_LOAD } from '../../store/actionConstants';

function UserGroupList(props) {
    const { t } = useTranslation();
    const dispatch = useDispatch();
    const selectedRowKeys = useSelector(state => state.userGroupList.selectedRowKeys);
    const fetching = useSelector(state => state.userGroupList.fetching);
    const groups = useSelector(state => state.userGroupList.groups);
    const searchKeyWord = useSelector(state => state.userGroupList.searchKeyWord);


    useEffect(() => {
        dispatch({ type: USER_GROUP_LIST_LOAD, payload: UserGroups.get(searchKeyWord, 0, 20)})
    }, [dispatch, searchKeyWord]);

    const columns = [
        {
            title: t('No'),
            width: 20,
            render: (text, record, index) => (index + 1)
        },
        {
            title: t('Name'),
            render: (text, record, index) => (
                <NavLink to={'/system/userGroups/' + record.id}>
                    {record.name}
                </NavLink>
            )
        },
        {
            title: t('Description'),
            render: (text, record, index) => (record.description)
        }
    ]

    const rowSelection = {
        selectedRowKeys: selectedRowKeys,
        onChange: (values) => {

        }
    }

    const breadcrumb = (
        <Breadcrumb className="mb-8">
            <Breadcrumb.Item>
                <NavLink to="/">
                    <HomeOutlined />
                </NavLink>
            </Breadcrumb.Item>
            <Breadcrumb.Item>
                {t('System settings')}
            </Breadcrumb.Item>
            <Breadcrumb.Item>
                {t('User groups')}
            </Breadcrumb.Item>
        </Breadcrumb>
    )
    const actionMenu = (
        <Menu>
            <Menu.Item key="1">Disable</Menu.Item>
        </Menu>
    );
    const headerButtons = (
        <HeaderButtons>
            <Button icon={<PlusOutlined />}
                onClick={() => { dispatch(push('/system/userGroups/addNew')) }}
                type='primary'>{t('Add new')}</Button>
            {
                selectedRowKeys && selectedRowKeys.length > 0 &&
                <Dropdown overlay={actionMenu}>
                    <Button style={{ marginLeft: '10px' }}>
                        Button <DownOutlined />
                    </Button>
                </Dropdown>
            }
        </HeaderButtons>
    );
    return (
        <Fragment>
            {breadcrumb}
            {headerButtons}
            <Spin spinning={fetching}>
                <Table
                    rowKey={record => record.id}
                    size='small'
                    rowSelection={rowSelection}
                    columns={columns}
                    dataSource={groups} />
            </Spin>
        </Fragment>
    );
}

export default UserGroupList;