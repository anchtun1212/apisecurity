import React, {Component} from 'react';

class ArticleDangerCreate extends Component {
	state = {
		article: "",
		articleId: ""
	};
	
	searchTask = async (event) => {
		event.preventDefault();
		let response = await fetch('http://localhost:8098/api/xss/danger/v1/article', {
				method: 'POST',
				headers: {'Content-type': 'application/json'},
				body: JSON.stringify({ article: this.state.article })
		});
		let body = await response.text();
		this.setState({ articleId: body});
	}
	
	updateArticle = (event) => {
		event.preventDefault();
		this.setState({article: event.target.value});
	}
	
	render() {
		return (
			<div>
				<div><h2>Article Danger Create</h2></div>
				<textarea rows="5" cols="50" onChange={(event)=>this.updateArticle(event)} placeholder="Create article"/>
				<button onClick={(event)=>this.searchTask(event)}>Create!</button>
				<br/>
				<h3>New article ID : {this.state.articleId}</h3>
			</div>
		);
	}
}

export default ArticleDangerCreate;
