import React, {Component} from 'react';

class GreetingSafe extends Component {
	state = {
		name: "",
		greeting: ""
	};
	
	greetMe = async (event) => {
		event.preventDefault();
		let response = await fetch('http://localhost:8098/api/xss/safe/v1/greeting?name=' + this.state.name);
		let body = await response.text();
		this.setState({ greeting: body});
	}
	
	updateName = (event) => {
		event.preventDefault();
		this.setState({name: event.target.value});
	}
	
	render() {
		return (
			<div>
				<div><h2>Greeting Safe</h2></div>
				<div><input size="50" onChange={(event)=>this.updateName(event)} placeholder="Name"></input></div>
				<div><button onClick={(event)=>this.greetMe(event)}>Submit</button></div>
				<div dangerouslySetInnerHTML={{ __html: this.state.greeting }} />
			</div>
		);
	}
}

export default GreetingSafe;
