import React, { useState } from 'react';

function WeatherDetails() {
  const [zipCode, setZipCode] = useState('');
  const [weatherData, setWeatherData] = useState(null);
  const [error, setError] = useState('');

  const fetchWeatherData = async () => {
    try {
      const response = await fetch(`/api/weather?zipCode=${zipCode}`);
      const data = await response.json();

      if (response.ok) {
        setWeatherData(data);
        setError('');
      } else {
        setError(data.message || 'Error fetching weather data');
        setWeatherData(null);
      }
    } catch (error) {
      console.error('Error fetching weather data:', error);
      setError('Error fetching weather data');
      setWeatherData(null);
    }
  };

  return (
    <div>
      <h2>Weather Details</h2>
      <form
        onSubmit={(e) => {
          e.preventDefault();
          fetchWeatherData();
        }}
      >
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
          <h3>Location: {weatherData.location.name}</h3>
          <p>Region: {weatherData.location.region}</p>
          <p>Country: {weatherData.location.country}</p>
          <p>Temperature: {weatherData.current.temp_c}°C / {weatherData.current.temp_f}°F</p>
          <p>Condition: {weatherData.current.condition.text}</p>
          {/* Add more weather details here */}
        </div>
      )}
    </div>
  );
}

export default WeatherDetails;
