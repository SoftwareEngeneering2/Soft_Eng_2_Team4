import React, { useState } from 'react';
import { Form, Button, Container, Row, Col } from 'react-bootstrap';
import backgroundVideo from '../videos/videoForPages.mp4'; // Make sure this path is correct
import './Login.css'; // Make sure this path is correct

const Login = () => {
  const [formData, setFormData] = useState({
    email: '',
    password: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prevState => ({
      ...prevState,
      [name]: value
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Implement your login logic here
    console.log('Login form submitted', formData);
    // You would typically handle server communication here
  };

  return (
    <div className="login-container">
      <video autoPlay loop muted className="background-video">
        <source src={backgroundVideo} type="video/mp4" />
        Your browser does not support the video tag.
      </video>
      <Container >
        <Row className="justify-content-md-center" style = {{ marginTop: '20vh'}}>
          <Col xs={12} sm={8} md={6} lg={4}>
            <Form onSubmit={handleSubmit} className="login-form">
              <h2 className="text-center mb-4">Login</h2>
              <Form.Group controlId="formBasicEmail" className="mb-3">
                <Form.Label>Email address</Form.Label>
                <Form.Control
                  type="email"
                  name="email"
                  placeholder="Enter email"
                  value={formData.email}
                  onChange={handleChange}
                />
              </Form.Group>

              <Form.Group controlId="formBasicPassword" className="mb-3">
                <Form.Label>Password</Form.Label>
                <Form.Control
                  type="password"
                  name="password"
                  placeholder="Password"
                  value={formData.password}
                  onChange={handleChange}
                />
              </Form.Group>

              <div className="text-center">
                <Button variant="primary" type="submit">
                  Login
                </Button>
              </div>
            </Form>
          </Col>
        </Row>
      </Container>
    </div>
  );
};

export default Login;
