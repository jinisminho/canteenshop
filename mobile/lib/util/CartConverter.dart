import 'dart:convert';

import 'package:customerappswd/models/cart.dart';
import 'package:customerappswd/models/newOrder.dart';

class CartConverter {
  static String toJson(Cart cartMap) {
    List cartList = [];
    cartMap.cart.forEach((key, value) {
      cartList.add(CartList(id: key, cartItem: value));
    });
    var jsonData = jsonEncode(cartList);
    return jsonData;
  }

  static Cart fromJson(String jsonData) {
    Cart cartMap = new Cart();
    var cartData = jsonDecode(jsonData);
    List<CartList> cartList = new List<CartList>();
    for (var item in cartData) {
      cartList.add(CartList.fromJson(item));
    }
    cartList.forEach((e) {
      CartItem cartItem = new CartItem(
          productID: e.cartItem.productID,
          price: e.cartItem.price,
          productName: e.cartItem.productName,
          quantity: e.cartItem.quantity);
      cartMap.cart[e.id] = cartItem;
    });
    return cartMap;
  }

  static List<OrderDetail> convertCartToNewOrder(Cart cart){
    List<OrderDetail> orderList = [];
    cart.cart.forEach((key, value) {
      orderList.add(OrderDetail(product: int.parse(key), quantity: value.quantity));
    });
    return orderList;
  }
}
