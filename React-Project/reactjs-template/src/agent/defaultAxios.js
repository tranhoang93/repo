import axios from 'axios';

axios.defaults.baseURL = 'http://localhost:8081/api';
// axios.defaults.headers.common['Accept-Language'] = 'vi';
//axios.defaults.headers.common['Authorization'] = AUTH_TOKEN;
//axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';

export const setAuthorizationToken = (token) => {
    axios.defaults.headers.common['Authorization'] = token;
}

export const setAcceptLanguage = (lang) => {
    axios.defaults.headers.common['Accept-Language'] = lang;
}

export default axios;