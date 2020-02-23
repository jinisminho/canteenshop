import * as actionTypes from './actionTypes'
import axios from '../../axios-manager'

export const getOrdersSuccess = (data, total, page, sizePerPage) => {
    return {
        type: actionTypes.GET_ORDER_SUCCESS,
        total:total,
        data: data,
        page:page,
        sizePerPage:sizePerPage
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

function formatDateToday(flag) {
    if(flag) {
        var today = new Date(),
        month = '' + (today.getMonth() > 1? today.getMonth()-1 : 13-today.getMonth()),
        day = '' + today.getDate(),
        year = (today.getMonth() <= 1? today.getFullYear()-1 : d.getFullYear());
    } else {
        var d = new Date(),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();
    }
    

    if (month.length < 2) 
        month = '0' + month;
    if (day.length < 2) 
        day = '0' + day;

    return [year, month, day].join('-');
}

function formatDate(date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) 
        month = '0' + month;
    if (day.length < 2) 
        day = '0' + day;

    return [year, month, day].join('-');
}

export const getOrders = (page, size,startDate, endDate) => {
    return dispatch => {
        dispatch(getOrdersStart())
        let url='/orders';
        
        let date = formatDateToday();
        let time = new Date().toTimeString().replace(/.*(\d{2}:\d{2}:\d{2}).*/, "$1");
        let dateTime = date+' '+time;
        let date2 = formatDateToday(true);
        let dateTime2 = date2+' '+time;

        if(startDate && endDate){
            let start = formatDate(startDate) + ' ' + startDate.toTimeString().replace(/.*(\d{2}:\d{2}:\d{2}).*/, "$1");
            let end = formatDate(endDate) + ' ' + endDate.toTimeString().replace(/.*(\d{2}:\d{2}:\d{2}).*/, "$1");
            url+='?page='+page+'&size='+size+'&startDate='+start+'&endDate='+end
        }else {
            url+='?page='+page+'&size='+size+'&startDate='+dateTime2+'&endDate='+dateTime
        }

        axios.get(url, { headers: {"Authorization" : `Bearer ${localStorage.getItem("accessToken")}`} })
            .then(response => {
                dispatch(getOrdersSuccess(response.data.content, response.data.totalElements, page, size))
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

export const getCancelReason = (orderId) => {
    return dispatch => {
        dispatch(getCancelReasonStart())
        let url='/cancelReasons?orderId='+orderId
        axios.get(url, { headers: {"Authorization" : `Bearer ${localStorage.getItem("accessToken")}`} })
            .then(response => {
                dispatch(getCancelReasonSuccess(response.data))
            })
            .catch(error => {
                dispatch(getCancelReasonFail(error))
            });
    }
}