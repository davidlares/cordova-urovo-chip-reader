
var app = {
    // Application Constructor
    initialize: function() {
        document.addEventListener('deviceready', this.onDeviceReady.bind(this), false);
    },

    // deviceready Event Handler
    //
    // Bind any cordova events here. Common events are:
    // 'pause', 'resume', etc.
    onDeviceReady: function() {
        this.receivedEvent('deviceready');
    },

    // Update DOM on a Received Event
    receivedEvent: function(id) {

        var atrSlot = document.getElementById("atr-slot");
        var rspSlot = document.getElementById("rsp-slot");
        var button = document.getElementById("trigger");

        atrSlot.style.display = "none";
        rspSlot.style.display = "none";

        button.addEventListener("click", function() {
          cordova.exec(apduResponse, null, "ChipPlugin", "transmitApdu");
          rspSlot.style.display = "block";
        });

      	// custom
      	function success(result) {
      	  alert(result);
      	}

        function printATR(atr) {
          if(atr != "") {
            atrSlot.style.display = "block";
            document.getElementById("atr").innerText = atr;
          }
        }

        function apduResponse(rsp) {
          var data = rsp.split(" - ");
          document.getElementById("rsp").innerText = "RSP: " + data[0];
          document.getElementById("status").innerText = "STATUS: " + data[1];
        }

        cordova.exec(success, null, "ChipPlugin", "openReader");

        // set timeout
      	setInterval(function() {
      	  cordova.exec(printATR, null, "ChipPlugin", "detectCard");
      	}, 2000);

    }
};

app.initialize();
