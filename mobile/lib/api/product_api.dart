import 'package:customerappswd/api/network.dart';
import 'package:customerappswd/config/host_config.dart';
import 'package:shared_preferences/shared_preferences.dart';

const String GET_PRODUCTS_URL =  HOST_URL + '/products/find?';
const String GET_PRODUCT_DETAIL_URL = HOST_URL + '/products/';
const String productName = 'Product%20name=';
//const String productId = 'Id=';

class ProductAPI {
  Future<dynamic> getProductList(search) async {
    SharedPreferences sharedPreferences = await SharedPreferences.getInstance();
    var jwt =  sharedPreferences.get("accessToken");
    Network network = Network(GET_PRODUCTS_URL+productName+search, "Bearer $jwt");
    var productData = await network.getData();
    return productData;
  }

  Future<dynamic> getProduct() async {
    SharedPreferences sharedPreferences = await SharedPreferences.getInstance();
    var jwt =  sharedPreferences.get("accessToken");
    Network network = Network('$GET_PRODUCTS_URL$productName', "Bearer $jwt");
    var productData = await network.getData();
    return productData;
  }

  Future<dynamic> getProductDetails(String id) async {
    SharedPreferences sharedPreferences = await SharedPreferences.getInstance();
    var jwt =  sharedPreferences.get("accessToken");
    Network network = Network('$GET_PRODUCT_DETAIL_URL$id',"Bearer $jwt" );
    var productData = await network.getData();
    return productData;
  }
}
