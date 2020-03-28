// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'loginUser.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

LoginUser _$LoginUserFromJson(Map<String, dynamic> json) {
  return LoginUser(
    json['username'] as String,
    json['password'] as String,
  );
}

Map<String, dynamic> _$LoginUserToJson(LoginUser instance) => <String, dynamic>{
      'username': instance.username,
      'password': instance.password,
    };
