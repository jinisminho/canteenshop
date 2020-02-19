import * as actionTypes from './actionTypes'
import axios from '../../axios-manager'

export const getCustomersSuccess = (data, total, page, sizePerPage) => {
    return {
        type: actionTypes.GET_CUSTOMER_SUCCESS,
        total: total,
        data: data,
        page: page,
        sizePerPage: sizePerPage
    }
}


export const getCustomersFail = (error) => {
    return {
        type: actionTypes.GET_CUSTOMER_FAILED,
        error: error
    }
}

export const getCustomersStart = () => {
    return {
        type: actionTypes.GET_CUSTOMER_START
    }
}

export const updateCustomerStart = () => {
    return ({
        type: actionTypes.UPDATE_CUSTOMER_START
    })
}
export const updateCustomerFail = (error) => {
    return ({
        type: actionTypes.UPDATE_CUSTOMER_FAILED,
        error: error
    })
}
export const updateCustomerSuccess = () => {
    return ({
        type: actionTypes.UPDATE_CUSTOMER_SUCCESS,
    })
}

export const getCustomers = (page, size, search) => {
    return dispatch => {
        dispatch(getCustomersStart())
        let url = '/appUsers'
        if (search) {
            url += '?page=' + page + '&size=' + size + "&name=" + search + "&userType=CUSTOMER"
        } else {
            url += '?page=' + page + '&size=' + size + "&userType=CUSTOMER"
        }
        axios.get(url, { headers: { "Authorization": `Bearer ${localStorage.getItem("accessToken")}` } })
            .then(response => {
                console.log(response.data.content)
                dispatch(getCustomersSuccess(response.data.content, response.data.totalElements, page, size))
            })
            .catch(error => {
                dispatch(getCustomersFail(error))
            });
    }
}

export const changeCustomerStatus = (id, isActive) => {
    return dispatch => {
        dispatch(updateCustomerStart())
        let url = '/appUsers/update/' + id + '?isActive=' + isActive + '&userType=CUSTOMER'
        console.log(url)
        axios.get(url, {
            headers: {
                "Authorization": `Bearer ${localStorage.getItem("accessToken")}`
            }
        })
            .then(response => {
                dispatch(updateCustomerSuccess())
            })
            .catch(error => {
                dispatch(updateCustomerFail(error))
            });
    }
}