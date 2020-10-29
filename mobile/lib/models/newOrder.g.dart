// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'newOrder.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

NewOrder _$NewOrderFromJson(Map<String, dynamic> json) {
  return NewOrder(
    customerId: json['customerId'] as int,
    location: json['location'] as String,
    note: json['note'] as String,
    orderDetails: (json['orderDetails'] as List)
        ?.map((e) =>
            e == null ? null : OrderDetail.fromJson(e as Map<String, dynamic>))
        ?.toList(),
  );
}

Map<String, dynamic> _$NewOrderToJson(NewOrder instance) => <String, dynamic>{
      'customerId': instance.customerId,
      'location': instance.location,
      'note': instance.note,
      'orderDetails': instance.orderDetails?.map((e) => e?.toJson())?.toList(),
    };

OrderDetail _$OrderDetailFromJson(Map<String, dynamic> json) {
  return OrderDetail(
    product: json['product'] as int,
    quantity: json['quantity'] as int,
  );
}

Map<String, dynamic> _$OrderDetailToJson(OrderDetail instance) =>
    <String, dynamic>{
      'product': instance.product,
      'quantity': instance.quantity,
    };
