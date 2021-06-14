
import java.security.PublicKey;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Phornsawan Roemsri
 * <Department of Computer Science | Texas Tech University>
 */
public class KeyExchange {
    
    private PublicKey pubk;
    private Boolean keyBufferFull;
    private Boolean responseBufferFull;
    private String resbuffer;
    

    public KeyExchange() {
        pubk = null;
        keyBufferFull = false;
        responseBufferFull = false;
    }
    public synchronized String send(PublicKey pk){
        pubk = pk;
        keyBufferFull = true;
        notifyAll();
        //waiting for response
        while (!responseBufferFull) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        return resbuffer;
    }
     public synchronized PublicKey receive(){
         //waiting for key
        while (!keyBufferFull) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        return pubk;
    }
     public synchronized void reply(String response) {
        if (!responseBufferFull) {
            resbuffer = response;
            responseBufferFull = true;
            notifyAll(); // send signal
        }
    }
}
