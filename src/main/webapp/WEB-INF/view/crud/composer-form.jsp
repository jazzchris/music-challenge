<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<title>Save Composer</title>
</head>

<body>
<div class="container">
	<table class="table">
		<tr>
			<td>Hi, <security:authentication property="principal.username" />!</td>
			<td><security:authentication property="principal.authorities" /></td>
			<td class="float-right"><form:form action="${pageContext.request.contextPath}/logout" method="POST">
					<input type="submit" value="logout" class="btn btn-sm btn-outline-secondary" />
				</form:form></td>
		</tr>
	</table>
	<hr>

	
	<h2 class="text-center">Composer and Piece Manager</h2>

	<h5>Save Composer</h5>
	<br><br>
	<div class="col-5">
	<form:form action="saveComposer" modelAttribute="composer"
		method="POST">
		<form:hidden path="id" />
		<table class="table">
			<tbody>
				<tr>
					<td><label>First and middle names:</label></td>
					<td><form:input path="firstName" /></td>
				</tr>

				<tr>
					<td><label>Last name:</label></td>
					<td><form:input path="lastName" /></td>
				</tr>

				<tr>
					<td><label></label></td>
					<td><input type="submit" value="Save" class="btn btn-sm btn-primary" /></td>
				</tr>

			</tbody>
		</table>
	</form:form>
	</div>

	<p>
		<a href="${pageContext.request.contextPath}/crud/composer/list">
			Back to List
		</a>
	</p>
</div>
</body>

</html>