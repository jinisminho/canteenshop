// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'product.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

Product _$ProductFromJson(Map<String, dynamic> json) {
  return Product(
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

Map<String, dynamic> _$ProductToJson(Product instance) => <String, dynamic>{
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

Content _$ContentFromJson(Map<String, dynamic> json) {
  return Content(
    content: (json['content'] as List)
        ?.map((e) =>
            e == null ? null : Product.fromJson(e as Map<String, dynamic>))
        ?.toList(),
  );
}

Map<String, dynamic> _$ContentToJson(Content instance) => <String, dynamic>{
      'content': instance.content?.map((e) => e?.toJson())?.toList(),
    };

ProductList _$ProductListFromJson(Map<String, dynamic> json) {
  return ProductList(
    products: (json['products'] as List)
        ?.map((e) =>
            e == null ? null : Product.fromJson(e as Map<String, dynamic>))
        ?.toList(),
  );
}

Map<String, dynamic> _$ProductListToJson(ProductList instance) =>
    <String, dynamic>{
      'products': instance.products,
    };
