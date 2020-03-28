import 'package:customerappswd/api/network.dart';
import 'package:shared_preferences/shared_preferences.dart';

const String getProductListURL = 'http://10.0.2.2:8057/products/find?';
const String getProductDetailsURL = 'http://10.0.2.2:8057/products/';
const String productName = 'Product%20name=';
//const String productId = 'Id=';

class ProductAPI {
  Future<dynamic> getProductList() async {
    SharedPreferences sharedPreferences = await SharedPreferences.getInstance();
    var jwt =  sharedPreferences.get("accessToken");
    Network network = Network(getProductListURL, "Bearer $jwt");
    var productData = await network.getData();
    return productData;
  }

  Future<dynamic> getProduct() async {
    SharedPreferences sharedPreferences = await SharedPreferences.getInstance();
    var jwt =  sharedPreferences.get("accessToken");
    Network network = Network('$getProductListURL$productName', "Bearer $jwt");
    var productData = await network.getData();
    return productData;
  }

  Future<dynamic> getProductDetails(String id) async {
    SharedPreferences sharedPreferences = await SharedPreferences.getInstance();
    var jwt =  sharedPreferences.get("accessToken");
    Network network = Network('$getProductDetailsURL$id',"Bearer $jwt" );
    var productData = await network.getData();
    return productData;
  }
}
