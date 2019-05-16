<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>

<html>
<head>
	<title>Save Composer</title>
</head>

<body>
	<h2>Composer and Piece Manager</h2>

	<h3>Save Composer</h3>
	
	<form:form action="saveComposer" modelAttribute="composer"
		method="POST">
		<form:hidden path="id" />
		<table>
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
					<td><input type="submit" value="Save" /></td>
				</tr>

			</tbody>
		</table>
	</form:form>

	<p>
		<a href="${pageContext.request.contextPath}/crud/composer/list">
			Back to List
		</a>
	</p>

</body>

</html>