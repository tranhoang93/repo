import React, { Fragment, useEffect } from 'react';
import { Form, Input, Row, Col, Button, Select, Switch } from 'antd';
import { SaveOutlined } from '@ant-design/icons';
import { useTranslation } from 'react-i18next';
import { useDispatch, useSelector } from 'react-redux';
import { 
    USER_DETAILS, TIMEZONES, UPDATE_USER, ERROR, METHOD, CLEAR_ERROR, CREAT_USER
 } from '../../store/actionConstants';
import { Users, System } from '../../agent/services';
import {
    buildBreadcrumb,
    getBreadcrumbDataFromMenuItem,
    clearErrorOnFormItem
} from '../utils';
import useBreakpoint from '../hooks/useBreakpoint';
import Loading from '../ui/Loading';
import { getResultForError } from '../utils';
import { push } from 'connected-react-router';

function UserEditor(props) {
    const userId = props.match.params.id;
    const updated = useSelector(state => state.users.updated);
    const dispatch = useDispatch();

    const onFormFinish = (values) => {
        console.log("Form values: ", values);
        if (mode === 'edit') {
            dispatch({ type: METHOD, method: 'PUT' })
            dispatch({ type: CLEAR_ERROR })
            dispatch({ type: UPDATE_USER, payload: Users.update(userId, values) });
        } else {
            console.log("Mode is 'create'");
            dispatch({ type: METHOD, method: 'POST' })
            dispatch({ type: CLEAR_ERROR })
            dispatch({ type: CREAT_USER, payload: Users.create(values) });
        }
    }

    if(updated){
        dispatch(push('/users/'+userId));
    } 
    const { t } = useTranslation();

    const point = useBreakpoint();

    const userDetails = useSelector(state => state.users.details);
    const loading = useSelector(state => state.common.loading);
    const error = useSelector(state => state.common.error);
    const method = useSelector(state => state.common.method);
    const availableTimezones = useSelector(state => state.common.availableTimezones);
    
    const { findMenuItemByPath } = props;
    

    useEffect(() => {
        if(userId){
            dispatch({ type: USER_DETAILS, payload: Users.details(userId) });
        }
        dispatch({ type: TIMEZONES, payload: System.availableTimezones() });

        return function cleanup() {
            dispatch({ type: USER_DETAILS, payload: { data: {} } });
            dispatch({ type: TIMEZONES, payload: { data: [] } });
            dispatch({ type: ERROR, error: null });
        }
    }, [dispatch, userId]);

    let breadcrumb = null;


    const layout = {
        labelCol: { span: 8 },
        wrapperCol: { span: 16 },
    };
    const tailLayout = {
        wrapperCol: { offset: 8, span: 16 },
    };
    let initValue = {};
    if (userDetails) {
        initValue = {
            username: userDetails.username || '',
            givenName: userDetails.givenName || '',
            surname: userDetails.surname || '',
            phone: userDetails.phone || '',
            email: userDetails.email || '',
            locale: userDetails.locale || '',
            timezone: userDetails.timezone || '',
            enabled: userDetails.enabled || true
        }
    }

    const timezoneOptions = availableTimezones.map(z => {
        return <Select.Option value={z} key={z}>{z}</Select.Option>
    })

    let mode;
    if (userDetails && userDetails.username && userDetails.username.length > 0) {
        mode = 'edit';
    } else {
        mode = 'create';
    }

    
    // rules={[{ required: true, message: 'Please input your username!' }]}
    const usernameRules = [{ required: true, message: t('Please input your username!') }];
    const givenNameRules = [{ required: true, message: t('Please input given name') }];
    const SurnameRules = [{ required: true, message: t('Please input surname') }];
    const usernameDisabled = userDetails ? !!userDetails.username : false;
    const submitBtnLbl = usernameDisabled ? t('Save changes') : t('Save');

    const [form] = Form.useForm();

    const onFormValuesChange = (values) => {
        clearErrorOnFormItem(form, values);
    }

    if (error) {
        if (method === 'GET') {
            const result = getResultForError(error);
            return result;
        } else if (method === 'POST' || method === 'PUT') {
            if (error.response) {
                const status = error.response.status;
                if (status === 400) {
                    const data = error.response.data;
                    if (data.code === 'VALIDATION_ERROR') {
                        const violations = data.violations;
                        console.log("Violaitons: ", violations);
                        const formValues = form.getFieldsValue();

                        form.setFields([{
                            name: 'email',
                            value: formValues['email'],
                            errors: ['This email is not available']
                        }]);
                        dispatch({ type: CLEAR_ERROR });
                    } else {
                        console.log("Not a VALIDATION_ERROR");
                    }
                } else {
                    console.log("Status is not 400, it is: ", status);
                }
            } else {
                return <p>There is something really bad</p>
            }
        }
    }

    if (!userId || (userDetails && userDetails.username)) {
        const menuItem = findMenuItemByPath('/users');
        const breadcrumbData = getBreadcrumbDataFromMenuItem(menuItem);

        if(userId){
            breadcrumbData.push({ title: userDetails.givenName + ' ' + userDetails.surname });
        } else {
            breadcrumbData.push({ title: t('Add new') });
        }
        breadcrumb = buildBreadcrumb(breadcrumbData);
        const gutter = [8, 8];
        let span = 8;
        if (point === 'md') {
            span = 16;
        } else if (point === 'sm') {
            span = 24;
        }
        return (
            <Fragment>
                {breadcrumb}
                <Row gutter={gutter}>
                    <Col span={span}>
                        <Form form={form} {...layout}
                            initialValues={initValue}
                            onValuesChange={onFormValuesChange}
                            onFinish={onFormFinish}>
                            <Form.Item
                                label={t('username')}
                                name="username" rules={usernameRules} >
                                <Input disabled={usernameDisabled} />
                            </Form.Item>
                            <Form.Item
                                label={t('Given name')}
                                name="givenName" rules={givenNameRules} >
                                <Input />
                            </Form.Item>
                            <Form.Item
                                label={t('Surname')}
                                name="surname" rules={SurnameRules} >
                                <Input />
                            </Form.Item>
                            <Form.Item
                                label={t('email')}
                                name="email" >
                                <Input />
                            </Form.Item>
                            <Form.Item
                                label={t('phoneNumber')}
                                name="phone" >
                                <Input />
                            </Form.Item>
                            <Form.Item
                                label={t('locale')}
                                name="locale" >
                                <Select>
                                    <Select.Option value='vi'>{t('Vietnamese')}</Select.Option>
                                    <Select.Option value='en_US'>{t('English (US)')}</Select.Option>
                                </Select>
                            </Form.Item>
                            <Form.Item
                                label={t('timezone')}
                                name="timezone" >
                                <Select
                                    showSearch
                                    optionFilterProp="children"
                                    filterOption={(input, option) => {
                                        return option.children.toLowerCase().indexOf(input.toLowerCase()) >= 0;
                                    }}>
                                    {timezoneOptions}
                                </Select>
                            </Form.Item>
                            <Form.Item
                                label={t('enabled')}
                                valuePropName='checked'
                                name="enabled" >
                                <Switch />
                            </Form.Item>
                            <Form.Item {...tailLayout}>
                                <Button
                                    loading={loading} icon={<SaveOutlined />}
                                    type='primary' htmlType='submit'>
                                    {submitBtnLbl}
                                </Button>
                            </Form.Item>
                        </Form>
                    </Col>
                </Row>
            </Fragment>
        );
    } else {
        return <Loading />
    }




}

export default UserEditor;