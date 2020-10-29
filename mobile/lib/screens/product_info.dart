import 'dart:convert';

import 'package:customerappswd/components/app_bar.dart';
import 'package:customerappswd/components/side_bar_menu.dart';
import 'package:customerappswd/models/cart.dart';
import 'package:customerappswd/util/CartConverter.dart';
import 'package:flutter/material.dart';
import 'package:flutter_money_formatter/flutter_money_formatter.dart';
import 'package:shared_preferences/shared_preferences.dart';

class ProductInfo extends StatefulWidget {
  final String productName;
  final String imgUrl;
  final String id;
  final double price;
  final String description;

  ProductInfo(
      {this.productName, this.imgUrl, this.id, this.price, this.description});

  @override
  State<StatefulWidget> createState() => _ProductInfoState();
}

class _ProductInfoState extends State<ProductInfo> {

  final GlobalKey<ScaffoldState> _scaffoldKey = new GlobalKey<ScaffoldState>();


  @override
  void initState() {
    super.initState();
//    getProductDetailsData(widget.id);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      key: _scaffoldKey,
      appBar: myAppBar(context),
      body: getProductDetails(),
      drawer: MyDrawer(),
    );
  }

  Widget getProductDetails() {
    var mediaSize = MediaQuery.of(context).size;
    var imgUrl = widget.imgUrl;
    return Padding(
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            Center(
              child: Container(
                  width: mediaSize.width,
                  height: mediaSize.height * (40 / 100),
                  child: Image.network(imgUrl),
                  decoration: BoxDecoration()),
            ),
            ListTile(
              trailing: Text(
                  new FlutterMoneyFormatter(amount: widget.price)
                          .output
                          .withoutFractionDigits +
                      " VND",
                  style: TextStyle(fontStyle: FontStyle.italic)),
            ),
            Text(widget.productName.toUpperCase(),
                style: TextStyle(fontWeight: FontWeight.bold)),
            SizedBox(height: 15.0),
            Text('Description: ',
                style: TextStyle(fontWeight: FontWeight.bold)),
            SizedBox(height: 10.0),
            Text(widget.description),
            SizedBox(height: 20.0),
            Container(
              width: MediaQuery.of(context).size.width,
              height: 40.0,
              padding: EdgeInsets.symmetric(horizontal: 15.0),
              margin: EdgeInsets.only(top: 15.0),
              child: getAddCartButton(),
            )
          ],
        ),
        padding: const EdgeInsets.fromLTRB(21.0, 20.0, 15.0, 0));
  }

  getAddCartButton() {
    return RaisedButton(
      color: Colors.orange,
      onPressed: () {
        addToCart();
      },
      child: Text(
        'Add To Cart',
        style: TextStyle(color: Colors.white, fontSize: 20),
      ),
    );
  }

  addToCart() async {
    Cart cart;
    SharedPreferences sharedPreferences = await SharedPreferences.getInstance();
    if (sharedPreferences.getString("cart") == null) {
      cart = new Cart();
    }else {
      cart = CartConverter.fromJson(sharedPreferences.getString("cart"));
    }
    CartItem cardItem = new CartItem(
        productID: int.parse(widget.id),
        price: widget.price,
        productName: widget.productName);
    cart.addToCart(cardItem);
    var cartJson = CartConverter.toJson(cart);
    sharedPreferences.setString("cart", cartJson);
    Duration duration = new Duration(seconds: 5);
    _scaffoldKey.currentState.showSnackBar(new SnackBar(
        content: Text("Added to cart"),
        //backgroundColor: Colors.black,
        duration: duration));
  }
}
