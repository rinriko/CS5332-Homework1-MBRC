
import java.security.MessageDigest;
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
public class Receiver extends Thread {

    private final MBRC mbrcObj;
    private PublicKey pubk;
    private final KeyExchange keyObj;
    private AddCalculation AddCal;
    private MultiplyCalculation MulCal;
    private boolean isFinished;

    private Response response;

    public Receiver(MBRC Obj, KeyExchange k) {
        mbrcObj = Obj;
        keyObj = k;
        isFinished = false;
        pubk = null;
    }

    public void getPKeyAndInit() {
        pubk = keyObj.receive();
        if (pubk != null) {
            System.out.println("The Receiver (" + Thread.currentThread().getName() + ") receives the Sender's public key.");
            AddCal = new AddCalculation();
            MulCal = new MultiplyCalculation();
            keyObj.reply("The Receiver received the Sender's public key.");
        }
    }

    //verify a digital signaure
    public static boolean verify(byte message[], PublicKey pubk, byte[] signature) throws Exception {
        Signature sg = Signature.getInstance("SHA1withDSA");
        sg.initVerify(pubk);
        sg.update(message);
        return sg.verify(signature);
    }

    //generate a message digest
    public static byte[] generate(String msg) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] message = msg.getBytes();
        md.update(message);
        byte[] mdbytes = md.digest();
        return (mdbytes);
    }

    public void printResult(String name, int content, String result) {
        if (name.equalsIgnoreCase("add")) {
            System.out.println("The Receiver (" + Thread.currentThread().getName() + ") is about to reply the result of " + content + " added 10, which is " + result);
        } else if (name.equalsIgnoreCase("multiply")) {
            System.out.println("The Receiver (" + Thread.currentThread().getName() + ") is about to reply the result of " + content + " multiply by 10, which is " + result);
        }
    }

    @Override
    public void run() {
        getPKeyAndInit();
        while (!isFinished) {
            Message message = mbrcObj.receive();
            try {
                boolean verifyResult = verify(message.getMessageByte(), pubk, message.getSignature());
                if (verifyResult) {
                    String result = null;
                    String messageName = message.getName();
                    if (messageName.equalsIgnoreCase("end")) {
                        result = "The program finished";
                        isFinished = true;
                    } else if (messageName.equalsIgnoreCase("add")) {
                        System.out.println("   The Receiver (" + Thread.currentThread().getName() + ") received the message and the signature verification result is " + verifyResult + ".");
                        result = String.valueOf(AddCal.add(message.getContent()));
                    } else if (messageName.equalsIgnoreCase("multiply")) {
                        System.out.println("   The Receiver (" + Thread.currentThread().getName() + ") received the message and the signature verification result is " + verifyResult + ".");
                        result = String.valueOf(MulCal.multiply(message.getContent()));
                    }

                    response = new Response(result);
                    response.setHashValue(generate(response.getStrResult()));
                    printResult(messageName, message.getContent(), response.getStrResult());

                    mbrcObj.reply(response);

                } else {
                    System.out.println("Signature verification result: " + verifyResult);
                }
            } catch (Exception ex) {
                Logger.getLogger(Receiver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
