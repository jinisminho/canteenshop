// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'signInUser.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

SignInUser _$SignInUserFromJson(Map<String, dynamic> json) {
  return SignInUser(
    json['firstName'] as String,
    json['lastName'] as String,
    json['username'] as String,
    json['password'] as String,
    json['email'] as String,
    json['phone'] as String,
    json['gender'] as String,
  );
}

Map<String, dynamic> _$SignInUserToJson(SignInUser instance) =>
    <String, dynamic>{
      'firstName': instance.firstName,
      'lastName': instance.lastName,
      'username': instance.username,
      'password': instance.password,
      'email': instance.email,
      'phone': instance.phone,
      'gender': instance.gender,
    };
