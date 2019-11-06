package edu.eci.arsw.synchdrive.controller.stomp;

import edu.eci.arsw.synchdrive.model.Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


@Controller
public class STOMPMessagesHandlerDriverServices {

    @Autowired
    SimpMessagingTemplate msgt;

    @MessageMapping("/services.{appOne}")
    public void handleServiceEvent(Servicio servicio, @DestinationVariable("appOne") String appName1){
        System.out.println("Nueva solicitud de servicio " + servicio + " " + appName1);
        //Generar servicio
        msgt.convertAndSend("/topic/services."+appName1,servicio);
    }

    @MessageMapping("/services.{appOne}.{appTwo}")
    public void handleServiceEvent(Servicio servicio, @DestinationVariable("appOne") String appName1,@DestinationVariable("appTwo") String appName2){
        //TODO
    }

    @MessageMapping("/services.{appOne}.{appTwo}.{appThree}")
    public void handleServiceEvent(Servicio servicio, @DestinationVariable("appOne") String appName1,@DestinationVariable("appTwo") String appName2,@DestinationVariable("appThree") String appName3){
        //TODO
    }


}
