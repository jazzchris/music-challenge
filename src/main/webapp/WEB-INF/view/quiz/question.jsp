<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<title>Question</title>
</head>
<body>
<div class="container">

	<h3 class="text-center">
	${theQuestion.question}
	</h3>

<br><br>
	<form:form action="check" enctype="multipart/form-data" method="POST">
		
		<c:forEach var="tempOption" items="${theQuestion.options}">
			<label>
				<input type="radio" name="theAnswer" value="${tempOption.text}" required>
				${tempOption.text}
			</label>
			<br>
		</c:forEach>
			
		<br>

		<input type="submit" value="Check" class="btn btn-sm btn-primary" />
	</form:form>
	</div>

</body>
</html>