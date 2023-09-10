package com.satish.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.satish.dao.AdminDao;
import com.satish.dao.CustomerDao;
import com.satish.impl.AdminDaoImpl;
import com.satish.impl.CustomerDaoImpl;
import com.satish.model.Address;
import com.satish.model.Customer;


@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	CustomerDao customerDao=new CustomerDaoImpl();
	AdminDao adminDao=new AdminDaoImpl();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String operation=req.getParameter("operation");
    	
    	if(operation.equalsIgnoreCase("logout")) {
    		req.getSession().invalidate();
    		RequestDispatcher rd=null;
    		rd=req.getRequestDispatcher("login.jsp");
			req.setAttribute("logoutSucess", "customer logged out sucessfullyy !");
			rd.forward(req, resp);
    	}else if(operation.equalsIgnoreCase("viewCustomer") || operation.equalsIgnoreCase("editCustomer")) {
    		HttpSession session=req.getSession();
    		String username=(String)session.getAttribute("un");
    		RequestDispatcher rd=null;
    		Customer customer=customerDao.viewCustomer(username);
			if (operation.equalsIgnoreCase("viewCustomer")) {
				rd = req.getRequestDispatcher("dashboard.jsp");
			} else {
				rd = req.getRequestDispatcher("editCustomer.jsp");
			}
			req.setAttribute("loggedInCustomer", customer);
			rd.forward(req, resp);
    	}
    }
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String operation=request.getParameter("operation");
		
		if(operation.equalsIgnoreCase("save")) {
			Customer customer=new Customer();
			customer.setUsername(request.getParameter("username"));
			customer.setPassword(request.getParameter("password").hashCode()+"");
			customer.setCustomerName(request.getParameter("customerName"));
			customer.setCustomerContact(request.getParameter("customerContact"));
			
			Address customerAddress=new Address();
			customerAddress.setAddressLine1(request.getParameter("addressLine1"));
			customerAddress.setAddressLine2(request.getParameter("addressLine2"));
			customerAddress.setPincode(request.getParameter("pincode"));
			customerAddress.setCity(request.getParameter("city"));
			customerAddress.setState(request.getParameter("state"));
			
			customer.setCustomerAddress(customerAddress);
			RequestDispatcher rd=null;
			
			if(customerDao.addCustomer(customer)==1) {
				rd=request.getRequestDispatcher("login.jsp");
				request.setAttribute("regSucessMsg", "registration sucessful! please login !");
			}else {
				rd=request.getRequestDispatcher("register.jsp");
				request.setAttribute("regFailMsg", "registration failed ! try again !");
			}
			rd.forward(request, response);
		}else if(operation.equalsIgnoreCase("customerLogin")) {
			String username=request.getParameter("username");
			String password=request.getParameter("password").hashCode()+"";
			
			RequestDispatcher rd=null;
			
			if(customerDao.customerLogin(username, password)) {
				Customer customer=customerDao.viewCustomer(username);
				rd=request.getRequestDispatcher("dashboard.jsp");
				HttpSession session=request.getSession();
				session.setAttribute("un", username);
				session.setAttribute("userType", "customer");
				request.setAttribute("loginSucessMsg", "Welcome  "+username+" !");
				request.setAttribute("loggedInCustomer", customer);
			}else {
				rd=request.getRequestDispatcher("login.jsp");
				request.setAttribute("loginFailMsg", "login failed ! check username password !");
			}
			rd.forward(request, response);
		}else if(operation.equalsIgnoreCase("adminLogin")) {
			String username=request.getParameter("username");
			String password=request.getParameter("password").hashCode()+"";
			
			RequestDispatcher rd=null;
			
			if(adminDao.adminLogin(username, password)) {
				rd=request.getRequestDispatcher("dashboard.jsp");
				HttpSession session=request.getSession();
				session.setAttribute("un", username);
				session.setAttribute("userType", "admin");
				request.setAttribute("loginSucessMsg", "Welcome  admin !");
			}else {
				rd=request.getRequestDispatcher("login.jsp");
				request.setAttribute("loginFailMsg", "login failed ! check username password !");
			}
			rd.forward(request, response);
		}else if(operation.equalsIgnoreCase("update")) {
			Customer customer=new Customer();
			customer.setUsername(request.getParameter("username"));
			customer.setCustomerName(request.getParameter("customerName"));
			customer.setCustomerContact(request.getParameter("customerContact"));
			
			Address customerAddress=new Address();
			customerAddress.setAddressId(Integer.parseInt(request.getParameter("addressId")));
			customerAddress.setAddressLine1(request.getParameter("addressLine1"));
			customerAddress.setAddressLine2(request.getParameter("addressLine2"));
			customerAddress.setPincode(request.getParameter("pincode"));
			customerAddress.setCity(request.getParameter("city"));
			customerAddress.setState(request.getParameter("state"));
			
			customer.setCustomerAddress(customerAddress);
			RequestDispatcher rd=null;
			
			if(customerDao.updateCustomerDetails(customer)==1) {
				rd=request.getRequestDispatcher("dashboard.jsp");
				request.setAttribute("updatecustsucess", "update customer details sucessfully !");
				request.setAttribute("loggedInCustomer", customer);
			}else {
				rd=request.getRequestDispatcher("dashboard.jsp");
				request.setAttribute("updatecustfailed", "update customer details failed  try again!!");
				request.setAttribute("loggedInCustomer", customer);
			}
			rd.forward(request, response);
		}
	}


}
