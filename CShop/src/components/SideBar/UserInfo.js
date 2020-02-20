import React, { Component } from 'react';
import { Collapse } from 'react-bootstrap';
import { connect } from 'react-redux';
import { Nav, NavItem} from 'react-bootstrap';
import { LinkContainer } from 'react-router-bootstrap'
import { Link, withRouter } from 'react-router-dom';

import cx from 'classnames';

class UserInfo extends Component {

  state = {
    isShowingUserMenu: false
  };

  render() {
    let { user } = this.props;
    let { isShowingUserMenu } = this.state;
    return (
      <div className="user-wrapper">
        <div className="user">
          <h1><i className="pe-7s-user"></i></h1>
          <div className="userinfo">
            <div className="username">
              {user.name}
            </div>
            <div className="title">Admin</div>
          </div>
          <span
            onClick={() => this.setState({ isShowingUserMenu: !this.state.isShowingUserMenu })}
            className={cx("pe-7s-angle-down collapse-arrow", {
              active: isShowingUserMenu
            })}></span>
        </div>
        <Collapse in={isShowingUserMenu}>
        <ul className="nav user-nav">
           <li><Link to="/profile">
             Profile
             </Link></li> 
           <li> <Link to="/changepw">Change Password</Link></li> 
          </ul>
        </Collapse>
      </div>
    );
  }
  isPathActive(path) {
    return this.props.location.pathname.startsWith(path);
  }
}

const mapStateToProps = state => ({
  user: state.Auth.user
});

export default connect(mapStateToProps)(UserInfo);