import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import AuthService from '../services/AuthService';

const Register = () => {
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [role, setRole] = useState('ROLE_CUSTOMER');
    const navigate = useNavigate();

    const handleRegister = async (e) => {
        e.preventDefault();
        try {
            await AuthService.register(name, email, password, role);
            navigate('/login');
            alert('Registration successful! Please log in.');
        } catch (error) {
            console.error('Registration error:', error);
            alert('Registration failed. Please try again.');
        }
    };

    return (
        <div className="container mt-4">
            <div className="card w-50 mx-auto">
                <div className="card-body">
                    <h2 className="card-title text-center">Register</h2>
                    <form onSubmit={handleRegister}>
                        {/* Form fields for name, email, password, role */}
                        {/* (Implementation details omitted for brevity, but they are standard form inputs) */}
                        <div className="form-group mb-3">
                            <label>Name</label>
                            <input type="text" value={name} onChange={e => setName(e.target.value)} className="form-control" required />
                        </div>
                        <div className="form-group mb-3">
                            <label>Email</label>
                            <input type="email" value={email} onChange={e => setEmail(e.target.value)} className="form-control" required />
                        </div>
                        <div className="form-group mb-3">
                            <label>Password</label>
                            <input type="password" value={password} onChange={e => setPassword(e.target.value)} className="form-control" required />
                        </div>
                        <div className="form-group mb-3">
                            <label>Register as:</label>
                            <select value={role} onChange={e => setRole(e.target.value)} className="form-control">
                                <option value="ROLE_CUSTOMER">Customer</option>
                                <option value="ROLE_OWNER">Property Owner</option>
                            </select>
                        </div>
                        <button type="submit" className="btn btn-primary w-100">Register</button>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default Register;