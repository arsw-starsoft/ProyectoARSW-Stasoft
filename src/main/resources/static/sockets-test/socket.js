var app = (function () {

    class Service {
        constructor(price, duration, distance, customer, active,app, idPeticion,destino) {
            this.price = price;
            this.duration = duration;
            this.distance = distance;
            this.customer = customer;
            this.active = active;
            this.app = app;
            this.idPeticion = idPeticion;
            this.destino = destino;
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
    var idCount = 0;
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
            listApps.forEach(function (appp) {
                console.log("Subscribing to " + appp.toLowerCase());
                stompClient.subscribe("/topic/services." + appp.toLowerCase(), function (eventBody) {
                    var object = JSON.parse(eventBody.body);
                    console.log("LLEGA! " + object);
                    object.forEach(function (s) {
                        app.webSocketActive.push(s);
                    });
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
                app.webSocketActive = [];
                console.log("Web "  + app.webSocketActive);
                object.forEach(function (obj) {
                    app.webSocketActive.push(obj);
                })
            });
        });
    };

    //El usuario publica servicio en /app/services
    var publishService = function () {
        for (var i = 0; i< 2; i++) {
            idCount++;
            var service = new Service(null,null,null,customer,true,null,idCount,"SPM"+i);
            console.log("Publishing....");
            console.log(service);
            console.log(stompClient);
            stompClient.send("/app/services", {}, JSON.stringify(service));
        }
    };

    var publishAcceptService = function (service) {
        var list = webSocketActive.filter(function (serv) {
            return serv.idPeticion !== service.idPeticion;
        });
        stompClient.send("/topic/accepted",{},JSON.stringify(list));
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