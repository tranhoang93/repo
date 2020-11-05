import React from 'react';
import { Route, Redirect } from 'react-router-dom';
import { useSelector } from 'react-redux';

/**
 * A Route that only display the component if user is NOT logged in
 */
const RestrictedRoute = ({ component: Component, ...rest }) => {
  const isLoggedIn = useSelector((state) => state.auth.accessToken);

  return (
    <Route
      {...rest}
      render={(props) => {
        console.log(
          '[RestrictedRoute] Redering ' +
            props.location.pathname +
            ', isLoggedIn = ',
          !!isLoggedIn
        );
        return isLoggedIn ? (
          <Redirect to={{ pathname: '/', state: { from: props.location } }} />
        ) : (
          <Component {...props} />
        );
      }}
    />
  );
};

export default RestrictedRoute;
