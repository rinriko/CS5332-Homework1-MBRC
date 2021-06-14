
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
public class MBRC {

    private Boolean messageBufferFull;
    private Boolean responseBufferFull;
    private Message buffer;
    private Response resbuffer;

    public MBRC() {
        messageBufferFull = false;
        responseBufferFull = false;
    }

    public synchronized Response send(Message message) {
        if (!messageBufferFull) {
            buffer = message;
            messageBufferFull = true;
            notifyAll(); // send signal
        }
        //waiting for response
        while (!responseBufferFull) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        Response response = resbuffer;
        resbuffer = null;// remove response from response buffer;
        responseBufferFull = false;
        return response;
    }

    public synchronized Message receive() {
        //waiting for message
        while (!messageBufferFull) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        Message message = buffer;
        buffer = null; // remove message from buffer;
        messageBufferFull = false;

        return message;
    }

    public synchronized void reply(Response response) {
        if (!responseBufferFull) {
            resbuffer = response;
            responseBufferFull = true;
            notifyAll(); // send signal
        }
    }
}
