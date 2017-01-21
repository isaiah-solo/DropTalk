function main() {
	var model = newWebChatModel();
	var view = newWebChatView(document.getElementById("butt"));
	var controller = newWebChatController(model, view);
}
