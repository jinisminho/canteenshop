import React from 'react'
import { Modal, Button} from 'react-bootstrap'
import { BootstrapTable, TableHeaderColumn } from 'react-bootstrap-table';
import 'react-bootstrap-table/dist/react-bootstrap-table-all.min.css';


const orderDetailModal=(props)=> {
    function getName(cell, row){
        return cell.name;
    }
    function getPrice(cell, row){
        return cell.price;
    }

    return (
      <Modal backdrop="static" aria-labelledby="contained-modal-title-vcenter" show={props.show} onHide={props.hide}>
        <Modal.Header closeButton>
          <Modal.Title id="contained-modal-title-vcenter">
          {props.title}
          </Modal.Title>
        </Modal.Header>
        <Modal.Body>
        <BootstrapTable
            data={props.data}
            striped
            hover
        >
            <TableHeaderColumn dataField="id" isKey dataAlign="center" width="15%">Id</TableHeaderColumn>
            <TableHeaderColumn dataField="quantity" dataAlign="center" width="15%">quantity</TableHeaderColumn>
            <TableHeaderColumn dataField="product" dataFormat={getName} dataAlign="center" width="15%">Product Name</TableHeaderColumn>
            <TableHeaderColumn dataField="product" dataFormat={getPrice} dataAlign="center" width="15%">Product price</TableHeaderColumn>

        </BootstrapTable>
        </Modal.Body>
        <Modal.Footer>
          <Button onClick={props.hide}>Close</Button>
        </Modal.Footer>
      </Modal>
    );
  }
export default orderDetailModal