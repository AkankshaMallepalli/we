import axios from 'axios';

const API_URL = 'http://localhost:8080/api/user'; // Replace with your backend API URL

const apiService = axios.create({
  baseURL: API_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

export const registerUser = async (userData) => {
  try {
    const response = await apiService.post('/registration', userData);
    return response.data;
  } catch (error) {
    throw new Error(error.response.data.message || 'Registration failed. Please try again.');
  }
};

export const loginUser = async (loginData) => {
  try {
    const response = await apiService.post('/login', loginData);
    return response.data;
  } catch (error) {
    throw new Error(error.response.data.message || 'Login failed. Please try again.');
  }
};
