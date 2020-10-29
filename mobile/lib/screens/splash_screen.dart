import 'package:customerappswd/screens/home_page.dart';
import 'package:customerappswd/screens/login.dart';
import 'package:customerappswd/screens/product_list.dart';
import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';


class SplashScreen extends StatefulWidget {
  @override
  _SplashScreenState createState() => _SplashScreenState();
}

class _SplashScreenState extends State<SplashScreen> {

  @override
  void initState() {
    super.initState();
    navigatePage();
  }

  void navigatePage() async {
    SharedPreferences sharedPreferences = await SharedPreferences.getInstance();
    var jwt = sharedPreferences.get("accessToken");
    if(jwt == null){
      Navigator.of(context)
          .pushReplacement(MaterialPageRoute(builder: (_) => LoginPage()));
    }else{
      Navigator.of(context)
          .pushReplacement(MaterialPageRoute(builder: (_) => HomePage()));
    }
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      color: Colors.white,
    );
  }
}
