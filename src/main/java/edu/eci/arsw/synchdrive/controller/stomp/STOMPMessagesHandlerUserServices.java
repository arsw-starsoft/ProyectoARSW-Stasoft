package edu.eci.arsw.synchdrive.controller.stomp;

import edu.eci.arsw.synchdrive.model.Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class STOMPMessagesHandlerUserServices {

    @Autowired
    SimpMessagingTemplate msgt;

    @MessageMapping("/services.users")
    public void handleServiceEvent(Servicio servicio){

    }
}
