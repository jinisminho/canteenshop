import 'package:customerappswd/components/app_bar.dart';
import 'package:customerappswd/components/side_bar_menu.dart';
import 'package:customerappswd/models/cart.dart';
import 'package:customerappswd/screens/cart_review.dart';
import 'package:customerappswd/util/CartConverter.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_money_formatter/flutter_money_formatter.dart';
import 'package:shared_preferences/shared_preferences.dart';

class CartPage extends StatefulWidget {
  @override
  _CartPageState createState() => _CartPageState();
}

class _CartPageState extends State<CartPage> {
  Cart cart;
  SharedPreferences _preferences;

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
      appBar: AppBar(
        title: Text("My Cart"),
      ),
      body: (cart == null || cart.cart.isEmpty)
          ? Center(
              child: Container(
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Image(
                    image: AssetImage("assets/empty-cart.png"),
                    height: 200,
                    width: 200,
                  ),
                  Text(
                    "Your Cart is Empty",
                    style: TextStyle(
                      fontSize: 25,
                    ),
                  ),
                  SizedBox(height: 20.0),
                  Text(
                    "Look like you haven't add anything to your cart yet",
                    style: TextStyle(fontSize: 15),
                  ),
                ],
              ),
            ))
          : Center(
              child: Column(
              children: [
                _showCart(),
                Divider(),
                Text(
                  "Total: " +
                      FlutterMoneyFormatter(amount: cart.getTotal())
                          .output
                          .withoutFractionDigits +
                      " VND",
                  style: TextStyle(
                      fontSize: 20,
                      fontWeight: FontWeight.bold,
                      color: Colors.orange),
                ),
              ],
            )),
      floatingActionButton: (cart == null || cart.cart.isEmpty)
          ? null
          : FloatingActionButton.extended(
              onPressed: () {
                Navigator.push(context, MaterialPageRoute(builder: (context) {
                  return CartReviewPage();
                }));
              },
              icon: Icon(Icons.shopping_cart_outlined),
              label: Text("Buy"),
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

  getCard(key) {
    return Card(
      child: Padding(
        padding: const EdgeInsets.all(8.0),
        child: Row(
          children: [
            Expanded(
                child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  cart.cart[key].productName,
                  style: TextStyle(fontWeight: FontWeight.bold, fontSize: 15),
                ),
                SizedBox(
                  height: 10,
                ),
                Text(new FlutterMoneyFormatter(amount: cart.cart[key].price)
                        .output
                        .withoutFractionDigits +
                    " VND"),
                Row(
                  mainAxisAlignment: MainAxisAlignment.end,
                  children: [
                    RaisedButton(
                      textColor: Colors.black,
                      color: Colors.white54,
                      child: Icon(Icons.remove),
                      onPressed: () {
                        decreaseQuantity(key);
                      },
                      shape: new CircleBorder(),
                    ),
                    Text(cart.cart[key].quantity.toString()),
                    RaisedButton(
                      textColor: Colors.black,
                      color: Colors.white54,
                      child: Icon(Icons.add),
                      onPressed: () {
                        increaseQuantity(key);
                      },
                      shape: new CircleBorder(),
                    ),
                    IconButton(
                      icon: Icon(Icons.delete),
                      onPressed: () {
                        removeItem(key);
                      },
                      color: Colors.red,
                    )
                  ],
                )
              ],
            )),
          ],
        ),
      ),
    );
  }

  increaseQuantity(key) {
    setState(() {
      cart.update(key, cart.cart[key].quantity + 1);
    });
    var cartJson = CartConverter.toJson(cart);
    _preferences.setString("cart", cartJson);
  }

  decreaseQuantity(key) {
    var newQuantity = cart.cart[key].quantity - 1;
    if (newQuantity == 0) {
      setState(() {
        cart.cart.remove(key);
      });
    } else {
      setState(() {
        cart.update(key, cart.cart[key].quantity - 1);
      });
    }
    var cartJson = CartConverter.toJson(cart);
    _preferences.setString("cart", cartJson);
  }

  removeItem(key) {
    setState(() {
      cart.removeCart(key);
    });
    var cartJson = CartConverter.toJson(cart);
    _preferences.setString("cart", cartJson);
  }
}
