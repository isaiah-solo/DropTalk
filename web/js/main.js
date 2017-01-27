function main() {
	var model = newWebChatModel();
	var view = newWebChatView();
	var controller = newWebChatController(model, view);
}
