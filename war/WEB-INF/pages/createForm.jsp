<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create image form.jsp</title>
</head>
<body>

	<h1>Create image FORM jsp</h1>


	------------------------------------
	<br> ${success }
	<br>

	<!-- 	sf:errors path="*" cssClass="error" / -->




	<sf:form method="POST" enctype="multipart/form-data"
		action="/AntSpringMVC1/images/" modelAttribute="imageModel">

		<br>
		<label for="image_description"> Image description: </label>
		<sf:input path="description" id="image_description" />
		<sf:errors path="description" cssClass="error" />

		<br>
		<label for="imageFile">Profile image: </label>
		<input name="imageFile" type="file" />

		<input name="commit" type="submit" value="Send..." />



	</sf:form>

	<sf:form method="GET" action="/AntSpringMVC1/images/">
		<input name="commit" type="submit" value="Show list of images" />
	</sf:form>
	<br>
	<c:if test="${!empty linkToImage}">
		<img width="250" height="250" src="${ linkToImage}" alt="image link" />
	</c:if>

</body>
</html>