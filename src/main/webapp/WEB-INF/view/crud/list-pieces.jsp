<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>

<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<title>List Pieces</title>
</head>

<body>
<div class="container">

<jsp:include page="navi.jsp" />

	<h2 class="text-center">Composer and Piece Manager</h2>

	<h5>Pieces by ${composer.firstName} ${composer.lastName}</h5>

	<c:url var="addLink" value="/crud/piece/showFormForAddPieceAndUpload">
		<c:param name="composerId" value="${composer.id}" />
	</c:url>

	<security:authorize access="hasRole('MANAGER')">
		<a href="${addLink}"> <input type="button" value="Add Piece"
			class="btn btn-sm btn-outline-primary" />
		</a>
	</security:authorize>
	<br><br>
	<table class="table table-striped table-hover">
		<tr class="thead-dark">
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
	</div>
</body>

</html>