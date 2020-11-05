import {} from 'axios'
import axios from './defaultAxios';

export const Auth = {
    login: (username, password) => {
        return axios.post('/login', { username: username, password: password });
    },
    getMyProfile: () => {
        return axios.get('/me/profile');
    },
    refreshToken: (refreshToken) => {
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': refreshToken
        }
        return axios.post('/refreshToken', {}, { headers: headers });
        // return axios.post('/login', {username: 'admin', password: '123456'});
    }
}

export const Users = {
    get: (search, page, size) => {
        return axios.get("/users");
        // return axios.get("http://localhost:9090/api/users");
    },
    details: (userId) => {
        return axios.get("/users/" + userId);
    },
    update: (userId, userData) => {
        return axios.put('/users/' + userId, userData);
        // return axios.put('http://localhost:9090/api/users/' + userId, userData);
    },
    create: (userData) => {
        return axios.post('/users', userData);
    },
    delete: (userId) => {
        return axios.delete("/users/" + userId);
    }
}

export const System = {
    availableTimezones: () => {
        return axios.get('/availableTimezones');
    },
    allMenus: () => {
        return axios.get('/menus/all');
    }
}

export const UserGroups = {
    get: (search, page, size) => {
        return axios.get('/userGroups');
    },
    details: (groupId) => {
        return axios.get('/userGroups/' + groupId);
    },
    create: (groupData) => {
        return axios.post('/userGroups', groupData);
    },
    update: (groupId, groupData) => {
        return axios.put('/userGroups/' + groupId, groupData);
    },
    allowedMenus: (groupId) => {
        return axios.get('/userGroups/' + groupId + '/allowedMenus');
    }
}