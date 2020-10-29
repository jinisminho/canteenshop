import 'dart:convert';
import 'dart:io';

import 'package:customerappswd/config/host_config.dart';
import 'package:customerappswd/models/profile.dart';
import 'package:customerappswd/models/signInUser.dart';
import 'package:http/http.dart' as http;
import 'package:shared_preferences/shared_preferences.dart';

import 'network.dart';

const String CUSTOMER_URL =  HOST_URL + '/customer';

Future<dynamic> createCustomer(SignInUser customer) async {
  var response = await http.post(
    CUSTOMER_URL + '/create',
    headers: <String, String>{
      'Content-Type': 'application/json; charset=UTF-8',
    },
    body: jsonEncode(customer),
  );
  return response;
}

Future<dynamic> getCustomerProfile(int id) async {
  SharedPreferences sharedPreferences = await SharedPreferences.getInstance();
  var jwt =  sharedPreferences.get("accessToken");
  String getCustomerProfileUrl= CUSTOMER_URL+"/"+id.toString();
  Network network = Network(getCustomerProfileUrl, "Bearer $jwt");
  var profile = await network.getData();
  return profile;
}
Future<dynamic> updateCustomerProfile(int id, Profile profile) async {
  SharedPreferences sharedPreferences = await SharedPreferences.getInstance();
  var jwt =  sharedPreferences.get("accessToken");
  var response = await http.put(
      CUSTOMER_URL+"/"+id.toString()+"/update",
    headers: {HttpHeaders.authorizationHeader: "Bearer $jwt", 'Content-Type': 'application/json; charset=UTF-8'},
    body: json.encode(profile)
  );
  return response;
}
