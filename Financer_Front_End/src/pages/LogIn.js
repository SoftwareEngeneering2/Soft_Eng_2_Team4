import React, { useState } from 'react';
import { Form, Button, Container, Row, Col } from 'react-bootstrap';
import backgroundVideo from '../videos/homeScreenBg.mp4'; // Make sure this path is correct
import './Login.css'; // Make sure this path is correct
import { Link, useNavigate } from 'react-router-dom';

const Login = () => {
  let [errorMessage, setErrorMessage] = useState('')
  const navigate = useNavigate();
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
    let endpoint = 'http://localhost:8080/api/v2/login/existing'
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
      navigate(`/logged-in/?data=${JSON.stringify(data)}`)
    })
    .catch((error) => {
      console.error('Error:', error);
      setErrorMessage("")
      setTimeout(setErrorMessage, 100, "Incorrect Username/Password")
      // setResult('Failed to calculate. Please try again.');
    });
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
              <div className="incorrect">{errorMessage}</div>
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
              <Link to="/reset-request" className="forgot-password-link">Forgot Password?</Link>
            </Form>
          </Col>
        </Row>
      </Container>
    </div>
  );
};

export default Login;
