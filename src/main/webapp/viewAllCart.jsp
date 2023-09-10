<%@ page  import="com.satish.model.Cart" import="java.util.*" language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
	String username = (String) session.getAttribute("un");
	if (username == null) {
	%>
	<jsp:forward page="login.jsp" />
	<%
	}
	%>

	<%
	List<Cart> cartListByCustomer = (List<Cart>) request.getAttribute("cartListByCustomer");
	String userType = (String) session.getAttribute("userType");
	%>
	<jsp:include page="menu.jsp" />

	<div class="container py-5">
		<div class="row">

			<div class="col-lg-12">
				<div class="row">
					<div class="col-md-12">
						<table class="table table-striped">

							<thead>
								<th scope="col">id</th>
								<th scope="col">Product Name</th>
								<th scope="col">brand</th>
								<th scope="col">edit</th>
								<th scope="col">delete</th>
							</thead>
							</tbody>
							<%
							for (Cart eachCart : cartListByCustomer) {
							%>
							<form action="cart" method="post">
							<input type="hidden" name="operation" value="updateCart"/>
							<input type="hidden" name="productid" value=""<%=eachCart.getProduct()%>"/>
							<input type="hidden" name="cartId" value="<%=eachCart.getCartId() %>"/>
							<tr scope="row">
								<td><%=eachCart.getCartId()%></td>
								<td><%=eachCart.getProduct().getProductName()%></td>
								<td><input type="number" name="cartQuentity" value="<%=eachCart.getQuantity()%>" /></td>
								<td><input type="submit" value="edit"/></td>
								<td><a
									href="product?operation=delete&productid=<%=eachCart.getCartId()%>">delete</a></td>

							</tr>
							</form>
							<%
							}
							%>
							<tbody>
						</table>
					</div>

				</div>
			</div>
		</div>

		<jsp:include page="footer.jsp" />
</body>
</html>