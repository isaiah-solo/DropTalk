function newWebChatView(buttonElem) {
	// Map between callbacks and their handlers
	var handlers = {};

	function bind(callback, fn) {
		handlers[callback] = fn;
	}

	buttonElem.onclick = function() {
		if (handlers.sendText) {
			handlers.sendText();
		}
	};

	window.onload = function() {
		if (handlers.loadTexts) {
			handlers.loadTexts(handlers);
		}
	};

	return {
		bind: bind
	};
}
