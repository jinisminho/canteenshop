import 'dart:convert';

import 'package:customerappswd/components/app_bar.dart';
import 'package:customerappswd/components/side_bar_menu.dart';
import 'package:customerappswd/models/profile.dart';
import 'package:customerappswd/util/CustomerValidator.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

import '../api/customer_api.dart';

class AccountPage extends StatefulWidget {
  @override
  _AccountPageState createState() => _AccountPageState();
}

class _AccountPageState extends State<AccountPage> {
  Profile profile = new Profile();
  String _message = "";
  TextEditingController firstnameController = new TextEditingController();
  TextEditingController lastnameController = new TextEditingController();
  TextEditingController emailController = new TextEditingController();
  TextEditingController phoneController = new TextEditingController();
  TextEditingController genderController = new TextEditingController();
  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(),
      body: generateProfileList(),
    );
  }

  void getProfile() async {
    SharedPreferences sharedPreferences = await SharedPreferences.getInstance();
    if (sharedPreferences.get("accessToken") != null) {
      var result =
          await getCustomerProfile(int.parse(sharedPreferences.get("userId")));
      var profileMap = json.decode(result);
      setState(() {
        profile = Profile.fromJson(profileMap);
      });
      genderController.text = profile.gender == "F" ? "Female" : "Male";
      firstnameController.text = profile.firstName;
      lastnameController.text = profile.lastName;
      emailController.text = profile.email;
      phoneController.text = profile.phone;
    }
  }

  void updateProfile() async {
    SharedPreferences sharedPreferences = await SharedPreferences.getInstance();
    if (sharedPreferences.get("accessToken") != null) {
      var response = await updateCustomerProfile(
          int.parse(sharedPreferences.get("userId")), profile);
      if (response.statusCode == 200) {
          _message = "Updated";
      } else {
        setState(() {
          _message = "Cannot update profile";
        });
      }
    }
  }

  @override
  void initState() {
    super.initState();
    getProfile();
  }

  Widget getUpdateButton() {
    return RaisedButton(
      color: Colors.orange,
      onPressed: () {
        if (_formKey.currentState.validate()) {
          profile.lastName = lastnameController.text;
          profile.firstName = firstnameController.text;
          profile.phone = phoneController.text;
          updateProfile();
        }
      },
      child: Text(
        'Update',
        style: TextStyle(color: Colors.white, fontSize: 20),
      ),
    );
  }

  Widget generateProfileList(){
    return ListView(
      children: <Widget>[
        Container(
            margin: EdgeInsets.all(16),
            child: Form(
              key: _formKey,
              child: Column(
                children: <Widget>[
                  TextFormField(
                    validator: validateFirstName,
                    controller: firstnameController,
                    decoration: InputDecoration(
                      labelText: "First Name",
                      border: UnderlineInputBorder(
                          borderSide: BorderSide(color: Colors.orange)),
                      prefixIcon: Icon(Icons.person),
                    ),
                  ),
                  SizedBox(
                    height: 30,
                  ),
                  TextFormField(
                    validator: validateLastName,
                    controller: lastnameController,
                    decoration: InputDecoration(
                      labelText: "Last Name",
                      border: UnderlineInputBorder(
                          borderSide: BorderSide(color: Colors.orange)),
                      prefixIcon: Icon(Icons.person),
                    ),
                  ),
                  SizedBox(
                    height: 30,
                  ),
                  TextFormField(
                    validator: validatePhone,
                    controller: phoneController,
                    decoration: InputDecoration(
                      labelText: "Phone",
                      border: UnderlineInputBorder(
                          borderSide: BorderSide(color: Colors.orange)),
                      prefixIcon: Icon(Icons.phone_android),
                    ),
                  ),
                  SizedBox(
                    height: 30,
                  ),
                  TextFormField(
                    readOnly: true,
                    controller: emailController,
                    decoration: InputDecoration(
                      labelText: "Email",
                      border: UnderlineInputBorder(
                          borderSide: BorderSide(color: Colors.orange)),
                      prefixIcon: Icon(Icons.email),
                    ),
                  ),
                  SizedBox(
                    height: 30,
                  ),
                  TextFormField(
                    readOnly: true,
                    controller: genderController,
                    decoration: InputDecoration(
                      labelText: "Gender",
                      border: UnderlineInputBorder(
                          borderSide: BorderSide(color: Colors.orange)),
                      prefixIcon: Icon(Icons.people),
                    ),
                  ),
                  SizedBox(
                    height: 30,
                  ),
                  Text(
                    _message,
                    style: TextStyle(
                      color: Colors.blue,
                    ),
                  )
                ],
              ),
            )),
        Container(
          width: MediaQuery.of(context).size.width,
          height: 40.0,
          padding: EdgeInsets.symmetric(horizontal: 15.0),
          margin: EdgeInsets.only(top: 15.0),
          child: getUpdateButton(),
        )
      ],
    );
  }
}
