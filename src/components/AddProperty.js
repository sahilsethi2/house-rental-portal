import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import PropertyService from '../services/PropertyService';

const AddProperty = () => {
    const [locations, setLocations] = useState([]);
    const [formData, setFormData] = useState({
        name: '',
        description: '',
        address: '',
        price: '',
        type: 'RENT',
        locationId: ''
    });
    const navigate = useNavigate();

    // Fetch locations when the component loads
    useEffect(() => {
        const fetchLocations = async () => {
            try {
                const response = await axios.get('http://localhost:8080/api/admin/locations/public');
                setLocations(response.data);
                if (response.data.length > 0) {
                    setFormData(prev => ({ ...prev, locationId: response.data[0].id }));
                }
            } catch (error) {
                console.error("Error fetching locations:", error);
            }
        };
        fetchLocations();
    }, []);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({ ...prev, [name]: value }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await PropertyService.addProperty(formData);
            alert('Property added successfully! It will be reviewed by an admin.');
            navigate('/'); // Redirect to home page
        } catch (error) {
            console.error("Error adding property:", error);
            alert('Failed to add property.');
        }
    };

    return (
        <div className="container mt-4">
            <h2>Add New Property</h2>
            <form onSubmit={handleSubmit}>
                {/* Form fields for name, description, address, price, type, and a location dropdown */}
                <div className="form-group mb-3">
                    <label>Name</label>
                    <input type="text" name="name" value={formData.name} onChange={handleChange} className="form-control" required />
                </div>
                <div className="form-group mb-3">
                    <label>Description</label>
                    <textarea name="description" value={formData.description} onChange={handleChange} className="form-control" required />
                </div>
                <div className="form-group mb-3">
                    <label>Address</label>
                    <input type="text" name="address" value={formData.address} onChange={handleChange} className="form-control" required />
                </div>
                <div className="form-group mb-3">
                    <label>Price</label>
                    <input type="number" name="price" value={formData.price} onChange={handleChange} className="form-control" required />
                </div>
                <div className="form-group mb-3">
                    <label>Type</label>
                    <select name="type" value={formData.type} onChange={handleChange} className="form-control">
                        <option value="RENT">Rent</option>
                        <option value="SALE">Sale</option>
                    </select>
                </div>
                <div className="form-group mb-3">
                    <label>Location</label>
                    <select name="locationId" value={formData.locationId} onChange={handleChange} className="form-control">
                        {locations.map(loc => (
                            <option key={loc.id} value={loc.id}>{loc.name}</option>
                        ))}
                    </select>
                </div>
                <button type="submit" className="btn btn-primary">Submit for Review</button>
            </form>
        </div>
    );
};

export default AddProperty;