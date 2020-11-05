import { combineReducers, applyMiddleware, createStore, compose } from 'redux'
import thunk from 'redux-thunk';
import { connectRouter, routerMiddleware } from 'connected-react-router';
import { createBrowserHistory } from 'history';

// Reducers
import common from '../reducers/common';
import auth from '../reducers/authReducer';
import userList from '../reducers/userListReducer';
import userEditor from '../reducers/userEditorReducer';
import appReducer from '../reducers/appReducer';
import userDetailsReducer from '../reducers/userDetailsReducer';
import userGroupListReducer from '../reducers/userGroup/userGroupListReducer';
import userGroupDetailsReducer from '../reducers/userGroup/userGroupDetailsReducer';
import userGroupEditorReducer from '../reducers/userGroup/userGroupEditorReducer';

// Middleware
import promiseMiddleware from './promiseMiddleware';
import { localStorageMiddleware } from './localStorageMiddleware';
import myProfileMiddleware from './myProfileMiddleware';
import {consoleMiddleware} from './consoleMiddleware';

const createRootReducer = (history) => {
    return combineReducers({
        common: common,
        router: connectRouter(history),
        auth: auth,
        userList: userList,
        userEditor: userEditor,
        userDetails: userDetailsReducer,
        userGroupList: userGroupListReducer,
        userGroupDetails: userGroupDetailsReducer,
        userGroupEditor: userGroupEditorReducer,
        app: appReducer
    });
}

export const history = createBrowserHistory();

let composeEnhancers;
if (process.env.NODE_ENV === 'production') {
    composeEnhancers = compose;
} else {
    composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;
}

const configureStore = (preloadedState) => {
    const store = createStore(
        createRootReducer(history),
        preloadedState,
        composeEnhancers(
            applyMiddleware(
                routerMiddleware(history),
                promiseMiddleware,
                localStorageMiddleware,
                myProfileMiddleware,
                consoleMiddleware,
                thunk
            )
        )
    );
    return store;
}

export default configureStore;