// Expose the native API to javascript
forge.card_io = {
    showAlert: function (text, success, error) {
        forge.internal.call('card_io.showAlert', {text: text}, success, error);
    }
};

// Register for our native event
forge.internal.addEventListener("card_io.resume", function () {
	alert("Weclome back!");
});
