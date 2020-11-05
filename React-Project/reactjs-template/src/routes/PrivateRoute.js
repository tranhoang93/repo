import React from 'react';
import { Route, Redirect } from 'react-router-dom';
import { useSelector } from 'react-redux';

const PrivateRoute = ({ component: Component, ...rest }) => {

    const isLoggedIn = useSelector(state => state.auth.accessToken);
    
    return (
        <Route {...rest}
            render={(props) => {
                console.log("[PrivateRoute] Entering " + props.location.pathname + ", isLoggedIn: " + !!isLoggedIn);
                return isLoggedIn ?
                    <Component {...props} />
                    : <Redirect to={{ pathname: '/login', state: { from: props.location } }} />
            }} />
    )
}

export default PrivateRoute;