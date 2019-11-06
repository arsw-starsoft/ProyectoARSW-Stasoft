package edu.eci.arsw.synchdrive.controller.stomp;

import edu.eci.arsw.synchdrive.model.App;
import edu.eci.arsw.synchdrive.model.Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Timer;
import java.util.TimerTask;

@Controller
public class STOMPMessagesHandlerUserServices {

    @Autowired
    SimpMessagingTemplate msgt;

    @MessageMapping("/services.users")
    public void handleServiceEvent(Servicio servicio){
        StringBuilder toTopicMessage = new StringBuilder();
        for (App app : servicio.getCustomer().getApps()){
            toTopicMessage.append("."); toTopicMessage.append(app.getName().toLowerCase());
        }
        System.out.println("New service! " + servicio + " " + toTopicMessage.toString());
        msgt.convertAndSend("/topic/services"+toTopicMessage.toString(),servicio);
    }


}
