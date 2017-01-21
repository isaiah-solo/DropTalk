function newWebChatController(model, view) {
	view.bind('tester', function() {
		model.test();
	});

	return {};
}
