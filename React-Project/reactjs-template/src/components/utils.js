import React from 'react';
import { Breadcrumb, Result } from 'antd';
import { NavLink } from 'react-router-dom';
import { HomeOutlined } from '@ant-design/icons';
import moment from 'moment';

export const getBreadcrumbDataFromMenuItem = (menuItem) => {
    const breadcrumbData = [];
    if (menuItem.parent) {
        breadcrumbData.push({
            link: menuItem.parent.path,
            title: menuItem.parent.title
        })
    }
    breadcrumbData.push({
        link: menuItem.path,
        title: menuItem.title
    });
    return breadcrumbData;
}


export const buildBreadcrumb = (items) => {
    if (items && items.length > 0) {
        const breadcrumnItems = items.map((itemData, index) => {
            if (index < items.length - 1) {
                return (
                    <Breadcrumb.Item key={itemData.link + itemData.title}>
                        {itemData.link ?
                            <NavLink to={itemData.link}>{itemData.title}</NavLink>
                            : itemData.title
                        }
                    </Breadcrumb.Item>
                );
            } else {
                return (
                    <Breadcrumb.Item key={itemData.link + itemData.title}>
                        {itemData.title}
                    </Breadcrumb.Item>
                );
            }

        });
        return (
            <div style={{ marginBottom: '24px' }}>
                <Breadcrumb>
                    <Breadcrumb.Item key="home">
                        <NavLink to="/">
                            <HomeOutlined />
                        </NavLink>
                    </Breadcrumb.Item>
                    {breadcrumnItems}
                </Breadcrumb>
            </div>
        );
    } else {
        return null;
    }
}

export const getDisplayDatetime = (apiDatetime, t) => {
    const datetimeFormat = t('datetimeFormat');
    return moment(apiDatetime, t('apiDatetimeFormat')).format(datetimeFormat);
}

const getError500 = () => (
    <Result status="500"
        subTitle="Sorry, something went wrong!"
        title="Error" />
)

export const getResultForError = (error) => {
    if (error.response) {
        const status = error.response.status;
        if (status === 500) {
            return getError500();
        } else if (status === 404) {
            return (
                <Result status="404"
                    subTitle="Sorry, we could not find what you were looking for!"
                    title="Not found" />
            );
        } else if (status === 403) {
            return (
                <Result status="403"
                    subTitle="Sorry, you are not authorized to access this resource!"
                    title="Access denied" />
            );
        }
    } else {
        return getError500();
    }
}

export const clearErrorOnFormItem = (form, values) => {
    Object.keys(values).forEach(k => {
        form.setFields([{
            name: k,
            value: form.getFieldsValue()[k],
            errors: []
        }])
    })
}

