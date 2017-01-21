function newWebChatModel() {
	function test() {
		console.log("test");
	}

	function sendText(text) {
		$.ajax({
			type: 'POST',
			data: text,
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
		test: test,
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
