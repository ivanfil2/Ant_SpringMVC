<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List of images</title>
</head>
<body>

	<h1>List of images</h1>
	
	<c:if test="${!empty mapNames}">
		
	
	
	<table border="3">
		<th>Image name:</th>
		<th>Preview:</th>
		<th>Show in dedicated window:</th>
		<c:forEach var="elem" items="${mapNames}">
			<tr>
				<td><c:out value="${ elem.key} " /></td>
				<td><img width="50" src="${ elem.value}"
					alt="image link" /></td>
				<td><sf:form method="GET"
						action="/AntSpringMVC1/images/showImageById">
						<input name="imageId" type="hidden" value="${ elem.value}" />
						<input name="button" type="submit" value="Show" />
					</sf:form></td>
			</tr>
		</c:forEach>
	</table>
	
	</c:if>
	<sf:form method="GET" action="/AntSpringMVC1/images/showCreateForm">
		<input name="commit" type="submit" value="Add new image" />
	</sf:form>

</body>
</html>