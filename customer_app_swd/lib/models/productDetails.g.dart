// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'productDetails.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

ProductDetails _$ProductDetailsFromJson(Map<String, dynamic> json) {
  return ProductDetails(
    available: json['available'] as bool,
    description: json['description'] as String,
    id: json['id'] as int,
    name: json['name'] as String,
    price: (json['price'] as num)?.toDouble(),
    url_img: json['url_img'] as String,
    category: json['category'] == null
        ? null
        : Category.fromJson(json['category'] as Map<String, dynamic>),
  );
}

Map<String, dynamic> _$ProductDetailsToJson(ProductDetails instance) =>
    <String, dynamic>{
      'available': instance.available,
      'description': instance.description,
      'id': instance.id,
      'name': instance.name,
      'price': instance.price,
      'url_img': instance.url_img,
      'category': instance.category?.toJson(),
    };

Category _$CategoryFromJson(Map<String, dynamic> json) {
  return Category(
    id: json['id'] as int,
    name: json['name'] as String,
  );
}

Map<String, dynamic> _$CategoryToJson(Category instance) => <String, dynamic>{
      'id': instance.id,
      'name': instance.name,
    };
