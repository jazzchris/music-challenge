<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8">
	<title>Question</title>
</head>
<body>
	<h3>
	${theQuestion.question}
	</h3>
	<h2>
	${currentQuestion}
	</h2>

	<form:form action="check" enctype="multipart/form-data" method="POST">
		
		<c:forEach var="tempOption" items="${theQuestion.options}">
			<label>
				<input type="radio" name="theAnswer" value="${tempOption.text}">
				${tempOption.text}
			</label>
			<br>
		</c:forEach>
			
		<br>

		<input type="submit" value="Check" />
	</form:form>

</body>
</html>