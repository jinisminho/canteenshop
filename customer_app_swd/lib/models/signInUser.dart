
import 'package:json_annotation/json_annotation.dart';

part 'signInUser.g.dart';

@JsonSerializable(explicitToJson: true)
class SignInUser{
  String firstName;
  String lastName;
  String username;
  String password;
  String email;
  String phone;
  String gender;


  SignInUser(this.firstName, this.lastName, this.username, this.password,
      this.email, this.phone, this.gender);

  factory SignInUser.fromJson(Map<String, dynamic>json)=> _$SignInUserFromJson(json);

  Map<String, dynamic> toJson() => _$SignInUserToJson(this);


}