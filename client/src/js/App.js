import React, {Component} from 'react';
import {
    Route,
    NavLink,
    HashRouter
} from "react-router-dom";
import AddRecipe from './AddRecipe'
import RandomDish from './RandomDish'
import Home from './Home'
import '../css/App.css'

class App extends Component {

    constructor() {
        super();
        this.state = {};
    }


    render() {
        return (
            <div className="app">

                <HashRouter>
                    <div>
                        <ul className="header">
                            <li><NavLink to="/home">Home</NavLink></li>
                            <li><NavLink to="/add">Add</NavLink></li>
                            <li><NavLink to="/random">Random dish</NavLink></li>
                            <li><NavLink to="/random">Login</NavLink></li>
                            <li><NavLink to="/random">Sign up</NavLink></li>
                        </ul>
                        <div className="content">
                            <Route path="/home" component={Home}/>
                            <Route path="/add" component={AddRecipe}/>
                            <Route path="/random" component={RandomDish}/>
                        </div>
                        <div className="footer">To moja super stopka</div>
                    </div>
                </HashRouter>
            </div>
        );
    }

}


export default App;
