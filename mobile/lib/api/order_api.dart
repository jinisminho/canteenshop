import 'dart:convert';
import 'dart:io';

import 'package:customerappswd/config/host_config.dart';
import 'package:customerappswd/models/cancel_reason.dart';
import 'package:customerappswd/models/newOrder.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:http/http.dart' as http;

import 'network.dart';

const String ORDER_HISTORY = HOST_URL + '/order/';
const String CREATE_ORDER = HOST_URL + "/order/create";
const String CANCEL_ORDER = HOST_URL + "/order/cancel";

Future<dynamic> getOrders() async {
  SharedPreferences sharedPreferences = await SharedPreferences.getInstance();
  var jwt = sharedPreferences.get("accessToken");
  var customerID = sharedPreferences.get("userId");
  Network network =
      Network(ORDER_HISTORY + customerID + "/history", "Bearer $jwt");
  var result = await network.getData();
  return result;
}

Future<dynamic> createOrder(NewOrder newOrder) async {
  SharedPreferences sharedPreferences = await SharedPreferences.getInstance();
  var jwt = sharedPreferences.get("accessToken");
  var response = await http.post(
    CREATE_ORDER,
    headers: {HttpHeaders.authorizationHeader: "Bearer $jwt", 'Content-Type': 'application/json; charset=UTF-8'},
    body: jsonEncode(newOrder),
  );
  return response;
}



Future<dynamic> cancelOrder(CancelReason cancelReason) async {
  SharedPreferences sharedPreferences = await SharedPreferences.getInstance();
  var jwt = sharedPreferences.get("accessToken");
  var response = await http.post(
    CANCEL_ORDER,
    headers: {HttpHeaders.authorizationHeader: "Bearer $jwt", 'Content-Type': 'application/json; charset=UTF-8'},
    body: jsonEncode(cancelReason),
  );
  return response;
}
