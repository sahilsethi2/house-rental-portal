import React from 'react';
import { Navigate } from 'react-router-dom';
import AuthService from '../services/AuthService';

const ProtectedRoute = ({ children, role }) => {
    const currentUser = AuthService.getCurrentUser();

    // If user is not logged in, redirect to login page
    if (!currentUser) {
        return <Navigate to="/login" />;
    }

    // If a role is required and the user's role doesn't match, redirect to home page
    if (role && currentUser.role !== role) {
        return <Navigate to="/" />;
    }

    // If all checks pass, show the component
    return children;
};

export default ProtectedRoute;