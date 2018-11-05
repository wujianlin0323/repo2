(function(global) {
	var helpAge = {}
	helpAge.createUser = {
		el: "#helpAgeContent",

		methods: {
			testClick() {
				var url = "activeInfo.html";
				window.location.href = url;
			}
		}
	}
	global.helpAgeApp = new Vue(helpAge.createUser)
})(this)