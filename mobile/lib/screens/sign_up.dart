import 'package:customerappswd/api/customer_api.dart';
import 'package:customerappswd/models/signInUser.dart';
import 'package:customerappswd/util/CustomerValidator.dart';
import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter/widgets.dart';
import 'package:http/http.dart';

class RegisterPage extends StatefulWidget {
  @override
  _RegisterPageState createState() => _RegisterPageState();
}

class _RegisterPageState extends State<RegisterPage> {
  String _message = "";
  bool _isLoading = false;
  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();
  final GlobalKey<ScaffoldState> _scaffoldKey = new GlobalKey<ScaffoldState>();
  bool _autoValidate = false;

  final TextEditingController firstName = new TextEditingController();
  final TextEditingController lastName = new TextEditingController();
  final TextEditingController username = new TextEditingController();
  final TextEditingController password = new TextEditingController();
  final TextEditingController email = new TextEditingController();
  final TextEditingController phone = new TextEditingController();
  String _gender;

  @override
  void initState() {
    super.initState();
    setState(() {
      _gender = 'M';
    });
  }

  @override
  Widget build(BuildContext context) {
    SystemChrome.setSystemUIOverlayStyle(SystemUiOverlayStyle.light
        .copyWith(statusBarColor: Colors.transparent));
    return Scaffold(
      key: _scaffoldKey,
      body: Container(
        padding: EdgeInsets.fromLTRB(0.0, 0.0, 0.0, 20.0),
        decoration: BoxDecoration(color: Colors.white),
        child: _isLoading
            ? Center(child: CircularProgressIndicator(backgroundColor: Colors.orange,))
            : ListView(
                children: <Widget>[
                  headerSection(),
                  formSection(),
                  SizedBox(height: 30.0),
                  registerLink()
                ],
              ),
      ),
    );
  }

  Widget registerLink() {
    return Center(
        child: RichText(
            text: TextSpan(
                text: 'Back To Login Page',
                style: TextStyle(
                  color: Colors.blue,
                ),
                recognizer: new TapGestureRecognizer()
                  ..onTap = () => Navigator.pop(context))));
  }

  Container buttonRegisterSection() {
    return Container(
      width: MediaQuery.of(context).size.width,
      height: 40.0,
      padding: EdgeInsets.symmetric(horizontal: 15.0),
      margin: EdgeInsets.only(top: 15.0),
      child: RaisedButton(
        onPressed: () {
          if (_formKey.currentState.validate()) {
            signUp();
          } else {
            setState(() {
              _isLoading = false;
              _autoValidate = true;
            });
          }
        },
        elevation: 0.0,
        color: Colors.orange,
        child: Text("Register", style: TextStyle(color: Colors.white)),
        shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(5.0)),
      ),
    );
  }

  Container textSection() {
    return Container(
      padding: EdgeInsets.symmetric(horizontal: 15.0, vertical: 20.0),
      child: Column(
        children: <Widget>[
          SizedBox(height: 30.0),
          TextFormField(
            controller: username,
            cursorColor: Colors.blue,
            style: TextStyle(color: Colors.blue),
            decoration: InputDecoration(
              hintText: 'Username',
              border: UnderlineInputBorder(
                  borderSide: BorderSide(color: Colors.orange)),
              hintStyle: TextStyle(color: Colors.blue),
            ),
            validator: validateUsername,
          ),
          SizedBox(height: 30.0),
          TextFormField(
            obscureText: true,
            controller: password,
            cursorColor: Colors.blue,
            style: TextStyle(color: Colors.blue),
            decoration: InputDecoration(
              hintText: 'Password',
              border: UnderlineInputBorder(
                  borderSide: BorderSide(color: Colors.orange)),
              hintStyle: TextStyle(color: Colors.blue),
            ),
            validator: validatePassword,
          ),
          SizedBox(height: 30.0),
          TextFormField(
            controller: firstName,
            cursorColor: Colors.blue,
            style: TextStyle(color: Colors.blue),
            decoration: InputDecoration(
              hintText: 'First Name',
              border: UnderlineInputBorder(
                  borderSide: BorderSide(color: Colors.orange)),
              hintStyle: TextStyle(color: Colors.blue),
            ),
            validator: validateFirstName,
          ),
          SizedBox(height: 30.0),
          TextFormField(
            controller: lastName,
            cursorColor: Colors.blue,
            style: TextStyle(color: Colors.blue),
            decoration: InputDecoration(
              hintText: 'Last Name',
              border: UnderlineInputBorder(
                  borderSide: BorderSide(color: Colors.orange)),
              hintStyle: TextStyle(color: Colors.blue),
            ),
            validator: validateLastName,
          ),
          SizedBox(height: 30.0),
          TextFormField(
            controller: email,
            cursorColor: Colors.blue,
            style: TextStyle(color: Colors.blue),
            decoration: InputDecoration(
              hintText: 'Email',
              border: UnderlineInputBorder(
                  borderSide: BorderSide(color: Colors.orange)),
              hintStyle: TextStyle(color: Colors.blue),
            ),
            validator: validateEmail,
          ),
          SizedBox(height: 30.0),
          TextFormField(
            controller: phone,
            cursorColor: Colors.blue,
            style: TextStyle(color: Colors.blue),
            decoration: InputDecoration(
              hintText: 'Phone',
              border: UnderlineInputBorder(
                  borderSide: BorderSide(color: Colors.orange)),
              hintStyle: TextStyle(color: Colors.blue),
            ),
            validator: validatePhone,
          ),
          SizedBox(height: 30.0),
          Container(
            width: double.infinity,
            padding: EdgeInsets.fromLTRB(2.0, 0.0, 0.0, 0.0),
            child: Text(
              'Gender',
              style: TextStyle(fontSize: 16.0, color: Colors.blue),
            ),
          ),
          RadioListTile<String>(
            activeColor: Colors.orange,
            title: const Text('Male'),
            value: 'M',
            groupValue: _gender,
            onChanged: (String value) {
              setState(() {
                _gender = value;
              });
            },
          ),
          RadioListTile<String>(
            activeColor: Colors.orange,
            title: const Text('Female'),
            value: 'F',
            groupValue: _gender,
            onChanged: (String value) {
              setState(() {
                _gender = value;
              });
            },
          ),
          SizedBox(height: 30.0),
          Text(
            _message,
            style: TextStyle(
              color: Colors.blue,
            ),
          )
        ],
      ),
    );
  }

  Container headerSection() {
    return Container(
      margin: EdgeInsets.only(top: 50.0),
      padding: EdgeInsets.symmetric(horizontal: 20.0, vertical: 30.0),
      child: Text("Register",
          style: TextStyle(
              color: Colors.orange,
              fontSize: 40.0,
              fontWeight: FontWeight.bold)),
    );
  }

  Form formSection() {
    return Form(
      key: _formKey,
      autovalidate: _autoValidate,
      child: Column(
        children: <Widget>[
          textSection(),
          buttonRegisterSection(),
        ],
      ),
    );
  }

  signUp() async {
    setState(() {
      _isLoading = true;
    });
    SignInUser signInUser = new SignInUser(firstName.text, lastName.text,
        username.text, password.text, email.text, phone.text, _gender);
    print(signInUser.toJson());
    Response response = await createCustomer(signInUser);
    if (response.statusCode == 200) {
      setState(() {
        _isLoading = false;
        _message = "";
      });
      Duration duration = new Duration(seconds: 5);
      _scaffoldKey.currentState.showSnackBar(new SnackBar(
          content: Text("Account Created"),
          backgroundColor: Colors.blue,
          duration: duration));
    } else {
      setState(() {
        _isLoading = false;
        _message = response.body;
      });
    }
  }
}
