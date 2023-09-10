<%@ page language="java" import="java.util.*"
	import="com.satish.model.*" contentType="text/html; charset=ISO-8859-1"
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
	List<ProductInfo> allProducts = (List<ProductInfo>) request.getAttribute("allProducts");
	String userType = (String) session.getAttribute("userType");
	%>
	<jsp:include page="menu.jsp" />

	<div class="container py-5">
		<div class="row">
			<div class="col-lg-3">
				<h1 class="h2 pb-4">Categories</h1>
				<ul class="list-unstyled templatemo-accordion">
					<form method="post" action="product">
						<input type="hidden" name="operation" value="search">
						<li class=pb-3><input type="text" name="pname"
							placeholder="enter product name"></li>
						<li class=pb-3><input type="text" name="pbrand"
							placeholder="enter brand"></li>
						<li class=pb-3><input type="text" name="pcategory"
							placeholder="enter category"></li>
						<li class=pb-3><input type="text" name="pprice"
							required="required" placeholder="enter price"></li>
						<li class=pb-3><input type="submit" value="search"></li>
					</form>
				</ul>
			</div>

			<div class="col-lg-9">
				<div class="row">
					<div class="col-md-6">
						<ul class="list-inline shop-top-menu pb-3 pt-1">
							<li class="list-inline-item"><a
								class="h3 text-dark text-decoration-none mr-3" href="#">All</a>
							</li>
							<li class="list-inline-item"><a
								class="h3 text-dark text-decoration-none mr-3" href="#">Men's</a>
							</li>
							<li class="list-inline-item"><a
								class="h3 text-dark text-decoration-none" href="#">Women's</a></li>
						</ul>
					</div>
					<div class="col-md-6 pb-4">
						<div class="d-flex">
							<select class="form-control">
								<option>Featured</option>
								<option>A to Z</option>
								<option>Item</option>
							</select>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<table class="table table-striped">

							<thead>
								<th scope="col"></th>
								<th scope="col">Product Name</th>
								<th scope="col">brand</th>
								<th scope="col">category</th>
								<th scope="col">price</th>
								<%
								if (userType.equalsIgnoreCase("admin")) {
								%>
								<th scope="col">edit</th>
								<th scope="col">delete</th>
								<%
								} else {
								%>
								<th scope="col">actions</th>
								<%
								}
								%>

							</thead>
							</tbody>
							<%
							for (ProductInfo eachProduct : allProducts) {
							%>
							<tr>
								<!-- <th scope="row">1</th> -->
								<td><%=eachProduct.getProductId()%></td>
								<td><%=eachProduct.getProductName()%></td>
								<td><%=eachProduct.getProductBrand()%></td>
								<td><%=eachProduct.getProductCategory()%></td>
								<td><%=eachProduct.getProductPrice()%></td>
								<%
								if (userType.equalsIgnoreCase("admin")) {
								%>
								<td><a
									href="product?operation=edit&productid=<%=eachProduct.getProductId()%>">edit</a></td>
								<td><a
									href="product?operation=delete&productid=<%=eachProduct.getProductId()%>">delete</a></td>
								<%
								} else {
								%>
								<td><a href="cart?operation=addToCart&productId=<%=eachProduct.getProductId()%>">add to cart</td>
								<%
								}
								%>
							</tr>
							<%
							}
							%>

							<tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="footer.jsp" />
</body>
</html>