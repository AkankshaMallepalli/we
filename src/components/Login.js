import React, { useState } from 'react';
import axios from 'axios';
import '../styles/Login.css'; // Import Login.css for styling

function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [loginError, setLoginError] = useState('');

  const handleLogin = async (e) => {
    e.preventDefault(); // Prevent default form submission

    try {
      const response = await axios.post('/api/users/login', {
        username,
        password,
      });

      setUsername('');
      setPassword('');
      setLoginError('');

      // Redirect user to zipcode page if login is successful
      if (response.status === 200) {
        localStorage.setItem('token', response.data.token); // Store token in localStorage
        window.location.href = '/zipcode';
      }
    } catch (error) {
      if (error.response) {
        setLoginError(error.response.data.message);
      } else if (error.request) {
        setLoginError('No response received from server');
      } else {
        setLoginError('Error logging in');
      }
    }
  };

  return (
    <div className="login-container"> {/* Apply class to container */}
      <div className="login-form"> {/* Apply class to form */}
        <h2>Login</h2>
        <form onSubmit={handleLogin}>
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
          <button type="submit">Login</button>
        </form>
        {loginError && <p>{loginError}</p>}
      </div>
    </div>
  );
}

export default Login;
