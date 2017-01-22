function newWebChatController(model, view) {
	view.bind('sendText', function() {
		model.postText();
	});

	view.bind('loadTexts', function(handlers) {
		model.getTexts(handlers);
	});

	view.bind('populateFeed', function(data) {
		var messageList = document.getElementById("list");
		for(i = 0; i < data.length; i++) {
			var element = document.createElement("li");
			var text = data[i].timestamp;
			element.appendChild(document.createTextNode(text + "\n"));
		}
	});

	return {};
}
