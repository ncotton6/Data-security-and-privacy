/**
 * While limited, this script will provide the functionality needed for
 * google maps to be inserted into the application.
 * 
 * @author Nathaniel Cotton
 */

/**
 * The google api uses a callback method to initiate the loading of the map.
 * This function is the entry point.
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

	/**
	 * Gets the base location entered on the first part of the application, and
	 * centers the map at that location.
	 */
	var addr = document.getElementById('baseLocation').value;
	geoCoder.geocode({
		'address' : addr
	}, function(results, status) {
		if (status === google.maps.GeocoderStatus.OK) {
			map.setCenter(results[0].geometry.location);
			map.setZoom(10);
		}
	});

	/**
	 * For ease of use jQuery was added to perform some of the color changes,
	 * and element lookups.
	 */
	$(document).ready(
			function() {
				var event = null;
				var food = null;

				var body = $('body');
				/**
				 * Run when an event is clicked. It will color the clicked event
				 * green, and set it as one of the locations on the map.
				 */
				body.on('click', '.event', function() {
					var $this = $(this);
					$('.event').find('.well')
							.css('background-color', '#f5f5f5');
					$this.find('.well').css('background-color', 'green');
					event = new google.maps.LatLng($this.find('.lat').val(),
							$this.find('.long').val());
					changeMap(event, food);
				});
				/**
				 * Run when a food is clicked. It will color the clicked food
				 * green, and set it as one of the locations on the map.
				 */
				body.on('click', '.food',
						function() {
							var $this = $(this);
							$('.food').find('.well').css('background-color',
									'#f5f5f5');
							$this.find('.well')
									.css('background-color', 'green');
							food = new google.maps.LatLng($this.find('.lat')
									.val(), $this.find('.long').val());
							changeMap(event, food);
						})

				/**
				 * Given an event and a food, the directions between the two
				 * will be generated.
				 */
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