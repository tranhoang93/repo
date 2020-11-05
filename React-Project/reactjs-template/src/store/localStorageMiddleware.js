import { LOGIN, ACCESS_TOKEN, REFRESH_TOKEN_SUCCESS } from './actionConstants';
import { setAuthorizationToken } from '../agent/defaultAxios';

export const localStorageMiddleware = store => next => action => {

    // if  action.type === REFRESH_TOKEN_SUCCESS
    // we also clear error in commonReducer
    if (action.type === LOGIN || action.type === REFRESH_TOKEN_SUCCESS) {
        if (!action.error) {
            const accessToken = action.payload.data.accessToken;
            const refreshToken = action.payload.data.refreshToken;
            console.log("Now store accessToken, action.type = ", action.type, action.payload);
            window.localStorage.setItem("accessToken", accessToken);
            window.localStorage.setItem("refreshToken", refreshToken);
            setAuthorizationToken(accessToken);
        }
    } else if (action.type === ACCESS_TOKEN){
        setAuthorizationToken(action.accessToken);
    }
    next(action);
}