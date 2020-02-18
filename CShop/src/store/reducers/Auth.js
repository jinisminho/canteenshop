import * as actionTypes from '../actions/actionTypes'
import {updateObject} from '../ultility'


const authStart = (state, action) =>{
  return updateObject(state,{error:null, loading:true})
}
const authSuccess = (state, action)=>{
  return updateObject(state,{
      token: action.idToken,
      userId: action.userId,
      error:null,
      loading:false
  })
}
const authFail = (state, action) =>{
  return updateObject(state,{
      error:action.error,
      loading:false
  })
}

const authLogout = (state, action) =>{
  return updateObject( state, {
      token:null,
      userId:null
  })
}

const changePasswordStart = (state, action) =>{
  return updateObject(state,{error:null, loading:true})
}
const changePasswordSuccess = (state, action)=>{
  return updateObject(state,{
      token: action.idToken,
      userId: action.userId,
      error:null,
      loading:false
  })
}
const changePasswordFail = (state, action) =>{
  return updateObject(state,{
      error:action.error,
      loading:false
  })
}

const setRedirectPath = (state,action) =>{
  return updateObject(state, {authRedirectPath: action.path})
}
const defaultUserInfo = {
  name: 'Demo User',
  image: 'http://demos.creative-tim.com/light-bootstrap-dashboard-pro/assets/img/default-avatar.png'
};

export default function reducer(state = {
  user:defaultUserInfo,
  token: null,
  userId: null,
  error: null,
  loading: false,
  authRedirectPath: '/'
}, action) {
  switch(action.type){
    case actionTypes.AUTH_START: return authStart(state, action)
    case actionTypes.AUTH_SUCCESS: return authSuccess(state, action)
    case actionTypes.AUTH_FAILED: return authFail(state, action)
    case actionTypes.AUTH_LOGOUT: return authLogout(state,action)
    case actionTypes.CHANGE_PASSWORD_START: return changePasswordStart(state, action)
    case actionTypes.CHANGE_PASSWORD_FAILED: return changePasswordFail(state, action)
    case actionTypes.CHANGE_PASSWORD_SUCCESS: return changePasswordSuccess(state,action)
    case actionTypes.SET_AUTH_REDIRECT_PATH: return setRedirectPath(state,action)
   
}
return state
}