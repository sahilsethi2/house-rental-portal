import axios from 'axios';

const API_URL = 'http://localhost:8081/api/auth/';

const register = (name, email, password, role) => {
    return axios.post(API_URL + 'register', {
        name,
        email,
        password,
        role,
    });
};

const login = async (email, password) => {
    const response = await axios.post(API_URL + 'login', {
        email,
        password,
    });
    if (response.data.token) {
        // Store the token and user info in local storage
        localStorage.setItem('user', JSON.stringify(response.data));
    }
    return response.data;
};

const logout = () => {
    localStorage.removeItem('user');
};

const getCurrentUser = () => {
    return JSON.parse(localStorage.getItem('user'));
};

const AuthService = {
    register,
    login,
    logout,
    getCurrentUser,
};

export default AuthService;