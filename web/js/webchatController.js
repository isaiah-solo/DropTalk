var stamp = "";

function newWebChatController(model, view) {
	view.bind('sendText', function() {
	    var textarea = document.getElementById("fname").value;
        // Only send stuff if there's stuff to send
        if(textarea !== ""){
		    model.postText(textarea);

            // Clear textarea
	        document.getElementById("fname").value = "";

        }
	});

	view.bind('loadTexts', function(handlers) {
		model.getTexts(handlers);
	});

	view.bind('populateFeed', function(data) {
        // Have a variable to hold all of the elements at once
        // so we can replace the whole ul seamlessly
	var flag = 0;
        var blob = "";
		for(i = 0; i < data.length; i++) {
			var text = data[i].message;
			if (data[i].timestamp == stamp) {
				flag = 1;
			} else if (stamp.length > 0 && i == data.length - 1) {
				stamp = data[i].timestamp;
				blob += "<li>" + text + "</li>";
			} else if (stamp.length == 0 && i == data.length - 1) {
				stamp = data[i].timestamp;
			} else if (flag == 1 || stamp.length == 0) {
				blob += "<li>" + text + "</li>";
			}
		}

	if (blob.length > 0) {
        	$("#inside").append(blob);
		$('#inside').scrollTop($('#inside')[0].scrollHeight);
	}
	});

	return {};
}
