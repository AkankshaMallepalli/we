import React, { useState } from 'react';
import axios from 'axios';
import '../styles/Registration.css';

function Registration() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [email, setEmail] = useState('');
  const [registrationSuccess, setRegistrationSuccess] = useState(false);
  const [registrationError, setRegistrationError] = useState('');

  const handleRegistration = async (e) => {
    e.preventDefault(); // Prevent default form submission

    try {
      // Send registration details to the backend
      const response = await axios.post('/api/users/register', {
        username,
        password,
        email,
      });

      // Check if registration was successful
      if (response.status === 200) { // Assuming 200 is the status code for successful registration
        setRegistrationSuccess(true); // Set registration success flag to true
        setRegistrationError(''); // Clear any existing registration error
      } else {
        // Registration failed, display error message
        setRegistrationError('Registration failed. Please try again.');
      }
    } catch (error) {
      // Registration failed, display error message based on the error
      setRegistrationError('Registration failed. Please try again.');
      console.error('Error registering user:', error);
    }
  };

  return (
    <div className="registration-container">
      <h2>Registration</h2>
      {registrationSuccess ? (
        <div className="registration-success">
          <p>Registration successful. Please proceed to login.</p>
          <button onClick={() => window.location.href = '/login'}>Go to Login</button>
        </div>
      ) : (
        <form className="registration-form" onSubmit={handleRegistration}>
          <input
            type="text"
            placeholder="Username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
          <input
            type="email"
            placeholder="Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
          <button type="submit">Register</button>
        </form>
      )}
      {registrationError && <p className="registration-error">{registrationError}</p>}
    </div>
  );
}

export default Registration;
