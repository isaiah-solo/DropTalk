function newWebChatView(buttonElem) {
    // Map between callbacks and their handlers
    var handlers = {};

    function bind(callback, fn) {
        handlers[callback] = fn;
    }

    buttonElem.onclick = function() {
        if (handlers.tester) {
            handlers.tester();
        }
    };

    return {
        bind: bind
    };
}
