package edu.eci.arsw.synchdrive.persistence;

public class SynchdrivePersistenceException extends Exception {

    public static final String CAR_NOT_FOUND = "Car not found";

    public SynchdrivePersistenceException(){
        super();
    }

    public SynchdrivePersistenceException(String msg){
        super(msg);
    }

}
