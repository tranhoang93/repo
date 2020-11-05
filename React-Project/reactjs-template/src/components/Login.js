import React, {useEffect} from 'react';
import { useSelector, useDispatch } from 'react-redux';

import { UPDATE_AUTH_VALUE, LOGIN } from '../store/actionConstants';
import { Auth } from '../agent/services';

const Login = (props) => {
    const dispatch = useDispatch();

    const username = useSelector(state => state.auth.username);
    const password = useSelector(state => state.auth.password);
    const loading = useSelector(state => state.common.loading);

    useEffect(()=>{
        return function cleanup() {
            console.log("[Login] Cleaning up");
            dispatch({type: UPDATE_AUTH_VALUE, key: 'username', value: ''});
            dispatch({type: UPDATE_AUTH_VALUE, key: 'password', value: ''});
        }``
    }, [dispatch])

    const onChangeUsername = event => {
        dispatch({ type: UPDATE_AUTH_VALUE, key: 'username', value: event.target.value });
    }
    const onChangePassword = event => {
        dispatch({ type: UPDATE_AUTH_VALUE, key: 'password', value: event.target.value });
    }

    const submitForm = () => {
        dispatch({type: LOGIN, payload: Auth.login(username, password)});
    }

    return (
        <div style={{padding: '20px'}}>
            <h1>Login</h1>
            <input id='username'
                value={username}
                onChange={(event) => onChangeUsername(event)} /> <br />
            <input id='password'
                value={password}
                onChange={(event) => onChangePassword(event)} /> <br />
            {loading && <p>Loading...</p>}
            <button onClick={() => submitForm()}>Login</button>

        </div>
    );
}

export default React.memo(Login);