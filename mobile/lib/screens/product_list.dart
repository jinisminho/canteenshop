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
    getProductData("");
  }

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return ListView.builder(
        itemCount: productList.content != null ? productList.content.length + 1 : 1,
        itemBuilder: (context, index) {
          return index == 0 ? _searchBar() :
            GestureDetector(
            onTap: () {
              Navigator.push(context, MaterialPageRoute(builder: (context) {
                return ProductInfo(
                  id: productList.content[index-1].id.toString(),
                  imgUrl: productList.content[index-1].url_img,
                  productName: productList.content[index-1].name,
                  description: productList.content[index-1].description,
                  price: productList.content[index-1].price,
                );
              }));
            },
            onLongPress: () {
              return showDialog(
                context: context,
                builder: (_) => AlertDialog(
                  title: Text(productList.content[index-1].name),
                  content: Text('Content'),
                ),
                barrierDismissible: true,
              );
            },
            child: getCard(index-1)
          );

        });
  }

  void getProductData(search) async {
    SharedPreferences sharedPreferences = await SharedPreferences.getInstance();
    if (sharedPreferences.get("accessToken") != null) {
      var result = await ProductAPI().getProductList(search);
      var productMap = json.decode(result);
      setState(() {
        productList = Content.fromJson(productMap);
      });
    }
  }


  Widget getCard(index){
    return Card(
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
    );
  }

   _searchBar(){
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: TextField(
         decoration: InputDecoration(
           hintText: "Search..."
         ),
        onSubmitted: (text){
           text = text.toLowerCase();
           getProductData(text);
        },
      ),
    );
   }


}
