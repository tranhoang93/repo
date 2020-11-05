import {
    USER_LIST_LOADED,
    USER_LIST_UNLOADED,
    ASYNC_START, ASYNC_END
} from '../store/actionConstants';

const initState = {
    data: [],
    pagination: {},
    selectedRowKeys: [],
    fetching: false,
    isError: false,
    error: null
}

const userReducer = (state = initState, action) => {
    switch (action.type) {
        case ASYNC_START:
            if (action.subtype === USER_LIST_LOADED) {
                return { ...state, fetching: true }
            } else {
                return state;
            }
        case ASYNC_END:
            return { ...state, fetching: false }
        
        case 'USER_TABLE_SELECTION_CHANGED':
            return { ...state, selectedRowKeys: action.selectedRowKeys }
        case USER_LIST_LOADED:
            if (action.error) {
                return { ...state, isError: true, error: action.payload }
            } else {
                return {
                    ...state,
                    isError: false,
                    data: action.payload.data.data,
                    pagination: action.payload.data.pagination
                }
            }
        case USER_LIST_UNLOADED:
            return {}
        default:
            return state;
    }
}

export default userReducer;