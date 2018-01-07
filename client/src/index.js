import React from 'react';
import ReactDOM from 'react-dom';
import './css/App.css';
import App from './js/App';
import registerServiceWorker from './registerServiceWorker';

ReactDOM.render(<App />, document.getElementById('root'));
registerServiceWorker();