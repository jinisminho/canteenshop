import 'dart:convert';

import 'package:customerappswd/models/signInUser.dart';
import 'package:http/http.dart' as http;

const String customerURL = 'http://10.0.2.2:8057/customer';

Future<dynamic> createCustomer(SignInUser customer) async {
  var response = await http.post(
    customerURL + '/create',
    headers: <String, String>{
      'Content-Type': 'application/json; charset=UTF-8',
    },
    body: jsonEncode(customer),
  );
  return response;
}
