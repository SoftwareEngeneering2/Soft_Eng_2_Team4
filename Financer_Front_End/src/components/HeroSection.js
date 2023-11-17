import React from 'react';
import { Button, Container, Row, Col } from 'react-bootstrap';
import { Link } from 'react-router-dom';


const HeroSection = () => {
  return (
    <Container fluid className="text-white py-5">
      <Row className="justify-content-md-center" style = {{ marginTop: '15vh'}}>
        <Col lg={6} md={12} sm={12} className="p-5">
          <h1>Believe in your financial journey that lies ahead of your footsteps</h1>
          <p className="lead">
            With FGL, you can generate different life pathways
            and advice to support your financial journey.
            Get started now!
          </p>
          <Button variant="primary" size="lg" className="m-2" as={Link} to="/signup">
            Get Started
          </Button>
          <Button variant="outline-light" size="lg" as={Link} to="/download-app">
            Download App
          </Button>
        </Col>
      </Row>
    </Container>
  );
};

export default HeroSection;
