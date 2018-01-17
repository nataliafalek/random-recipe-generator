import React, {Component} from 'react';
import './../css/RandomDish.css'

class RandomDish extends Component {

    constructor() {
        super();
        this.state = {
            recipe: null,
            listOfcuisine: (this.state || {}).cuisine || [],
            cuisine: null,
        };
    }


    changeCuisine = (event) => {
        this.setState({cuisine: event.target.value});
    };


//TODO zmienic zeby zwracac html w render a nie w funkcji/componencie
    randomDish = event => {
        fetch('http://localhost:8080/random/recipe?cuisine=' + this.state.cuisine)
            .then(function (response) {
                if (!response.ok) {
                    throw Error(response.statusText);
                }
                return response;
            })
            .then(results => {
                return results.json();
            })
            .then(data => {
                this.setState({recipe: data});

            }).catch(error => console.log(error));
    };

    componentDidMount() {
        fetch('http://localhost:8080/cuisine')
            .then(results => {
                return results.json();
            })
            .then(data => {
                console.log("pobrałam z backendu cuisine", data);
                this.setState({listOfcuisine: data, cuisine: data[0].cuisine});
            })
    };

    render() {
        console.log("stan recipe", this.state.recipe);
        return (
            <div>
                <div>
                    <h1>Random regional cuisine from all over the world!</h1>
                </div>
                <label>Cuisine:</label>
                <select value={this.state.cuisine}
                        onChange={this.changeCuisine}>
                    {this.state.listOfcuisine.map((a) => <option value={a.cuisine}>{a.cuisine}</option>)}
                </select>

                <button type="button" onClick={this.randomDish} className="dish">Random!</button>
                {this.state.recipe === null ? null :
                    <form>
                        <p>Dish name: {this.state.recipe.dish}</p>
                        <p>Country name: {this.state.recipe.country}</p>
                        <p>Ingredients: {this.state.recipe.ingredients.map(
                            (a) => <ul>
                                <li>{a.item} {a.quantity} {a.unit}</li>
                            </ul>
                        )}</p>
                        <p>Method: {this.state.recipe.method}</p>

                    </form>}
            </div>
        );
    }


}

export default RandomDish;