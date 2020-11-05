import {
    ASYNC_START, ASYNC_END, REFRESH_TOKEN, REFRESH_TOKEN_SUCCESS, ERROR
} from './actionConstants';
import { Auth } from '../agent/services';
import axios from '../agent/defaultAxios';

function isPromise(v) {
    return v && typeof v.then === 'function';
}

const promiseMiddleware = store => next => action => {
    if (isPromise(action.payload)) {
        store.dispatch({ type: ASYNC_START, subtype: action.type });

        if (action.type !== REFRESH_TOKEN) {
            // console.log("Action type is NOT REFRESH_TOKEN: ", action.payload);
            action.payload
                .then((resp) => {
                    console.log(resp)
                    store.dispatch({ type: ASYNC_END });
                    action.payload = resp;
                    action.error = false;
                    store.dispatch(action);
                })
                .catch(error => {
                    store.dispatch({ type: ASYNC_END });

                    // If the cause is token expired, try to refresh it
                    if (error.response && error.response.status === 401
                        && error.response.data.code === 'ACCESS_TOKEN_EXPIRED') {

                        const refreshToken = window.localStorage.getItem("refreshToken");
                        console.log("ACCESS_TOKEN_EXPIRED, Now dispatch REFRESH_TOKEN");
                        store.dispatch({
                            type: REFRESH_TOKEN,
                            payload: Auth.refreshToken(refreshToken),
                            originalAction: action,
                            axiosRequestConfig: error.config
                        })
                    } else {
                        action.error = true;
                        action.payload = error;
                        store.dispatch(action);
                    }
                });
        }

        else {
            // console.log("Action type is REFRESH_TOKEN, originalAction: ", action.originalAction);
            const { originalAction, axiosRequestConfig } = action;
            action.payload
                .then((resp) => {
                    // store.dispatch({type: ASYNC_END});
                    store.dispatch({ type: REFRESH_TOKEN_SUCCESS, payload: resp, error: false })
                    //console.log("Now dispatch main action: ", action.originalAction);

                    //console.log("Now update Authz header with new access token");
                    axiosRequestConfig.headers['Authorization'] = resp.data.accessToken;
                    const newRequestPromise = axios(axiosRequestConfig);
                    // console.log("a ===== ", a);
                    originalAction.payload = newRequestPromise;
                    store.dispatch(originalAction);
                })
                .catch(error => {
                    if(error.response){
                        if(error.response.status === 401){
                            window.localStorage.removeItem("accessToken");
                            window.localStorage.removeItem("refreshToken");
                        }
                    } else {
                        console.log("Error when refreshing token: ", error);
                    }
                })
        }

    } else {
        next(action);
    }
}

export default promiseMiddleware;

