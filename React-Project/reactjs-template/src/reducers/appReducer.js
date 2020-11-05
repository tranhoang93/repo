import { ASYNC_START, ASYNC_END, APP_LOAD, MY_PROFILE } from '../store/actionConstants';
const initState = {
    loading: false,
    isError: false,
    error: null
}

const appReducer = (state = initState, action) => {
    switch (action.type) {
        case ASYNC_START:
            if (action.subtype === APP_LOAD || action.subtype === MY_PROFILE) {
                return { ...state, loading: true }
            } else {
                return state;
            }
        case ASYNC_END:
            return { ...state, loading: false }
        case MY_PROFILE:
            if (action.error) {
                return { ...state, isError: true, error: action.payload }
            } else {
                return { ...state, isError: false, error: null }
            }
        default:
            return state;
    }
}

export default appReducer;