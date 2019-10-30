package edu.eci.arsw.synchdrive.controller;

import edu.eci.arsw.synchdrive.model.Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Timer;
import java.util.TimerTask;

@Controller
public class STOMPMessagesHandler {

    @Autowired
    SimpMessagingTemplate msgt;

    @MessageMapping("/services")
    public void handleServiceEvent(Servicio servicio){
        System.out.println("New service! " + servicio);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Servicio servicio1 = new Servicio();
                servicio1.setPrice(24.0);
                servicio1.setDistance(78.09);
                servicio1.setDuration(18.14);
                msgt.convertAndSend("/topic/services",servicio1);
            }
        };
        Timer timer = new Timer(true);
        timer.schedule(timerTask,5000,3*100);
        //msgt.convertAndSend("/topic/services."+numService);
    }


}
