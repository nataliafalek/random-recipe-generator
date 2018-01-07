import React, {Component} from 'react';
import './../css/RandomDish.css'

class RandomDish extends Component {

    constructor() {
        super();
        this.state = {
            dish: ''
        };
    }

    handleRandomDish = e => {
        this.setState({dish: Math.floor((Math.random() * 10) + 1)});
    };

    render() {

        return (

            <div>
                <div>
                    <h1>Random regional cuisine from all over the world!</h1>
                </div>
                <button type="button" onClick={this.handleRandomDish} className="dish">Random!</button>
                <p>{this.state.dish}</p>
            </div>
        );
    }


}

export default RandomDish;