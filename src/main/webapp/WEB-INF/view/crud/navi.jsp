<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<table class="table">
	<tr>
		<td>Hi, <security:authentication property="principal.username" />!
		</td>
		<td><security:authentication property="principal.authorities" /></td>
		<td class="float-right">
			<form:form action="${pageContext.request.contextPath}/logout" method="POST">
				<input type="submit" value="logout"
					class="btn btn-sm btn-outline-secondary" />
			</form:form></td>
	</tr>
</table>
<hr>
