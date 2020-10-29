import 'dart:convert';

import 'package:customerappswd/config/host_config.dart';
import 'package:http/http.dart' as http;

const String LOGIN_URL = HOST_URL + "/auth/login";

Future<dynamic> checkLogin (String username, password) async{
  var loginUser = {"username": username, "password": password};
  var response = await http.post(
    LOGIN_URL,
    headers: <String, String>{
      'Content-Type': 'application/json; charset=UTF-8',
    },
    body: json.encode(loginUser),
  );
  return response;
}