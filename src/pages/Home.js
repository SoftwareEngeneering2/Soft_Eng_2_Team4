import React from 'react';
import { Container, Navbar } from 'react-bootstrap';
import HeroSection from '../components/HeroSection';
import './Home.css';

const Home = () => {
  return (
    <Container fluid className="home-container">
        <Navbar />
        <HeroSection/>
    </Container>
  );

};

export default Home;
