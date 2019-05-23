import React, { Component } from 'react';
import './App.css';
//import AppNavbar from './AppNavbar';
import JobsList from './JobsList';

class Home extends Component {
  render() {
    return (
      <div>
        <JobsList />
      </div>
    );
  }
}

export default Home;
