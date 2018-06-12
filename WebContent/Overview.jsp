<%@page import="mp1.web.WebController"%>
<%@page import="java.sql.Date"%>
<%@page import="mp1.jena.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% String restaurantsJson = WebController.getInstance().getRestaurants();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<title>Insert title here</title>
</head>
<body>
<p id="demo"></p>
<p>Ausgabe von jsp</p>
<%=restaurantsJson %>
<script type="text/javascript">

var str = '<%=restaurantsJson %>';
var restaurants = JSON.parse(str);
document.getElementById("demo").innerHTML = restaurants[0].long
</script>
</body>
</html>