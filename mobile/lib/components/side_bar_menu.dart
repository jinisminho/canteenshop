import 'package:customerappswd/components/app_base_color.dart';
import 'package:customerappswd/screens/account.dart';
import 'package:customerappswd/screens/home_page.dart';
import 'package:customerappswd/screens/login.dart';
import 'package:customerappswd/screens/order_history.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

class MyDrawer extends StatefulWidget {
  @override
  _MyDrawerState createState() => _MyDrawerState();
}

class _MyDrawerState extends State<MyDrawer> {
  SharedPreferences sharedPreferences;
  String _username = "";
  final TextStyle itemTextStyle = TextStyle(
    fontSize: 17,
  );

  @override
  void initState() {
    super.initState();
    getProfile();
  }

  @override
  Widget build(BuildContext context) {
    return Drawer(
      child: ListView(
        // Important: Remove any padding from the ListView.
        padding: EdgeInsets.zero,
        children: <Widget>[
          UserAccountsDrawerHeader(
            accountName: Text(
              _username,
              style: TextStyle(fontSize: 20),
            ),
            accountEmail: null,
          ),
          ListTile(
            title: Text(
              'Home',
              style: itemTextStyle,
            ),
            onTap: () {
              Navigator.of(context).pop();
              Navigator.push(context, MaterialPageRoute(builder: (context) {
                return HomePage();
              }));
            },
          ),
          ListTile(
            title: Text(
              'Orders History',
              style: itemTextStyle,
            ),
            onTap: () {
              Navigator.pop(context);
              Navigator.push(context, MaterialPageRoute(builder: (context) {
                return OrderPage();
              }));
            },
          ),
          ListTile(
            title: Text(
              'Profile',
              style: itemTextStyle,
            ),
            onTap: () {
              Navigator.of(context).pop();
              Navigator.push(context, MaterialPageRoute(builder: (context) {
                return AccountPage();
              }));
            },
          ),
          Divider(),
          ListTile(
            title: Text(
              'Logout',
              style: itemTextStyle,
            ),
            onTap: () {
              sharedPreferences.clear();
              sharedPreferences.commit();
              Navigator.of(context).pushAndRemoveUntil(
                  MaterialPageRoute(builder: (context) => LoginPage()),
                  (Route<dynamic> route) => false);
            },
          ),
        ],
      ),
    );
  }

  void getProfile() async {
    sharedPreferences = await SharedPreferences.getInstance();
    setState(() {
      _username = sharedPreferences.getString("username");
    });
  }
}
