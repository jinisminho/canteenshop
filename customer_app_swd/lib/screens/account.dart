import 'package:flutter/material.dart';


class AccountPage extends StatefulWidget {
  @override
  _AccountPageState createState() => _AccountPageState();
}

class _AccountPageState extends State<AccountPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("C Shop", style: TextStyle(color: Colors.white)),
        backgroundColor: Colors.orange,
      ),
      body: Center(child: Text("Account Page")),
    );
  }
}
