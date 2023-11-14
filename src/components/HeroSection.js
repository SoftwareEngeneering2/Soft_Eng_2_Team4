import React from 'react';
import { Button, Container, Row, Col } from 'react-bootstrap';
import heroImage from '../assets/images/financehelp.jpeg';


const HeroSection = () => {
  return (
    <Container fluid className="text-white py-5 custom-translucent-bg">
      <Row className="align-items-center">
        <Col lg={6} md={12} sm={12} className="p-5">
          <h1>Believe in your financial journey that lies ahead of your footsteps</h1>
          <p className="lead">
            With FGL, you can generate different life pathways
            and advice to support your financial journey.
            Get started now!
          </p>
          <Button variant="primary" size="lg" className="m-2">
            Get Started
          </Button>
          <Button variant="outline-light" size="lg">
            Download App
          </Button>
        </Col>
        <Col lg={6} md={12} sm={12}>
          <img
            src={heroImage}
            alt="Financial Independence"
            className="img-fluid rounded-custom"
          />
        </Col>
      </Row>
    </Container>
  );
};

export default HeroSection;
