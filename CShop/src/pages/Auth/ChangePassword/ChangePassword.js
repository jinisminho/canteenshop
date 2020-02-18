import React, { Component } from 'react'
import * as actions from '../../../store/actions/index'
import { connect } from 'react-redux'
import Spinner from '../../../components/Spinner/Spinner'
import ChangePw from '../../Forms/ChangePassword/index';
import { Redirect } from 'react-router-dom'
class ChangePassword extends Component {
    //this.props.onChangePassword(localStorage.getItem("userId"), values["equal1"])

    render() {

        let form = <ChangePw onSubmit={values => this.props.onChangePassword(localStorage.getItem("token"), values["equal1"])} />
        if (this.props.loading) {
            form = <Spinner />
        }

        let errorMessage = null;
        if (this.props.error) {
            errorMessage = <p>{this.props.error.message}</p>
        }
        return (

            <div className="content ">
            <div className="container-fluid">
              <div className="row ">
                <div className="col-md-8">
                  {form}
                </div>
              </div>
            </div>
          </div>
        )
    }
}
const mapStateToProps = state => {
    return {
        loading: state.Auth.loading,
        error: state.Auth.error,
        isAuthenticated: state.Auth.token !== null,
        authRedirectPath: state.Auth.authRedirectPath
    }
}

const mapDispatchToProps = dispatch => {
    return {
        onChangePassword: (id, password) => dispatch(actions.changePassword(id, password)),
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(ChangePassword)