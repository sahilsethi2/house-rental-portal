import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar';
import PropertyList from './components/PropertyList';
import PropertyDetail from './components/PropertyDetail';
import Login from './components/Login';
import Register from './components/Register';
import AddProperty from './components/AddProperty';
import AdminDashboard from './components/AdminDashboard';
import ProtectedRoute from './components/ProtectedRoute'; // 1. Import ProtectedRoute

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <Navbar />
        <main>
          <Routes>
            {/* Public Routes */}
            <Route path="/" element={<PropertyList />} />
            <Route path="/property/:propertyId" element={<PropertyDetail />} />
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />

            {/* Protected Routes */}
            <Route path="/add-property" element={
              <ProtectedRoute role="ROLE_OWNER"><AddProperty /></ProtectedRoute>
            } />
            <Route path="/admin-dashboard" element={
              <ProtectedRoute role="ROLE_ADMIN"><AdminDashboard /></ProtectedRoute>
            } />
          </Routes>
        </main>
      </div>
    </BrowserRouter>
  );
}

export default App;