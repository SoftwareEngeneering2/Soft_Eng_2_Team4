import { Form, Container, Row, Col } from 'react-bootstrap';
import backgroundVideo from '../videos/homeScreenBg.mp4'; // Make sure this path is correct
import './LoggedIn.css'; // Ensure you create a SignUp.css file

const LoggedIn = () => {
    const data = JSON.parse(new URLSearchParams(window.location.search).get('data'));
    const message = `Hello, ${data.firstName} ${data.lastName}!`
  return (
    <div>
      <video autoPlay loop muted className="background-video">
        <source src={backgroundVideo} type="video/mp4" />
        Your browser does not support the video tag.
      </video>
      <Container className="loggedin-container">
        <Row className="justify-content-md-center align-items-center" style={{ minHeight: '100vh' }}>
          <Col xs={12} sm={8} md={6} lg={4}>
            <Form className="loggedin-form">
              <h2 className="text-center mb-4">{message}</h2>
            </Form>
          </Col>
        </Row>
      </Container>
    </div>
  );
};

export default LoggedIn;