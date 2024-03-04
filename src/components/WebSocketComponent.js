import React, { useEffect } from 'react';

function WebSocketComponent() {
  useEffect(() => {
    // Create WebSocket connection
    const ws = new WebSocket('ws://localhost:3000');

    // Handle WebSocket events
    ws.onopen = () => {
      console.log('WebSocket connected');
    };

    ws.onmessage = (event) => {
      console.log('Message from server:', event.data);
    };

    ws.onerror = (error) => {
      console.error('WebSocket error:', error);
    };

    ws.onclose = () => {
      console.log('WebSocket closed');
    };

    // Cleanup function
    return () => {
      ws.close();
    };
  }, []); // Run effect only once on component mount

  return (
    <div>
      <h1>WebSocket Example</h1>
      {/* Add your component UI here */}
    </div>
  );
}

export default WebSocketComponent;
