import React from 'react';
import { Spin } from 'antd';
import { LoadingOutlined } from '@ant-design/icons';
import { useTranslation } from 'react-i18next';
import './Loading.css';


function Loading(props){
    const {t } = useTranslation();
    const loadingText = t('Loading');
    const antIcon = <LoadingOutlined style={{ fontSize: 48 }} spin />;

    return (
        <div className="loading">
            <Spin indicator={antIcon} />
            <div className="loading-text">{loadingText}</div>
        </div>
    )
}

export default Loading;
