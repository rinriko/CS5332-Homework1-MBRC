
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.logging.Level;
import java.util.logging.Logger;


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
public class Sender extends Thread {

    private final MBRC mbrcObj;
    private final KeyExchange keyObj;
    private final Message[] messages;
    private final Message endMessage;
    private PublicKey pubk;
    private PrivateKey prvk;

    public Sender(MBRC Obj, KeyExchange k) {
        mbrcObj = Obj;
        messages = new Message[7];
        messages[0] = new Message("add", 4);
        messages[1] = new Message("multiply", 1);
        messages[2] = new Message("multiply", 8);
        messages[3] = new Message("add", 2);
        messages[4] = new Message("add", 3);
        messages[5] = new Message("add", 99);
        messages[6] = new Message("multiply", 53);
        endMessage = new Message("end", 0);
        keyObj = k;
    }

    public void setKeyAndSendToReceiver() throws NoSuchAlgorithmException {
        // Generate a key-pair
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA");
        kpg.initialize(512); // 512 is the key size.
        KeyPair kp = kpg.generateKeyPair();
        pubk = kp.getPublic();
        prvk = kp.getPrivate();
        //send the public key to key buffer (waiting for reciever get it)

        System.out.println("The Sender (" + Thread.currentThread().getName() + ") is about to send the Sender's public key to the Reciever.");
        String response = keyObj.send(pubk);
        System.out.println("The Sender (" + Thread.currentThread().getName() + ") acknowledges that " + response);
        System.out.println("                --------------------------------------------                ");
    }

    public void sendEndMessage() throws Exception {

        byte[] signature = sign(endMessage.getMessageByte(), prvk);
        endMessage.setSignature(signature);
        Response response = mbrcObj.send(endMessage);
        boolean verifyResult = verify(response.getHashValue(), response.getStrResult());

        System.out.println();
        if (verifyResult) {
            System.out.println("                --------------------------------------------                ");
            System.out.println(response.getStrResult());
            System.out.println("========================================================================");
        } else {
            System.out.println("Ending Message has been corrupted");
        }
    }

    //generate a digial signature
    public static byte[] sign(byte message[], PrivateKey prvk) throws Exception {
        Signature sg = Signature.getInstance("SHA1withDSA");
        sg.initSign(prvk);
        sg.update(message);
        return sg.sign();
    }

    //verify a message digest
    public static Boolean verify(byte[] hashValue, String msg) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] msgBytes = msg.getBytes();
        md.update(msgBytes);
        byte[] mdBytes = md.digest();
        return MessageDigest.isEqual(hashValue, mdBytes);
    }

    public void printReceivedResult(Response response) {
        System.out.println("The Sender (" + Thread.currentThread().getName() + ") receives the response (or the result) that valid and not corrupted from the Receiver, which is " + response.getStrResult() + ".");
    }

    public void printMessage(Message message) {
        System.out.println();
        System.out.println("The Sender (" + Thread.currentThread().getName() + ") is about to send the message: (\"" + message.getName() + "\", " + message.getContent() + ") to the Reciever.");
    }

    @Override
    public void run() {
        try {
            setKeyAndSendToReceiver();
            for (Message message : messages) {
                printMessage(message);
                try {
                    byte[] signature = sign(message.getMessageByte(), prvk);
                    message.setSignature(signature);
                } catch (Exception ex) {
                    Logger.getLogger(Sender.class.getName()).log(Level.SEVERE, null, ex);
                }
                Response response = mbrcObj.send(message);
                boolean verifyResult = verify(response.getHashValue(), response.getStrResult());
                if (verifyResult) {
                    printReceivedResult(response);
                } else {
                    System.out.println("Message has been corrupted");
                }
            }
            sendEndMessage();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Sender.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Sender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
