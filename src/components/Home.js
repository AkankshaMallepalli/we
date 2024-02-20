// Home.js
import React from 'react';
import { Link } from 'react-router-dom';

const Home = () => {
  return (
    <div>
      <h1>Welcome to Weather App</h1>
      <p>New user? <Link to="/registration">Register here</Link>.</p>
      <p>Already have an account? <Link to="/login">Login here</Link>.</p>
    </div>
  );
}

export default Home;
