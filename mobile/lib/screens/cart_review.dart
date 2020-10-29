import 'dart:convert';

import 'package:customerappswd/api/order_api.dart';
import 'package:customerappswd/models/cart.dart';
import 'package:customerappswd/models/newOrder.dart';
import 'package:customerappswd/screens/home_page.dart';
import 'package:customerappswd/util/CartConverter.dart';
import 'package:customerappswd/util/PlacedOrderValidator.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_money_formatter/flutter_money_formatter.dart';
import 'package:http/http.dart';
import 'package:shared_preferences/shared_preferences.dart';

class CartReviewPage extends StatefulWidget {
  @override
  _CartReviewPageState createState() => _CartReviewPageState();
}

class _CartReviewPageState extends State<CartReviewPage> {
  Cart cart;
  SharedPreferences _preferences;

  TextEditingController locationController = new TextEditingController();
  TextEditingController noteController = new TextEditingController();
  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();
  final GlobalKey<ScaffoldState> _scaffoldKey = new GlobalKey<ScaffoldState>();
  bool _autoValidate = false;

  @override
  void initState() {
    super.initState();
    getCart();
  }

  void getCart() async {
    _preferences = await SharedPreferences.getInstance();
    if (_preferences.containsKey("cart")) {
      String cartJson = _preferences.getString("cart");
      setState(() {
        cart = CartConverter.fromJson(cartJson);
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      key: _scaffoldKey,
      appBar: AppBar(
        title: Text("Order Information"),
      ),
      body: ListView(
        children: [
          _showCart(),
          _showForm(),
          _showTotal(),
        ],
      ),
      floatingActionButton: FloatingActionButton.extended(
        onPressed: () {
          if (_formKey.currentState.validate()) {
            _placeOrder();
          } else {
            setState(() {
              _autoValidate = true;
            });
          }
        },
        icon: Icon(Icons.shopping_cart_outlined),
        label: Text("Place Order"),
      ),
    );
  }

  Widget getCard(key) {
    return Card(
      child: Padding(
        padding: const EdgeInsets.all(8.0),
        child: ListTile(
          title: Text(cart.cart[key].productName),
          trailing: Text(new FlutterMoneyFormatter(amount: cart.cart[key].price)
                  .output
                  .withoutFractionDigits +
              " VND"),
          subtitle: Text("Quantity: " + cart.cart[key].quantity.toString()),
        ),
      ),
    );
  }

  _showCart() {
    return ListView.builder(
      scrollDirection: Axis.vertical,
      shrinkWrap: true,
      itemCount: cart.cart.length,
      itemBuilder: (context, index) {
        String key = cart.cart.keys.elementAt(index);
        return getCard(key);
      },
    );
  }

  _showTotal() {
    return Container(
        alignment: Alignment.centerLeft,
        padding: EdgeInsets.all(15),
        child: Text(
          "Total: " +
              FlutterMoneyFormatter(amount: cart.getTotal())
                  .output
                  .withoutFractionDigits +
              " VND",
          style: TextStyle(
              fontSize: 20, fontWeight: FontWeight.bold, color: Colors.orange),
        ));
  }

  _showForm() {
    return Container(
      padding: EdgeInsets.all(20),
      child: Form(
        key: _formKey,
        autovalidate: _autoValidate,
        child: Column(
          children: [
            Text("Please fill in the information below"),
            TextFormField(
              validator: validateLocation,
              controller: locationController,
              decoration: InputDecoration(
                labelText: "Location",
                border: UnderlineInputBorder(
                    borderSide: BorderSide(color: Colors.orange)),
              ),
            ),
            SizedBox(height: 30),
            TextFormField(
              validator: validateNote,
              maxLines: 8,
              controller: noteController,
              decoration: InputDecoration(
                labelText: "Note",
                border: UnderlineInputBorder(
                    borderSide: BorderSide(color: Colors.orange)),
              ),
            ),
          ],
        ),
      ),
    );
  }

  _placeOrder() async {
    int customerId = int.parse(_preferences.getString("userId"));
    List<OrderDetail> orderList = CartConverter.convertCartToNewOrder(cart);
    NewOrder newOrder = new NewOrder(
        customerId: customerId,
        location: locationController.text,
        note: noteController.text,
        orderDetails: orderList);
    print(jsonEncode(newOrder));
    Response response = await createOrder(newOrder);
    if (response.statusCode == 200) {
      _preferences.remove("cart");
      Duration duration = new Duration(seconds: 5);
      _scaffoldKey.currentState.showSnackBar(new SnackBar(
          content:
              Text("Placed order success. View Order History to check status"),
          backgroundColor: Colors.black,
          duration: duration));
      Future.delayed(Duration(seconds: 5), () {
        // 5s over, navigate to a new page
        Navigator.of(context).pushAndRemoveUntil(
            MaterialPageRoute(builder: (BuildContext context) => HomePage()),
            (Route<dynamic> route) => false);
      });
    } else {
      Duration duration = new Duration(seconds: 20);
      _scaffoldKey.currentState.showSnackBar(new SnackBar(
          content: Text("Place order failed"),
          backgroundColor: Colors.black,
          duration: duration));
    }
  }
}
