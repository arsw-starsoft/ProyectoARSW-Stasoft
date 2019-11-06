var app = (function () {

    class Service {
        constructor(price, duration, distance, customer) {
            this.price = price;
            this.duration = duration;
            this.distance = distance;
            this.customer = customer;
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

    class App{
        constructor(name) {
            this.name = name;
        }

    }

    var stompClient = null;
    var serviceObjectDriver = null;

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
        listApps.sort(); //Ordenar para el back message  uber didi beat
        var stringMessage = "";
        listApps.forEach(function (app) {
            stringMessage += "." + app.toLowerCase();
        });
        console.log(stringMessage);
        var socket = new SockJS("/stompendpoint");
        stompClient = Stomp.over(socket);

        stompClient.connect({}, function (frame) {
            console.log("Connected: " + frame);
            stompClient.subscribe("/topic/services" + stringMessage, function (eventBody) {
                var object = JSON.parse(eventBody.body);
                serviceObjectDriver = object;
            });
        });
    };

    //El usuario publica en el topico x
    var publishService = function () {
        console.log("Publishing....");
        var customer = new Customer("test@mail.com","Test","TT","test","44564","123",[new App("Uber")]);
        var service = new Service(null,null,null,customer);
        console.log(service);
        console.log(stompClient);
        var listApps = customer.apps;
        listApps.sort(function (n1,n2) {
            return n1 > n2;
        }); //Ordenar para el back message
        var stringMessage = "";
        listApps.forEach(function (app) {
            stringMessage += "." + app.name.toLowerCase();
        });
        console.log(stringMessage);
        stompClient.send("/app/services" + stringMessage, {}, JSON.stringify(service));
    };

    var connectAndSubscribeDriverToSingle = function (usernameService,callback) {
        console.log("Connecting to WS...");
        var socket = new SockJS("/stompendpoint");
        stompClient = Stomp.over(socket);

        stompClient.connect({}, function (frame) {
            console.log("Connected: " + frame);
            stompClient.subscribe("/topic/services.users." + usernameService, function (eventBody) {
                var object = JSON.parse(eventBody.body);
                serviceObjectDriver = object;
            });
        });
        callback("/app/services.users."+usernameService,{},JSON.stringify(serviceObjectDriver));
    };

    var acceptService = function (usernameService) {
        console.log("Disconnecting from services...");
        disconnect();
        console.log("Connected to single stomp");
        connectAndSubscribeDriverToSingle(usernameService,stompClient.send);
        console.log("Accepting service from " + usernameService + "...");
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
        acceptService: acceptService
    }

})();