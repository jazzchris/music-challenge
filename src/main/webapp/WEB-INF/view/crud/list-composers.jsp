<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>

<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<title>List Composers</title>
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

	<h5>List of composers</h5>
	<security:authorize access="hasRole('MANAGER')">
		<input type="button" value="Add Composer" class="btn btn-sm btn-outline-primary"
			onclick="window.location.href='showFormForAdd'; return false;" />
	</security:authorize>
	<br><br>
	<table class="table table-striped table-hover">
		<tr class="thead-dark">
			<th>Name</th>
			<th>Pieces</th>
			<security:authorize access="hasRole('MANAGER')">			
				<th>Action</th>
			</security:authorize>
		</tr>

		<c:forEach var="tempComposer" items="${composers}">
			
			<c:url var="pieceLink" value="/crud/piece">
				<c:param name="composerId" value="${tempComposer.id}" />
			</c:url>
			<security:authorize access="hasRole('MANAGER')">
				<c:url var="updateLink" value="/crud/composer/showFormForUpdate">
					<c:param name="composerId" value="${tempComposer.id}" />
				</c:url>
			</security:authorize>
			
			<security:authorize access="hasRole('ADMIN')">
				<c:url var="deleteLink" value="/crud/composer/delete">
					<c:param name="composerId" value="${tempComposer.id}" />
				</c:url>
			</security:authorize>
			
			<tr>
				<td>${tempComposer.firstName} ${tempComposer.lastName}</td>

				<td><a href="${pieceLink}">Piece</a></td>
				
				<security:authorize access="hasRole('MANAGER')">
					<td>
						<a href="${updateLink}">Update</a>
						<security:authorize access="hasRole('ADMIN')"> | <a href="${deleteLink}"
							onclick="if (!(confirm('Are you sure you want to delete this composer? You will also delete all pieces by this composer'))) return false">Delete</a>
						</security:authorize>
					</td>
				</security:authorize>
			</tr>
		</c:forEach>
	</table>
</div>
</body>

</html>