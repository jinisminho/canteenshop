import 'package:customerappswd/components/app_bar.dart';
import 'package:customerappswd/components/side_bar_menu.dart';
import 'package:customerappswd/screens/product_list.dart';
import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

class HomePage extends StatefulWidget {
  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  SharedPreferences sharedPreferences;

  @override
  void initState() {
    super.initState();
    getSharedPreference();
  }

  void getSharedPreference() async{
    sharedPreferences = await SharedPreferences.getInstance();
  }
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: myAppBar(context),
      body: ProductListPage(),
      drawer: MyDrawer(),
    );
  }


}


