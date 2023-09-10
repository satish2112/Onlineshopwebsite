<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="menu.jsp" />
	<!-- Start Categories of The Month -->
	<section class="container py-5">
		<div class="row text-center pt-3">
			<div class="col-lg-6 m-auto">
				<h1 class="h1">Customer Registration</h1>
				<center>

					<c:if test="${regFailMsg!=null}">
            ``			<p style="color: red">
							<c:out value="${regFailMsg}" />
						</p>
					</c:if>

					<form method="post" action="customer">
						<input type="hidden" name="operation" value="save">
						<table>
							<tr>
								<td>Customer name :</td>
								<td><input name="customerName" required="true" type="text" /></td>
							</tr>
							<tr>
								<td>Contact:</td>
								<td><input name="customerContact" required="true"
									type="text" /></td>
							</tr>
							<tr>
								<td>username :</td>
								<td><input name="username" required="true" type="text" /></td>
							</tr>
							<tr>
								<td>password</td>
								<td><input name="password" required="true" type="password" /></td>
							</tr>

							<tr>
								<td>Address Line1 :</td>
								<td><textarea name="addressLine1"></textarea></td>
							</tr>
							<tr>
								<td>Address Line2 :</td>
								<td><textarea name="addressLine2"></textarea></td>
							</tr>
							<tr>
								<td>pincode :</td>
								<td><input name="pincode" type="text" /></td>
							</tr>
							<tr>
								<td>city</td>
								<td><input name="city" type="text" /></td>
							</tr>
							<tr>
								<td>state :</td>
								<td><input name="state" type="text" /></td>
							</tr>


							<tr>
								<td></td>
								<td><input type="submit" value="register" /></td>
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