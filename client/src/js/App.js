import React, {Component} from 'react';
import {
    Route,
    NavLink,
    HashRouter
} from "react-router-dom";
import AddRecipe from './AddRecipe'
import RandomDish from './RandomDish'
import '../css/App.css'

class App extends Component {

    constructor() {
        super();
        this.state = {};
    }


    render() {

        return (
            <HashRouter>
                <div>
                    <ul className="header">
                        <li><NavLink to="/">Home</NavLink></li>
                        <li><NavLink to="/add">Add</NavLink></li>
                        <li><NavLink to="/random">Random dish</NavLink></li>
                    </ul>
                    <div className="content">
                        <Route path="/add" component={AddRecipe}/>
                        <Route path="/random" component={RandomDish}/>
                    </div>
                </div>
            </HashRouter>
        );
    }

}


export default App;
