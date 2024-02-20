import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';

const Registration = () => {
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');

  const handleSubmit = async (event)   => {
    event.preventDefault();
    try { 
      const response = await axios.post('http://localhost:8080/api/user/registration', {
        username,
        email,
        password
      });
      console.log('Registration successful:', response.data);
      // Handle successful registration, such as displaying a success message or redirecting to the login page
    } catch (error) {
      console.error('Registration failed:', error);
      setError(error.response.data.message || 'Registration failed. Please try again.');
    }
  };

  return (
    <div>
      <h2>Registration</h2>
      {error && <p>{error}</p>}
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="username">Username:</label>
          <input type="text" id="username" value={username} onChange={(event) => setUsername(event.target.value)} required />
        </div>
        <div>
          <label htmlFor="email">Email:</label>
          <input type="email" id="email" value={email} onChange={(event) => setEmail(event.target.value)} required />
        </div>
        <div>
          <label htmlFor="password">Password:</label>
          <input type="password" id="password" value={password} onChange={(event) => setPassword(event.target.value)} required />
        </div>
        <button type="submit">Register</button>
      </form>
      <p>Already have an account? <Link to="/login">Login here</Link>.</p>
    </div>
  );
};

export default Registration;
