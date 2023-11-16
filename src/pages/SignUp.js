import React, { useState } from 'react';
import { Form, Button, Container, Row, Col } from 'react-bootstrap';
import backgroundVideo from '../videos/homeScreenBg.mp4'; // Make sure this path is correct
import './SignUp.css'; // Ensure you create a SignUp.css file
import { Link, useNavigate } from 'react-router-dom';

const SignUp = () => {
  const navigate = useNavigate()
  const [formData, setFormData] = useState({
    firstname: '',
    lastname: '',
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
    // Implement your sign up logic here
    // Make HTTP POST request to the Spring Boot backend
    let endpoint = 'http://localhost:8080/api/v2/login/signup'
    fetch(endpoint, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(formData),
    })
    .then((response) => {
      if (!response.ok) {
        throw new Error('Error: ' + response.status);
      }
      return response.json();
    })
    .then((data) => {
      navigate('/signed-up')
    })
    .catch((error) => {
      console.error('Error:', error);
      // setResult('Failed to calculate. Please try again.');
    });
    console.log('Sign Up form submitted', formData);
    // You would typically handle server communication here
  };

  return (
    <div className="signup-container">
      <video autoPlay loop muted className="background-video">
        <source src={backgroundVideo} type="video/mp4" />
        Your browser does not support the video tag.
      </video>
      <Container>
        <Row className="justify-content-md-center align-items-center" style={{ minHeight: '100vh' }}>
          <Col xs={12} sm={8} md={6} lg={4}>
            <Form onSubmit={handleSubmit} className="signup-form">
              <h2 className="text-center mb-4">Sign Up</h2>
              <Form.Group controlId="formBasicFirstName" className="mb-3">
                <Form.Label>First Name</Form.Label>
                <Form.Control
                  type="text"
                  name="firstname"
                  placeholder="Enter first name"
                  value={formData.firstname}
                  onChange={handleChange}
                />
              </Form.Group>
              <Form.Group controlId="formBasicLastName" className="mb-3">
                <Form.Label>Last Name</Form.Label>
                <Form.Control
                  type="text"
                  name="lastname"
                  placeholder="Enter last name"
                  value={formData.lastname}
                  onChange={handleChange}
                />
              </Form.Group>
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
                  Sign Up
                </Button>
              </div>
              <p className='mt-3 text-center'>
              Already have an account? <Link to="/login" className="sign-up-link">Login here</Link>
            </p>
            </Form>
            
          </Col>
        </Row>
      </Container>
    </div>
  );
};

export default SignUp;
