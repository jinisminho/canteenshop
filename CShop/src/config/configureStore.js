import { createStore, combineReducers, applyMiddleware, compose } from 'redux';
import thunk from 'redux-thunk';
import reducers from '../store/reducers';

const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;


export default function configureStore() {
  return createStore(
    combineReducers({
      ...reducers,
    }),
    {},
    composeEnhancers(applyMiddleware(thunk))
  );
}