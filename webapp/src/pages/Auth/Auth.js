import React, { Component } from 'react'
import * as actions from '../../store/actions/index'
import { connect } from 'react-redux'
import Spinner from '../../components/Spinner/Spinner'
import LoginForm from '../Forms/LoginForms/index';
import { Redirect } from 'react-router-dom'
import {Alert} from 'react-bootstrap'
class Auth extends Component {
    componentDidMount() {
        if (this.props.authRedirectPath !== '/') {
            this.props.onSetAuthRedirectPath()
        }

    }

    render() {

        let form = <LoginForm onSubmit={values => this.props.onAuth(values["username"], values["password"])} />
        if (this.props.loading) {
            form = <Spinner />
        }

        let errorMessage = null;
        if (this.props.error) {
            let msg=null
            switch(this.props.error){
                case "Unauthorized":
                    msg="Username or password is not correct"
                    break;
                default:
                    msg="Error"
                    break
            }
            errorMessage = <Alert bsStyle="danger">{msg}</Alert>
        }
        let authRedirect = null
        if (this.props.isAuthenticated) {
            authRedirect = <Redirect to={this.props.authRedirectPath} />
        }
        return (

            <div className="container h-100">
                <div className="row">
                {authRedirect}
                
                <div className="col-md-4 col-lg-4 col-sm-6 h-100 col-md-offset-3 col-lg-offset-3">
                    {form}
                    {errorMessage}
                </div>
            </div >
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
        onAuth: (username, password) => dispatch(actions.authStaff(username, password)),
        onSetAuthRedirectPath: () => dispatch(actions.setAuthRedirectPath('/'))
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Auth)