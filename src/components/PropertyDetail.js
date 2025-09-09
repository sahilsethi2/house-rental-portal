import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';

const PropertyDetail = () => {
    // 'useParams' hook from react-router-dom to get the 'propertyId' from the URL
    const { propertyId } = useParams();
    const [property, setProperty] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchPropertyDetails = async () => {
            try {
                // Fetch details for the specific property using its ID
                const response = await axios.get(`http://localhost:8080/api/properties/${propertyId}`);
                setProperty(response.data);
                setLoading(false);
            } catch (error) {
                console.error("Error fetching property details:", error);
                setLoading(false);
            }
        };

        fetchPropertyDetails();
    }, [propertyId]); // This effect runs whenever the propertyId in the URL changes

    if (loading) {
        return <div className="container mt-4"><h2>Loading...</h2></div>;
    }

    if (!property) {
        return <div className="container mt-4"><h2>Property not found.</h2></div>;
    }

    return (
        <div className="container mt-4">
            <div className="card">
                <div className="card-header">
                    <h2>{property.name}</h2>
                    <h5 className="text-muted">{property.address}, {property.location.name}</h5>
                </div>
                <div className="card-body">
                    <p className="card-text">{property.description}</p>
                    <ul className="list-group list-group-flush">
                        <li className="list-group-item"><strong>Price:</strong> ₹{property.price.toLocaleString()}</li>
                        <li className="list-group-item"><strong>Type:</strong> {property.type}</li>
                        <li className="list-group-item"><strong>Owner:</strong> {property.owner.name}</li>
                    </ul>
                </div>
            </div>
        </div>
    );
};

export default PropertyDetail;