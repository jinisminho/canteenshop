import 'dart:convert';
import 'dart:io';

import 'package:customerappswd/api/order_api.dart';
import 'package:customerappswd/models/order.dart';
import 'package:customerappswd/screens/order_detail.dart';
import 'package:customerappswd/util/FormatString.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_money_formatter/flutter_money_formatter.dart';

class OrderPage extends StatefulWidget {
  @override
  _OrderPageState createState() => _OrderPageState();
}

class _OrderPageState extends State<OrderPage> {
  List<CustomerOrder> orderList = new List<CustomerOrder>();

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    getOrderHistory();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text("Orders History"),
        ),
        body: orderList.length == 0 ? getMessage() : ListView.builder(
            itemCount: orderList.length,
            itemBuilder: (context, index) {
              return GestureDetector(
                  onTap: () {
                    Navigator.push(context, MaterialPageRoute(builder: (context) {
                      return OrderDetailPage(order: orderList[index]);
                    }));
                  },
                  child: getCard(index));
            }));
  }


  Widget getMessage(){
    return Container(
      alignment: Alignment.center,
      child: Text("There's no order", style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold), ),
    );
  }

  void getOrderHistory() async {
    var result = await getOrders();
    var jsonData = json.decode(result);
    print(jsonData);

    List<CustomerOrder> orders = [];

    for (var o in jsonData) {
      CustomerOrder order = CustomerOrder.fromJson(o);
      setState(() {
        orderList.add(order);
      });
    }

  }

  Widget getCard(index) {
    return Card(
      child: Padding(
        padding: const EdgeInsets.all(8.0),
        child: Column(
          children: [
            ListTile(
              leading: Icon(Icons.shopping_cart),
              title: Text("No." + orderList[index].id.toString()),
              subtitle: Text(
                FlutterMoneyFormatter(amount: orderList[index].totalPrice)
                        .output
                        .withoutFractionDigits +
                    " VND",
                style: TextStyle(color: Colors.black.withOpacity(0.6)),
              ),
            ),
            Divider(),
            Row(
              children: [
                Padding(
                  padding: const EdgeInsets.only(left: 72),
                  child: Text(
                    Formatter.formatDate(orderList[index].createAt),
                    style: TextStyle(color: Colors.black.withOpacity(0.6)),
                  ),
                ),
                Padding(
                  padding: const EdgeInsets.only(left:100),
                  child: Text(
                    orderList[index].status.status,
                    style: TextStyle(color: Colors.black.withOpacity(0.6)),
                  ),
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }

}
