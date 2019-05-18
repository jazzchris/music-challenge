<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>

<html>

<head>
	<title>List Composers</title>
</head>

<body>
	<table>
		<tr>
			<td>Hi, <security:authentication property="principal.username" />!</td>
			<td><security:authentication property="principal.authorities" /></td>
			<td><form:form action="${pageContext.request.contextPath}/logout" method="POST">
					<input type="submit" value="logout" />
				</form:form></td>
		</tr>
	</table>

	<h2>Composer and Piece Manager</h2>

	<h3>List of composers</h3>
	<input type="button" value="Add Composer"
		onclick="window.location.href='showFormForAdd'; return false;" />

	<table>
		<tr>
			<th>Name</th>
			<th>Pieces</th>
			<th>Action</th>
		</tr>

		<c:forEach var="tempComposer" items="${composers}">

			<c:url var="pieceLink" value="/crud/piece">
				<c:param name="composerId" value="${tempComposer.id}" />
			</c:url>

			<c:url var="updateLink" value="/crud/composer/showFormForUpdate">
				<c:param name="composerId" value="${tempComposer.id}" />
			</c:url>

			<c:url var="deleteLink" value="/crud/composer/delete">
				<c:param name="composerId" value="${tempComposer.id}" />
			</c:url>

			<tr>
				<td>${tempComposer.firstName} ${tempComposer.lastName}</td>

				<td><a href="${pieceLink}">Piece</a></td>

				<td><a href="${updateLink}">Update</a> | <a
					href="${deleteLink}"
					onclick="if (!(confirm('Are you sure you want to delete this composer? You will also delete all pieces by this composer'))) return false">Delete</a>
				</td>

			</tr>
		</c:forEach>
	</table>

</body>

</html>