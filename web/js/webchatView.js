function newWebChatView() {
    var handlers = {};

    function bind(callback, fn) {
        handlers[callback] = fn;
    }

    $('#textbox').on('keyup', function(e) {
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
