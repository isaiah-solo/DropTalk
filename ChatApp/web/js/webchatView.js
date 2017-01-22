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

    $('#fname').on('keyup', function(e) {
        if (e.which == 13 && handlers.sendText) {
			handlers.sendText();
        }
    });

	window.onload = function() {
		if (handlers.loadTexts) {
			handlers.loadTexts(handlers);

            // Refresh every second
            setInterval(function(){ 
                handlers.loadTexts(handlers);
            }, 500);
		}
	};

	return {
		bind: bind
	};
}
