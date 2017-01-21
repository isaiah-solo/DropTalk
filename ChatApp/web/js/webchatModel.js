function newWebChatModel() {
	function sendText() {
		var test = {
			user: "isaiah",
			text: "This is a test",
		};
		$.ajax({
			type: 'POST',
			data: test,
			dataType: "json",
			url: "",
			success: function(responseData, textStatus, jqXHR) {
				console.log(responseData);
			},
			error: function(responseData, textStatus, errorThrown) {
				console.log(responseData.responseText);
			}
       		});
	}

	return {
		sendText: sendText,
	};
}

function Text(currentUser, newText) {
	var user = currentUser;
	var text = newText;

	function getUser() {
		return user;
	}

	function getText() {
		return text;
	}

	return {
		getUser: getUser,
		getText: getText,
	}
}
