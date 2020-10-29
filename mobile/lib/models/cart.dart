import 'dart:collection';
import 'package:json_annotation/json_annotation.dart';

part 'cart.g.dart';

class Cart {
  HashMap<String, CartItem> cart = new HashMap();


  void addToCart(CartItem item){
    if(this.cart.containsKey(item.productID)){
      int quantity = this.cart[item.productID].quantity + 1;
      item.quantity = quantity;
    }else{
      item.quantity = 1;
    }
    this.cart[item.productID.toString()] = item;
  }

  void removeCart(String id){
    if(this.cart.containsKey(id)){
      this.cart.remove(id);
    }
  }

   double getTotal(){
    double total = 0;
    for(CartItem item in this.cart.values){
      total += item.quantity * item.price;
    }
    return total;
  }

  void update(String productID, int quantity){
    if(this.cart.containsKey(productID)){
      this.cart[productID].quantity = quantity;
    }
  }

}


@JsonSerializable(explicitToJson: true)
class CartItem{
  int productID;
  int quantity;
  double price;
  String productName;

  CartItem({this.productID, this.quantity, this.price, this.productName});

  factory CartItem.fromJson(Map<String, dynamic> json) => _$CartItemFromJson(json);
  Map<String, dynamic> toJson() => _$CartItemToJson(this);


}

@JsonSerializable(explicitToJson: true)
class CartList{
  String id;
  CartItem cartItem;

  CartList({this.id, this.cartItem});


  factory CartList.fromJson(Map<String, dynamic> json) => _$CartListFromJson(json);
  Map<String, dynamic> toJson() => _$CartListToJson(this);

}
