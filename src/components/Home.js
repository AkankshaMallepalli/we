import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/Home.css'; // Ensure correct path to the CSS file

function Home() {
  return (
    <div className="home-container">
      <h1>Welcome to Weather App</h1>
      <div className="center-links">
        <Link to="/register">Register</Link>
        <br />
        <Link to="/login">Login</Link>
      </div>
    </div>
  );
}

export default Home;
