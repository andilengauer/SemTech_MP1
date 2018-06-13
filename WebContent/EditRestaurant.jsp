<%@page import="mp1.web.WebController"%>
<%@page import="mp1.jena.Restaurant"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%String restaurant = request.getParameter("restaurant");
Restaurant r = WebController.getInstance().getRestaurant(restaurant);
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Restaurant bearbeiten</title>
</head>
<body>
<h1>Restaurant bearbeiten</h1>
<form action="EditServlet" method="post">
<input type="hidden" name="origin" value="<%=restaurant %>">
<table>
<tr>
<td>Name:</td><td><input type="text" name="name" value="<%=r.getName() %>"></input></td>
</tr>
<tr>
<td>Longitude:</td><td><input type="text" name="long" value="<%=r.getLongitude() %>"></input></td>
</tr>
<tr>
<td>Latitude:</td><td><input type="text" name="lat" value="<%=r.getLatitude() %>"></input></td>
</tr>
<tr>
<td colspan="2" > <input type="submit" value="Änderungen speichern" width="200"><td>
</table>
</form>
</body>
</html>