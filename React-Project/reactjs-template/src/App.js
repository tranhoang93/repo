import React, { useEffect, useCallback } from 'react';
import { Switch } from 'react-router';
import { useDispatch, useSelector } from 'react-redux';
import { push } from 'connected-react-router';
import { useTranslation } from 'react-i18next';
import 'antd/dist/antd.css';
import './App.css';

import { REDIRECT, ACCESS_TOKEN, MY_PROFILE, APP_LOAD } from './store/actionConstants';
// import PrivateRoute from './routes/PrivateRoute';
import NotLoggedInRoute from './routes/NotLoggedInRoute';

import ManagementLayout from './components/ManagementLayout';
import Login from './components/Login';

import { Auth } from './agent/services';
import { Result, Spin } from 'antd';

const App = (props) => {
  const dispatch = useDispatch();
  const { t } = useTranslation();

  const redirectTo = useSelector(state => state.common.redirectTo);
  const localStorageAccessToken = window.localStorage.getItem("accessToken");
  const reduxAccessToken = useSelector(state => state.auth.accessToken);
  const loading = useSelector(state => state.app.loading);
  const isError = useSelector(state => state.app.isError);
  // const error = useSelector(state => state.app.error);


  const onLoad = useCallback(() => {
    console.log("App onLoad....");
    dispatch({ type: APP_LOAD })
  }, [dispatch]);

  useEffect(() => {
    if (redirectTo) {
      dispatch(push(redirectTo));
      dispatch({ type: REDIRECT });
    }

    if (localStorageAccessToken) {
      dispatch({ type: ACCESS_TOKEN, accessToken: localStorageAccessToken });
    } else {
      dispatch(push('/login'));
      dispatch({ type: REDIRECT });
    }

    onLoad();

    if (reduxAccessToken) {
      dispatch({ type: MY_PROFILE, payload: Auth.getMyProfile() });
    }
  }, [redirectTo, dispatch, localStorageAccessToken, reduxAccessToken, onLoad])

  console.log("[App] Is there reduxAccessToken: ", !!reduxAccessToken);
  if (!localStorageAccessToken) {
    console.log("There is no localStorageAccessToken");
    return (
      <Switch>
        <NotLoggedInRoute path="/login" component={Login} />
      </Switch>
    );
  }
  if (loading) {
    console.log("App loading");
    return (
      <Spin spinning>
        <div style={{ width: '200px', height: '100px', margin: '0 auto' }}></div>
      </Spin>
    );
    // if (!!reduxAccessToken) {
    //   console.log("There is redux token");
    //   return (
    //     <div>
    //       <ManagementLayout />
    //     </div>
    //   );
    // } else {
    //   console.log("There is no accessToken in redux");
    // }
  } else {
    // console.log("App loading ... ");
    if (isError) {
      return (
        <Result
          status="500"
          title={t('Could not connect')}
          subTitle={t('couldNotConnectionSubTitle')}
        />
      );
    } else {
      if (!!reduxAccessToken) {
        console.log("There is redux token");
        return (
          <ManagementLayout />
        );
      } else {
        console.log("There is no accessToken in redux");
      }
    }
  }

}

export default React.memo(App);

/**
 * accessToken
 *   - yes:
 *      - expired:
 *        - yes:
 *          - refreshToken:
 *            - yes:
 *              - refreshToken expired:
 *                 - expired:
 *                    - Login
 *                 - no:
 *                    - doRefresh
 *                      - success:
 *                        - store & get user info
 *                      - failure:
 *                        - Login
 *        - no (not expired)
 *   - no:
 *      - Login
 */