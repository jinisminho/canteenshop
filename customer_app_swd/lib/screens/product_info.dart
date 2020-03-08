import 'package:flutter/material.dart';
import 'package:flutter_money_formatter/flutter_money_formatter.dart';

class ProductInfo extends StatefulWidget {
  final String productName;
  final String imgUrl;
  final String id;
  final double price;
  final String description;

  ProductInfo(
      {this.productName, this.imgUrl, this.id, this.price, this.description});

  @override
  State<StatefulWidget> createState() => _ProductInfoState();
}

class _ProductInfoState extends State<ProductInfo> {
//  ProductDetails productDetails = new ProductDetails();

  @override
  void initState() {
    super.initState();
    print(widget.imgUrl);
//    getProductDetailsData(widget.id);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.productName.toUpperCase()),
        backgroundColor: Colors.orange,
      ),
      body: getProductDetails(),
    );
  }

  Widget getProductDetails() {
    var mediaSize = MediaQuery.of(context).size;
    var imgUrl = widget.imgUrl;
    return Padding(
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            Center(
              child: Container(
                  width: mediaSize.width,
                  height: mediaSize.height * (40 / 100),
                  child: Image.network(imgUrl),
                  decoration: BoxDecoration()),
            ),
            ListTile(
              trailing: Text(
                  new FlutterMoneyFormatter(amount: widget.price)
                          .output
                          .withoutFractionDigits +
                      " VND",
                  style: TextStyle(fontStyle: FontStyle.italic)),
            ),
            Text('Description: ',
                style: TextStyle(fontWeight: FontWeight.bold)),
            SizedBox(height: 10.0),
            Text(widget.description),
          ],
        ),
        padding: const EdgeInsets.fromLTRB(21.0, 20.0, 15.0, 0));
  }
}
