import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import AppNavbar from './AppNavbar';

class JobAdd extends Component {

    emptyItem = {
        channel: 'web-apps',
        messageText: '',
        time: new Date(),
        status: false
    };

    constructor(props) {
        super(props);
        this.state = {
            item: this.emptyItem
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleDateChange = this.handleDateChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async componentDidMount() {
        if (this.props.match.params.id !== 'new') {
            const job = await (await fetch(`/api/schedulejobs/${this.props.match.params.id}`)).json();
            this.setState({ item: job });
        }
    }

    handleChange(event) {
        console.log('handleChange -->', event);
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let item = { ...this.state.item };
        item[name] = value;
        this.setState({ item });
    }
    handleDateChange(event) {
        console.log('handleDateChange -->', event);

        const value = event;
        const name = 'time';
        let item = { ...this.state.item };
        item[name] = value;
        this.setState({ item });
    }
    async handleSubmit(event) {
        event.preventDefault();
        const { item } = this.state;

        await fetch('/api/schedulejobs', {
            method: (item.id) ? 'PUT' : 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item),
        });
        this.props.history.push('/jobs');

    }

    render() {
        const { item } = this.state;
        const title = <h2>{item.id ? 'Edit Job' : 'Add Job'}</h2>;

        return <div>
            <AppNavbar />
            <Container>
                {title}
                <Form onSubmit={this.handleSubmit}>
                    <FormGroup>
                        <Label for="channel">Channel</Label>
                        <Input type="text" name="channel" id="channel" value={item.channel || 'web-apps'} readOnly />
                    </FormGroup>
                    <FormGroup>
                        <Label for="messageText">Message Text</Label>
                        <Input type="textarea" name="messageText" id="messageText" value={item.messageText || ''}
                            onChange={this.handleChange}
                            required={true} />
                    </FormGroup>
                    <FormGroup>
                        <Label for="time">Date</Label>
                        <DatePicker
                            className="form-control"
                            selected={item.time || new Date()}
                            name="time"
                            id="time"
                            onChange={this.handleDateChange}
                            showTimeSelect
                            minDate={new Date()}
                            timeFormat="HH:mm"
                            timeIntervals={5}
                            dateFormat="yyyy-MM-dd HH:mm"
                        />
                    </FormGroup>
                    <FormGroup>
                        <Button color="primary" type="submit">Save</Button>{' '}
                        <Button color="secondary" tag={Link} to="/jobs">Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    }
}

export default withRouter(JobAdd);