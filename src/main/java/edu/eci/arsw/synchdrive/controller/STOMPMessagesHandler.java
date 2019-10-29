package edu.eci.arsw.synchdrive.controller;

import edu.eci.arsw.synchdrive.model.Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class STOMPMessagesHandler {

    @Autowired
    SimpMessagingTemplate msgt;

    @MessageMapping("/services")
    public void handleServiceEvent(Servicio servicio){
        System.out.println("New service! " + servicio);
        //msgt.convertAndSend("/topic/services."+numService);
    }


}
