import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import HomePage from './compoment/home_page/Home';
import CartDetail from './compoment/cart/CartDetail';
import Cart from './compoment/cart/Cart';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Login from './compoment/login/Login';
import Register from './compoment/login/Register';
import { Provider } from 'react-redux';
import thunk from "redux-thunk";
import { combineReducers, createStore, applyMiddleware } from "redux"
import cartReducer from './compoment/reducer/reducer';
import { PayPalScriptProvider } from '@paypal/react-paypal-js';
import Customer from './compoment/customer/Customer';
import Order from './compoment/orders/Order';
import SearchHome from './compoment/home_page/SearchHome';
import OrderDetail from './compoment/orders/OrderDetail';
import SearchRacket from './compoment/home_page/SearchRacket';
import SearchBirdie from './compoment/home_page/SearchBirdie';
const middleware = [thunk];
const rootReduce = combineReducers({
  cartReducer
});
const store = createStore(rootReduce, applyMiddleware(...middleware));

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <Provider store={store}>
      <PayPalScriptProvider>
        <BrowserRouter>
          <Routes>
            <Route path='/' element={<HomePage />} />
            <Route path='/home/cart' element={<Cart />} />
            <Route path='/home/cartDetail/:id' element={<CartDetail />} />
            <Route path='/home/login' element={<Login />} />
            <Route path='/home/register' element={<Register />} />
            <Route path='/home/customer' element={<Customer />} />
            <Route path='/home/orders' element={<Order />} />
            <Route path="/home/search/:keyword" element={<SearchHome />} />
            <Route path='/home/orders/detail/:code' element={<OrderDetail />} />
            <Route path='/home/product-racket' element={<SearchRacket />} />
            <Route path='/home/product-birdie' element={<SearchBirdie />} />
          </Routes>
        </BrowserRouter>
      </PayPalScriptProvider>
    </Provider>
    {/* <HomePage /> */}
    {/* <CartDetail /> */}
    {/* <Cart /> */}
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
