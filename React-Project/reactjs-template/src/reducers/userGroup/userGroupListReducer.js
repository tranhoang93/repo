import {
    ASYNC_START, ASYNC_END,
    USER_GROUP_LIST_LOAD
} from '../../store/actionConstants';

const initState = {
    fetching: false,
    isError: false,
    error: false,
    groups: [],
    pagination: {}
}

const userGroupListReducer = (state = initState, action) => {
    switch (action.type) {
        case ASYNC_START:
            if (action.subtype === USER_GROUP_LIST_LOAD) {
                return { ...state, fetching: true }
            } else {
                return state;
            }
        case ASYNC_END:
            return { ...state, fetching: false }
        case USER_GROUP_LIST_LOAD:
            if(action.error){
                return {
                    ...state,
                    isError: true,
                    error: action.payload
                }
            } else {
                return {
                    ...state,
                    isError: false, error: null,
                    groups: action.payload.data.data,
                    pagination: action.payload.data.pagination
                }
            }
        default:
            return state;
    }
}

export default userGroupListReducer;