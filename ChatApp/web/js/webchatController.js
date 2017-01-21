function newWebChatController(model, view) {
	view.bind('sendText', function() {
		model.sendText();
	});

	return {};
}
