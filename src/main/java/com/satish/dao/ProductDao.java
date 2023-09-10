package com.satish.dao;

import java.util.List;

import com.satish.model.ProductInfo;

public interface ProductDao {
	int addProduct(ProductInfo product);
	int updateProductInfo(ProductInfo product);
	int deleteProductInfo(int productId);
	List<ProductInfo> viewAllProducts();
	
	ProductInfo searchProduct(int productsId);
	List<ProductInfo> searchProduct(String category ,double price);
	List<ProductInfo> searchProduct(double price , String brand);
	List<ProductInfo> searchProduct(String productname,String productcategory,String productbrand,Double productpric);	

	
	List<ProductInfo> searchByCategory(String category);
	List<ProductInfo> searchByBeand(String brand);
	List<ProductInfo> searchByName(String name);
	
}
