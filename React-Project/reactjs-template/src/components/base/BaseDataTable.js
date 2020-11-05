import React from 'react';
import { Table } from 'antd';

function BaseDataTable(props) {
    
    const { columns, data } = props;

    return (
        <Table
            columns={columns}
            dataSource={data}
            size='small'
            rowKey={(record) => record.id} />
    );
}

export default BaseDataTable;