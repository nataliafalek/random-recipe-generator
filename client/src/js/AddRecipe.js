import React, {Component} from 'react';
import '../css/AddRecipe.css';


class Add extends Component {

    constructor() {
        super();
        this.state = {
            country: '',
            dish: '',
            ingredients: [{item: '', quantity: 1, unit: ''}],
            method: '',
        };
    }

    handleCountryChange = (evt) => {
        this.setState({country: evt.target.value});
    };

    handleDishChange = (evt) => {
        this.setState({dish: evt.target.value});
    };

    handleItemChange = (evt, idx) => {
        const newIngredients = this.state.ingredients.map((ingredient, sidx) => {
            if (idx !== sidx) return ingredient;
            return {
                ...ingredient,
                item: evt.target.value
            };
        });
        this.setState({ingredients: newIngredients});
    };

    handleQuantityChange = (evt, idx) => {
        const newIngredients = this.state.ingredients.map((ingredient, sidx) => {
            if (idx !== sidx) return ingredient;
            return {
                ...ingredient,
                quantity: evt.target.value
            };
        });
        this.setState({ingredients: newIngredients});
    };
    handleUnitChange = (evt, idx) => {
        const newIngredients = this.state.ingredients.map((ingredient, sidx) => {
            if (idx !== sidx) return ingredient;
            return {
                ...ingredient,
                unit: evt.target.value
            };
        });
        this.setState({ingredients: newIngredients});
    };

    handleMethodChange = (evt) => {
        this.setState({method: evt.target.value});
    };

    handleSubmit = (evt) => {
        // const { country, dish, ingredients } = this.state;
        console.log(this.state)
        evt.preventDefault()
    };

    handleAddIngredients = () => {
        this.setState({ingredients: this.state.ingredients.concat([{item: '', quantity: 1, unit: ''}])})
    };

    handleRemoveIngredients = (idx) => {
        this.setState({ingredients: this.state.ingredients.filter((s, sidx) => idx !== sidx)})
    };

    render() {

        return (
            <div>
                <h1>Add your own favourite dish! </h1>
                <form onSubmit={this.handleSubmit}>
                    <label htmlFor="country" id="country">Country </label>
                    <select
                        value={this.state.country}
                        onChange={this.handleCountryChange}>
                        <option value="Poland">Poland</option>
                        <option value="Germany">Germany</option>

                    </select>
                    <label htmlFor="dish">Dish name</label>
                    <input
                        type="text"
                        value={this.state.dish}
                        onChange={this.handleDishChange}
                    />
                    <label htmlFor="ingredient">Ingredient</label>
                    {this.state.ingredients.map((ingredient, idx) => (
                        <div className="ingredient">
                            <input
                                type="text"
                                value={ingredient.item}
                                onChange={(event) => this.handleItemChange(event, idx)}/>
                            <input
                                type="number"
                                value={ingredient.quantity}
                                onChange={(event) => this.handleQuantityChange(event, idx)}/>
                            <select
                                value={ingredient.unit}
                                onChange={(event) => this.handleUnitChange(event, idx)}
                            >
                                <option value="g">g</option>
                                <option value="kg">kg</option>
                                <option value="cup">cup</option>
                                <option value="ml">ml</option>
                            </select>
                            <button type="button" onClick={(event) => this.handleRemoveIngredients(idx)}
                                    className="small">-
                            </button>
                        </div>

                    ))}

                    <button type="button" onClick={this.handleAddIngredients} className="small">Add ingredient!</button>
                    <label htmlFor="method">Method</label>
                    <textarea
                        rows="4" cols="50"
                        value={this.state.method}
                        onChange={this.handleMethodChange}
                    >Method</textarea>

                    <button>Send</button>
                </form>
            </div>
        );
    }

}


export default Add;
