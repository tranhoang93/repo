import {
    ASYNC_START, ASYNC_END, USER_DETAILS, DELETE_USER,
    USER_DETAILS_CLEAR_ERROR
} from '../store/actionConstants';
const initState = {
    fetching: false,
    submitting: false,
    isError: false,
    error: null,
    userDetails: null,
    userDeleted: false
}

const userDetailsReducer = (state = initState, action) => {
    switch (action.type) {
        case ASYNC_START:
            if (action.subtype === USER_DETAILS) {
                return { ...state, fetching: true }
            } else if (action.subtype === DELETE_USER) {
                return { ...state, submitting: true }
            }
            else {
                return state;
            }
        case ASYNC_END:
            return { ...state, fetching: false, submitting: false }
        case USER_DETAILS:
            if (action.error) {
                return {
                    ...state,
                    isError: true,
                    error: action.payload
                }
            } else {
                return { ...state, userDetails: action.payload.data }
            }
        case DELETE_USER:
            if (action.error) {
                return {
                    ...state,
                    isError: true,
                    error: action.payload
                }
            } else {
                return { ...state, userDeleted: true }
            }
        case USER_DETAILS_CLEAR_ERROR:
            return { ...state, isError: false, error: null }
        default:
            return state;
    }
}

export default userDetailsReducer;