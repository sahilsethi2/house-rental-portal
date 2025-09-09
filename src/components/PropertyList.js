import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom'; // Import the Link component

const PropertyList = () => {
    const [properties, setProperties] = useState([]);

    useEffect(() => {
        const fetchProperties = async () => {
            try {
                const response = await axios.get('http://localhost:8080/api/properties');
                setProperties(response.data);
            } catch (error) {
                console.error("Error fetching properties:", error);
            }
        };
        fetchProperties();
    }, []);

    return (
        <div className="container mt-4">
            <h2>Approved Properties</h2>
            <div className="row">
                {properties.map(property => (
                    // Wrap the entire column in a Link component
                    <div className="col-md-4 mb-4" key={property.id}>
                        <Link to={`/property/${property.id}`} style={{ textDecoration: 'none', color: 'inherit' }}>
                            <div className="card h-100">
                                <div className="card-body">
                                    <h5 className="card-title">{property.name}</h5>
                                    <h6 className="card-subtitle mb-2 text-muted">{property.location.name}</h6>
                                    <p className="card-text">{property.description.substring(0, 100)}...</p>
                                    <p className="card-text"><strong>Price: ₹{property.price.toLocaleString()}</strong></p>
                                </div>
                            </div>
                        </Link>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default PropertyList;