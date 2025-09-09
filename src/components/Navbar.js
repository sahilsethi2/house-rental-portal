import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import AuthService from '../services/AuthService';

const Navbar = () => {
    const navigate = useNavigate();
    const user = AuthService.getCurrentUser();

    const handleLogout = () => {
        AuthService.logout();
        navigate('/');
        window.location.reload();
    };

    return (
        <header className="bg-dark text-white p-3">
            <div className="container d-flex justify-content-between align-items-center">
                <Link to="/" className="text-white text-decoration-none">
                    <h1>House Rental Portal</h1>
                </Link>
                <nav>
                    {user ? (
                        <div className="d-flex align-items-center">
                            {/* Show 'Add Property' link for OWNERS */}
                            {user.role === 'ROLE_OWNER' && (
                                <Link to="/add-property" className="btn btn-primary me-3">Add Property</Link>
                            )}

                            {/* Show 'Admin Dashboard' link for ADMINS */}
                            {user.role === 'ROLE_ADMIN' && (
                                <Link to="/admin-dashboard" className="btn btn-warning me-3">Admin Dashboard</Link>
                            )}
                            
                            <span className="me-3">Welcome, {user.name}!</span>
                            <button onClick={handleLogout} className="btn btn-outline-light">Logout</button>
                        </div>
                    ) : (
                        <div>
                            <Link to="/login" className="btn btn-outline-light me-2">Login</Link>
                            <Link to="/register" className="btn btn-light">Register</Link>
                        </div>
                    )}
                </nav>
            </div>
        </header>
    );
};

export default Navbar;