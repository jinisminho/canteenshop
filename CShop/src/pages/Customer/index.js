import React, { Component } from 'react';
import { BootstrapTable, TableHeaderColumn } from 'react-bootstrap-table';
import 'react-bootstrap-table/dist/react-bootstrap-table-all.min.css';
import * as actions from '../../store/actions/index'
import { connect } from 'react-redux'
import Spinner from '../../components/Spinner/Spinner'
import DeleteButton from '../../components/Button/DeleteButton'
import UpdateButton from '../../components/Button/UpdateButton'
import SweetAlert from 'sweetalert-react';
import { Navbar, FormGroup, FormControl, Alert } from 'react-bootstrap'
import CategoryModal from '../Components/Modal/CategoryModal'
import Switch from 'components/Switch';
import SwitchControl from "components/Switch";

class Customer extends Component {
    constructor(props) {
        super(props);
        this.state = {
            confirmDelete: false,
            deleteId: null,
            updateFormShow: false,
            updateData: null,
            searchValue: '',
            successNotice: '',
            successShow: false,
            errorShow: false
        }
        this.fetchData = this.fetchData.bind(this);
        this.handlePageChange = this.handlePageChange.bind(this);
        this.handleSizePerPageChange = this.handleSizePerPageChange.bind(this);
        this.activeFormatter = this.activeFormatter.bind(this);
        this.handleUpdateCancel = this.handleUpdateCancel.bind(this);
        this.getInitialValues = this.getInitialValues.bind(this)
        this.handleAddCancel = this.handleAddCancel.bind(this);
        this.handleAddSubmit = this.handleAddSubmit.bind(this);
        this.handleUpdateSubmit = this.handleUpdateSubmit.bind(this);
        this.handleSearch = this.handleSearch.bind(this);
        this.handleDeleteSubmit = this.handleDeleteSubmit.bind(this)
    }
    componentDidMount() {
        this.fetchData();
    }


    fetchData(page = this.props.page, sizePerPage = this.props.sizePerPage, searchValue = this.state.searchValue) {
        this.props.onFetchData(page - 1, sizePerPage, searchValue)
    }

    handlePageChange(page, sizePerPage) {
        this.fetchData(page, sizePerPage, this.state.searchValue);
    }

    handleSizePerPageChange(sizePerPage) {
        // When changing the size per page always navigating to the first page
        this.fetchData(1, sizePerPage, this.state.searchValue);


    }
    activeFormatter(cell, row) {
        return (
            <div className="col-md-4">
                <SwitchControl
                    value={row.active}
                    onChange={() => {
                        this.handleChangeSwitch(row.id, !row.active);
                    }}
                />
            </div>
        );
    }

    handleChangeSwitch = (id, isActive) => {
        console.log(id + "-" + isActive);
        this.props.onChangeStatus(id, isActive);
        //this.fetchData();
    };

    handleUpdateCancel = () => {
        this.setState({
            updateFormShow: false,
            updateData: null,
            cancelUpdate: true,
        })
    }
    handleAddCancel = () => {
        this.setState({
            addFormShow: false,
            cancelAdd: true,
        })
    }
    getInitialValues = () => {
        return {
            name: this.state.updateData ? this.state.updateData.name : '',
            id: this.state.updateData ? this.state.updateData.id : ''
        };
    }
    inputChangedHandler = (event) => {
        this.setState({ searchValue: event.target.value })
    }
    handleAddSubmit(values) {
        this.setState({ successNotice: 'Your category has been added.', successShow: true, addFormShow: false, errorShow: true })
        this.props.onAddCategory(values)
    }
    handleUpdateSubmit(values) {
        this.setState({ successNotice: 'Your category has been updated.', successShow: true, updateFormShow: false, errorShow: true })
        this.props.onUpdateCategory(values)
    }
    handleSearch() {
        this.setState({
            successShow: false,
            errorShow: false
        })
        this.fetchData(1, 20, this.state.searchValue)
    }
    handleDeleteSubmit() {
        this.setState({ confirmDelete: false, successNotice: 'Your category has been deleted.', successShow: true, errorShow: true })
        this.props.onDeleteCategory(this.state.deleteId)
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
            hideSizePerPage: false,
        };
        console.log("Data: " + this.props.data)
        let display = (
            <div className="content">
                <div className="row">
                    <div className="col-md-4 col-lg-4">
                        <Navbar.Form pullLeft>
                            <FormGroup>
                                <FormControl value={this.state.searchValue ? this.state.searchValue : ""} onChange={(event => this.inputChangedHandler(event))} type="text" placeholder="search by name" />
                                <button onClick={() => this.handleSearch()} className="btn btn-simple  "><span><i className="fa fa-search"></i></span></button>
                            </FormGroup>
                        </Navbar.Form>                    </div>
                    {/* <div className="col-md-4 col-lg-4 pull-right">
                        <button onClick={() => this.setState({ addFormShow: true })}
                            type="button" className="btn btn-info btn-fill btn-wd pull-right" >
                            <span className="btn-label">
                            </span> <i className="fa fa-plus"></i> Add Category
                        </button>
                    </div> */}
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
                    <TableHeaderColumn dataField="id" isKey dataAlign="center" width="10%">Id</TableHeaderColumn>
                    <TableHeaderColumn dataField="firstName" dataAlign="center" width="20%">First Name</TableHeaderColumn>
                    <TableHeaderColumn dataField="lastName" dataAlign="center" width="20%">Last Name</TableHeaderColumn>
                    <TableHeaderColumn dataField="gender" dataAlign="center" width="10%">Gender</TableHeaderColumn>
                    <TableHeaderColumn dataField="phone" dataAlign="center" width="25%">Phone</TableHeaderColumn>
                    <TableHeaderColumn dataField="email" dataAlign="center" width="30%">Email</TableHeaderColumn>
                    <TableHeaderColumn dataField='active' dataAlign="center" dataFormat={this.activeFormatter} width="10%">Action</TableHeaderColumn>
                </BootstrapTable>
                {/* delete popup */}
                <SweetAlert
                    title="Are you sure?"
                    show={this.state.confirmDelete}
                    text="You will not be able to recover this category"
                    showCancelButton
                    onConfirm={() => this.handleDeleteSubmit()}
                    onCancel={() => this.setState({ confirmDelete: false })} />

                <CategoryModal
                    show={this.state.updateFormShow}
                    hide={() => this.handleUpdateCancel()}
                    title="Update Category"
                    initialValues={this.getInitialValues()}
                    submitCategory={(values) => this.handleUpdateSubmit(values)} />

                <CategoryModal
                    show={this.state.addFormShow}
                    hide={() => this.handleAddCancel()}
                    title="Add Category"
                    submitCategory={(values) => this.handleAddSubmit(values)} />

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
            this.fetchData(1, 20, this.state.searchValue)
        }
        return (
            <div className="container-fluid">
                <div className="row">
                    <div className="col-md-12">
                        <div className="card">
                            <div className="header">
                                <h4>Customers</h4>
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
        loading: state.customerAction.loading,
        data: state.customerAction.data,
        error: state.customerAction.error,
        totalSize: state.customerAction.total,
        page: state.customerAction.page,
        sizePerPage: state.customerAction.sizePerPage,
        deleteSuccess: state.customerAction.deleteSuccess,
        updateSuccess: state.customerAction.updateSuccess,
        addSuccess: state.customerAction.addSuccess
    }
}

const mapDispatchToProps = dispatch => {
    return {
        onFetchData: (page, size, search) => dispatch(actions.getCustomers(page, size, search)),
        onChangeStatus: (id, isActive) => dispatch(actions.changeCustomerStatus(id, isActive))
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Customer)