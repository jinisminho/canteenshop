import 'dart:convert';

import 'package:customerappswd/api/product_api.dart';
import 'package:customerappswd/models/product.dart';
import 'package:customerappswd/screens/product_info.dart';
import 'package:flutter/material.dart';
import 'package:flutter_money_formatter/flutter_money_formatter.dart';
import 'package:shared_preferences/shared_preferences.dart';

class ProductListPage extends StatefulWidget {
  final String title;

  ProductListPage({Key key, this.title}) : super(key: key);

  @override
  State<StatefulWidget> createState() => _ProductListPageState();
}

class _ProductListPageState extends State<ProductListPage> {
  Content productList = Content();
  Category category = Category();

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    getProductData();
  }

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return ListView.builder(
        itemCount: (productList == null ||
                productList.content == null ||
                productList.content.length == 0)
            ? 0
            : productList.content.length,
        itemBuilder: (context, index) {
          return GestureDetector(
            onTap: () {
              Navigator.push(context, MaterialPageRoute(builder: (context) {
                return ProductInfo(
                  id: productList.content[index].id.toString(),
                  imgUrl: productList.content[index].url_img,
                  productName: productList.content[index].name,
                  description: productList.content[index].description,
                  price: productList.content[index].price,
                );
              }));
            },
            onLongPress: () {
              return showDialog(
                context: context,
                builder: (_) => AlertDialog(
                  title: Text(productList.content[index].name),
                  content: Text('Content'),
                ),
                barrierDismissible: true,
              );
            },
            child: Card(
              child: Padding(
                padding: const EdgeInsets.all(8.0),
                child: ListTile(
                  title: Text(productList.content[index].name),
                  trailing: Text(
//                        productList.content[index].price.toString() + ' VND'),
                      new FlutterMoneyFormatter(
                                  amount: productList.content[index].price)
                              .output
                              .withoutFractionDigits +
                          " VND"),
                  subtitle: Text(productList.content[index].category.name),
                ),
              ),
            ),
          );
        });
  }

  void getProductData() async {
    SharedPreferences sharedPreferences = await SharedPreferences.getInstance();
    if (sharedPreferences.get("accessToken") != null) {
      var result = await ProductAPI().getProductList();
      var productMap = json.decode(result);
      setState(() {
        productList = Content.fromJson(productMap);
      });
    }
  }

  void getCategoryData() async {
    var result = await ProductAPI().getProductList();
    var productMap = json.decode(result);
    setState(() {
      productList = Content.fromJson(productMap);
    });
  }
}
