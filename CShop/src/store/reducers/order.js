import * as actionTypes from '../actions/actionTypes'
import {updateObject} from '../ultility'


const getOrderStart = (state, action) =>{
  return updateObject(state,{
    error:null, 
    loading:true,
  })
}
const getOrderSuccess = (state, action)=>{
  return updateObject(state,{
      data: action.data,
      total:action.total,
      error:null,
      loading:false,
      page:action.page+1,
      sizePerPage: action.sizePerPage
  })
}
const getOrderFail = (state, action) =>{
  return updateObject(state,{
      error:action.error,
      loading:false,
      total:0,
      page:1,
      sizePerPage:20,
      
  })
}
const getCancelReasonStart = (state, action) =>{
  return updateObject(state,{
    reason:null,
    error:null, 
    loading:true,
  })
}
const getCancelReasonSuccess = (state, action)=>{
  
    return updateObject(state,{
      reason:action.reason,
      error:null,
      loading:false
  })
}
const getCancelReasonFail = (state, action) =>{
  return updateObject(state,{
      error:action.error,
      loading:false   
  })
}

export default function reducer(state = {
    data: null,
    total:0,
    error:null,
    loading:false,
    page:1,
    sizePerPage:20,
    reason:null    
}, action) {
  switch(action.type){
    case actionTypes.GET_ORDER_START: return getOrderStart(state, action)
    case actionTypes.GET_ORDER_FAILED: return getOrderFail(state, action)
    case actionTypes.GET_ORDER_SUCCESS: return getOrderSuccess(state, action)
    case actionTypes.GET_CANCELREASON_START: return getCancelReasonStart(state, action)
    case actionTypes.GET_CANCELREASON_FAILED: return getCancelReasonFail(state, action)
    case actionTypes.GET_CANCELREASON_SUCCESS: return getCancelReasonSuccess(state, action)
}
return state
}
 