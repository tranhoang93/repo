import {
    PREPARE_USER, TIMEZONES, ASYNC_START, ASYNC_END,
    USER_EDITOR_UNLOADED, UPDATE_USER, USER_EDITOR_ADD_NEW
} from '../store/actionConstants';

const initState = {
    fetching: false,
    submitting: false,
    userEdit: null,
    isError: false,
    error: null,
    timezones: [],
    createdUser: null,
    userUpdated: false,
}

const userEditReducer = (state = initState, action) => {
    switch (action.type) {
        case ASYNC_START:
            if (action.subtype === PREPARE_USER || action.subtype === TIMEZONES) {
                return { ...state, fetching: true }
            } else if (action.subtype === UPDATE_USER || action.subtype === USER_EDITOR_ADD_NEW) {
                return { ...state, submitting: true }
            }
            else {
                return state;
            }
        case ASYNC_END:
            return { ...state, fetching: false, submitting: false }
        case PREPARE_USER:
            if (action.error) {
                return { ...state, isError: true, error: true }
            } else {
                if (action.payload) {
                    // Case edit
                    return { ...state, userEdit: action.payload.data }
                } else {
                    // Case add new
                    return { ...state, userEdit: {} }
                }
            }
        case TIMEZONES:
            if (action.error) {
                return { ...state, isError: true, error: true }
            } else {
                return { ...state, timezones: action.payload.data }
            }
        case USER_EDITOR_ADD_NEW:
            if (action.error) {
                return { ...state, isError: true, error: true }
            } else {
                return { ...state, createdUser: action.payload.data };
            }
        case USER_EDITOR_UNLOADED:
            return { ...initState }
        case UPDATE_USER:
            if (action.error) {
                return { ...state, isError: true, error: action.payload }
            } else {
                return { ...state, userUpdated: true }
            }
        default:
            return state;
    }
}

export default userEditReducer;