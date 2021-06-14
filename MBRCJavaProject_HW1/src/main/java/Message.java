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
public class Message {

    private final String messageName;
    private final int messageContent;
    private byte[] signature;

    public Message(String name, int content) {
        messageName = name;
        messageContent = content;
    }
    

    public void setSignature(byte[] sign) {
        signature = sign;
    }

    public String getName() {
        return messageName;
    }

    public int getContent() {
        return messageContent;
    }

    public byte[] getSignature() {
        return signature;
    }

    public byte[] getMessageByte() {
        String strContent = String.valueOf(messageContent);
        byte[] messageByte = strContent.getBytes();
        return messageByte;
    }
}
