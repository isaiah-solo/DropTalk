function main() {
	var model = newWebChatModel();
	var view = newWebChatView(document.getElementById("submitBox"));
	var controller = newWebChatController(model, view);

}
