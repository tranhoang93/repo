import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useTranslation } from 'react-i18next';

import { USER_DETAILS, DELETE_USER, USER_DETAILS_CLEAR_ERROR } from '../../store/actionConstants';
import { Users } from '../../agent/services';

import {
    getBreadcrumbDataFromMenuItem, buildBreadcrumb,
    getDisplayDatetime
} from '../utils';

import { Row, Col, Button, Modal, Spin } from 'antd';
import {
    EditOutlined, StopOutlined, DeleteOutlined,
    ExclamationCircleOutlined
} from '@ant-design/icons';
import Text from 'antd/lib/typography/Text';
import BadgeTag from '../ui/BadgeTag';
import { NavLink } from 'react-router-dom';
import HeaderButtons from '../ui/HeaderButtons';
import useBreakpoint from '../hooks/useBreakpoint';
import { push } from 'react-router-redux';

const { confirm } = Modal;

function UserDetails(props) {
    const { t } = useTranslation();
    const brkPoint = useBreakpoint();
    const userDetails = useSelector(state => state.userDetails.userDetails);
    const isError = useSelector(state => state.userDetails.isError);
    const error = useSelector(state => state.userDetails.error);
    const submitting = useSelector(state => state.userDetails.submitting);
    const dispatch = useDispatch();
    const { findMenuItemByPath } = props;
    const userId = props.match.params.id;

    useEffect(() => {
        dispatch({ type: USER_DETAILS, payload: Users.details(userId) });
        return function cleanup() {
            dispatch({ type: USER_DETAILS, payload: {} });
        }
    }, [dispatch, userId]);

    const gotoEdit = () => {
        dispatch(push('/users/' + userId + "/edit"));
    }

    const deleteUser = () => {
        dispatch({ type: DELETE_USER, payload: Users.delete(userId) });
    }

    const showConfirmDelete = () => {
        confirm({
            title: t('Confirm'),
            content: t('Are you sure want to delete this user?'),
            icon: <ExclamationCircleOutlined />,
            onOk() {
                deleteUser();
            },
            onCancel() {

            }
        });
    }

    if (isError) {
        if (error.response) {
            const errorStatus = error.response.status;
            if (errorStatus === 400) {
                const errorData = error.response.data;
                const errorCode = errorData.code;
                if (errorCode && errorCode === 'BAD_REQUEST') {
                    Modal.error({ title: t('Error'), content: errorData.description });
                    dispatch({ type: USER_DETAILS_CLEAR_ERROR });
                }
            }
        }
    }


    let breadcrumb = null;
    if (userDetails) {
        const menuItem = findMenuItemByPath('/users');
        const breadcrumbData = getBreadcrumbDataFromMenuItem(menuItem);
        breadcrumbData.push({ title: userDetails.givenName + ' ' + userDetails.surname });
        breadcrumb = buildBreadcrumb(breadcrumbData);

        let span = 4;
        switch (brkPoint) {
            case 'lg':
                span = 4;
                break;
            case 'md':
                span = 12;
                break;
            default:
                span = 12;
        }
        const gutter = [8, 8];

        // console.log('User details created date: ', userDetails.createdDate);
        // console.log('API format: ', t('apiDatetimeFormat'));
        const createdDate = getDisplayDatetime(userDetails.createdDate, t);
        const lastModifiedDate = getDisplayDatetime(userDetails.lastModifiedDate, t);
        return (
            <Spin spinning={submitting}>
                <React.Fragment>
                    {breadcrumb}

                    <HeaderButtons>
                        <Button icon={<EditOutlined />} type='primary' onClick={gotoEdit}>{t('edit')}</Button>
                        <Button className="ml-8" icon={<StopOutlined />} danger>{t('toLock')}</Button>
                        <Button className="ml-8"
                            icon={<DeleteOutlined />}
                            type='primary' danger
                            onClick={showConfirmDelete}>
                            {t('delete')}
                        </Button>
                    </HeaderButtons>

                    <h2>{userDetails.givenName + ' ' + userDetails.surname}</h2>
                    <h3>{t('generalInfo')}</h3>
                    <Row gutter={gutter}>
                        <Col span={span}>
                            <Text strong>{t('username')}:</Text>
                        </Col>
                        <Col span={span}>
                            {userDetails.username}
                        </Col>
                        <Col span={span}>
                            <Text strong>{t('email')}:</Text>
                        </Col>
                        <Col span={span}>
                            {userDetails.email}
                        </Col>
                    </Row>

                    <Row gutter={gutter}>
                        <Col span={span}>
                            <Text strong>{t('phoneNumber')}:</Text>
                        </Col>
                        <Col span={span}>
                            {userDetails.phone}
                        </Col>
                        <Col span={span}>
                            <Text strong>{t('locale')}:</Text>
                        </Col>
                        <Col span={span}>
                            {userDetails.locale}
                        </Col>
                    </Row>

                    <Row gutter={gutter}>
                        <Col span={span}>
                            <Text strong>{t('status')}:</Text>
                        </Col>
                        <Col span={span}>
                            {userDetails.enabled ?
                                <BadgeTag backgroundColor='#52c41a'>{t('enabled')}</BadgeTag>
                                : <BadgeTag>{t('disabled')}</BadgeTag>
                            }
                        </Col>
                        <Col span={span}>
                            <Text strong>{t('timezone')}:</Text>
                        </Col>
                        <Col span={span}>
                            {userDetails.timezone}
                        </Col>
                    </Row>

                    <h3>{t('otherInfo')}</h3>
                    <Row gutter={gutter}>
                        <Col span={span}>
                            <Text strong>{t('createdBy')}:</Text>
                        </Col>
                        <Col span={span}>
                            <NavLink to={'/users/' + userDetails.createdById}>
                                {userDetails.createdByGivenName + ' ' + userDetails.createdBySurname}
                            </NavLink>
                        </Col>
                        <Col span={span}>
                            <Text strong>{t('createdDate')}:</Text>
                        </Col>
                        <Col span={span}>
                            {createdDate}
                        </Col>
                    </Row>
                    <Row gutter={gutter}>
                        <Col span={span}>
                            <Text strong>{t('lastModifiedBy')}:</Text>
                        </Col>
                        <Col span={span}>
                            <NavLink to={'/users/' + userDetails.createdById}>
                                {userDetails.lastModifiedByGivenName + ' ' + userDetails.lastModifiedBySurname}
                            </NavLink>
                        </Col>
                        <Col span={span}>
                            <Text strong>{t('lastModifiedDate')}:</Text>
                        </Col>
                        <Col span={span}>
                            {lastModifiedDate}
                        </Col>
                    </Row>
                </React.Fragment>
            </Spin>
        );
    } else {
        return (<p>Loading...</p>);
    }
}

export default UserDetails;