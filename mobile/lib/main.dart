import 'package:customerappswd/components/app_base_color.dart';
import 'package:customerappswd/screens/splash_screen.dart';
import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "C Shop",
      debugShowCheckedModeBanner: false,
      home: SplashScreen(),
      theme: ThemeData(primarySwatch: MAIN_ORANGE),
    );
  }
}
