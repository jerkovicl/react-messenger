import React, { Component } from 'react';
import './App.css';
import Home from './Home';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import JobsList from './JobsList';
import JobAdd from './JobAdd';

class App extends Component {
  render() {
    return (
      <Router>
        <Switch>
          <Route path='/' exact={true} component={Home} />
          <Route path='/jobs' exact={true} component={JobsList} />
          <Route path='/jobs/:id' component={JobAdd} />
        </Switch>
      </Router>
    )
  }
}

export default App;