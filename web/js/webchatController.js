var stamp = "";

function newWebChatController(model, view) {
    view.bind('sendText', function() {
        var textbox = document.getElementById("textbox").value;
        if (textbox !== "") {
            model.postText(textbox);
            document.getElementById("textbox").value = "";
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

    return {};
}
