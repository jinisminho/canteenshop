

import 'package:json_annotation/json_annotation.dart';
part 'cancel_reason.g.dart';


@JsonSerializable(explicitToJson: true)
class CancelReason{
  int orderId;
  String reason;

  CancelReason({this.orderId, this.reason});


  factory CancelReason.fromJson(Map<String, dynamic> json) => _$CancelReasonFromJson(json);
  Map<String, dynamic> toJson() => _$CancelReasonToJson(this);

}