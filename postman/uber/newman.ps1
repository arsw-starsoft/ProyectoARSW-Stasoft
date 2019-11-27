$peticiones = 10;
$hilos = 1;
For ($i = 0; $i -lt $hilos; $i++){
    Start-Process 'cmd' -ArgumentList "/c start newman run .\uber.postman_collection.json -e .\[uber].postman_enviroment.json -n $peticiones";
}