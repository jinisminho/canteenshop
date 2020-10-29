
import 'package:json_annotation/json_annotation.dart';

part 'order.g.dart';

@JsonSerializable(explicitToJson: true)
class OrderDetail{
  int id;
  int quantity;
  ProductOrder product;

  OrderDetail({this.id, this.quantity, this.product});

  factory OrderDetail.fromJson(Map<String, dynamic> json) => _$OrderDetailFromJson(json);
  Map<String, dynamic> toJson() => _$OrderDetailToJson(this);
}

@JsonSerializable(explicitToJson: true)
class ProductOrder{
  int id;
  String name;
  double price;

  ProductOrder({this.id, this.name, this.price});

  factory ProductOrder.fromJson(Map<String, dynamic> json) => _$ProductOrderFromJson(json);
  Map<String, dynamic> toJson() => _$ProductOrderToJson(this);

}

@JsonSerializable(explicitToJson: true)
class CustomerOrder{
  int id;
  String location;
  String note;
  double totalPrice;
  List<int> createAt;
  List<OrderDetail> orderDetails;
  Status status;

  CustomerOrder({this.id, this.location, this.note, this.totalPrice, this.createAt, this.orderDetails, this.status});

  factory CustomerOrder.fromJson(Map<String, dynamic> json) => _$CustomerOrderFromJson(json);
  Map<String, dynamic> toJson() => _$CustomerOrderToJson(this);
}

@JsonSerializable(explicitToJson: true)
class CustomerOrders{
  List<CustomerOrder> customerOrders;

  CustomerOrders({this.customerOrders});

  factory CustomerOrders.fromJson(Map<String, dynamic> json) => _$CustomerOrdersFromJson(json);
  Map<String, dynamic> toJson() => _$CustomerOrdersToJson(this);
}

@JsonSerializable(explicitToJson: true)
class Status{
  int id;
  String status;

  Status({this.id,this.status});

  factory Status.fromJson(Map<String, dynamic> json) => _$StatusFromJson(json);
  Map<String, dynamic> toJson() => _$StatusToJson(this);

}