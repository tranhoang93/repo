import React from 'react'
import { Breadcrumb, Tree } from 'antd';
import { HomeOutlined } from '@ant-design/icons';
import { NavLink } from 'react-router-dom';
import { useTranslation } from 'react-i18next';

function MenuManagement(props) {
    const { t } = useTranslation();
    const treeData = [
        {
            title: 'System settings',
            key: '1',
            icon: <HomeOutlined/>,
            children: [
                { title: 'Users', key: 2, icon: <HomeOutlined/> },
                { title: 'Groups', key: 3, icon: <HomeOutlined/> }
            ]
        }
    ];
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
                {t('Menu management')}
            </Breadcrumb.Item>

        </Breadcrumb>
    )
    return (
        <React.Fragment>
            {breadcrumb}
            <Tree 
                showLine
                defaultExpandAll
                treeData={treeData} />
        </React.Fragment>
    );
}

export default MenuManagement;