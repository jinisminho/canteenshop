import React from 'react'
import { Modal, Button} from 'react-bootstrap'
import { BootstrapTable, TableHeaderColumn } from 'react-bootstrap-table';
import 'react-bootstrap-table/dist/react-bootstrap-table-all.min.css';

const ReasonModal=(props)=> {
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
            <TableHeaderColumn isKey dataField="cancelAt" dataAlign="center" width="15%">Cancel At</TableHeaderColumn>
            <TableHeaderColumn dataField="reason" dataAlign="center" width="15%">Reason</TableHeaderColumn>
        </BootstrapTable>
        </Modal.Body>
        <Modal.Footer>
          <Button onClick={props.hide}>Close</Button>
        </Modal.Footer>
      </Modal>
    );
  }
  export default ReasonModal