import { UPDATE_AUTH_VALUE, LOGIN, ACCESS_TOKEN, MY_PROFILE } from '../store/actionConstants';

const initState = {
    username: '',
    password: '',
    accessToken: null,
    currentUser: {}
};

const auth = (state = initState, action) => {
    switch (action.type) {
        case UPDATE_AUTH_VALUE:
            return { ...state, [action.key]: action.value }
        case LOGIN:
            // A redirect handled in commonReducer
            const accessToken = action.payload.error ? null : action.payload.data.accessToken;
            return { ...state, accessToken: accessToken }
        case ACCESS_TOKEN:
            return { ...state, accessToken: action.accessToken }
        case MY_PROFILE:
            // Also set appLoaded to true in commonReducer
            // Also handled in appReducer
            console.log("[MY_PROFILE] action.payload = ", action.payload);
            return { ...state, currentUser: action.payload.data }
        default:
            return state;
    }
}

export default auth;