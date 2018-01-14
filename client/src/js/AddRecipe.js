import React, {Component} from 'react';
import '../css/AddRecipe.css';


class Add extends Component {

    constructor() {
        super();
        this.state = this.defaultState();
    }

    defaultState = () => {
        return {
            country: '',
            dish: '',
            ingredients: [{item: '', quantity: 1, unit: ''}],
            method: '',
        }
    };

    changeCountry = (event) => {
        this.setState({country: event.target.value});
    };

    changeDish = (event) => {
        this.setState({dish: event.target.value});
    };

    changeItem = (event, idx) => {
        const newIngredients = this.state.ingredients.map((ingredient, index) => {
            if (idx !== index) return ingredient;
            return {
                ...ingredient,
                item: event.target.value
            };
        });
        this.setState({ingredients: newIngredients});
    };

    changeQuantity = (event, idx) => {
        const newIngredients = this.state.ingredients.map((ingredient, index) => {
            if (idx !== index) return ingredient;
            return {
                ...ingredient,
                quantity: event.target.value
            };
        });
        this.setState({ingredients: newIngredients});
    };
    changeUnit = (event, idx) => {
        const newIngredients = this.state.ingredients.map((ingredient, index) => {
            if (idx !== index) return ingredient;
            return {
                ...ingredient,
                unit: event.target.value
            };
        });
        this.setState({ingredients: newIngredients});
    };

    changeMethod = (event) => {
        this.setState({method: event.target.value});
    };

    addRecipe = (event) => {
        // const { country, dish, ingredients } = this.state;
        fetch('http://localhost:8080/read/recipe', {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(this.state)
        })
            .then(json => {
                console.log("dostalam z backendu", json)
                this.setState(this.defaultState())
            })
        console.log(this.state)
        event.preventDefault()

    };


    addIngredients = () => {
        this.setState({ingredients: this.state.ingredients.concat([{item: '', quantity: 1, unit: ''}])})
    };

    removeIngredients = (idx) => {
        this.setState({ingredients: this.state.ingredients.filter((s, index) => idx !== index)})
    };

    render() {

        return (
            <div>
                <h1>Add your own favourite dish! </h1>
                <form onSubmit={this.addRecipe}>
                    <label htmlFor="country" id="country">Country </label>
                    <select
                        value={this.state.country}
                        onChange={this.changeCountry}>
                        <option value="Poland">Poland</option>
                        <option value="Germany">Germany</option>

                    </select>
                    <label htmlFor="dish">Dish name</label>
                    <input
                        type="text"
                        value={this.state.dish}
                        onChange={this.changeDish}
                        required
                    />
                    <label htmlFor="ingredient">Ingredient</label>
                    {this.state.ingredients.map((ingredient, idx) => (
                        <div className="ingredient">
                            <input
                                type="text"
                                value={ingredient.item}
                                onChange={(event) => this.changeItem(event, idx)} required/>
                            <input
                                type="number"
                                value={ingredient.quantity}
                                onChange={(event) => this.changeQuantity(event, idx)} required/>
                            <select
                                value={ingredient.unit}
                                onChange={(event) => this.changeUnit(event, idx)}
                            >
                                <option value="g">g</option>
                                <option value="kg">kg</option>
                                <option value="cup">cup</option>
                                <option value="ml">ml</option>
                            </select>
                            <button type="button" onClick={(event) => this.removeIngredients(idx)}
                                    className="small">-
                            </button>
                        </div>

                    ))}

                    <button type="button" onClick={this.addIngredients} className="small">Add ingredient!</button>
                    <label htmlFor="method">Method</label>
                    <textarea
                        rows="4" cols="50"
                        value={this.state.method}
                        onChange={this.changeMethod}
                        required
                    >Method</textarea>

                    <button>Send</button>
                </form>
            </div>
        );
    }

}


export default Add;
