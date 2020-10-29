

import 'package:json_annotation/json_annotation.dart';

part 'newOrder.g.dart';

@JsonSerializable(explicitToJson: true)
class NewOrder{
  int customerId;
  String location;
  String note;
  List<OrderDetail> orderDetails;

  NewOrder({this.customerId, this.location, this.note, this.orderDetails});

  factory NewOrder.fromJson(Map<String, dynamic> json) => _$NewOrderFromJson(json);
  Map<String, dynamic> toJson() => _$NewOrderToJson(this);

}

@JsonSerializable(explicitToJson: true)
class OrderDetail{
  int product;
  int quantity;

  OrderDetail({this.product, this.quantity});

  factory OrderDetail.fromJson(Map<String, dynamic> json) => _$OrderDetailFromJson(json);
  Map<String, dynamic> toJson() => _$OrderDetailToJson(this);
}