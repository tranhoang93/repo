import React from 'react';
import ListBase from '../base/ListBase';
import AddNewButton from '../base/AddNewButton';
import BaseBreadcrumb from '../base/BaseBreadcrumb';
import { useTranslation } from 'react-i18next';
import { push } from 'connected-react-router';
import { useDispatch } from 'react-redux'

function BaseTestList(props) {

    const { t } = useTranslation();
    const dispatch = useDispatch();

    function gotoAddNew() {
        dispatch(push('/'))
    }
    function renderTopbar() {
        return (
            <AddNewButton onClick={gotoAddNew} />
        )
    }

    function renderBreadcrumb() {
        const items = [
            { title: t('Base'), link: '/users' },
            { title: t('Test'), link: null },
        ]
        return (
            <BaseBreadcrumb items={items} />
        )
    }

    const columns = [
        {
            title: 'Lala',
            render: (text, record, index) => (index)
        },
    ]
    const data = [{ id: 1 }, { id: 2 }]
    const tableData = { columns: columns, data: null }

    return (
        <ListBase
            error={false}
            tableData={tableData}
            renderBreadcrumb={renderBreadcrumb}
            renderTopbarButtons={renderTopbar} />
    )
}

export default BaseTestList;