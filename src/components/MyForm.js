import React, { useState } from 'react';

const MyForm = () => {
  // Define state to store form data
  const [formData, setFormData] = useState({
    username: '',
    email: '',
    password: ''
  });

  // Define a function to handle form submission
  const handleSubmit = (event) => {
    event.preventDefault(); // Prevent default form submission behavior

    // Perform any validation or data processing here

    // Example: Log the form data to the console
    console.log('Form submitted:', formData);

    // Reset form fields if needed
    setFormData({
      username: '',
      email: '',
      password: ''
    });
  };

  // Define functions to handle input changes and update form state
  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setFormData({ ...formData, [name]: value });
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label htmlFor="username">Username:</label>
        <input
          type="text"
          id="username"
          name="username"
          value={formData.username}
          onChange={handleInputChange}
          required
        />
      </div>
      <div>
        <label htmlFor="email">Email:</label>
        <input
          type="email"
          id="email"
          name="email"
          value={formData.email}
          onChange={handleInputChange}
          required
        />
      </div>
      <div>
        <label htmlFor="password">Password:</label>
        <input
          type="password"
          id="password"
          name="password"
          value={formData.password}
          onChange={handleInputChange}
          required
        />
      </div>
      <button type="submit">Submit</button>
    </form>
  );
};

export default MyForm;
