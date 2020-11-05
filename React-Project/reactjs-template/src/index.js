import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import * as serviceWorker from './serviceWorker';

import { Provider } from 'react-redux';
import configureStore, { history } from './store/store';
import { ConnectedRouter } from 'connected-react-router';
import './i18n';

const store = configureStore({});

ReactDOM.render(
  // <React.StrictMode>
  <Provider store={store}>
    <ConnectedRouter history={history}>
      <App />
    </ConnectedRouter>
  </Provider>,
  // </React.StrictMode>
  document.getElementById('root')
);

serviceWorker.unregister();
