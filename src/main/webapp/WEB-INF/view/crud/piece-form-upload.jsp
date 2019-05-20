<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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

<jsp:include page="navi.jsp" />
	
	<h2 class="text-center">Composer and Piece Manager</h2>

	<h5>Save Piece</h5>
	<br><br>
	<div class="col-5">
	<c:url var="backToComposer" value="/crud/piece">
		<c:param name="composerId" value="${piece.composer.id}" />
	</c:url>

	<form:form action="savePieceAndUpload" modelAttribute="piece"
		method="POST" enctype="multipart/form-data">

		<form:hidden path="id" />
		<form:hidden path="composer.id" />
		<table class="table">
			<tbody>
				<tr>
					<td><label>Title:</label></td>
					<td><form:input path="title" /></td>
				</tr>
				<tr>
					<td><label>Upload file:</label></td>
					<td><input type="file" name="file" class="form-control-file border"/></td>
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
		<a href="${backToComposer}">Back to List</a>
	</p>
</div>
</body>

</html>