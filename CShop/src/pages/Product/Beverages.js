import React, { Component } from 'react';
import { BootstrapTable, TableHeaderColumn } from 'react-bootstrap-table';
import 'react-bootstrap-table/dist/react-bootstrap-table-all.min.css';
import * as actions from '../../store/actions/index'
import { connect } from 'react-redux'
import Spinner from '../../components/Spinner/Spinner'
class Beverages extends Component {


    componentDidMount() {
        console.log('aaaa')
        this.fetchData();
    }

    fetchData(page = this.props.page, sizePerPage = this.props.sizePerPage) {
       this.props.onFetchData(page,sizePerPage)
    }

    handlePageChange(page, sizePerPage) {
        this.fetchData(page, sizePerPage);
    }

    handleSizePerPageChange(sizePerPage) {
        // When changing the size per page always navigating to the first page
        this.fetchData(1, sizePerPage);
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
        return (
            <div className="container-fluid">
                <div className="row">
                    <div className="col-md-12">
                        <div className="card">
                            <div className="header">
                                <h4>Categories</h4>
                            </div>
                            <div className="content">
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
                                    <TableHeaderColumn dataField="id" isKey dataAlign="center">Id</TableHeaderColumn>
                                    <TableHeaderColumn dataField="category" dataAlign="center">Category</TableHeaderColumn>
                                </BootstrapTable>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        );
    }
}
const mapStateToProps = state => {
    return {
        loading: state.category.loading,
        data: state.category.data,
        error: state.category.error,
        totalSize: state.category.total,
        page: state.category.state,
        sizePerPage: state.category.sizePerPage
    }
}

const mapDispatchToProps = dispatch => {
    return {
        onFetchData: (page,size) => dispatch(actions.getCategories(page,size))
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Beverages)