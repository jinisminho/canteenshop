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