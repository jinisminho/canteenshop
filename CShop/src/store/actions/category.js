import * as actionTypes from './actionTypes'
import axios from '../../axios-manager'
export const getCategoriesSuccess = (data, total, page, sizePerPage) => {
    return {
        type: actionTypes.GET_CATEGORY_SUCCESS,
        total:total,
        data: data,
        page:page,
        sizePerPage:sizePerPage
    }
}

export const getCategoriesFail = (error) => {
    return {
        type: actionTypes.GET_CATEGORY_FAILED,
        error: error
    }
}

export const getCategoriesStart = () => {
    return {
        type: actionTypes.GET_CATEGORY_START
    }
}

export const getCategories = (page, size,search) => {
    return dispatch => {
        dispatch(getCategoriesStart())
        let url='/categories'
        if(search){
            url+='?page='+page+'&size='+size+"&name="+search
        }else {
            url+='?page='+page+'&size='+size
        }
        axios.get(url, { headers: {"Authorization" : `Bearer ${localStorage.getItem("accessToken")}`} })
            .then(response => {
                dispatch(getCategoriesSuccess(response.data.content, response.data.totalElements, page, size))
            })
            .catch(error => {
                dispatch(getCategoriesFail(error))
            });
    }
}

export const deleteCategoryStart =()=>{
    return({
        type: actionTypes.DELETE_CATEGORY_START
    })
} 
export const deleteCategoryFail =(error)=>{
    return({
        type: actionTypes.DELETE_CATEGORY_FAILED,
        error:error
    })
} 
export const deleteCategorySuccess =()=>{
    return({
        type: actionTypes.DELETE_CATEGORY_SUCCESS,
    })
} 
export const deleteCategory = (id) => {
    return dispatch => {
        dispatch(deleteCategoryStart())    
        axios.delete('categories/'+id,{ headers: {"Authorization" : `Bearer ${localStorage.getItem("accessToken")}`} })
            .then(response => {
                dispatch(deleteCategorySuccess())
            })
            .catch(error => {
                dispatch(deleteCategoryFail(error))
            });
    }
}

export const updateCategoryStart =()=>{
    return({
        type: actionTypes.UPDATE_CATEGORY_START
    })
} 
export const updateCategoryFail =(error)=>{
    return({
        type: actionTypes.UPDATE_CATEGORY_FAILED,
        error:error
    })
} 
export const updateCategorySuccess =()=>{
    return({
        type: actionTypes.UPDATE_CATEGORY_SUCCESS,
    })
} 
export const updateCategory = (data) => {
    return dispatch => {
        dispatch(updateCategoryStart())    
        axios.put('categories/'+data["id"],data,{ headers: {"Authorization" : `Bearer ${localStorage.getItem("accessToken")}`} })
            .then(response => {
                dispatch(updateCategorySuccess())
            })
            .catch(error => {
                dispatch(updateCategoryFail(error))
            });
    }
}

export const addCategoryStart =()=>{
    return({
        type: actionTypes.ADD_CATEGORY_START
    })
} 
export const addCategoryFail =(error)=>{
    return({
        type: actionTypes.ADD_CATEGORY_FAILED,
        error:error
    })
} 
export const addCategorySuccess =()=>{
    return({
        type: actionTypes.ADD_CATEGORY_SUCCESS,
    })
} 
export const addCategory = (data) => {
    return dispatch => {
        dispatch(addCategoryStart())    
        axios.post('categories/',data,{ headers: {"Authorization" : `Bearer ${localStorage.getItem("accessToken")}`} })
            .then(response => {
                dispatch(addCategorySuccess())
            })
            .catch(error => {
                console.log(error)
                dispatch(addCategoryFail(error))
            });
    }
}