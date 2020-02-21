import React, { Component } from 'react';
import { BootstrapTable, TableHeaderColumn } from 'react-bootstrap-table';
import 'react-bootstrap-table/dist/react-bootstrap-table-all.min.css';
import * as actions from '../../store/actions/index'
import { connect } from 'react-redux'
import Spinner from '../../components/Spinner/Spinner'
import { Navbar,FormGroup, FormControl, Alert } from 'react-bootstrap'
import ReasonModal from '../Components/Modal/ReasonModal'
import OrderDetailModal from '../Components/Modal/OrderDetailModal';
import Datetime from 'react-datetime';
import "react-datetime/css/react-datetime.css";

class Orders extends Component {
    constructor(props) {
        super(props);
        this.state = {
            orderId: null,
            startDate: null,
            endDate: null,
            successNotice: '',
            successShow: false,
            errorShow: false,
            showDetail:false,
            detailData:null,
            showReason:false,
            reasonData:null,
            cancelDetail:false,
            cancelReason:false
        }
        this.fetchData = this.fetchData.bind(this);
        this.handlePageChange = this.handlePageChange.bind(this);
        this.handleSizePerPageChange = this.handleSizePerPageChange.bind(this);
        this.handleSearch = this.handleSearch.bind(this);
        this.showStatus = this.showStatus.bind(this);
        this.setStateReasonData = this.setStateReasonData.bind(this);
        this.showDetail = this.showDetail.bind(this);
        this.setStateDetailData = this.setStateDetailData.bind(this);
        this.handleDetailCancel = this.handleDetailCancel.bind(this);
    }

    componentDidMount() {
        this.fetchData();
    }
    fetchData(page = this.props.page, sizePerPage = this.props.sizePerPage, startDate = this.state.startDate, endDate = this.state.endtDate) {
        this.props.onFetchData(page - 1, sizePerPage, startDate, endDate)
    }
    handlePageChange(page, sizePerPage) {
        this.fetchData(page, sizePerPage, this.state.startDate, this.state.endDate);
    }
    handleInputChange(name, value) {
        let result = null
        if(typeof(value) === "string")
            result = value
        else
            result = value.toDate()    
        this.setState({
          [name]: result
        });
    } 
    handleSearch() {
        this.setState({
            successShow: false,
            errorShow: false
        })
        this.fetchData(1, 20, this.state.startDate, this.state.endDate)
    }
    handleSizePerPageChange(sizePerPage) {
        // When changing the size per page always navigating to the first page
        this.fetchData(1, sizePerPage, this.state.startDate, this.state.endDate);
    }
    showStatus(cell, row){
        if(cell != null) {
            if(cell.status === "Canceled") {
                return <a href="#" onClick={this.setStateReasonData.bind(this,row.id)}>{cell.status}</a>
            }
            return cell.status
        }
        return cell+'';
    }
    setStateReasonData(id,e) {
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
    showDetail(cell, row){
        return <a href="#" onClick={this.setStateDetailData.bind(this,cell)}>Details</a>;
    }
    setStateDetailData(cell,e) {
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
                <div className="row">
                    <div className="col-md-4 col-lg-4">
                    <Datetime
                        value={this.state.startDate}
                        dateFormat="YYYY-MM-DD"
                        timeFormat="HH:mm:ss"
                        onChange={(value)=>(this.handleInputChange("startDate",value))}
                    />
                    <Datetime
                        value={this.state.endDate}
                        dateFormat="YYYY-MM-DD"
                        timeFormat="HH:mm:ss"
                        onChange={(value)=>(this.handleInputChange("endDate",value))}
                    />
                    <button onClick={() => this.handleSearch()} className="btn btn-simple  "><span><i className="fa fa-search"></i></span></button>
                    </div>
                </div>

                <br />
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
                    <TableHeaderColumn dataField="id" isKey dataAlign="center" width="15%">Id</TableHeaderColumn>
                    <TableHeaderColumn dataField="location" dataAlign="center" width="50%">Location</TableHeaderColumn>
                    <TableHeaderColumn dataField="note" dataAlign="center" width="50%">Note</TableHeaderColumn>
                    <TableHeaderColumn dataField="totalPrice" dataAlign="center" width="50%">Total Price</TableHeaderColumn>
                    <TableHeaderColumn dataField="staff" dataAlign="center" width="50%">Staff</TableHeaderColumn>
                    <TableHeaderColumn dataField="orderDetails" dataAlign="center" dataFormat={this.showDetail} width="15%">Detail</TableHeaderColumn>
                    <TableHeaderColumn dataField='status' dataAlign="center" dataFormat={this.showStatus} width="15%">Status</TableHeaderColumn>

                </BootstrapTable>

                <ReasonModal
                    show={this.state.showReason}
                    hide= {() => this.handleReasonCancel()}
                    data = {[this.props.reason]}
                    title="Reason"
                />
                <OrderDetailModal
                    show={this.state.showDetail}
                    hide= {() => this.handleDetailCancel()}
                    data = {this.state.detailData}
                    title="Detail"
                />

            </div>
        )
        if (this.props.loading) {
            display = <Spinner />
        }
        let errorMsg = null
        if (this.props.error && this.state.errorShow) {
            errorMsg = <Alert bsStyle="danger" onDismiss={() => this.setState({ errorShow: false })}>{this.props.error.message}</Alert>
        }
        let successMsg = null
        if (this.state.successShow && this.state.successNotice !== '' && !this.props.error) {
            successMsg = <Alert bsStyle="success" onDismiss={() => this.setState({ successShow: false, successNotice: '' })}>{this.state.successNotice}</Alert>
        }
        if (this.props.deleteSuccess || this.props.updateSuccess || this.props.addSuccess) {
            this.fetchData(1, 20, this.state.startDate, this.state.endDate)
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
                            {successMsg}
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
        loading: state.order.loading,
        data: state.order.data,
        error: state.order.error,
        totalSize: state.order.total,
        page: state.order.page,
        sizePerPage: state.order.sizePerPage,
        reason: state.order.reason
    }
}

const mapDispatchToProps = dispatch => {
    return {
        onFetchData: (page, size, startDate, endDate) => dispatch(actions.getOrders(page, size,startDate, endDate)),
        onGetCancelReason: (orderId) => dispatch(actions.getCancelReason(orderId))
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Orders)