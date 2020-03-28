import 'package:json_annotation/json_annotation.dart';

part 'product.g.dart';

@JsonSerializable(explicitToJson: true)
class Product {
  bool available;
  String description;
  int id;
  String name;
  double price;
  String url_img;
  Category category;

  Product({this.available, this.description, this.id, this.name, this.price,
    this.url_img, this.category});

  factory Product.fromJson(Map<String, dynamic> json) => _$ProductFromJson(json);
  Map<String, dynamic> toJson() => _$ProductToJson(this);
}

@JsonSerializable()
class Category {
  int id;
  String name;

  Category({this.id, this.name});

  factory Category.fromJson(Map<String, dynamic>json)=> _$CategoryFromJson(json);

  Map<String, dynamic> toJson() => _$CategoryToJson(this);
}

@JsonSerializable(explicitToJson: true)
class Content {
  List<Product> content;

  Content({this.content});

  factory Content.fromJson(Map<String, dynamic>json)=> _$ContentFromJson(json);

  Map<String, dynamic> toJson() => _$ContentToJson(this);
}

@JsonSerializable()
class ProductList {
  List<Product> products;

  ProductList({this.products});

  factory ProductList.fromJson(List<dynamic> json){
    return ProductList(
        products: json
            .map((e) => Product.fromJson(e as Map<String, dynamic>))
            .toList());
  }
}