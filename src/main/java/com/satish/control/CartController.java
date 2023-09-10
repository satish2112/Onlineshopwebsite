package com.satish.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.satish.dao.CartDao;
import com.satish.dao.CustomerDao;
import com.satish.impl.CartDaoImpl;
import com.satish.impl.CustomerDaoImpl;
import com.satish.model.Cart;
import com.satish.model.Customer;

@WebServlet("/cart")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CartDao cartDao = new CartDaoImpl();
	CustomerDao customerDao = new CustomerDaoImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operation = (String) request.getParameter("operation");

		if (operation.equalsIgnoreCase("addToCart")) {

			HttpSession session = request.getSession();
			String username = (String) session.getAttribute("un");
			
			Customer customer = customerDao.viewCustomer(username);
			
			if (customer != null) {
				int productId = Integer.parseInt(request.getParameter("productId"));
				Cart cart = new Cart();
				cart.setProductId(productId);
				cart.setQuantity(1);
				cart.setCustomerId(customer.getUserId());
				int result = cartDao.addToCart(cart);
				if (result == 1) {
					List<Cart> cartListByCustomer = cartDao.viewAllCart(customer.getUserId());
					request.setAttribute("cartAddedSuccesMsg", "Products Added Sucessfully");
					request.setAttribute("cartListByCustomer", cartListByCustomer);
					request.getRequestDispatcher("viewAllCart.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("cartAddedFailMsg", "Products fail to add");
				request.setAttribute("cartListByCustomer", new ArrayList<Cart>());
				request.getRequestDispatcher("viewAllCart.jsp").forward(request, response);
				
			}
		} else if (operation.equalsIgnoreCase("viewAllCarts")) {
			HttpSession session = request.getSession();
			String username = (String) session.getAttribute("un");

			Customer customer = customerDao.viewCustomer(username);
			List<Cart> cartListByCustomer = cartDao.viewAllCart(customer.getUserId());
			request.setAttribute("cartListByCustomer", cartListByCustomer);
			request.getRequestDispatcher("viewAllCart.jsp").forward(request, response);
		}

			}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		}
}