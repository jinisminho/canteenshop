import 'dart:io';

import 'package:http/http.dart';

class Network {
  final String url;
  final String jwtToken;

  Network(this.url, this.jwtToken);

  Future getData() async {
    print('Calling uri: $url');

    Response response = await get(
      url,
      headers: {HttpHeaders.authorizationHeader: jwtToken},
    );

    if (response.statusCode == 200) {
      return response.body;
    } else {
      throw Exception("ERROR: " + response.statusCode.toString());
    }
  }
}
