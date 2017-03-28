var stamp = "";

function newWebChatController(model, view) {
    var message = "";

    view.bind('sendText', function() {
        if (document.getElementById("textbox").value !== "") {
            message = document.getElementById("textbox").value;
            document.getElementById("textbox").value = "";
            navigator.geolocation.getCurrentPosition(successLocation, errorLocation);
        }
    });

    view.bind('loadTexts', function(handlers) {
        model.getTexts(handlers);
    });

    view.bind('populateFeed', function(data) {
        var flag = 0;
        var blob = "";
        for(i = 0; i < data.length; i++) {
            var text = data[i].message;
            if (data[i].timestamp == stamp) {
                flag = 1;
            } else if (stamp.length > 0 && i == data.length - 1) {
                stamp = data[i].timestamp;
                blob += "<li>" + text + "</li>";
            } else if (stamp.length == 0 && i == data.length - 1) {
                stamp = data[i].timestamp;
                blob += "<li>" + text + "</li>";
            } else if (flag == 1 || stamp.length == 0) {
                blob += "<li>" + text + "</li>";
            }
        }

        if (blob.length > 0) {
            $("#messagelist").append(blob);
            $("#messagelist").animate({scrollTop: $("#messagelist")[0].scrollHeight});
        }
    });

    function successLocation(position) {
        if (message !== "") {
            model.postText(message, position.coords.latitude, position.coords.longitude);
            message = "";
        }
    }

    function errorLocation(position) {
        console.log("error");
    }

    return {};
}
