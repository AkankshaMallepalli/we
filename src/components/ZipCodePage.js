import React, { useState } from 'react';
import axios from 'axios';

function ZipCodePage() {
  const [zipCode, setZipCode] = useState('');
  const [weatherData, setWeatherData] = useState(null);
  const [error, setError] = useState('');

  const handleFormSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.get(`/api/weather/${zipCode}`);
      setWeatherData(response.data);
      setError('');
    } catch (error) {
      setError('Failed to fetch weather data');
      setWeatherData(null);
    }
  };

  return (
    <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
      <div style={{ textAlign: 'center' }}>
        <h2>Enter Zip Code</h2>
        <form onSubmit={handleFormSubmit}>
          <input
            type="text"
            placeholder="Enter Zip Code"
            value={zipCode}
            onChange={(e) => setZipCode(e.target.value)}
          />
          <button type="submit">Get Weather</button>
        </form>
        {error && <p>{error}</p>}
        {weatherData && (
          <div>
            <h3>Weather Information</h3>
            <p>Location: {weatherData.location.name}</p>
            <p>Region: {weatherData.location.region}</p>
            <p>Country: {weatherData.location.country}</p>
            <p>Temperature: {weatherData.current.temp_c}°C / {weatherData.current.temp_f}°F</p>
            <p>Condition: {weatherData.current.condition.text}</p>
            {/* Add more weather details here */}
          </div>
        )}
      </div>
    </div>
  );
}

export default ZipCodePage;
