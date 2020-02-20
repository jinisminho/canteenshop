import React from 'react'
const deleteButton = (props) => {
   return (
      <button type="button" rel="tooltip" data-placement="left" title="" className="btn btn-danger btn-simple btn-icon " data-original-title="Remove" onClick={props.clicked}>
         <i className="fa fa-trash"></i>
      </button>
   )
}

export default deleteButton