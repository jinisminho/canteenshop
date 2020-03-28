import 'dart:convert';

import 'package:customerappswd/api/login_api.dart';
import 'package:customerappswd/screens/sign_up.dart';
import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:shared_preferences/shared_preferences.dart';

import '../main.dart';

class LoginPage extends StatefulWidget {
  @override
  _LoginPageState createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  final TextEditingController usernameController = new TextEditingController();
  final TextEditingController passwordController = new TextEditingController();
  String message = "";
  bool _isLoading = false;
  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();
  bool _autoValidate = false;

  @override
  Widget build(BuildContext context) {
    SystemChrome.setSystemUIOverlayStyle(SystemUiOverlayStyle.light
        .copyWith(statusBarColor: Colors.transparent));
    return Scaffold(
      body: Container(
        decoration: BoxDecoration(color: Colors.orange),
        child: _isLoading
            ? Center(child: CircularProgressIndicator())
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

  signIn(String username, password) async {
    if (_formKey.currentState.validate()) {
      SharedPreferences sharedPreferences =
          await SharedPreferences.getInstance();
      var response = await checkLogin(username, password);
      var jsonResponse;
      if (response.statusCode == 200) {
        jsonResponse = json.decode(response.body);
        if (jsonResponse != null) {
          setState(() {
            _isLoading = false;
          });
          print(jsonResponse);
          sharedPreferences.setString(
              "accessToken", jsonResponse['accessToken']);
          sharedPreferences.setString("username", username);
          sharedPreferences.setString(
              "userId", jsonResponse['userId'].toString());
          Navigator.of(context).pushAndRemoveUntil(
              MaterialPageRoute(builder: (BuildContext context) => MainPage()),
              (Route<dynamic> route) => false);
        }
      } else {
        setState(() {
          _isLoading = false;
          message = "Username or password is incorrect";
        });
        print(response.body);
      }
    } else {
      setState(() {
        _autoValidate = true;
        _isLoading = false;
        print("invalid");
      });
    }
  }

  Container buttonLoginSection() {
    return Container(
      width: MediaQuery.of(context).size.width,
      height: 40.0,
      padding: EdgeInsets.symmetric(horizontal: 15.0),
      margin: EdgeInsets.only(top: 15.0),
      child: RaisedButton(
        onPressed: () {
          setState(() {
            _isLoading = true;
          });
          signIn(usernameController.text, passwordController.text);
        },
        elevation: 0.0,
        color: Colors.white,
        child: Text("Sign In", style: TextStyle(color: Colors.orange)),
        shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(5.0)),
      ),
    );
  }

  Container textSection() {
    return Container(
      padding: EdgeInsets.symmetric(horizontal: 15.0, vertical: 20.0),
      child: Column(
        children: <Widget>[
          TextFormField(
            controller: usernameController,
            cursorColor: Colors.white,
            style: TextStyle(color: Colors.white70),
            decoration: InputDecoration(
              icon: Icon(Icons.people, color: Colors.white70),
              hintText: "Username",
              border: UnderlineInputBorder(
                  borderSide: BorderSide(color: Colors.white70)),
              hintStyle: TextStyle(color: Colors.white70),
            ),
            validator: (String value) {
              if (value == "") {
                return "Username is required";
              }
              if (value.length < 6 || value.length > 50) {
                return "Username from 6 characters";
              }
              return null;
            },
          ),
          SizedBox(height: 30.0),
          TextFormField(
            controller: passwordController,
            cursorColor: Colors.white,
            obscureText: true,
            style: TextStyle(color: Colors.white70),
            decoration: InputDecoration(
              icon: Icon(Icons.lock, color: Colors.white70),
              hintText: "Password",
              border: UnderlineInputBorder(
                  borderSide: BorderSide(color: Colors.white70)),
              hintStyle: TextStyle(color: Colors.white70),
            ),
            validator: (String value) {
              if (value == "") {
                return "Password is required";
              }
              if (value.length < 3 || value.length > 100) {
                return "Password from 6 characters";
              }
              return null;
            },
          ),
          SizedBox(height: 30.0),
          Text(
            message,
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
      child: Text("CShop",
          style: TextStyle(
              color: Colors.white70,
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
          buttonLoginSection(),
        ],
      ),
    );
  }

  Widget registerLink() {
    return Center(
      child: RichText(
          text: TextSpan(
              text: 'Register Here',
              style: TextStyle(
                color: Colors.blue,
              ),
              recognizer: new TapGestureRecognizer()
                ..onTap = () => Navigator.of(context).push(MaterialPageRoute(
                    builder: (BuildContext context) => RegisterPage())))),
    );
  }

}
