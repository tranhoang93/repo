import React from 'react';
import { Button } from 'antd';
import { PlusOutlined } from '@ant-design/icons';
import { useTranslation } from 'react-i18next';


function AddNewButton(props) {
    const { t } = useTranslation();
    let onClick = props.onClick;
    if (!onClick) {
        onClick = () => { }
    }

    return (
        <Button
            onClick={onClick}
            icon={<PlusOutlined />}
            type='primary'>
            {t('Add new')}
        </Button>
    );
}

export default AddNewButton;