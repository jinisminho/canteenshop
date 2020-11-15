import React, { Component } from 'react';
import { BootstrapTable, TableHeaderColumn } from 'react-bootstrap-table';
import 'react-bootstrap-table/dist/react-bootstrap-table-all.min.css';
import * as actions from '../../store/actions/index'
import { connect } from 'react-redux'
import Spinner from '../../components/Spinner/Spinner'
import { Alert } from 'react-bootstrap'
import OrderDetailModal from '../Components/Modal/OrderDetailModal';
import DateRangePicker from '../Forms/ExtendedForms/DatePicker'
import "react-datetime/css/react-datetime.css";
import ReasonStaffModal from '../Components/Modal/ReasonStaffModal';
import DeleteButton from '../../components/Button/DeleteButton'
import CancelModal from '../Components/Modal/CancelModal';
import SweetAlert from 'sweetalert-react';


class Orders extends Component {
    constructor(props) {
        super(props);
        this.state = {
            orderId: null,
            errorShow: false,
            showDetail: false,
            detailData: null,
            showReason: false,
            reasonData: null,
            cancelDetail: false,
            cancelReason: false,
            cancelId: null,
            newCancelReason: null,
            showCancelForm: false,
            showConfirmChangeStatus: false,
            newStatus: null,
        }
        this.fetchData = this.fetchData.bind(this);
        this.handlePageChange = this.handlePageChange.bind(this);
        this.handleSizePerPageChange = this.handleSizePerPageChange.bind(this);
        this.changeDate = this.changeDate.bind(this);
        this.formatDate = this.formatDate.bind(this)
        this.showStatus = this.showStatus.bind(this);
        this.setStateReasonData = this.setStateReasonData.bind(this);
        this.showDetail = this.showDetail.bind(this);
        this.setStateDetailData = this.setStateDetailData.bind(this);
        this.handleDetailCancel = this.handleDetailCancel.bind(this);
        this.changeStatus = this.changeStatus.bind(this);
        this.activeFormatter = this.activeFormatter.bind(this);
    }

    componentDidMount() {
        this.fetchData();
    }
    fetchData(page = this.props.page, sizePerPage = this.props.sizePerPage, startDate = this.props.startDate, endDate = this.props.endDate, orderStatus = this.props.orderStatus) {
        this.props.onFetchData(page - 1, sizePerPage, startDate, endDate, orderStatus)
    }
    handlePageChange(page, sizePerPage) {
        this.fetchData(page, sizePerPage, this.props.startDate, this.props.endDate, this.props.orderStatus);
    }

    changeDate(startD, endD) {
        let start,end;
        if(startD != null) {
            start = this.formatDate(startD.toDate())
        }
        if(endD != null) {
            end = this.formatDate(endD.toDate())
        }
        this.fetchData(1, 20, start, end, this.props.orderStatus)
    }

    changeStatus(status) {
        this.setState(state => ({
            orderStatus: status
        }))
        this.fetchData(1, 20, this.props.startDate, this.props.endDate, status)
    }

    formatDate(date) {
        var day = date.getDate() < 10 ? '0' + date.getDate() : date.getDate();
        var monthIndex = date.getMonth();
        var year = date.getFullYear();
        var month = (monthIndex + 1) < 10 ? '0' + (monthIndex + 1) : (monthIndex + 1)
        return (year + "-" + month + "-" + day)
    }

    handleSizePerPageChange(sizePerPage) {
        // When changing the size per page always navigating to the first page
        this.fetchData(1, sizePerPage, this.props.startDate,this.props.endDate, this.props.orderStatus);
    }
    showStatus(cell, row) {
        if (cell != null) {
            if (cell.status === "Canceled") {
                return <button className="btn btn-fill btn-warning" onClick={this.setStateReasonData.bind(this, row.id)} >{cell.status}</button>
            }
            return <a className="btn btn-fill btn-success" >{cell.status}</a>
        }
        return cell + '';
    }
    showStaffName(cell, row) {
        if (cell != null) {
            return cell.firstName + " " + cell.lastName
        }
    }
    setStateReasonData(id, e) {
        this.props.onGetCancelReason(id)
        this.setState(state => ({
            showReason: true
        }))
        e.preventDefault();
    }
    handleReasonCancel = () => {
        this.setState({
            showReason: false,
            reasonData: null,
            cancelReason: true,
        })
    }
    showDetail(cell, row) {
        return <a href="#" onClick={this.setStateDetailData.bind(this, cell)}><button className="btn btn-fill btn-primary"> Details</button></a>;
    }
    setStateDetailData(cell, e) {
        this.setState(state => ({
            showDetail: true,
            detailData: cell
        }))
        e.preventDefault();
    }
    handleDetailCancel = () => {
        this.setState({
            showDetail: false,
            detailData: null,
            cancelDetail: true,
        })
    }

    activeFormatter(cell, row) {        

        if (cell.status == "Pending") {
            return (
                <div>
                    <button className="btn btn-fill btn-info" onClick={() => this.setState({
                        showConfirmChangeStatus: true,
                        orderId: row.id,
                        newStatus: 'Confirmed'
                    })} >Confirm</button>
                    <button className="btn btn-fill btn-danger" onClick={() => this.setState({
                        showCancelForm: true,
                        cancelId: row.id
                    })} >Cancel</button>
                </div>              
            )
        }
        if (cell.status == "Confirmed") {
            return (
                <div>
                    <button className="btn btn-fill btn-info" onClick={() => this.setState({
                        showConfirmChangeStatus: true,
                        orderId: row.id,
                        newStatus: 'Delivering'
                    })} >Delivery</button>
                    <button className="btn btn-fill btn-danger" onClick={() => this.setState({
                        showCancelForm: true,
                        cancelId: row.id
                    })} >Cancel</button>
                </div>
            )
        }
        if (cell.status == "Delivering") {
            return (
                <div>
                    <button className="btn btn-fill btn-info" onClick={() => this.setState({
                        showConfirmChangeStatus: true,
                        orderId: row.id,
                        newStatus: 'Completed'
                    })} >Complete</button>
                    <button className="btn btn-fill btn-danger" onClick={() => this.setState({
                        showCancelForm: true,
                        cancelId: row.id
                    })} >Cancel</button>
                </div>
            )
        }
        if (cell.status != "Canceled" && cell.status != "Completed" ) {
            return (
                <div>
                    <button className="btn btn-fill btn-danger" onClick={() => this.setState({
                        showCancelForm: true,
                        cancelId: row.id
                    })} >Cancel</button>
                </div>
            )
        }
        
    }
    handleCancelSubmit(reason, cancelId) {
        this.setState({ showCancelForm: false })
        const doCancelThenReloadTable = async () => { 
            await this.props.onCancelOrder(reason.reason, cancelId)
            await this.fetchData(this.props.page, this.props.sizePerPage, this.props.startDate, this.props.endDate, this.props.orderStatus)
            return
        }
        return doCancelThenReloadTable()
    }
    handleCancelFormClose = () => {
        this.setState({
            showCancelForm: false,
            cancelId: null,
        })
    }
    handleChangeOrderStatus = () => {
        this.setState({showConfirmChangeStatus: false})
        const doChangeThenReloadTable = async () => { 
            await this.props.onChangeOrderStatus(this.state.orderId, this.state.newStatus)
            await this.fetchData(this.props.page, this.props.sizePerPage, this.props.startDate, this.props.endDate, this.props.orderStatus)
            return
        }
        return doChangeThenReloadTable()
    } 

    render() {
        const options = {
            onPageChange: this.handlePageChange,
            onSizePerPageList: this.handleSizePerPageChange,
            page: this.props.page,
            sizePerPage: this.props.sizePerPage,
            prePage: 'Previous',
            nextPage: 'Next',
            firstPage: 'First',
            lastPage: 'Last',
            hideSizePerPage: true,
        };

        let display = (
            <div className="content">
                <div className="container-fluid">
                    
                    
                    <div className="row ">
                        
                        

                        <BootstrapTable
                            data={this.props.data}
                            options={options}
                            fetchInfo={{ dataTotalSize: this.props.totalSize }}
                            remote
                            pagination
                            striped
                            hover
                            condensed
                        >
                            <TableHeaderColumn dataField="id" isKey dataAlign="center" width="10%">Id</TableHeaderColumn>
                            <TableHeaderColumn dataField="location" dataAlign="center" width="20%">Location</TableHeaderColumn>
                            <TableHeaderColumn dataField="note" dataAlign="center" width="20%">Note</TableHeaderColumn>
                            <TableHeaderColumn dataField="totalPrice" dataAlign="center" width="10%">Total Price</TableHeaderColumn>
                            <TableHeaderColumn dataField="orderDetails" dataAlign="center" dataFormat={this.showDetail} width="10%">Detail</TableHeaderColumn>
                            <TableHeaderColumn dataField='status' dataAlign="center" dataFormat={this.showStatus} width="10%">Status</TableHeaderColumn>
                            <TableHeaderColumn dataField='status' dataAlign="center" dataFormat={this.activeFormatter} width="15%">Action</TableHeaderColumn>

                        </BootstrapTable>

                        <ReasonStaffModal
                            show={this.state.showReason}
                            hide={() => this.handleReasonCancel()}
                            data={[this.props.reason]}
                            title="Reason"
                        />
                        <OrderDetailModal
                            show={this.state.showDetail}
                            hide={() => this.handleDetailCancel()}
                            data={this.state.detailData}
                            title="Detail"
                        />
                        <CancelModal
                            show={this.state.showCancelForm}
                            hide={() => this.handleCancelFormClose()}
                            title="Cancel Order"
                            submitCancel={(reason) => this.handleCancelSubmit(reason, this.state.cancelId)} 
                        />

                        <SweetAlert
                            title="Are you sure?"
                            show={this.state.showConfirmChangeStatus}
                            text="You will not be able to undo this action"
                            showCancelButton
                            onConfirm={() => this.handleChangeOrderStatus()}
                            onCancel={() => this.setState({ showConfirmChangeStatus: false })} />     
                    </div>

                </div>
            </div>

        )
        if (this.props.loading) {
            display = <Spinner />
        }
        let errorMsg = null
        if (this.props.error && this.state.errorShow) {
            errorMsg = <Alert bsStyle="danger" onDismiss={() => this.setState({ errorShow: false })}>{this.props.error.message}</Alert>
        }

        return (
            <div className="container-fluid">
                <div className="row">
                    <div className="col-md-12">
                        <div className="card">
                            <div className="header">
                                <h4>Orders</h4>
                            </div>
                            {errorMsg}
                            <div className="row">
                                <div className="col-md-4 col-lg-4">
                                    <button onClick={() => this.changeStatus(null)}
                                        type="button" className="btn btn-primary btn-fill" >
                                        <span className="btn-label">
                                        </span>All
                                    </button>
                                    <button onClick={() => this.changeStatus('Pending')}
                                        type="button" className="btn btn-info btn-fill" >
                                        <span className="btn-label">
                                        </span>Pending
                                    </button>
                                    <button onClick={() => this.changeStatus('Completed')}
                                        type="button" className="btn btn-success btn-fill" >
                                        <span className="btn-label">
                                        </span>Completed
                                    </button>
                                    <button onClick={() => this.changeStatus('Canceled')}
                                        type="button" className="btn btn-warning btn-fill" >
                                        <span className="btn-label">
                                        </span>Canceled
                                    </button>
                                </div>
                                <DateRangePicker 
                                    startDateId="ord_start"
                                    endDateId="ord_end"
                                    onClose={(startDate, endDate) => {
                                    this.setState({
                                        errorShow: true
                                    })
                                    this.changeDate(startDate, endDate)
                                    }} 
                                />
                            </div>
                            
                            {display}
                        </div>
                    </div>
                </div>
            </div>

        );
    }
}
const mapStateToProps = state => {
    return {
        loading: state.orderStaff.loading,
        data: state.orderStaff.data,
        error: state.orderStaff.error,
        totalSize: state.orderStaff.total,
        page: state.orderStaff.page,
        sizePerPage: state.orderStaff.sizePerPage,
        reason: state.orderStaff.reason,
        startDate:state.orderStaff.startDate,
        endDate:state.orderStaff.endDate,
        orderStatus:state.orderStaff.orderStatus
    }
}

const mapDispatchToProps = dispatch => {
    return {
        onFetchData: (page, size, startDate, endDate, orderStatus) => dispatch(actions.getOrdersStaff(page, size, startDate, endDate, orderStatus)),
        onGetCancelReason: (orderId) => dispatch(actions.getCancelReasonStaff(orderId)),
        onCancelOrder: (reason, cancelId) => dispatch(actions.cancelOrder(reason,cancelId)),
        onChangeOrderStatus: (orderId, newStatus) => dispatch(actions.changeOrderStatusStaff(orderId,newStatus)),
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Orders)