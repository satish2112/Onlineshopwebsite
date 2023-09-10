<%@ page language="java" import="com.satish.model.*"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Online shopee : dashboard</title>
</head>
<body>
	<c:if test="${un==null }">
		<jsp:forward page="login.jsp" />
	</c:if>
	<%  Customer customer=(Customer)request.getAttribute("loggedInCustomer"); %>
	<jsp:include page="menu.jsp" />
	<section class="container py-5">
		<div class="row text-center pt-3">
			<div class="col-lg-6 m-auto">
				<c:if test="${un!=null }">
					<h1 class="h1">Customer details</h1>
					<center>
						<form method="post" action="customer">
							<input type="hidden" name="operation" value="update">
							<table>
								<tr>
									<td>Customer name :</td>
									<td><input name="customerName"
										value="<%=customer.getCustomerName() %>" required="true"
										type="text" /></td>
								</tr>
								<tr>
									<td>Contact:</td>
									<td><input name="customerContact"
										value="<%=customer.getCustomerContact() %>" required="true"
										type="text" /></td>
								</tr>
								<tr>
									<td>username :</td>
									<td><input name="username"
										value="<%=customer.getUsername() %>" readonly required="true"
										type="text" /></td>
								</tr>

								<tr>
									<td></td>
									<td><input type="hidden" name="addressId"
										value="<%=customer.getCustomerAddress().getAddressId() %>" /></td>
								</tr>

								<tr>
									<td>Address Line1 :</td>
									<td><textarea name="addressLine1"><%=customer.getCustomerAddress().getAddressLine1() %></textarea></td>
								</tr>
								<tr>
									<td>Address Line2 :</td>
									<td><textarea name="addressLine2"><%=customer.getCustomerAddress().getAddressLine2() %></textarea></td>
								</tr>
								<tr>
									<td>pincode :</td>
									<td><input name="pincode" type="text"
										value="<%=customer.getCustomerAddress().getPincode() %>" /></td>
								</tr>
								<tr>
									<td>city</td>
									<td><input name="city" type="text"
										value="<%=customer.getCustomerAddress().getCity() %>" /></td>
								</tr>
								<tr>
									<td>state :</td>
									<td><input name="state" type="text"
										value="<%=customer.getCustomerAddress().getState() %>" /></td>
								</tr>


								<tr>
									<td></td>
									<td><input type="submit" value="update" /></td>
								</tr>
							</table>
						</form>
					</center>
				</c:if>

			</div>
		</div>
	</section>

	<jsp:include page="footer.jsp" />
</body>
</html>