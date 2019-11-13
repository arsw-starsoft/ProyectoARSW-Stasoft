package edu.eci.arsw.synchdrive.controller.stomp;

import edu.eci.arsw.synchdrive.connection.HttpConnectionService;
import edu.eci.arsw.synchdrive.model.App;
import edu.eci.arsw.synchdrive.model.Servicio;

import java.io.IOException;
import java.util.*;
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
        Map<String,Queue<Servicio>> servicios = servicioServices.generateServices(servicio);

        //Si no esta iniciado el task
        if (!servicioServices.isStarted()) {
            System.out.println("Starting task...");
            servicioServices.setStarted(true);
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    //CONSULTAR QUIENS SIGUEN ACTIVOS
                    Map<String,Queue<Servicio>> servicios = servicioServices.loadActiveServices();

                    msgt.convertAndSend("/topic/services.uber", servicios.get("uber"));
                    msgt.convertAndSend("/topic/services.didi", servicios.get("didi"));
                    msgt.convertAndSend("/topic/services.beat", servicios.get("beat"));

                    //Limpiar "cache"
                    servicioServices.cleanServices();
                }
            }, 10, 10000);
        }
    }
}


