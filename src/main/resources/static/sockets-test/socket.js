var app = (function () {

    class Service {
        constructor(price, duration, distance, customer, active) {
            this.price = price;
            this.duration = duration;
            this.distance = distance;
            this.customer = customer;
            this.active = active;
        }

    }

    class Customer {
        constructor(email, firstName, lastName, userName, cellPhone, password, apps) {
            this.email = email;
            this.firstName = firstName;
            this.lastName = lastName;
            this.userName = userName;
            this.cellPhone = cellPhone;
            this.password = password;
            this.apps = apps;
        }
    }

    class Driver {
        constructor(email, firstName, lastName, userName, cellPhone, password, apps) {
            this.email = email;
            this.firstName = firstName;
            this.lastName = lastName;
            this.userName = userName;
            this.cellPhone = cellPhone;
            this.password = password;
            this.apps = apps;
        }
    }

    class App{
        constructor(name,user,driver) {
            this.name = name;
        }

    }

    var stompClient = null;

    //Se conecta a el usuario a stomp
    var connectAndSubscribeUser = function () {
        console.log("Connecting to WS...");
        var socket = new SockJS("/stompendpoint");
        stompClient = Stomp.over(socket);

        stompClient.connect({}, function (frame) {
            console.log("Connected: " + frame);
            stompClient.subscribe("/topic/services.users.test@mail.com", function (eventBody) {
                var object = JSON.parse(eventBody.body);
                console.log(object);
            });
        });
    };

    //Conductor se conecta a el stomp, con la lista de apps que tiene el conductor
    var connectAndSubscribeDriver = function (listApps) {
        console.log("Connecting to WS...");
        var socket = new SockJS("/stompendpoint");
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            console.log("Connected: " + frame);
            listApps.forEach(function (app) {
                console.log("Subscribing to " + app.toLowerCase());
                stompClient.subscribe("/topic/services." + app.toLowerCase(), function (eventBody) {
                    var object = JSON.parse(eventBody.body);
                });
            });
        });
    };

    //El usuario publica servicio en /app/services
    var publishService = function () {
        console.log("Publishing....");
        var customer = new Customer("carlos@mail.com","Test","TT","test","44564","123",[new App("Uber"),new App("Didi"),new App("Beat")]);
        var service = new Service(null,null,null,customer,true);
        console.log(service);
        console.log(stompClient);
        stompClient.send("/app/services", {}, JSON.stringify(service));
    };


    var disconnect = function() {
        if (stompClient !== null) {
            stompClient.disconnect();
        }
        //setConnected(false);
        console.log("Disconnected");
    };

    return {
        connectAndSubscribeDriver: connectAndSubscribeDriver,
        connectAndSubscribeUser : connectAndSubscribeUser,
        publishService: publishService,
    }

})();