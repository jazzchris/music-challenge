<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>

<html>

<head>
	<title>List Pieces</title>
</head>

<body>
	<form:form action="${pageContext.request.contextPath}/logout" method="POST">
		<input type="submit" value="logout" />
	</form:form>

	<h2>Composer and Piece Manager</h2>

	<h3>Pieces by ${composer.firstName} ${composer.lastName}</h3>

	<c:url var="addLink" value="/crud/piece/showFormForAddPieceAndUpload">
		<c:param name="composerId" value="${composer.id}" />
	</c:url>

	<a href="${addLink}"> <input type="button" value="Add Piece"
		class="add-button" />
	</a>

	<table>
		<tr>
			<th>Title</th>
			<th>Action</th>
		</tr>

		<c:forEach var="tempPiece" items="${piece}">

			<c:url var="updateLink" value="/crud/piece/showFormForUpdate">
				<c:param name="pieceId" value="${tempPiece.id}" />
			</c:url>

			<c:url var="deleteLink" value="/crud/piece/delete">
				<c:param name="pieceId" value="${tempPiece.id}" />
			</c:url>

			<tr>
				<td>${tempPiece.title}</td>
				<td><a href="${updateLink}">Update</a> | <a
					href="${deleteLink}"
					onclick="if (!(confirm('Are you sure you want to delete this piece?'))) return false">Delete</a>
				</td>
			</tr>

		</c:forEach>

	</table>

	<p>
		<a href="${pageContext.request.contextPath}/crud/composer/list">
			Back to composer list
		</a>
	</p>
</body>

</html>