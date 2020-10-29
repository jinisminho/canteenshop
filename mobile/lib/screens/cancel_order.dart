import 'package:customerappswd/api/order_api.dart';
import 'package:customerappswd/models/cancel_reason.dart';
import 'package:customerappswd/screens/order_history.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart';

class CancelOrderPage extends StatefulWidget {
  final int orderId;

  CancelOrderPage({this.orderId});

  @override
  _CancelOrderPageState createState() => _CancelOrderPageState();
}

class _CancelOrderPageState extends State<CancelOrderPage> {
  TextEditingController reasonController = new TextEditingController();
  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();
  final GlobalKey<ScaffoldState> _scaffoldKey = new GlobalKey<ScaffoldState>();
  bool _autoValidate = false;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      key: _scaffoldKey,
        appBar: AppBar(
          title: Text("Cancel Order"),
        ),
        body: Container(
          child: _showForm(),
        ),
        floatingActionButton: FloatingActionButton.extended(
            label: Text("Confirm"),
            onPressed: () {
              if (_formKey.currentState.validate()) {
                _cancelOrder();
              } else {
                setState(() {
                  _autoValidate = true;
                });
              }
            }));
  }

  _showForm() {
    return Container(
      padding: EdgeInsets.all(20),
      child: Form(
        key: _formKey,
        autovalidate: _autoValidate,
        child: Column(
          children: [
            Text("Please let us know the reason"),
            TextFormField(
              //validator: validateLocation,
              maxLines: 8,
              controller: reasonController,
              decoration: InputDecoration(
                labelText: "Reason",
                border: UnderlineInputBorder(
                    borderSide: BorderSide(color: Colors.orange)),
              ),
            ),
          ],
        ),
      ),
    );
  }

  _cancelOrder() async {
    CancelReason cancelReason = new CancelReason(
        orderId: widget.orderId, reason: reasonController.text);
    Response response = await cancelOrder(cancelReason);
    if(response.statusCode == 200){
      Duration duration = new Duration(seconds: 20);
      _scaffoldKey.currentState.showSnackBar(new SnackBar(
          content: Text("Canceled order success"),
          backgroundColor: Colors.black,
          duration: duration));
      Navigator.of(context).pop();
      Navigator.of(context).pop();
      Navigator.of(context).pop();
      Navigator.push( context, MaterialPageRoute( builder: (context) => OrderPage()), ).then((value) => setState(() {}));
    }else{
      Duration duration = new Duration(seconds: 20);
      _scaffoldKey.currentState.showSnackBar(new SnackBar(
          content: Text("Canceled order failed"),
          backgroundColor: Colors.black,
          duration: duration));
    }
  }
}
