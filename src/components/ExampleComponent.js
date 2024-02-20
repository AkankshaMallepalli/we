// ExampleComponent.js

import React, { useState } from 'react';
import { registerUser, loginUser } from './apiService';

const ExampleComponent = () => {
  const [registerFormData, setRegisterFormData] = useState({
    username: '',
    email: '',
    password: '',
  });

  const [loginFormData, setLoginFormData] = useState({
    username: '',
    password: '',
  });

  const handleRegister = async () => {
    try {
      const userData = await registerUser(registerFormData);
      console.log('User registered successfully:', userData);
      // Handle success (e.g., show success message, redirect to another page)
    } catch (error) {
      console.error('Error registering user:', error.message);
      // Handle error (e.g., display error message to user)
    }
  };

  const handleLogin = async () => {
    try {
      const userData = await loginUser(loginFormData);
      console.log('User logged in successfully:', userData);
      // Handle success (e.g., store user data in state, redirect to dashboard)
    } catch (error) {
      console.error('Error logging in:', error.message);
      // Handle error (e.g., display error message to user)
    }
  };

  return (
    <div>
      {/* Register Form */}
      <input
        type="text"
        placeholder="Username"
        value={registerFormData.username}
        onChange={(e) => setRegisterFormData({ ...registerFormData, username: e.target.value })}
      />
      {/* Add more form fields for email and password */}
      <button onClick={handleRegister}>Register</button>

      {/* Login Form */}
      <input
        type="text"
        placeholder="Username"
        value={loginFormData.username}
        onChange={(e) => setLoginFormData({ ...loginFormData, username: e.target.value })}
      />
      {/* Add more form fields for password */}
      <button onClick={handleLogin}>Login</button>
    </div>
  );
};

export default ExampleComponent;
