import React, { Component } from 'react';
import { Route, Router, Redirect, Switch } from 'react-router-dom';
import { connect } from 'react-redux';
import cx from 'classnames';
import { setMobileNavVisibility } from '../../store/reducers/Layout';
import { withRouter } from 'react-router-dom';
import Header from './Header';
import Footer from './Footer';
import SideBar from '../../components/SideBar';
import ThemeOptions from '../../components/ThemeOptions';
import MobileMenu from '../../components/MobileMenu';
/**
 * Pages
 */
import Dashboard from '../Dashboard';
import Components from '../Components';
import UserProfile from '../UserProfile';
import MapsPage from '../MapsPage';
import Forms from '../Forms';
import Charts from '../Charts';
import Calendar from '../Calendar';
import Tables from '../Tables';
import Auth from '../Auth/Auth';
import Logout from '../Auth/Logout/Logout'
import ChangePassword from '../Auth/ChangePassword/ChangePassword'
import * as actions from '../../store/actions/index'
import Product from '../Product';
class Main extends Component {
  componentDidMount() {
    this.props.onTryAutoSignUp()
  }
  render() {
    this.props.history.listen(() => {
      if (this.props.mobileNavVisibility === true) {
        this.props.hideMobileMenu();
      }
    })
    let display = (
      <div className="wrapper">
        <Switch>
          <Route path="/auth" component={Auth} />
          <Redirect to="/auth" />
        </Switch>
      </div>
    )
    if (this.props.isAuthenticated) {
      display = (
        <div className="wrapper">
          <div className="close-layer" onClick={this.props.hideMobileMenu}></div>
          <SideBar />

          <div className="main-panel">
            <Header />
            <Route path="/" exact component={Dashboard} />
            <Route path="/logout" component={Logout} />
            <Route path="/auth" component={Auth} />
            <Route path="/product" component={Product} />
            <Route path="/changepw" component={ChangePassword} />
            <Route path="/components" component={Components} />
            <Route path="/profile" component={UserProfile} />
            <Route path="/forms" component={Forms} />
            <Route path="/tables" component={Tables} />
            <Route path="/maps" component={MapsPage} />
            <Route path="/charts" component={Charts} />
            <Route path="/calendar" component={Calendar} />
            <Footer />
          </div>
        </div>
      )
    }
    return (
      <div className={cx({
        'nav-open': this.props.mobileNavVisibility === true
      })}>
        {display}
      </div>
    )
  };
}

const mapStateToProp = state => {
  return {
    mobileNavVisibility: state.Layout.mobileNavVisibility,
    isAuthenticated: state.Auth.token !== null,
  }
};

const mapDispatchToProps = (dispatch, ownProps) => {
  return {
    hideMobileMenu: () => dispatch(setMobileNavVisibility(false)),
    onTryAutoSignUp: () => dispatch(actions.authCheckState())
  }
}


export default withRouter(connect(mapStateToProp, mapDispatchToProps)(Main));