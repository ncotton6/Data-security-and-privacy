/**
 * 
 */

function initMap() {
	var directionsService = new google.maps.DirectionsService;
	var directionsDisplay = new google.maps.DirectionsRenderer;
	var geoCoder = new google.maps.Geocoder();
	var map = new google.maps.Map(document.getElementById('map'), {
		zoom : 7,
		center : {
			lat : 41.85,
			lng : -87.65
		}
	});
	directionsDisplay.setMap(map);

	var addr = document.getElementById('baseLocation').value;
	geoCoder.geocode({
		'address' : addr
	}, function(results, status) {
		if (status === google.maps.GeocoderStatus.OK) {
			map.setCenter(results[0].geometry.location);
			map.setZoom(10);
		}
	});

	$(document).ready(function() {
		var event = null;
		var food = null;

		var body = $('body');
		body.on('click', '.event', function() {
			var $this = $(this);
			$('.event').find('.well').css('background-color','#f5f5f5');
			$this.find('.well').css('background-color','green');
			event = new google.maps.LatLng($this.find('.lat').val(),$this.find('.long').val());
			changeMap(event,food);
		});
		body.on('click', '.food', function() {
			var $this = $(this);
			$('.food').find('.well').css('background-color','#f5f5f5');
			$this.find('.well').css('background-color','green');
			food = new google.maps.LatLng($this.find('.lat').val(),$this.find('.long').val());
			changeMap(event,food);
		})

		var changeMap = function(event, food) {
			if (event != null && food != null) {
				directionsService.route({
					origin : event,
					destination : food,
					travelMode : google.maps.TravelMode.DRIVING
				}, function(response, status) {
					if (status === google.maps.DirectionsStatus.OK) {
						directionsDisplay.setDirections(response);
					} else {
						window.alert('Failed to get directions');
					}
				});
			}
		}
	});
}