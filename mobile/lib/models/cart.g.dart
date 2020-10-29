// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'cart.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

CartItem _$CartItemFromJson(Map<String, dynamic> json) {
  return CartItem(
    productID: json['productID'] as int,
    quantity: json['quantity'] as int,
    price: (json['price'] as num)?.toDouble(),
    productName: json['productName'] as String,
  );
}

Map<String, dynamic> _$CartItemToJson(CartItem instance) => <String, dynamic>{
      'productID': instance.productID,
      'quantity': instance.quantity,
      'price': instance.price,
      'productName': instance.productName,
    };

CartList _$CartListFromJson(Map<String, dynamic> json) {
  return CartList(
    id: json['id'] as String,
    cartItem: json['cartItem'] == null
        ? null
        : CartItem.fromJson(json['cartItem'] as Map<String, dynamic>),
  );
}

Map<String, dynamic> _$CartListToJson(CartList instance) => <String, dynamic>{
      'id': instance.id,
      'cartItem': instance.cartItem?.toJson(),
    };
