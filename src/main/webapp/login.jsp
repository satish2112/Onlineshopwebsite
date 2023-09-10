<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="menu.jsp" />
	<section class="container py-5">
		<div class="row text-center pt-3">
			<div class="col-lg-6 m-auto">

				<c:if test="${regSucessMsg!=null}">
					<p style="color: green">
						<c:out value="${regSucessMsg}" />
					</p>
				</c:if>

				<c:if test="${logoutSucess!=null}">
					<p style="color: green">
						<c:out value="${logoutSucess}" />
					</p>
				</c:if>

				<c:if test="${loginFailMsg!=null}">
					<p style="color: red">
						<c:out value="${loginFailMsg}" />
					</p>
				</c:if>

				<h1 class="h1">Customer Login</h1>
				<center>
					<form method="post" action="customer">
						<table>
							<tr>
								<td>username :</td>
								<td><input name="username" required="true" type="text" /></td>
							</tr>
							<tr>
								<td>password</td>
								<td><input name="password" required="true" type="password" /></td>
							</tr>
							<tr>
								<td>type</td>
								<td><select name="operation">
										<option value="customerLogin">Customer</option>
										<option value="adminLogin">Admin</option>
								</select></td>
							</tr>

							<tr>
								<td></td>
								<td><input type="submit" value="login" /></td>
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