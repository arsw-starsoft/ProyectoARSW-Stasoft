var token;

function login(username,password){
    console.log(username,password);

    var loginData = {username: username, password: password};

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/authenticate",
        contentType:"application/json; charset=utf-8", //importante para el back
        dataType: 'json',
        data : JSON.stringify(loginData) ,
        success: function (data){
            alert('Thanks for your comment!');
            token = data["token"]; //jwt token
        }
    });
}

function tokenSet(){
    return "Bearer " + token; //deja el token con 'bearer' para el back
}

function consultarApi(){
    token = tokenSet();
    console.log(token);
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/cars",
        headers: { "Authorization": token} //Header de autorización
      }).done(function(data){
          console.log(data);
      });
}
