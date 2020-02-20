import { combineReducers } from 'redux';
import { reducer as formReducer } from 'redux-form'
import ThemeOptions from './ThemeOptions';
import Layout from './Layout';
import Auth from './Auth';
import category from './category'
import order from './order'
export default {
  Auth,
  ThemeOptions,
  Layout,
  category,
  form: formReducer,
  order
};