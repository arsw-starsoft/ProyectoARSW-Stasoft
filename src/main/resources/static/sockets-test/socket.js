var app = (function () {

    class Service{
        constructor(price, duration,distance){
            this.price = price;
            this.duration = duration;
            this.distance = distance;
        }
    }

    var stompClient = null;

    var connectAndSubscribe = function(){
        console.log("Connecting to WS...");
        var socket = new SockJS("/stompendpoint");
        stompClient = Stomp.over(socket);

        stompClient.connect({}, function (frame) {
            console.log("Connected: " + frame);
            stompClient.subscribe("/topic/services",function (eventBody) {
                var object = JSON.parse(eventBody.body);
                console.log(object);
            });
        });
    };

    var publishService = function () {
      console.log("Publishing....");
      var service = new Service(15,15,100);
      console.log(service);
      console.log(stompClient);
      stompClient.send("/app/services",{},JSON.stringify(service));
    };

    return {
        connectAndSubscribe: connectAndSubscribe,
        publishService:publishService
    }

})();