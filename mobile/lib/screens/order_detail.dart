import 'package:customerappswd/models/order.dart';
import 'package:customerappswd/screens/cancel_order.dart';
import 'package:customerappswd/util/FormatString.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_money_formatter/flutter_money_formatter.dart';

class OrderDetailPage extends StatefulWidget {
  final CustomerOrder order;

  OrderDetailPage({this.order});

  @override
  _OrderDetailPageState createState() => _OrderDetailPageState();
}

class _OrderDetailPageState extends State<OrderDetailPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Order Detail"),
      ),
      body: getOrderDetails(),
      floatingActionButton: widget.order.status.status == 'Pending'
          ? FloatingActionButton.extended(
              icon: Icon(Icons.cancel),
              label: Text("Cancel"),
              onPressed: () {
                Navigator.push(context, MaterialPageRoute(builder: (context) {
                  return CancelOrderPage(
                    orderId: widget.order.id,
                  );
                }));
              })
          : null,
    );
  }

  Widget getOrderDetails() {
    var mediaSize = MediaQuery.of(context).size;
    return Padding(
        child: ListView(
          //crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            Center(
              child: Container(
                  width: mediaSize.width,
                  child: Text("No." + widget.order.id.toString()),
                  decoration: BoxDecoration()),
            ),
            SizedBox(height: 15.0),
            Text(Formatter.formatDate(widget.order.createAt),
                style: TextStyle(fontWeight: FontWeight.bold)),
            SizedBox(height: 15.0),
            Text(
                "Total: " +
                    FlutterMoneyFormatter(amount: widget.order.totalPrice)
                        .output
                        .withoutFractionDigits +
                    " VND",
                style: TextStyle(fontWeight: FontWeight.bold)),
            SizedBox(height: 15.0),
            Text("Status: " + widget.order.status.status,
                style: TextStyle(fontWeight: FontWeight.bold)),
            SizedBox(height: 20.0),
            Divider(),
            SizedBox(height: 20.0),
            Text("ITEMS: ", style: TextStyle(fontWeight: FontWeight.bold)),
            SizedBox(height: 20.0),
            Table(
              children: [
                TableRow(children: [
                  Text('Name'),
                  Text(
                    'Quantity',
                    textAlign: TextAlign.center,
                  ),
                  Text('Unit Price'),
                ]),
              ],
            ),
            SizedBox(
              height: 10,
            ),
            Container(child: _getDetails()),
          ],
        ),
        padding: const EdgeInsets.fromLTRB(21.0, 20.0, 15.0, 0));
  }

  _getDetails() {
    return ListView.builder(
      scrollDirection: Axis.vertical,
      shrinkWrap: true,
      itemCount: widget.order.orderDetails != null
          ? widget.order.orderDetails.length
          : 0,
      itemBuilder: (context, index) {
        return Table(
          border: TableBorder(
              horizontalInside: BorderSide(
                  width: 1, color: Colors.blue, style: BorderStyle.solid)),
          children: [
            TableRow(children: [
              Text(widget.order.orderDetails[index].product.name),
              Text(
                widget.order.orderDetails[index].quantity.toString(),
                textAlign: TextAlign.center,
              ),
              Text(new FlutterMoneyFormatter(
                          amount:
                              widget.order.orderDetails[index].product.price)
                      .output
                      .withoutFractionDigits +
                  " VND"),
            ]),
          ],
        );
      },
    );
  }

  Widget getCancelButton() {
    return Container(
      child: RaisedButton(
        onPressed: () {},
        child: Text(
          'Cancel',
          style: TextStyle(color: Colors.white, fontSize: 20),
        ),
      ),
    );
  }
}
