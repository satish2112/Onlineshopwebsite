package com.satish.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.satish.dao.ProductDao;
import com.satish.dbconnection.DbConnection;
import com.satish.model.ProductInfo;


public class ProductInfoDaoImpl implements ProductDao{

	Connection con=DbConnection.getDatabaseConnection();
	PreparedStatement pstmt=null;   
	ResultSet rs=null;
	
	@Override
	public int addProduct(ProductInfo product) {
		try {
			pstmt = con.prepareStatement(
					"insert into products (product_Name, product_Price, product_Catagory, product_Brand) values(?,?,?,?)");
			pstmt.setString(1, product.getProductName());
			pstmt.setDouble(2, product.getProductPrice());
			pstmt.setString(3, product.getProductCategory());
			pstmt.setString(4, product.getProductBrand());
			int executeUpdate = pstmt.executeUpdate();
			if (executeUpdate == 1) {
				return 1;
			}
		} catch (SQLException e) {
			System.out.println("Error " + e.getMessage());
		}
		return 0;
	}

	@Override
	public int updateProductInfo(ProductInfo product) {
		try {
			pstmt = con.prepareStatement(
					"update products set  product_Name= ? ,product_Price=?, product_Catagory=? , product_Brand=? where product_Id =?");
			pstmt.setString(1, product.getProductName());
			pstmt.setDouble(2, product.getProductPrice());
			pstmt.setString(3, product.getProductCategory());
			pstmt.setString(4, product.getProductBrand());
			pstmt.setInt(5, product.getProductId());
			int executeUpdate = pstmt.executeUpdate();
			if (executeUpdate == 1) {
				return 1;
			}
		} catch (SQLException e) {
			System.out.println("Error " + e.getMessage());
		}
		return 0;
	}

	@Override
	public int deleteProductInfo(int producId) {
		try {
			pstmt = con.prepareStatement("DELETE from products where product_Id =? ");
			pstmt.setInt(1, producId);
			int executeUpdate = pstmt.executeUpdate();
			if (executeUpdate == 1) {
				return 1;
			}
		} catch (SQLException e) {
			System.out.println("Error " + e.getMessage());
		}
		return 0;
	}

	@Override
	public List<ProductInfo> viewAllProducts() {
		List<ProductInfo> productList = new ArrayList<ProductInfo>();
		try {
			pstmt = con.prepareStatement("select * from products");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductInfo productInfo = new ProductInfo();
				productInfo.setProductId(rs.getInt("product_Id"));
				productInfo.setProductName(rs.getString("product_Name"));
				productInfo.setProductPrice(rs.getDouble("product_Price"));
				productInfo.setProductCategory(rs.getString("product_Catagory"));
				productInfo.setProductBrand(rs.getString("product_Brand"));
				productList.add(productInfo);
			}
		} catch (SQLException e) {
			System.out.println("error" + e.getMessage());
		} catch (Exception e) {
			System.out.println("error" + e.getMessage());
		}
		return productList;
	}

	@Override
	public ProductInfo searchProduct(int producId) {
		ProductInfo productInfo = null;
		try {
			pstmt = con.prepareStatement("select * from products where product_Id =?");
			pstmt.setInt(1, producId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				productInfo= new ProductInfo();
				productInfo.setProductId(rs.getInt("product_Id"));
				productInfo.setProductName(rs.getString("product_Name"));
				productInfo.setProductPrice(rs.getDouble("product_Price"));
				productInfo.setProductCategory(rs.getString("product_Catagory"));
				productInfo.setProductBrand(rs.getString("product_Brand"));
			}
		} catch (SQLException e) {
			System.out.println("Error " + e.getMessage());
		}
		return productInfo;
	}

	@Override
	public List<ProductInfo> searchProduct(String category, double price) {
		List<ProductInfo> productList = new ArrayList<ProductInfo>();
		try {
			pstmt = con.prepareStatement("select * from products where product_Catagory =? or product_Price=?");
			pstmt.setString(1, category);
			pstmt.setDouble(2, price);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductInfo productInfo = new ProductInfo();
				productInfo.setProductId(rs.getInt("product_Id"));
				productInfo.setProductName(rs.getString("product_Name"));
				productInfo.setProductPrice(rs.getDouble("product_Price"));
				productInfo.setProductCategory(rs.getString("product_Catagory"));
				productInfo.setProductBrand(rs.getString("product_Brand"));
				productList.add(productInfo);
			}
		} catch (SQLException e) {
			System.out.println("error" + e.getMessage());
		} catch (Exception e) {
			System.out.println("error" + e.getMessage());
		}
		return productList;
	}

	@Override
	public List<ProductInfo> searchProduct(double price, String brand) {
		List<ProductInfo> productList = new ArrayList<ProductInfo>();
		try {
			pstmt = con.prepareStatement("select * from products where product_Price=? or product_Brand =?");
			pstmt.setDouble(1, price);
			pstmt.setString(2, brand);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductInfo productInfo = new ProductInfo();
				productInfo.setProductId(rs.getInt("product_Id"));
				productInfo.setProductName(rs.getString("product_Name"));
				productInfo.setProductPrice(rs.getDouble("product_Price"));
				productInfo.setProductCategory(rs.getString("product_Catagory"));
				productInfo.setProductBrand(rs.getString("product_Brand"));
				productList.add(productInfo);
			}
		} catch (SQLException e) {
			System.out.println("error" + e.getMessage());
		} catch (Exception e) {
			System.out.println("error" + e.getMessage());
		}
		return productList;
	}

	@Override
	public List<ProductInfo> searchByCategory(String category) {
		List<ProductInfo> productList = new ArrayList<ProductInfo>();
		try {
			pstmt = con.prepareStatement("select * from products where product_Catagory=?");
			pstmt.setString(1, category);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductInfo productInfo = new ProductInfo();
				productInfo.setProductId(rs.getInt("product_Id"));
				productInfo.setProductName(rs.getString("product_Name"));
				productInfo.setProductPrice(rs.getDouble("product_Price"));
				productInfo.setProductCategory(rs.getString("product_Catagory"));
				productInfo.setProductBrand(rs.getString("product_Brand"));
				productList.add(productInfo);
			}
		} catch (SQLException e) {
			System.out.println("error" + e.getMessage());
		} catch (Exception e) {
			System.out.println("error" + e.getMessage());
		}
		return productList;
	}

	@Override
	public List<ProductInfo> searchByBeand(String brand) {
		List<ProductInfo> productList = new ArrayList<ProductInfo>();
		try {
			pstmt = con.prepareStatement("select * from products where product_Brand=?");
			pstmt.setString(1, brand);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductInfo productInfo = new ProductInfo();
				productInfo.setProductId(rs.getInt("product_Id"));
				productInfo.setProductName(rs.getString("product_Name"));
				productInfo.setProductPrice(rs.getDouble("product_Price"));
				productInfo.setProductCategory(rs.getString("product_Catagory"));
				productInfo.setProductBrand(rs.getString("product_Brand"));
				productList.add(productInfo);
			}
		} catch (SQLException e) {
			System.out.println("error" + e.getMessage());
		} catch (Exception e) {
			System.out.println("error" + e.getMessage());
		}
		return productList;
	}

	@Override
	public List<ProductInfo> searchByName(String name) {
		List<ProductInfo> productList = new ArrayList<ProductInfo>();
		try {
			pstmt = con.prepareStatement("select * from products where product_Name=?");
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductInfo productInfo = new ProductInfo();
				productInfo.setProductId(rs.getInt("product_Id"));
				productInfo.setProductName(rs.getString("product_Name"));
				productInfo.setProductPrice(rs.getDouble("product_Price"));
				productInfo.setProductCategory(rs.getString("product_Catagory"));
				productInfo.setProductBrand(rs.getString("product_Brand"));
				productList.add(productInfo);
			}
		} catch (SQLException e) {
			System.out.println("error" + e.getMessage());
		} catch (Exception e) {
			System.out.println("error" + e.getMessage());
		}
		return productList;
	}

	@Override
	public List<ProductInfo> searchProduct(String productname, String productcategory, String productbrand,
			Double productpric) {
		List<ProductInfo> productList = new ArrayList<ProductInfo>();
		try {
			pstmt = con.prepareStatement("select * from products where product_Name like ? and product_Catagory like ? and product_Brand like ?");
			pstmt.setString(1, "%"+productname+"%");
			pstmt.setString(2,  "%"+productcategory+"%");
//			pstmt.setDouble(3,  productpric);
			pstmt.setString(3,  "%"+productbrand+"%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductInfo productInfo = new ProductInfo();
				productInfo.setProductId(rs.getInt("product_Id"));
				productInfo.setProductName(rs.getString("product_Name"));
				productInfo.setProductPrice(rs.getDouble("product_Price"));
				productInfo.setProductCategory(rs.getString("product_Catagory"));
				productInfo.setProductBrand(rs.getString("product_Brand"));
				productList.add(productInfo);
			}
		} catch (SQLException e) {
			System.out.println("error" + e.getMessage());
		} catch (Exception e) {
			System.out.println("error" + e.getMessage());
		}
		return productList;
	}

}