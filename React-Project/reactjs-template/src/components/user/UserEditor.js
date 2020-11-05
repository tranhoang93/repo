import React, { Fragment, useEffect, useCallback } from 'react';
import {
  Form,
  Input,
  Row,
  Col,
  Button,
  Select,
  Switch,
  Spin,
  message,
} from 'antd';
import { SaveOutlined } from '@ant-design/icons';
import { useTranslation } from 'react-i18next';
import { useDispatch, useSelector } from 'react-redux';
import {
  PREPARE_USER,
  TIMEZONES,
  USER_EDITOR_UNLOADED,
  UPDATE_USER,
  USER_EDITOR_ADD_NEW,
} from '../../store/actionConstants';
import { Users, System } from '../../agent/services';
import {
  buildBreadcrumb,
  getBreadcrumbDataFromMenuItem,
  clearErrorOnFormItem,
} from '../utils';
import useBreakpoint from '../hooks/useBreakpoint';
// import { getResultForError } from '../utils';
import { push } from 'connected-react-router';
import Modal from 'antd/lib/modal/Modal';

function UserEditor(props) {
  const { t } = useTranslation();
  const userId = props.match.params.id;
  const dispatch = useDispatch();

  // const fetching = useSelector(state => state.userEditor.fetching);
  // const isError = useSelector(state => state.userEditor.isError);
  // const error = useSelector(state => state.userEditor.error);
  // const submitting = useSelector(state => state.userEditor.submitting);
  // const timezones = useSelector(state => state.userEditor.timezones);
  // const userEdit = useSelector(state => state.userEditor.userEdit);
  // const createdUser = useSelector(state => state.userEditor.createdUser);
  // const userUpdated = useSelector(state => state.userEditor.userUpdated);

  const {
    fetching,
    isError,
    error,
    submitting,
    timezones,
    userEdit,
    createdUser,
    userUpdated,
  } = useSelector((state) => state.userEditor);

  const point = useBreakpoint();
  const [form] = Form.useForm();

  const onLoad = useCallback(
    (userPayload) => {
      dispatch({ type: PREPARE_USER, payload: userPayload });
      dispatch({ type: TIMEZONES, payload: System.availableTimezones() });
    },
    [dispatch]
  );

  const getFormInitialValues = (userEdit) => {
    console.log('getFormInitialValues: ', userEdit);
    return {
      username: userEdit ? userEdit.username : '',
      givenName: userEdit ? userEdit.givenName : '',
      surname: userEdit ? userEdit.surname : '',
      phone: userEdit ? userEdit.phone : '',
      email: userEdit ? userEdit.email : '',
      locale: userEdit ? userEdit.locale : '',
      timezone: userEdit ? userEdit.timezone : '',
      enabled: userEdit ? (userEdit.id ? userEdit.enabled : true) : true,
    };
  };

  useEffect(() => {
    if (userId) {
      onLoad(Users.details(userId));
    } else {
      onLoad(null);
    }

    return function cleanup() {
      dispatch({ type: USER_EDITOR_UNLOADED });
    };
  }, [userId, onLoad, dispatch]);

  const onFormValuesChange = (values) => {
    clearErrorOnFormItem(form, values);
  };

  const onFormFinish = (values) => {
    if (userId) {
      dispatch({ type: UPDATE_USER, payload: Users.update(userId, values) });
    } else {
      dispatch({ type: USER_EDITOR_ADD_NEW, payload: Users.create(values) });
    }
  };

  if (createdUser) {
    message.success(t('User created successfully'));
    dispatch(push('/users'));
  }

  if (userUpdated) {
    message.success(t('User updated successfully'));
    dispatch(push('/users/' + userId));
  }

  if (fetching) {
    return <Spin spinning />;
  } else {
    if (userEdit) {
      const timezoneOptions = timezones.map((z) => {
        return (
          <Select.Option value={z} key={z}>
            {z}
          </Select.Option>
        );
      });

      const { findMenuItemByPath } = props;
      const menuItem = findMenuItemByPath('/users');
      const breadcrumbData = getBreadcrumbDataFromMenuItem(menuItem);

      if (userEdit && userEdit.id) {
        breadcrumbData.push({
          title: userEdit.givenName + ' ' + userEdit.surname,
        });
      } else {
        breadcrumbData.push({ title: t('Add new') });
      }
      const breadcrumb = buildBreadcrumb(breadcrumbData);
      const gutter = [8, 8];
      let span = 8;
      if (point === 'md') {
        span = 16;
      } else if (point === 'sm') {
        span = 24;
      }

      const usernameRules = [
        { required: true, message: t('Please input your username!') },
      ];
      const givenNameRules = [
        { required: true, message: t('Please input given name') },
      ];
      const SurnameRules = [
        { required: true, message: t('Please input surname') },
      ];
      const usernameDisabled = userEdit ? !!userEdit.username : false;
      const submitBtnLbl = usernameDisabled ? t('Save changes') : t('Save');

      const layout = {
        labelCol: { span: 8 },
        wrapperCol: { span: 16 },
      };
      const tailLayout = {
        wrapperCol: { offset: 8, span: 16 },
      };

      if (isError) {
        if (error.response) {
          console.log('Error status: ', error.response.status);
          if (error.response.status === 400) {
            if (error.response.data.code === 'VALIDATION_ERROR') {
              console.log('VALIDATION_ERROR ====== ');
              const violations = error.response.data.violations;
              const fields = [];
              const formValues = form.getFieldsValue();
              violations.forEach((v) => {
                const errors = [];
                errors.push(v.errorMessage);
                fields.push({
                  name: v.fieldName,
                  value: formValues[v.fieldName],
                  errors: errors,
                });
              });
              console.log('Fields: ', fields);
              form.setFields(fields);
            }
          } else if (error.response.status === 403) {
            if (error.response.description) {
              Modal.error({
                title: t('Error'),
                content: error.response.description,
              });
            } else {
              Modal.error({
                title: t('Error'),
                content: t('Access is denied!'),
              });
            }
          }
        } else {
          return <p>Bad thing</p>;
        }
      }

      return (
        <Fragment>
          {breadcrumb}
          <Row gutter={gutter}>
            <Col span={span}>
              <Form
                form={form}
                {...layout}
                initialValues={getFormInitialValues(userEdit)}
                onValuesChange={onFormValuesChange}
                onFinish={onFormFinish}
              >
                <Form.Item
                  label={t('username')}
                  name='username'
                  rules={usernameRules}
                >
                  <Input disabled={usernameDisabled} />
                </Form.Item>
                <Form.Item
                  label={t('Given name')}
                  name='givenName'
                  rules={givenNameRules}
                >
                  <Input />
                </Form.Item>
                <Form.Item
                  label={t('Surname')}
                  name='surname'
                  rules={SurnameRules}
                >
                  <Input />
                </Form.Item>
                <Form.Item label={t('email')} name='email'>
                  <Input />
                </Form.Item>
                <Form.Item label={t('phoneNumber')} name='phone'>
                  <Input />
                </Form.Item>
                <Form.Item label={t('locale')} name='locale'>
                  <Select>
                    <Select.Option value='vi'>{t('Vietnamese')}</Select.Option>
                    <Select.Option value='en_US'>
                      {t('English (US)')}
                    </Select.Option>
                  </Select>
                </Form.Item>
                <Form.Item label={t('timezone')} name='timezone'>
                  <Select
                    showSearch
                    optionFilterProp='children'
                    filterOption={(input, option) => {
                      return (
                        option.children
                          .toLowerCase()
                          .indexOf(input.toLowerCase()) >= 0
                      );
                    }}
                  >
                    {timezoneOptions}
                  </Select>
                </Form.Item>
                <Form.Item
                  label={t('enabled')}
                  valuePropName='checked'
                  name='enabled'
                >
                  <Switch />
                </Form.Item>
                <Form.Item {...tailLayout}>
                  <Button
                    loading={submitting}
                    icon={<SaveOutlined />}
                    type='primary'
                    htmlType='submit'
                  >
                    {submitBtnLbl}
                  </Button>
                </Form.Item>
              </Form>
            </Col>
          </Row>
        </Fragment>
      );
    } // end there is userEdit
    else {
      return <Spin spinning />;
    }
  }
}

export default UserEditor;
