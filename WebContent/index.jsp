<%@page import="mp1.web.WebController"%>
<%@page import="java.sql.Date"%>
<%@page import="mp1.jena.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% String restaurantsJson = WebController.getInstance().getRestaurants();
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SemTech MP1</title>

    <!-- Bootstrap core CSS -->
    <!-- <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet"> -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">

    <!-- Custom styles for this template -->
    <link href="style.css" rel="stylesheet">


    
    
  </head>

  <body>
<input id="restaurants" type="hidden">
    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        <a class="navbar-brand" href="#">Restaurants</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <!-- <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
              <a class="nav-link" href="#">Home
                <span class="sr-only">(current)</span>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">About</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Services</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Contact</a>
            </li>
          </ul>
        </div>
      </div> -->
    </nav>

    <!-- Page Content -->
    <div class="container">

      <!-- Portfolio Item Heading -->
      <h1 class="my-4">MP1 - Gruppe 7
        <small>Frieske, Lengauer, Rabitsch</small>
      </h1>

      <!-- Portfolio Item Row -->
      <div class="row">

        <div class="col-md-8">
          <div id="map"></div>
        </div>

        <div class="col-md-4">
          <h3 class="my-3">Projektbeschreibung</h3>
          <p id="demo">Abfrage von Restaurants in DBPedia und Darstellung auf einer Map.</p>
          <h3 class="my-3">Details</h3>
          <ul>
            <li>DBPedia Abfrage</li>
            <li>Jena und SPARQL</li>
            <li>JSP Web Applikation</li>
            <li>Javascript und CSS für Clientdarstellung</li>
          </ul>
        </div>

      </div>
      <div class="row">
      <div class="col-md-8">
      <a href="AddRestaurant.jsp" class="button">Restaurant hinzufügen</a>
      </div>
      </div>
      <!-- /.row -->

      <!-- Related Projects Row -->
      <!-- <h3 class="my-4">Related Projects</h3>

      <div class="row">

        <div class="col-md-3 col-sm-6 mb-4">
          <a href="#">
            <img class="img-fluid" src="http://placehold.it/500x300" alt="">
          </a>
        </div>

        <div class="col-md-3 col-sm-6 mb-4">
          <a href="#">
            <img class="img-fluid" src="http://placehold.it/500x300" alt="">
          </a>
        </div>

        <div class="col-md-3 col-sm-6 mb-4">
          <a href="#">
            <img class="img-fluid" src="http://placehold.it/500x300" alt="">
          </a>
        </div>

        <div class="col-md-3 col-sm-6 mb-4">
          <a href="#">
            <img class="img-fluid" src="http://placehold.it/500x300" alt="">
          </a>
        </div>

      </div> -->
      <!-- /.row -->

    </div>
    <!-- /.container -->

    <!-- Footer -->
    <footer class="py-5 bg-dark" id="footer">
      <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; Students @ JKU</p>
      </div>
      <!-- /.container -->
    </footer>
<script type="text/javascript">

var str = '<%=restaurantsJson %>';
document.getElementById("restaurants").value = str;

/*
for(var i=0; i < restaurants.length; i++)
{
	var lng = restaurants[i]['long'];
	var lat = restaurants[i].lat;
	var name = restaurants[i].name;
	//alert(lng)
	var marker = {coords:{lat:42.8584,lng:-70.9300}, content:'<div id="content"></div>'};
	
	//addMarker(marker);
	//alert('addmarker')
}*/

</script>
    <!-- Bootstrap core JavaScript -->
    <!-- <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script> -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="script.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD-aO02KDkhih5RPBBrc-Rn3Kn4goZx0oE&callback=initMap" async
    defer></script>
    
    
  </body>

</html>
