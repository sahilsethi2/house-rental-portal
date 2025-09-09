import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import AuthService from '../services/AuthService';

const Login = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            await AuthService.login(email, password);
            navigate('/');
            window.location.reload(); // Reload to update navbar and other components
        } catch (error) {
            console.error('Login error:', error);
            alert('Login failed. Check your credentials.');
        }
    };

    return (
        <div className="container mt-4">
            <div className="card w-50 mx-auto">
                <div className="card-body">
                    <h2 className="card-title text-center">Login</h2>
                    <form onSubmit={handleLogin}>
                        {/* Form fields for email and password */}
                        <div className="form-group mb-3">
                            <label>Email</label>
                            <input type="email" value={email} onChange={e => setEmail(e.target.value)} className="form-control" required />
                        </div>
                        <div className="form-group mb-3">
                            <label>Password</label>
                            <input type="password" value={password} onChange={e => setPassword(e.target.value)} className="form-control" required />
                        </div>
                        <button type="submit" className="btn btn-primary w-100">Login</button>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default Login;