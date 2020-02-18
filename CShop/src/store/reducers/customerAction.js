import * as actionTypes from '../actions/actionTypes'
import { updateObject } from '../ultility'

const getCustomerStart = (state, action) => {
    return updateObject(state, {
        error: null,
        loading: true,
        deleteSuccess: false,
        updateSuccess: false,
        addSuccess: false
    })
}

const getCustomerSuccess = (state, action) => {
    return updateObject(state, {
        data: action.data,
        total: action.total,
        error: null,
        loading: false,
        page: action.page + 1,
        sizePerPage: action.sizePerPage
    })
}
const getCustomerFail = (state, action) => {
    return updateObject(state, {
        error: action.error,
        loading: false,
        total: 0,
        page: 1,
        sizePerPage: 20,

    })
}
export default function reducer(state = {
    data: null,
    total: 0,
    error: null,
    loading: false,
    page: 1,
    sizePerPage: 20,
    deleteSuccess: false,
    updateSuccess: false,
    addSuccess: false

}, action) {
    switch (action.type) {
        case actionTypes.GET_CUSTOMER_START: return getCustomerStart(state, action)
        case actionTypes.GET_CUSTOMER_FAILED: return getCustomerFail(state, action)
        case actionTypes.GET_CUSTOMER_SUCCESS: return getCustomerSuccess(state, action)

    }
    return state
}