import 'package:json_annotation/json_annotation.dart';

part 'productDetails.g.dart';

@JsonSerializable(explicitToJson: true)
class ProductDetails {
  bool available;
  String description;
  int id;
  String name;
  double price;
  String url_img;
  Category category;

  ProductDetails(
      {this.available,
      this.description,
      this.id,
      this.name,
      this.price,
      this.url_img,
      this.category});

  factory ProductDetails.fromJson(Map<String, dynamic> json) =>
      _$ProductDetailsFromJson(json);

  Map<String, dynamic> toJson() => _$ProductDetailsToJson(this);
}

@JsonSerializable()
class Category {
  int id;
  String name;

  Category({this.id, this.name});

  factory Category.fromJson(Map<String, dynamic> json) =>
      _$CategoryFromJson(json);

  Map<String, dynamic> toJson() => _$CategoryToJson(this);
}
