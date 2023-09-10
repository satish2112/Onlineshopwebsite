<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%String username=(String)session.getAttribute("un");
if(username==null){%>
	<jsp:forward page="login.jsp" />
	<%} %>
	<jsp:include page="menu.jsp" />
	<!-- Start Categories of The Month -->
	<section class="container py-5">
		<div class="row text-center pt-3">
			<div class="col-lg-6 m-auto">
				<h1 class="h1">Customer Registration</h1>
				<center>


					<form method="post" action="product">
						<input type="hidden" name="operation" value="saveProduct">
						<table>
							<tr>
								<td>name :</td>
								<td><input name="pname" required="true" type="text" /></td>
							</tr>
							<tr>
								<td>catagory :</td>
								<td><input name="category" required="true" type="text" /></td>
							</tr>
							<tr>
								<td>Brand :</td>
								<td><input name="brand" required="true" type="text" /></td>
							</tr>
							<tr>
								<td>Price :</td>
								<td><input name="price" required="true" type="number" /></td>
							</tr>
							<td></td>
							<td><input type="submit" value="add product" /></td>
							</tr>
						</table>
					</form>
				</center>
			</div>
		</div>
	</section>

	<jsp:include page="footer.jsp" />

</body>
</html>