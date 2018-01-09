import React, {Component} from 'react';
import './../css/RandomDish.css'

class RandomDish extends Component {

    constructor() {
        super();
        this.state = {
            recipe: '',
        };
    }

    handleRandomDish = e => {
        fetch('http://localhost:8080/random/recipe')
            .then(results => {
                return results.json();
            })
            .then(data => {
                console.log("pobrałam z backendu", data);
                let recipe = (() => {
                    return (
                        <form>
                            <p>Dish name: {data.dish}</p>
                            <p>Country name: {data.country}</p>
                            <p>Ingredients: {data.ingredients.map(
                                (a) => <ul>
                                    <li>{a.item} {a.quantity} {a.unit}</li>
                                </ul>
                            )}</p>
                            <p>Method: {data.method}</p>

                        </form>
                    )
                });
                this.setState({recipe: recipe()});
                console.log("stan recipe", this.state.recipe);
            })


    };

    render() {

        return (

            <div>
                <div>
                    <h1>Random regional cuisine from all over the world!</h1>
                </div>
                <label>Category:</label>
                <select>
                    <option value="Poland">Poland</option>
                    <option value="Germany">Germany</option>
                </select>
                <button type="button" onClick={this.handleRandomDish} className="dish">Random!</button>
                <div> {this.state.recipe}</div>
            </div>
        );
    }


}

export default RandomDish;