package com.satish.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.satish.dao.ProductDao;
import com.satish.impl.ProductInfoDaoImpl;
import com.satish.model.ProductInfo;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	ProductDao productDao=new ProductInfoDaoImpl();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		String operation=req.getParameter("operation");
		
		if(operation.equalsIgnoreCase("viewAllProducts")) {
			List<ProductInfo> allProducts= productDao.viewAllProducts();	
			req.setAttribute("allProducts", allProducts);
			
			req.getRequestDispatcher("viewAllProducts.jsp").forward(req, response);	
		}
		else if(operation.equalsIgnoreCase("edit")) {
			ProductInfo product= productDao.searchProduct(Integer.parseInt(req.getParameter("productid")));
			req.setAttribute("product", product);
			req.getRequestDispatcher("editProduct.jsp").forward(req, response);	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation=request.getParameter("operation");
		
		if(operation.equalsIgnoreCase("saveProduct")) {
			
			ProductInfo product= new ProductInfo();
			product.setProductBrand(request.getParameter("brand"));
			product.setProductCategory(request.getParameter("category"));
			product.setProductName(request.getParameter("pname"));
			product.setProductPrice(Double.parseDouble(request.getParameter("price")));
			
			int productAdded=productDao.addProduct(product);
			
			List<ProductInfo> allProducts= productDao.viewAllProducts();	
			request.setAttribute("allProducts", allProducts);
			
			if (productAdded==1) {
				request.setAttribute("productAddSucessMsg", "Product added sucessully");
			}else {
				request.setAttribute("productAddFailedMsg", "Product added failed !");
			}
			
			request.getRequestDispatcher("viewAllProducts.jsp").forward(request, response);	
		}else if(operation.equalsIgnoreCase("search")) {
			String productname=request.getParameter("pname");
			String productcategory=request.getParameter("pcategory");
			String productbrand=request.getParameter("pbrand");
			Double productpric=Double.parseDouble(request.getParameter("pprice")) ;
			
			List<ProductInfo> filterdProducts= productDao.searchProduct(productname,productcategory,productbrand,productpric);	
			request.setAttribute("allProducts", filterdProducts);
			
			request.getRequestDispatcher("viewAllProducts.jsp").forward(request, response);	
		}
	}

}
