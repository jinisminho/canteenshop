import * as actionTypes from './actionTypes'
import axios from '../../axios-manager'
export const authStart = ()=>{
    return{
        type: actionTypes.AUTH_START
    };
}

export const authSuccess = (token, userId)=>{
    return{
        type: actionTypes.AUTH_SUCCESS,
        idToken: token,
        userId: userId
    };
}

export const authFail =(error) =>{
    return{
        type: actionTypes.AUTH_FAILED,
        error: error
    }
}

export const logout =()=>{
    localStorage.removeItem('accessToken')
    localStorage.removeItem('expiryDate')
    localStorage.removeItem('userId')

    return{
        type: actionTypes.AUTH_LOGOUT
    }
}

export const checkAuthTimeOut = (expirationTime) =>{
    return dispatch =>{
        setTimeout(()=>{
            console.log("time: "+expirationTime)
            dispatch(logout())
        },expirationTime*1000)
    }
}

export const auth = (username, password) =>{
    return dispatch => {
        dispatch(authStart());
        const authData ={
            username:username,
            password:password,
            }
        let url='/auth/login'
        // if(!isSignup){
        ///       url= 'https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=AIzaSyDlIksbOmIbctM2LrIvzxQJznoKcyIUEJI'
        //     
        // }
        axios.post(url,authData)
        .then(response =>{
            const expiryDate= new Date(new Date().getTime() + response.data.expiryDate)
            localStorage.setItem('accessToken',response.data.accessToken)
            localStorage.setItem('expiryDate', expiryDate)
            localStorage.setItem('userId', response.data.userId)
            dispatch(authSuccess(response.data.accessToken, response.data.userId))
            dispatch(checkAuthTimeOut(response.data.expiryDate))
        })
        .catch(err =>{
            dispatch(authFail(err.response.data.error))
        })
    }
}
export const setAuthRedirectPath = (path) =>{
    return {
        type: actionTypes.SET_AUTH_REDIRECT_PATH,
        path: path
    }
}

export const authCheckState = ()=>{
    console.log('aaa')

    return dispatch=>{
        const token =localStorage.getItem('accessToken')
        if(!token){
            dispatch(logout())
        }else{
            const expiryDate = new Date(localStorage.getItem('expiryDate'))

            if(expiryDate <= new Date()){
                dispatch(logout())
            }else{
                const userId = localStorage.getItem('userId')
                dispatch(authSuccess(token, userId))
                dispatch(checkAuthTimeOut((expiryDate.getTime()+new Date().getTime()/1000)))
            }
        }
    }
}

export const changePasswordStart = ()=>{
    return{
        type: actionTypes.CHANGE_PASSWORD_FAILED
    };
}
export const changePasswordFailed =(error) =>{
    return{
        type: actionTypes.CHANGE_PASSWORD_FAILED,
        error: error
    }
}
export const changePasswordSuccess = (token, userId)=>{
    return{
        type: actionTypes.CHANGE_PASSWORD_SUCCESS,
        idToken: token,
        userId: userId
    };
}
export const changePassword = (id, newPass) =>{
    return dispatch => {
        dispatch(changePasswordStart());
        const changeData ={
            idToken:id,
            password:newPass,
            returnSecureToken :true
        }
        let url='https://identitytoolkit.googleapis.com/v1/accounts:update?key=AIzaSyDlIksbOmIbctM2LrIvzxQJznoKcyIUEJI'
        // if(!isSignup){
        ///       url= 'https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=AIzaSyDlIksbOmIbctM2LrIvzxQJznoKcyIUEJI'
        //     
        // }
        axios.post(url,changeData)
        .then(response =>{
            const expiryDate= new Date(new Date().getTime() + response.data.expiryDate*1000)
            localStorage.setItem('accessToken',response.data.accessToken)
            localStorage.setItem('expiryDate', expiryDate)
            localStorage.setItem('userId', response.data.userId)
            dispatch(changePasswordSuccess(response.data.accessToken, response.data.userId))
            dispatch(checkAuthTimeOut(response.data.expiresIn))
        })
        .catch(err =>{
            dispatch(changePasswordFailed(err.response.data.error))
        })
    }
}