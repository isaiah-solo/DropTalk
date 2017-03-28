function newWebChatModel() {
    function postText(text, lat, lng) {
        var data = {
            user_id: "isaiah",
            message: text,
            latitude: lat.toString(),
            longitude: lng.toString(),
        };
        $.ajax({
            type: 'POST',
            data: data,
            dataType: "text",
	    url: "https://hackucsc2017-156309.appspot.com/post",
            success: function(responseData, textStatus, jqXHR) {
            },
            error: function(responseData, textStatus, errorThrown) {
                console.log(errorThrown);
            }
        });
    }

    function getTexts(handlers) {
        $.ajax({
            type: 'GET',
            dataType: "text",
            url: "https://hackucsc2017-156309.appspot.com/get",
            success: function(responseData, textStatus, jqXHR) {
                handlers.populateFeed(JSON.parse(responseData));
            },
            error: function(responseData, textStatus, errorThrown) {
                console.log(errorThrown);
            }
        });
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
