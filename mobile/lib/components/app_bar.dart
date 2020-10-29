import 'package:customerappswd/components/app_base_color.dart';
import 'package:customerappswd/screens/shopping_cart.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';



Widget myAppBar(context){
  return AppBar(
    title: Text("C Shop", style: TextStyle(color: Colors.white)),
    backgroundColor: MAIN_ORANGE,
    actions: <Widget>[
      IconButton(
        icon: Icon(Icons.shopping_cart),
        tooltip: 'Open shopping cart',
        onPressed: () {
          Navigator.push(context, MaterialPageRoute(builder: (context) {
            return CartPage();
          }));
        },
      ),
    ],
  );
}
