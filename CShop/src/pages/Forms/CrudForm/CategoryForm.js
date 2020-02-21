import React, { Component } from 'react';
import { Field, reduxForm } from 'redux-form';
import renderField from 'components/FormInputs/renderField';
const validate = values => {
    const errors = {};
    if (!values.name) {
        errors.name = 'Category is required';
      }
    if (!values.email) {
        errors.email = 'Email is required';
    } else if (!/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i.test(values.email)) {
        errors.email = 'Invalid email address'
    }
    if (!values.password) {
        errors.password = 'Password is required';
    } else if (values.password.length < 6) {
        errors.password = 'Must be 6 characters or more';
    }
    return errors;
};

const CategoryForm = ({
    handleSubmit,
    handleCancel
}) => (
        <div className="card">
            <div className="content">
                <form onSubmit={handleSubmit}>
                    <div className="form-group">
                        <label className="control-label">Category:</label>
                        <Field
                            name="name"
                            type="text"
                            placeholder="Category"
                            component={renderField} />
                           
                    </div>
                    <button onClick={handleCancel} type="button" className="btn btn-wd btn-default" >
                        <span className="btn-label">
                        </span> Cancel
                        </button>
                        &nbsp;&nbsp;
                    <button type="submit" className="btn btn-wd btn-success ">
                        <span className="btn-label">
                        </span> Save 
                        </button>
                </form>
            </div>
        </div>
    );

export default reduxForm({
    form: 'categoryForm',
    validate,
})(CategoryForm)