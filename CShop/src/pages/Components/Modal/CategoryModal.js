import React from 'react'
import { Modal } from 'react-bootstrap'
import CategoriesForm from '../../Forms/CrudForm/CategoryForm'

const categoryModal=(props)=>{
    return (
        <Modal backdrop="static" show={props.show} onHide={props.hide}>
                    <Modal.Header closeButton>
                        <Modal.Title>{props.title}</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <CategoriesForm handleCancel={props.hide} initialValues={props.initialValues} onSubmit={props.submitCategory} />
                    </Modal.Body>
                </Modal>
    )
}
export default categoryModal