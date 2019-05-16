<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
	<title>Check the answer</title>
</head>

<body>
	<h3>
	${toCheck.question}
	</h3>

<br>
Your answer:
<br>
<b>${theAnswer}</b>
<br>
<c:choose>
    <c:when test="${toCheck.correctOption.text==theAnswer}">
    	<font color="green"><b>Correct!</b></font>
    </c:when>    
    <c:otherwise>
    	<font color="red"><b>Wrong!</b></font>
    </c:otherwise>
</c:choose>
	
<br><br>
<input type="button" value="Continue"
				onclick="window.location.href='cleanup'; return false;" />
</body>

</html>