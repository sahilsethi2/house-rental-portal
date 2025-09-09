import axios from 'axios';
import AuthService from './AuthService';

const API_URL = 'http://localhost:8081/api/admin/';

// Helper function to get the auth token
const getAuthHeader = () => {
    const user = AuthService.getCurrentUser();
    if (user && user.token) {
        return { Authorization: 'Bearer ' + user.token };
    } else {
        return {};
    }
};

const getAllProperties = () => {
    return axios.get(API_URL + 'properties', { headers: getAuthHeader() });
};

const approveProperty = (propertyId) => {
    return axios.put(API_URL + `properties/${propertyId}/approve`, {}, { headers: getAuthHeader() });
};

const rejectProperty = (propertyId) => {
    return axios.put(API_URL + `properties/${propertyId}/reject`, {}, { headers: getAuthHeader() });
};

const AdminService = {
    getAllProperties,
    approveProperty,
    rejectProperty,
};

export default AdminService;