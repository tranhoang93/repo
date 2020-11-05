import {
    ASYNC_START, ASYNC_END, REDIRECT,
    LOGIN, MY_PROFILE, SIDER_COLLAPSE,
    SUBMIT_FORM, SUBMIT_FORM_END,
    METHOD, ERROR, REFRESH_TOKEN_SUCCESS, CLEAR_ERROR
} from '../store/actionConstants';
import allMenuData from './allMenuData';

const menu = [
    {
        code: 'DashBoard',
    },
    {
        code: 'System',
        children: [
            {
                code: 'users'
            },
            {
                code: 'userGroups'
            },
            {
                code: 'basetestlist'
            },
        ]
    }
]

const initialState = {
    appLoaded: false,
    loading: false,
    formSubmitting: false,
    redirectTo: null,
    menuCollapsed: false,
    availableTimezones: [],
    method: null,
    error: null,
    fetchedMenu: menu,
    allMenuData: allMenuData
};
const reducer = (state = initialState, action) => {
    switch (action.type) {
        case ASYNC_START:
            if(action.subtype === LOGIN){
                return { ...state, loading: true }
            } else {
                return state;
            }
        case ASYNC_END:
            return { ...state, loading: false }
        case SUBMIT_FORM:
            return { ...state, formSubmitting: true }
        case SUBMIT_FORM_END:
            return { ...state, formSubmitting: false }
        case REDIRECT:
            return { ...state, redirectTo: null }
        case LOGIN:
            return { ...state, redirectTo: action.error ? null : '/' }
        case MY_PROFILE:
            return { ...state, appLoaded: true }
        case SIDER_COLLAPSE:
            //console.log("[SIDER_COLLAPSE] Now change menuCollapsed to: ", action.collapsed)
            return { ...state, menuCollapsed: action.collapsed }
        case METHOD:
            return { ...state, method: action.method }
        case ERROR:
            return { ...state, error: action.error }
        case CLEAR_ERROR:
            return { ...state, error: null }
        case REFRESH_TOKEN_SUCCESS:
            return { ...state, error: null }
        default:
            return state;
    }

}

export default reducer;