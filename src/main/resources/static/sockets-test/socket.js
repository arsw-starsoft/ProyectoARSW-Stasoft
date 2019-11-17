var app = (function () {

    class Service {
        constructor(price, duration, distance, customer, active,app) {
            this.price = price;
            this.duration = duration;
            this.distance = distance;
            this.customer = customer;
            this.active = active;
            this.app = app;
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

    var customer = new Customer("luis@mail.com","Test","TT","test","44564","123",[new App("Uber"),new App("Didi"),new App("Beat")]);
    var driver = new Driver("julian@mail.com","t","t","t","t","1",[new App("Uber"),new App("Didi"),new App("Beat")]);

    var webSocketActive = [];

    //Se conecta a el usuario a stomp
    var connectAndSubscribeUser = function () {
        console.log("Connecting to WS...");
        var socket = new SockJS("/stompendpoint");
        stompClient = Stomp.over(socket);

        stompClient.connect({}, function (frame) {
            console.log("Connected: " + frame);
            //Canal del usuario
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
                    console.log(object);
                    webSocketActive.push(object[0]);
                    console.log(webSocketActive);
                });

            });

            //Esta pendiente de los servicios cancelados para eliminarlos
            stompClient.subscribe("/topic/canceled",function (eventBody) {
                var object = JSON.parse(eventBody.body);
                console.log("Canceled... " + object);
            });

            //Está pendiente de los servicios acceptados para eliminarlos
            stompClient.subscribe("/topic/accepted",function (eventBody) {
                var object = JSON.parse(eventBody.body);
                console.log("Accepted... " + object);
                var indexService = webSocketActive.indexOf(object[0]);
                webSocketActive.splice(indexService,1);
                console.log("Removed from arr " + webSocketActive);
            });
        });
    };

    //El usuario publica servicio en /app/services
    var publishService = function () {
        console.log("Publishing....");

        var service = new Service(null,null,null,customer,true,null);
        console.log(service);
        console.log(stompClient);
        stompClient.send("/app/services", {}, JSON.stringify(service));
    };

    var publishAcceptService = function (service) {
       stompClient.send("/topic/accepted",{},JSON.stringify(service));
    };

    var acceptService = function (service,callback) {
      console.log("Accepting... "  + service);
        $.ajax({
            method: "PUT",
            contentType: "application/json",
            url: "http://localhost:8080/servicios/"+driver.email+"/"+service.app.name,
            data: JSON.stringify(service),
            success: function () {
                console.log("Success");
                callback(service);
            },
            error: function () {
                alert("ERROR ACCEPTING");
            }
        });
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
        acceptService: acceptService,
        publishAcceptService : publishAcceptService,
        webSocketActive : webSocketActive
    }

})();