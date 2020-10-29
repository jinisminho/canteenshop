Function(String) validateLocation = (String value) {
  if (value == "") {
    return "Location is required";
  }
  if (value.length < 1 || value.length > 500) {
    return "Location is not valid";
  }
  return null;
};


Function(String) validateNote = (String value) {
  if (value != "" && value.length > 500) {
    return "Note is too long";
  }
  return null;
};


