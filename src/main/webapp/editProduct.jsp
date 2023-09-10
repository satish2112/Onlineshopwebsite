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

	<%  ProductInfo product=(ProductInfo)request.getAttribute("product"); %>
	<section class="container py-5">
		<div class="row text-center pt-3">
			<div class="col-lg-6 m-auto">

				<h1 class="h1">Customer details</h1>
				<center>
					<form method="post" action="product">
						<input type="hidden" name="operation" value="update">
						<table>
							<tr>
								<td>Name :</td>
								<td><input name="pName"
									value="<%=product.getProductName() %>" required="true"
									type="text" /></td>
							</tr>
							<tr>
								<td>Brand:</td>
								<td><input name="pBrand"
									value="<%=product.getProductBrand() %>" required="true"
									type="text" /></td>
							</tr>
							<tr>
								<td>Category :</td>
								<td><input name="pCat"
									value="<%=product.getProductCategory() %>" readonly
									required="true" type="text" /></td>
							</tr>

							<tr>
								<td></td>
								<td><input type="hidden" name="pid"
									value="<%=product.getProductId() %>" /></td>
							</tr>

							<tr>
								<td>Price</td>
								<td><input name="pprice" type="text"
									value="<%=product.getProductPrice() %>" /></td>
							</tr>


							<tr>
								<td></td>
								<td><input type="submit" value="update" /></td>
							</tr>
						</table>
					</form>
				</center>


			</div>
		</div>
	</section>

</body>
</html>