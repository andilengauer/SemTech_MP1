<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Neues Restaurant</title>
</head>
<body>
<h1>Neues Restaurant</h1>
<form action="InsertServlet" method="post">
<table>
<tr>
<td>Name:</td><td><input type="text" name="name"></input></td>
</tr>
<tr>
<td>Longitude:</td><td><input type="text" name="long"></input></td>
</tr>
<tr>
<td>Latitude:</td><td><input type="text" name="lat"></input></td>
</tr>
<tr>
<td colspan="2" > <input type="submit" value="Hinzufügen" width="200"><td>
</table>
</form>
</body>
</html>