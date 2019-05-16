<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
	<title>The result</title>
</head>

<body>
	<h3>End of quiz</h3>
	<h4>Your score:<br>${result}/${total} </h4>
	<br>
	<input type="button" value="Start again"
		onclick="window.location.href='create'; return false;" />
</body>

</html>