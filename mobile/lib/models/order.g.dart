// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'order.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

OrderDetail _$OrderDetailFromJson(Map<String, dynamic> json) {
  return OrderDetail(
    id: json['id'] as int,
    quantity: json['quantity'] as int,
    product: json['product'] == null
        ? null
        : ProductOrder.fromJson(json['product'] as Map<String, dynamic>),
  );
}

Map<String, dynamic> _$OrderDetailToJson(OrderDetail instance) =>
    <String, dynamic>{
      'id': instance.id,
      'quantity': instance.quantity,
      'product': instance.product?.toJson(),
    };

ProductOrder _$ProductOrderFromJson(Map<String, dynamic> json) {
  return ProductOrder(
    id: json['id'] as int,
    name: json['name'] as String,
    price: (json['price'] as num)?.toDouble(),
  );
}

Map<String, dynamic> _$ProductOrderToJson(ProductOrder instance) =>
    <String, dynamic>{
      'id': instance.id,
      'name': instance.name,
      'price': instance.price,
    };

CustomerOrder _$CustomerOrderFromJson(Map<String, dynamic> json) {
  return CustomerOrder(
    id: json['id'] as int,
    location: json['location'] as String,
    note: json['note'] as String,
    totalPrice: (json['totalPrice'] as num)?.toDouble(),
    createAt: (json['createAt'] as List)?.map((e) => e as int)?.toList(),
    orderDetails: (json['orderDetails'] as List)
        ?.map((e) =>
            e == null ? null : OrderDetail.fromJson(e as Map<String, dynamic>))
        ?.toList(),
    status: json['status'] == null
        ? null
        : Status.fromJson(json['status'] as Map<String, dynamic>),
  );
}

Map<String, dynamic> _$CustomerOrderToJson(CustomerOrder instance) =>
    <String, dynamic>{
      'id': instance.id,
      'location': instance.location,
      'note': instance.note,
      'totalPrice': instance.totalPrice,
      'createAt': instance.createAt,
      'orderDetails': instance.orderDetails?.map((e) => e?.toJson())?.toList(),
      'status': instance.status?.toJson(),
    };

CustomerOrders _$CustomerOrdersFromJson(Map<String, dynamic> json) {
  return CustomerOrders(
    customerOrders: (json['customerOrders'] as List)
        ?.map((e) => e == null
            ? null
            : CustomerOrder.fromJson(e as Map<String, dynamic>))
        ?.toList(),
  );
}

Map<String, dynamic> _$CustomerOrdersToJson(CustomerOrders instance) =>
    <String, dynamic>{
      'customerOrders':
          instance.customerOrders?.map((e) => e?.toJson())?.toList(),
    };

Status _$StatusFromJson(Map<String, dynamic> json) {
  return Status(
    id: json['id'] as int,
    status: json['status'] as String,
  );
}

Map<String, dynamic> _$StatusToJson(Status instance) => <String, dynamic>{
      'id': instance.id,
      'status': instance.status,
    };
