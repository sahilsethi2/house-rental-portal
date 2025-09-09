import React, { useState, useEffect } from 'react';
import AdminService from '../services/AdminService';

const AdminDashboard = () => {
    const [properties, setProperties] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        fetchProperties();
    }, []);

    const fetchProperties = async () => {
        try {
            const response = await AdminService.getAllProperties();
            setProperties(response.data);
            setLoading(false);
        } catch (error) {
            console.error("Error fetching properties:", error);
            setLoading(false);
        }
    };

    const handleApprove = async (propertyId) => {
        try {
            await AdminService.approveProperty(propertyId);
            // Refresh the list to show the updated status
            fetchProperties();
        } catch (error) {
            console.error("Error approving property:", error);
        }
    };

    const handleReject = async (propertyId) => {
        try {
            await AdminService.rejectProperty(propertyId);
            // Refresh the list
            fetchProperties();
        } catch (error) {
            console.error("Error rejecting property:", error);
        }
    };

    if (loading) {
        return <h2>Loading...</h2>;
    }

    return (
        <div className="container mt-4">
            <h2>Admin Dashboard: Manage Properties</h2>
            <table className="table table-striped">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Owner</th>
                        <th>Price</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {properties.map(property => (
                        <tr key={property.id}>
                            <td>{property.name}</td>
                            <td>{property.owner.name}</td>
                            <td>₹{property.price.toLocaleString()}</td>
                            <td>
                                <span className={`badge ${property.status === 'APPROVED' ? 'bg-success' : property.status === 'REJECTED' ? 'bg-danger' : 'bg-warning'}`}>
                                    {property.status}
                                </span>
                            </td>
                            <td>
                                {property.status === 'PENDING' && (
                                    <>
                                        <button onClick={() => handleApprove(property.id)} className="btn btn-success btn-sm me-2">Approve</button>
                                        <button onClick={() => handleReject(property.id)} className="btn btn-danger btn-sm">Reject</button>
                                    </>
                                )}
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default AdminDashboard;