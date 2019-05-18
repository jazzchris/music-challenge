<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>

<html>

<head>
	<title>List Pieces</title>
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

	<h3>Pieces by ${composer.firstName} ${composer.lastName}</h3>

	<c:url var="addLink" value="/crud/piece/showFormForAddPieceAndUpload">
		<c:param name="composerId" value="${composer.id}" />
	</c:url>

	<security:authorize access="hasRole('MANAGER')">
		<a href="${addLink}"> <input type="button" value="Add Piece"
			class="add-button" />
		</a>
	</security:authorize>

	<table>
		<tr>
			<th>Title</th>
			<security:authorize access="hasRole('MANAGER')">			
				<th>Action</th>
			</security:authorize>
		</tr>

		<c:forEach var="tempPiece" items="${piece}">
			<security:authorize access="hasRole('MANAGER')">
				<c:url var="updateLink" value="/crud/piece/showFormForUpdate">
					<c:param name="pieceId" value="${tempPiece.id}" />
				</c:url>
			</security:authorize>
			<security:authorize access="hasRole('ADMIN')">
				<c:url var="deleteLink" value="/crud/piece/delete">
					<c:param name="pieceId" value="${tempPiece.id}" />
				</c:url>
			</security:authorize>
			<tr>
				<td>${tempPiece.title}</td>
				
				<security:authorize access="hasRole('MANAGER')">
					<td><a href="${updateLink}">Update</a>
						<security:authorize access="hasRole('ADMIN')"> | <a href="${deleteLink}"
							onclick="if (!(confirm('Are you sure you want to delete this piece?'))) return false">Delete</a>
						</security:authorize>
					</td>
				</security:authorize>
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