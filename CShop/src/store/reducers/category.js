import * as actionTypes from '../actions/actionTypes'
import {updateObject} from '../ultility'


const getCategoryStart = (state, action) =>{
  return updateObject(state,{
    error:null, 
    loading:true,
    deleteSuccess:false,
    updateSuccess:false,
    addSuccess:false
  })
}
const getCategorySuccess = (state, action)=>{
  return updateObject(state,{
      data: action.data,
      total:action.total,
      error:null,
      loading:false,
      page:action.page+1,
      sizePerPage: action.sizePerPage
  })
}
const getCategoryFail = (state, action) =>{
  return updateObject(state,{
      error:action.error,
      loading:false,
      total:0,
      page:1,
      sizePerPage:20,
      
  })
}

const deleteCategoryStart = (state, action) =>{
  return updateObject(state,{
    error:null,
    loading:true,
    deleteSuccess:false,
  })
}
const deleteCategorySuccess = (state, action)=>{
  return updateObject(state,{
      error:null,
      loading:false,
      deleteSuccess:true
  })
}
const deleteCategoryFail = (state, action) =>{
  return updateObject(state,{
      error:action.error,
      loading:false,
      total:0,
      page:1,
      sizePerPage:20,
  })
}

const updateCategoryStart = (state, action) =>{
  return updateObject(state,{
    error:null,
    loading:true,
    updateSuccess:false,
  })
}
const updateCategorySuccess = (state, action)=>{
  return updateObject(state,{
      error:null,
      loading:false,
      updateSuccess:true
  })
}
const updateCategoryFail = (state, action) =>{
  return updateObject(state,{
      error:action.error,
      loading:false,
      total:0,
      page:1,
      sizePerPage:20,
  })
}

const addCategoryStart = (state, action) =>{
  return updateObject(state,{
    error:null,
    loading:true,
    addSuccess:false,
  })
}
const addCategorySuccess = (state, action)=>{
  return updateObject(state,{
      error:null,
      loading:false,
      addSuccess:true
  })
}
const addCategoryFail = (state, action) =>{
  return updateObject(state,{
      error:action.error,
      loading:false,
      total:0,
      page:1,
      sizePerPage:20,
  })
}
export default function reducer(state = {
    data: null,
    total:0,
    error:null,
    loading:false,
    page:1,
    sizePerPage:20,
    deleteSuccess: false,
    updateSuccess:false,
    addSuccess:false
    
}, action) {
  switch(action.type){
    case actionTypes.GET_CATEGORY_START: return getCategoryStart(state, action)
    case actionTypes.GET_CATEGORY_FAILED: return getCategoryFail(state, action)
    case actionTypes.GET_CATEGORY_SUCCESS: return getCategorySuccess(state, action)
    
    case actionTypes.DELETE_CATEGORY_START: return deleteCategoryStart(state, action)
    case actionTypes.DELETE_CATEGORY_FAILED: return deleteCategoryFail(state, action)
    case actionTypes.DELETE_CATEGORY_SUCCESS: return deleteCategorySuccess(state, action)
    
    case actionTypes.UPDATE_CATEGORY_START: return updateCategoryStart(state, action)
    case actionTypes.UPDATE_CATEGORY_FAILED: return updateCategoryFail(state, action)
    case actionTypes.UPDATE_CATEGORY_SUCCESS: return updateCategorySuccess(state, action)

    case actionTypes.ADD_CATEGORY_START: return addCategoryStart(state, action)
    case actionTypes.ADD_CATEGORY_FAILED: return addCategoryFail(state, action)
    case actionTypes.ADD_CATEGORY_SUCCESS: return addCategorySuccess(state, action)
   
}
return state
}
 