import React from 'react';
import { Route } from 'react-router-dom';
import Categories from './Categories';
import Beverages from './Beverages';

const Products = ({match}) => (
  <div className="content">
    <Route path={`${match.url}/categories`} component={Categories} />
    <Route path={`${match.url}/beverages`} component={Beverages} />
  </div>
);

export default Products;