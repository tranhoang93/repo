import {
    ASYNC_START, ASYNC_END, USER_GROUP_DETAILS_LOAD, 
    USER_GROUP_DETAILS_UNLOAD, ALL_MENUS, ALLOWED_MENUS,
    UPDATE_USER_GROUP
} from '../../store/actionConstants';

const initState = {
    fetching: false,
    submitting: false,
    isError: false,
    error: null,
    group: {},
    allMenus: [],
    groupUpdated: false
}

const userGroupDetailsReducer = (state = initState, action) => {
    switch (action.type) {
        case ASYNC_START:
            if (action.subtype === USER_GROUP_DETAILS_LOAD) {
                return { ...state, fetching: true }
            } else {
                return state;
            }
        case ASYNC_END:
            return { ...state, fetching: false, submitting: false }
        case USER_GROUP_DETAILS_LOAD:
            if (action.error) {
                return { ...state, isError: true, error: action.payload }
            } else {
                return { ...state, isError: false, group: action.payload.data }
            }
        case USER_GROUP_DETAILS_UNLOAD:
            return { ...initState }
        case ALL_MENUS:
            if (action.error) {
                return { ...state, isError: true, error: action.payload }
            } else {
                return { ...state, isError: false, allMenus: action.payload.data }
            }
        case ALLOWED_MENUS:
            // console.log("[ALLOWED_MENUS]: ", action);
            if (action.error) {
                return { ...state, isError: true, error: action.payload }
            } else {
                return { ...state, isError: false, allowedMenus: action.payload.data }
            }
        case UPDATE_USER_GROUP:
            if (action.error) {
                return { ...state, isError: true, error: action.payload }
            } else {
                return { ...state, isError: false, groupUpdated: true }
            }
        default:
            return state;
    }
}

export default userGroupDetailsReducer;