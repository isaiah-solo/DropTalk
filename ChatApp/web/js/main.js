function main() {
	var model = newWebChatModel();
	var view = newWebChatView(document.getElementById("post"));
	var controller = newWebChatController(model, view);

}
