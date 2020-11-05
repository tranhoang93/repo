import React from 'react';
import { Breadcrumb } from 'antd';
import { HomeOutlined } from '@ant-design/icons';
import { NavLink } from 'react-router-dom';
import { useTranslation } from 'react-i18next';

function BaseBreadcrumb(props) {
    const { t } = useTranslation();
    let otherItems = null;
    if (props.items && props.items.length > 0) {
        otherItems = props.items.map(item => {
            if (item.link) {
                return (
                    <Breadcrumb.Item>
                        <NavLink to='/'>
                            {item.title}
                        </NavLink>
                    </Breadcrumb.Item>
                )
            } else {
                return (
                    <Breadcrumb.Item>
                        {item.title}
                    </Breadcrumb.Item>
                )
            }
        })
    }
    return (
        <Breadcrumb className="mb-16 mt-8">
            <Breadcrumb.Item>
                <NavLink to='/' title={t('Home page')}>
                    <HomeOutlined />
                </NavLink>
            </Breadcrumb.Item>
            {otherItems}
        </Breadcrumb>
    );
}

export default BaseBreadcrumb;