import {
    ASYNC_START, ASYNC_END,
    USER_GROUP_EDITOR_LOAD,
    USER_GROUP_EDITOR_UNLOAD,
    ALL_MENUS,
    ALLOWED_MENUS,
    UPDATE_USER_GROUP,
    CREATE_USER_GROUP
} from '../../store/actionConstants';

const initState = {
    fetching: false,
    submitting: false,
    fetchError: null,
    submitError: null,
    group: null,
    allMenus: null,
    allowedMenus: null,
    groupUpdated: false
}

const userGroupEditorReducer = (state = initState, action) => {
    switch (action.type) {
        case ASYNC_START:
            const fetchSubtypes = [USER_GROUP_EDITOR_LOAD, ALL_MENUS, ALLOWED_MENUS];
            const submitSubtypes = [UPDATE_USER_GROUP, CREATE_USER_GROUP];
            if (fetchSubtypes.includes(action.subtype)) {
                return { ...state, fetching: true }
            } else if(submitSubtypes.includes(action.subtype)){
                return { ...state, submitting: true, submitError: null }
            } 
            else {
                return state;
            }
        case ASYNC_END:
            return { ...state, fetching: false, submitting: false }
        case USER_GROUP_EDITOR_LOAD:
            if (action.error) {
                return { ...state, fetchError: action.payload }
            } else {
                if (action.payload && action.payload.data) {
                    return { ...state, fetchError: null, group: action.payload.data }
                } else {
                    return { ...state, fetchError: null, group: {} }
                }
            }
        case ALL_MENUS:
            if (action.error) {
                return { ...state, fetchError: action.payload }
            } else {
                return { ...state, fetchError: null, allMenus: action.payload.data }
            }
        case ALLOWED_MENUS:
            if (action.error) {
                return { ...state, fetchError: action.payload }
            } else {
                return { ...state, fetchError: null, allowedMenus: action.payload.data }
            }
        case UPDATE_USER_GROUP:
            if (action.error) {
                return { ...state, submitError: action.payload }
            } else {
                return { ...state, submitError: null, groupUpdated: true }
            }
        case CREATE_USER_GROUP:
            if (action.error) {
                return { ...state, submitError: action.payload }
            } else {
                return { ...state, submitError: null, groupCreated: true }
            }
        case USER_GROUP_EDITOR_UNLOAD:
            return { ...initState }

        default:
            return state;
    }
}

export default userGroupEditorReducer;