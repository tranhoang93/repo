import React, { useEffect, Fragment, useCallback } from 'react';
import { useSelector, useDispatch } from 'react-redux';

// import { NavLink } from 'react-router-dom';
// import { HomeOutlined } from '@ant-design/icons';
import { Spin } from 'antd';
import HeaderButtons from '../ui/HeaderButtons';
import { USER_LIST_LOADED, USER_LIST_UNLOADED } from '../../store/actionConstants';
import { Users } from '../../agent/services';
import { getBreadcrumbDataFromMenuItem, buildBreadcrumb, getResultForError } from '../utils';
import { Button, Table, Dropdown, Menu } from 'antd';
import { PlusOutlined, DownOutlined } from '@ant-design/icons';
import { NavLink } from 'react-router-dom';
import { push } from 'connected-react-router';
import { useTranslation } from 'react-i18next';


function UserList(props) {

    const {t} = useTranslation();
    const dispatch = useDispatch()
    const { findMenuItemByPath } = props;

    const fetching = useSelector(state => state.userList.fetching);
    const selectedRowKeys = useSelector(state => state.userList.selectedRowKeys);
    const users = useSelector(state => state.userList.data);
    let v = null;

    const onLoad = useCallback(() => {
        dispatch({ type: USER_LIST_LOADED, payload: Users.get() });
    }, [dispatch]);

    useEffect(() => {
        onLoad();
        return function cleanup() {
            dispatch({ type: USER_LIST_UNLOADED });
        }
    }, [dispatch, onLoad]);

    const menuItem = findMenuItemByPath('/users');
    const breadcrumbData = getBreadcrumbDataFromMenuItem(menuItem);
    const breadcrumb = buildBreadcrumb(breadcrumbData);


    const columns = [
        {
            title: "Fullname",
            render: (text, record, index) => {
                return (
                    <NavLink to={{ pathname: '/users/' + record.id }}>
                        {record.givenName + ' ' + record.surname}
                    </NavLink>
                );
            }
        },
        {
            title: "Username",
            render: (text, record, index) => {
                return (
                    <NavLink to={{ pathname: '/users/' + record.id }}>
                        {record.username}
                    </NavLink>
                )
            }
        },
        {
            title: "Email",
            render: (text, record, index) => {
                return record.email
            }
        },
        {
            title: "Phone",
            render: (text, record, index) => {
                return record.phone
            }
        },
        {
            title: "Locale",
            render: (text, record, index) => {
                return record.locale
            }
        },
        {
            title: "Timezone",
            render: (text, record, index) => {
                return record.timezone
            }
        }
    ];

    const rowSelection = {
        selectedRowKeys: selectedRowKeys,
        onChange: (selection) => {
            dispatch({ type: 'USER_TABLE_SELECTION_CHANGED', selectedRowKeys: selection })
        }
    }

    const actionMenu = (
        <Menu>
            <Menu.Item key="1">Disable</Menu.Item>
        </Menu>
    );

    const headerButtons = (
        <HeaderButtons>
            <Button icon={<PlusOutlined />}
                onClick={() => { dispatch(push('/users/addNew')) }}
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
                    rowKey={(r) => r.id}
                    size='small'
                    rowSelection={rowSelection}
                    dataSource={users}
                    columns={columns} />
            </Spin>
        </Fragment>
    );


}

export default React.memo(UserList);


/**




    if (loading) {
        v = (
            <Fragment>
                {breadcrumb}
                <Loading />
            </Fragment>
        )
    } else {

        if (error) {
            if (method === 'GET') {
                v = (
                    <Fragment>
                        {breadcrumb}
                        {getResultForError(error)}
                    </Fragment>
                );
            } else {
                v = <p>Handle POST/PUT later</p>
            }
        } else {

            v = (
                <Fragment>
                    {breadcrumb}

                    <p>There are {users.length} users</p>

                </Fragment>
            );
        }
    }
    return v;
 */