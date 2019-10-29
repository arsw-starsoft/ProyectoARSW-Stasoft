var app = (function () {

    var stompClient = null;

    var connectAndSubscribe = function(){
        console.log("Connecting to WS...");
        var socket = new SockJS("/stompendpoint");
        var stompClient = Stomp.over(socket);

        stompClient.connect({}, function (frame) {
            console.log("Connected: " + frame);
            stompClient.subscribe("/topic/services",function (eventBody) {
                var object = JSON.parse(eventBody.body);
                console.log(object);
            });
        });
    };

    return {
        connectAndSubscribe: connectAndSubscribe
    }

})();