<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html>
<head>
	<title>Save Composer</title>
</head>

<body>

	<h2>Composer and Piece Manager</h2>

	<h3>Save Piece</h3>

	<c:url var="backToComposer" value="/crud/piece">
		<c:param name="composerId" value="${piece.composer.id}" />
	</c:url>

	<form:form action="savePieceAndUpload" modelAttribute="piece"
		method="POST" enctype="multipart/form-data">

		<form:hidden path="id" />
		<form:hidden path="composer.id" />
		<table>
			<tbody>
				<tr>
					<td><label>Title:</label></td>
					<td><form:input path="title" /></td>
				</tr>
				<tr>
					<td><label>Upload file:</label></td>
					<td><input type="file" name="file" /></td>
				</tr>
				<tr>
					<td><label></label></td>
					<td><input type="submit" value="Save" class="save" /></td>
				</tr>
			</tbody>
		</table>
	</form:form>
	
	<p>
		<a href="${backToComposer}">Back to List</a>
	</p>

</body>

</html>