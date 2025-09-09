import axios from 'axios';
import AuthService from './AuthService';

const API_URL = 'http://localhost:8080/api/properties';

// Helper function to get the auth token
const getAuthHeader = () => {
    const user = AuthService.getCurrentUser();
    if (user && user.token) {
        return { Authorization: 'Bearer ' + user.token };
    } else {
        return {};
    }
};

const addProperty = (propertyData) => {
    return axios.post(API_URL, propertyData, { headers: getAuthHeader() });
};

const PropertyService = {
    addProperty,
};

export default PropertyService;