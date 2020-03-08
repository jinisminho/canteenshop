import 'package:json_annotation/json_annotation.dart';

part 'loginUser.g.dart';

@JsonSerializable()
class LoginUser{
  String username;
  String password;

  LoginUser(this.username, this.password);

  factory LoginUser.fromJson(Map<String, dynamic>json)=> _$LoginUserFromJson(json);

  Map<String, dynamic> toJson() => _$LoginUserToJson(this);
}