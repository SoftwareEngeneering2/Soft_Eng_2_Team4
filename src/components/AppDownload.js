import React from 'react';
import { Container, Row, Col } from 'react-bootstrap';
import backgroundVideo from '../videos/homeScreenBg.mp4'; // Make sure the path is correct
import './AppDownload.css'; // Create this CSS file for your styles

const AppDownload = () => {
  return (
    <div className="app-download-container">
      <video autoPlay loop muted className="background-video">
        <source src={backgroundVideo} type="video/mp4" />
        Your browser does not support the video tag.
      </video>
      <Container className="text-center">
        <Row className="justify-content-center align-items-center" style={{ height: '60vh' }}>
          <Col>
            <h1 className="coming-soon-message">More info on App release and design coming soon!</h1>
          </Col>
        </Row>
      </Container>
    </div>
  );
};

export default AppDownload;
