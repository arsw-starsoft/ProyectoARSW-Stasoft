$peticiones = 1;
$hilos = 1;
For ($i = 0; $i -lt $hilos; $i++){
    Start-Process 'cmd' -ArgumentList "/c start newman run .\database.postman_collection.json -e .\[database].postman_enviroment.json -n $peticiones";
}