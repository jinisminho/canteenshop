// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'cancel_reason.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

CancelReason _$CancelReasonFromJson(Map<String, dynamic> json) {
  return CancelReason(
    orderId: json['orderId'] as int,
    reason: json['reason'] as String,
  );
}

Map<String, dynamic> _$CancelReasonToJson(CancelReason instance) =>
    <String, dynamic>{
      'orderId': instance.orderId,
      'reason': instance.reason,
    };
