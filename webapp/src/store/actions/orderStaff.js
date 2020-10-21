import * as actionTypes from './actionTypes'
import axios from '../../axios-staff'

export const getOrdersSuccess = (data, total, page, sizePerPage, startDate, endDate, orderStatus) => {
    return {
        type: actionTypes.GET_ORDER_SUCCESS,
        total:total,
        data: data,
        page:page,
        sizePerPage:sizePerPage,
        startDate:startDate,
        endDate:endDate,
        orderStatus:orderStatus
    }
}

export const getOrdersFail = (error) => {
    return {
        type: actionTypes.GET_ORDER_FAILED,
        error: error
    }
}

export const getOrdersStart = () => {
    return {
        type: actionTypes.GET_ORDER_START
    }
}

export const getOrdersStaff = (page, size,startDate, endDate, orderStatus) => {
    return dispatch => {
        dispatch(getOrdersStart())
        let url = ''
        if (orderStatus != null) {
            url='/orders/searchInPeriodByStatus?'+'From='+startDate+'&To='+endDate+'&pageNumber='+page+'&pageSize='+size+'&Status='+orderStatus
        } else {
            url='/orders/searchInPeriod?'+'From='+startDate+'&To='+endDate+'&pageNumber='+page+'&pageSize='+size
        }

        axios.get(url, { headers: {"Authorization" : `Bearer ${localStorage.getItem("accessToken")}`} })
            .then(response => {
                dispatch(getOrdersSuccess(response.data.content, response.data.totalElements, page, size, startDate, endDate, orderStatus))
            })
            .catch(error => {
                dispatch(getOrdersFail(error))
            });
    }
}

export const getCancelReasonSuccess = (data) => {
    return {
        type: actionTypes.GET_CANCELREASON_SUCCESS,
        reason: data
    }
}

export const getCancelReasonFail = (error) => {
    return {
        type: actionTypes.GET_CANCELREASON_FAILED,
        error: error
    }
}

export const getCancelReasonStart = () => {
    return {
        type: actionTypes.GET_CANCELREASON_START
    }
}

export const getCancelReasonStaff = (orderId) => {
    return dispatch => {
        dispatch(getCancelReasonStart())
        let url='/cancelReason/get?customerOrderId='+orderId
        axios.get(url, { headers: {"Authorization" : `Bearer ${localStorage.getItem("accessToken")}`} })
            .then(response => {
                dispatch(getCancelReasonSuccess(response.data))
            })
            .catch(error => {
                dispatch(getCancelReasonFail(error))
            });
    }
}

export const cancelOrdersSuccess = () => {
    return {
        type: actionTypes.CANCEL_ORDER_SUCCESS,
    }
}

export const cancelOrdersFail = (error) => {
    return {
        type: actionTypes.CANCEL_ORDER_FAILED,
        error: error
    }
}

export const cancelOrdersStart = () => {
    return {
        type: actionTypes.CANCEL_ORDER_START
    }
}

export const cancelOrder = (reason, cancelId) => {
    return dispatch => {
        dispatch(cancelOrdersStart())
        let url='/orders/editStatus?Id='+cancelId+'&Status=Canceled'
        return axios.get(url, { headers: {"Authorization" : `Bearer ${localStorage.getItem("accessToken")}`} })
            .then(response => {
                dispatch(cancelOrdersSuccess())
                url='/cancelReason/create?customerOrderId='+cancelId+'&reason='+reason
                return axios.post(url, {}, { headers: {"Authorization" : `Bearer ${localStorage.getItem("accessToken")}`} })
                    .then(response => {
                        dispatch(cancelOrdersSuccess())
                    })
                    .catch(error => {
                        dispatch(cancelOrdersFail(error))
                    });
            })
            .catch(error => {
                dispatch(cancelOrdersFail(error))
            });
    }
}

export const changeOrderStatusStaffSuccess = () => {
    return {
        type: actionTypes.CHANGE_ORDERSTATUS_SUCCESS,
    }
}

export const changeOrderStatusStaffFail = (error) => {
    return {
        type: actionTypes.CHANGE_ORDERSTATUS_FAILED,
        error: error
    }
}

export const changeOrderStatusStaffStart = () => {
    return {
        type: actionTypes.CHANGE_ORDERSTATUS_START
    }
}

export const changeOrderStatusStaff = (orderId, newStatus) => {
    return dispatch => {
        dispatch(changeOrderStatusStaffStart())
        
        let url='/orders/editStatus?Id='+orderId+'&Status='+newStatus
        return axios.get(url, { headers: {"Authorization" : `Bearer ${localStorage.getItem("accessToken")}`} })
            .then(response => {
                dispatch(changeOrderStatusStaffSuccess())
            })
            .catch(error => {
                dispatch(changeOrderStatusStaffFail(error))
            });
    }
}