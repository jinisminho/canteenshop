
Function(String) validateUsername = (String value) {
  if (value == "") {
    return "Username is required";
  }
  if (value.length < 6 || value.length > 50) {
    return "Username from 6 characters";
  }
  return null;
};

Function(String) validatePassword = (String value) {
  if (value == "") {
    return "Password is required";
  }
  if (value.length < 3 || value.length > 100) {
    return "Username from 3 characters";
  }
  return null;
};

Function(String) validateFirstName = (String value) {
  if (value == "") {
    return "First Name is required";
  }
  if (value.length < 1 || value.length > 100) {
    return "First Name from 1 - 100 characters";
  }
  return null;
};

Function(String) validateLastName = (String value) {
  if (value == "") {
    return "Last Name is required";
  }
  if (value.length < 1 || value.length > 50) {
    return "Last Name from 1 - 50 characters";
  }
  return null;
};

Function(String) validateEmail = (String value) {
  Pattern pattern =
      r'^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$';
  RegExp regex = new RegExp(pattern);
  if (!regex.hasMatch(value))
    return 'Enter Valid Email';
  else
    return null;
};


Function(String) validatePhone = (String value) {
  if(value == ""){
    return 'Phone is required';
  }
  Pattern pattern =
      r'^[0-9]{10}$';
  RegExp regExp = new RegExp(pattern);
  if(!regExp.hasMatch(value)){
    return 'Phone must be 10 numbers';
  }
  return null;
};




