<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Show image jsp</title>
</head>
<body>

	<img width="500" src="${imageURL }" alt="image link" />

	<br>
	<br>
	<sf:form method="GET" action="/AntSpringMVC1/images/">
		<input name="commit" type="submit" value="Show list of images" />
	</sf:form>

</body>
</html>