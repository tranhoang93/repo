import React, { Fragment } from 'react';
import { Layout, Table } from 'antd';
import BaseDataTable from './BaseDataTable';

const { Content } = Layout;

function ListBase(props) {
    let topbarButtons = null;
    if (props.renderTopbarButtons && typeof (props.renderTopbarButtons) === 'function') {
        topbarButtons = props.renderTopbarButtons();
    }

    let breadcrumb = null;
    if (props.renderBreadcrumb && typeof (props.renderBreadcrumb) === 'function') {
        breadcrumb = props.renderBreadcrumb();
    }

    const fetchError = props.fetchError;
    let tableOrError = null;
    if (fetchError) {
        tableOrError = <p>Fetch Error</p>
    } else {
        const tableData = props.tableData;
        const { columns, data } = tableData;
        tableOrError = (
            <BaseDataTable columns={columns} data={data} />
        )
    }

    return (
        <Fragment>
            <div className="content-topbar">
                <div className="topbar-buttons-search">
                    {topbarButtons}
                </div>
            </div>
            <Content style={{ margin: '24px 16px 0', overflow: 'initial' }}>
                <div className="site-layout-background" style={{ minHeight: '100%', padding: 24, backgroundColor: '#fff' }}>
                    {breadcrumb}
                    {tableOrError}
                </div>
            </Content>
        </Fragment>
    )
}

export default ListBase;