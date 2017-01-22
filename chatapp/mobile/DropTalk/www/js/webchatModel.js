import { Geolocation } from 'ionic-native';


function newWebChatModel() {
	function postText(textarea) {
		var data = {
			user_id: "isaiah",
			message: textarea,
			latitude: "1.1",
			longitude: "1.1",
		};
		$.ajax({
			type: 'POST',
			data: data,
			dataType: "application/json",
			url: "https://hackucsc2017-156309.appspot.com/post",
			success: function(responseData, textStatus, jqXHR) {
                console.log("Successfully posted");
			},
			error: function(responseData, textStatus, errorThrown) {
				console.log(responseData.responseText);
			}
       		});
	}

	function getTexts(handlers) {

   // onSuccess Callback
    // This method accepts a Position object, which contains the
    // current GPS coordinates
    //
    var onSuccess = function(position) {
        alert('Latitude: '          + position.coords.latitude          + '\n' +
              'Longitude: '         + position.coords.longitude         + '\n' +
              'Altitude: '          + position.coords.altitude          + '\n' +
              'Accuracy: '          + position.coords.accuracy          + '\n' +
              'Altitude Accuracy: ' + position.coords.altitudeAccuracy  + '\n' +
              'Heading: '           + position.coords.heading           + '\n' +
              'Speed: '             + position.coords.speed             + '\n' +
              'Timestamp: '         + position.timestamp                + '\n');
			var data = {
				latitude: position.coords.latitude,
				longitude: position.coords.longitude,
			};
			$.ajax({
				type: 'POST',
				data: data,
				dataType: "application/json",
				url: "https://hackucsc2017-156309.appspot.com/postToGet",
				success: function(responseData, textStatus, jqXHR) {
	                handlers.populateFeed(JSON.parse(responseData))
				},
				error: function(responseData, textStatus, errorThrown) {
					console.log(responseData.responseText);
				}
	       		});
    };

    // onError Callback receives a PositionError object
    //
    function onError(error) {
        alert('code: '    + error.code    + '\n' +
              'message: ' + error.message + '\n');
    }

		navigator.geolocation.getCurrentPosition(onSuccess, onError);

	}

	return {
		postText: postText,
		getTexts: getTexts,
	};
}

function Texts(data) {
	var texts = [];

	console.log("Help: " + data.timestamp);

	for (i = 0; i < data.length; i++) {
		texts[i] = Text(data.user_id, data.message, data.timestamp, data.latitude, data.longitude);
	}

	function getText(index) {
		return texts[index];
	}

	function getLength() {
		return texts.length;
	}

	return {
		getText: getText,
		getLength: getLength,
	}
}

function Text(newUserId, newMessage, newTimestamp, newLat, newLng) {
	var userId = newUserId;
	var message = newMessage;
	var timestamp = newTimestamp;
	var lat = newLat;
	var lng = newLng;

	function getUserId() {
		return userId;
	}

	function getMessage() {
		return message;
	}

	function getTimestamp() {
		return timestamp;
	}

	function getLat() {
		return lat;
	}

	function getLng() {
		return lng;
	}

	return {
		getUserId: getUserId,
		getMessage: getMessage,
		getTimestamp: getTimestamp,
		getLat: getLat,
		getLng: getLng,
	}
}
