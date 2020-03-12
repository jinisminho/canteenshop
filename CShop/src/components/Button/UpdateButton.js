import React from 'react'
const updateButton = (props) => {
   return (
    <button onClick={props.clicked} type="button" rel="tooltip" data-placement="left" title="" className="btn btn-success btn-simple btn-icon" data-original-title="Edit Category">
    <i className="fa fa-edit"></i>
</button>
   )
}

export default updateButton