function newWebChatController(model, view) {
	view.bind('sendText', function() {
	    var textarea = document.getElementById("textarea").value;
        // Only send stuff if there's stuff to send
        if(textarea !== ""){
		    model.postText(textarea);

            // Clear textarea
	        document.getElementById("textarea").value = "";

            $("#list").append(
                $('<li>').append(textarea)
            );
        }
	});

	view.bind('loadTexts', function(handlers) {
		model.getTexts(handlers);
	});

	view.bind('populateFeed', function(data) {
        // Have a variable to hold all of the elements at once
        // so we can replace the whole ul seamlessly
        var blob = "";
		for(i = 0; i < data.length; i++) {
			var text = data[i].message;
            blob += "<li>"+text+"</li>";
		}
        $("#list").html(blob);
	});

	return {};
}
