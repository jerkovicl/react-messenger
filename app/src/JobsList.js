import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';

class JobsList extends Component {
  constructor(props) {
    super(props);
    this.state = { jobs: [], isLoading: true };
    this.remove = this.remove.bind(this);
  }

  componentDidMount() {
    this.setState({ isLoading: true });

    fetch('/api/schedulejobs')
      .then(response => response.json())
      .then(data => this.setState({ jobs: data, isLoading: false }))
      .catch(error => {
        console.log('request failed', error, this.state);
      });
  }

  async remove(id) {
    await fetch(`/api/schedulejobs/${id}`, {
      method: 'DELETE',
      headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(() => {
      let updatedJobs = [...this.state.jobs].filter(i => i.id !== id);
      this.setState({ jobs: updatedJobs });
    });
  }

  render() {
    const { jobs, isLoading } = this.state;

    if (isLoading) {
      return (
        <div>
          <AppNavbar />
          <Container fluid>
            <div className="float-right">
              <Button color="success" tag={Link} to="/jobs/new">
                Add Job
              </Button>
            </div>
          </Container>
        </div>
      );
    }

    const jobsList = jobs.map(job => {
      return (
        <tr key={job.id}>
          <td style={{ whiteSpace: 'nowrap' }}>{job.channel}</td>
          <td>{job.messageText}</td>
          <td>
            {new Intl.DateTimeFormat('en-GB', {
              year: 'numeric',
              month: '2-digit',
              day: '2-digit',
              hour: '2-digit',
              minute: '2-digit',
              second: '2-digit'
            }).format(new Date(job.time))}
          </td>
          <td>{job.status === true ? 'sent' : 'scheduled'}</td>
          <td>
            <ButtonGroup>
              <Button
                size="sm"
                color="danger"
                onClick={() => this.remove(job.id)}
              >
                Delete
              </Button>
            </ButtonGroup>
          </td>
        </tr>
      );
    });

    return (
      <div>
        <AppNavbar />
        <Container fluid>
          <div className="float-right">
            <Button color="success" tag={Link} to="/jobs/new">
              Add Job
            </Button>
          </div>
          <h3>Schedule Jobs List</h3>
          <Table className="mt-4">
            <thead>
              <tr>
                <th width="20%">Channel Name</th>
                <th width="20%">Message Text</th>
                <th>Date</th>
                <th>Status</th>
                <th width="10%">Actions</th>
              </tr>
            </thead>
            <tbody>{jobsList}</tbody>
          </Table>
        </Container>
      </div>
    );
  }
}

export default JobsList;
