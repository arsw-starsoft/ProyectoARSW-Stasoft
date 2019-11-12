package edu.eci.arsw.synchdrive.controller.stomp;

import edu.eci.arsw.synchdrive.connection.HttpConnectionService;
import edu.eci.arsw.synchdrive.model.App;
import edu.eci.arsw.synchdrive.model.Servicio;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import edu.eci.arsw.synchdrive.persistence.ServicioRepository;
import edu.eci.arsw.synchdrive.persistence.UserRepository;
import edu.eci.arsw.synchdrive.services.ServicioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Queue;

@Controller
public class STOMPMessagesHandlerServices {

    @Autowired
    SimpMessagingTemplate msgt;

    @Autowired
    ServicioServices servicioServices;


    @MessageMapping("/services")
    public void handleServiceEvent(Servicio servicio) {
        System.out.println("Nueva solicitud de servicio " + servicio + " " + servicio.getCustomer().getApps());
        // Generar servicios
        Queue<Servicio> servicios = servicioServices.generateServices(servicio);

        //Si no esta iniciado el task
        if (!servicioServices.isStarted()) {
            System.out.println("Starting task...");
            servicioServices.setStarted(true);
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    //Tarea se ejecuta cada x tiempo, por cada servicio solicitado, se guarda en base de datos.
                    for (Servicio servicio : servicios) {
                        servicioServices.saveService(servicio);
                        //Por cada app del servicio, se envia al correspondiente canal.
                        System.out.println(servicio.getCustomer().getApps());
                        for (App app : servicio.getCustomer().getApps()) {
                            msgt.convertAndSend("/topic/services." + app.getName().toLowerCase(), servicio);
                        }
                    }
                    //Limpiar "cache"
                    servicioServices.cleanServices();

                }
            }, 10, 10000);
        }
    }
}


