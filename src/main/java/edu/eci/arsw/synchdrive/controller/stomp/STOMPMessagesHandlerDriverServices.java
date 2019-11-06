package edu.eci.arsw.synchdrive.controller.stomp;

import edu.eci.arsw.synchdrive.model.Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Timer;
import java.util.TimerTask;

@Controller
public class STOMPMessagesHandlerDriverServices {

    @Autowired
    SimpMessagingTemplate msgt;

    @MessageMapping("/services.{appOne}")
    public void handleServiceEvent(Servicio servicio, @DestinationVariable("appOne") String appName){
        //System.out.println("New service! " + servicio + " " + appName);
        msgt.convertAndSend("/topic/services."+appName,servicio);
    }


}
