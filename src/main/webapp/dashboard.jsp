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
	<%
	Customer customer = (Customer) request.getAttribute("loggedInCustomer");
	%>
	<jsp:include page="menu.jsp" />
	<section class="container py-5">
		<div class="row text-center pt-3">
			<div class="col-lg-6 m-auto">

				<c:if test="${loginSucessMsg!=null}">
					<p style="color: green">
						<c:out value="${loginSucessMsg}" />
					</p>
				</c:if>

				<%
				if (((String) session.getAttribute("userType")).equalsIgnoreCase("customer")) {
				%>
				<h1 class="h1">customer Deshbord</h1>
				<center>
					<p>
						hi you are on deskbord
						<c:out value="${un}" />
					</p>
					<fieldset>
						<legend>person details</legend>
						<table>
							<tr>
								<td>customer name:</td>
								<td><p><%=customer.getCustomerName()%></p></td>
							</tr>
							<tr>
								<td>contact:</td>
								<td><p><%=customer.getCustomerContact()%></p></td>
							</tr>
						</table>
					</fieldset>
					<fieldset>
						<legend>Address details</legend>
						<table>
							<tr>
								<td>Address line1:</td>
								<td><p><%=customer.getCustomerAddress().getAddressLine2()%></p></td>
							</tr>
							<tr>
								<td>Address line2:</td>
								<td><p><%=customer.getCustomerAddress().getAddressLine2()%></p></td>
							</tr>
							<tr>
								<td>state:</td>
								<td><p><%=customer.getCustomerAddress().getState()%></p></td>
							</tr>
							<tr>
								<td>city:</td>
								<td><p><%=customer.getCustomerAddress().getCity()%></p></td>
							</tr>
							<tr>
								<td>city:</td>
								<td><p><%=customer.getCustomerAddress().getPincode()%></p></td>
							</tr>
						</table>
					</fieldset>
				</center>
				<%
				}
				%>

				<%
				if (((String) session.getAttribute("userType")).equalsIgnoreCase("admin")) {
				%>
				<h1 class="h1">welcome admin</h1>
				<center>
					<%
					}
					%>
				
			</div>
		</div>
	</section>

	<jsp:include page="footer.jsp" />
</body>
</html>