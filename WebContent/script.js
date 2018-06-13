window.onload = function () {
    //alert("onload");
	initMap();
}

function initMap() {

    // Map options
    var options = {
      zoom: 6,
      center: {
        lat: 51.5,
        lng: -0.10589
      }
    }

    // New map
    var map = new google.maps.Map(document.getElementById('map'), options);

    // Listen for click on map
    google.maps.event.addListener(map, 'click', function (event) {
      // Add marker
      addMarker({
        coords: event.latLng
      });
    });

    /*
    // Add marker
    var marker = new google.maps.Marker({
      position:{lat:42.4668,lng:-70.9495},
      map:map,
      icon:'https://developers.google.com/maps/documentation/javascript/examples/full/images/beachflag.png'
    });

    var infoWindow = new google.maps.InfoWindow({
      content:'<h1>Lynn MA</h1>'
    });

    marker.addListener('click', function(){
      infoWindow.open(map, marker);
    });
    */
    //alert('init')
    var str = document.getElementById("restaurants").value;
    //alert(str)
    var restaurants = JSON.parse(str)
    //alert(restaurants[0].name)
    
    for(var i=0; i < restaurants.length; i++)
    {
    	var lng = restaurants[i]['long'];
    	var lat = restaurants[i].lat;
    	var name = restaurants[i].name;
    	var rating = restaurants[i].rating;
    	var content = '<div id="content"><h1>' + name +
    				'</h1><p>Bewertung: ' + rating + 
    				'<form action="RateServlet" method="post">' +
    				'<input type="radio" name="rating" value="1" checked> 1&nbsp;' +
    				  '<input type="radio" name="rating" value="2"> 2&nbsp;' +
    				  '<input type="radio" name="rating" value="3"> 3&nbsp;' +
    				  '<input type="radio" name="rating" value="4"> 4&nbsp; ' +
    				  '<input type="radio" name="rating" value="5"> 5&nbsp; ' +
    				  '<input type="hidden" name="name" value="'+name +'">' +
    				  
    				  '<input type="submit" value="Bewerten">' +
    				
    				'</form>'+
    				'<p><p><a href="EditRestaurant.jsp?restaurant='+name+'">Restaurant bearbeiten</a>&nbsp;'+
    				'<a href="DeleteServlet?restaurant='+name+'" style="margin-left: 40px">Restaurant löschen</a></div>';
    	
    	
    	/*
    	content = '<div id="content">'+
        '<div id="siteNotice">'+
        '</div>'+
        '<h1 id="name" class="name">Name of restaurant</h1>'+
        '<div id="content">'+
        '<p><b>Name of Restaurant</b>, DESCRIPTION ' + 'text ...' +
        '<p>Link to restaurant, <a href="https://de.wikipedia.org/wiki/Restaurant">https://de.wikipedia.org/wiki/Restaurant</a> '+
        '</div>'+
        '</div>';
    	//alert(content);*/
    	var marker = {coords:{lat:lat,lng:lng}, content:content};
	
    	addMarker(marker);
    	//alert('addmarker')
    }
    
    // Array of markers
    var markers = [
      {
        coords: {
          lat: 42.8584,
          lng: -70.9300
        },
        content: '<div id="content">'+
          '<div id="siteNotice">'+
          '</div>'+
          '<h1 id="name" class="name">Name of restaurant</h1>'+
          '<div id="content">'+
          '<p><b>Name of Restaurant</b>, DESCRIPTION ' + 'text ...' +
          '<p>Link to restaurant, <a href="https://de.wikipedia.org/wiki/Restaurant">https://de.wikipedia.org/wiki/Restaurant</a> '+
          
          '</div>'+
          '</div>'    

      },
      {
        coords: {
          lat: 42.7762,
          lng: -71.0773
        },
        content: '<div id="content">'+
          '<div id="siteNotice">'+
          '</div>'+
          '<h1 id="name" class="name">Name of restaurant</h1>'+
          '<div id="content">'+
          '<p><b>Name of Restaurant</b>, DESCRIPTION ' + 'text ...' +
          '<p>Link to restaurant, <a href="https://de.wikipedia.org/wiki/Restaurant">https://de.wikipedia.org/wiki/Restaurant</a> '+
          
          '</div>'+
          '</div>'
      }
    ];

    // Loop through markers
    /*
    for (var i = 0; i < markers.length; i++) {
      // Add marker
      addMarker(markers[i]);
    }*/

    // Add Marker Function
    function addMarker(props) {
      var marker = new google.maps.Marker({
        position: props.coords,
        map: map,
        icon:props.iconImage
      });

      // Check for customicon
      if (props.iconImage) {
        // Set icon image
        marker.setIcon(props.iconImage);
      }

      // Check content
      if (props.content) {
        var infoWindow = new google.maps.InfoWindow({
          content: props.content
        });

        marker.addListener('click', function () {
          infoWindow.open(map, marker);
        });
      }

    }
  }