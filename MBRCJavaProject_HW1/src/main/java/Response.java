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
public class Response {
    private final String result;
    private byte[] hashValue;

    public Response(String res) {
        result = res;
    }
    
    public void setHashValue(byte[] hashVal) {
        hashValue = hashVal;
    }
    
    public String getStrResult() {
        return String.valueOf(result);
    }
    
    public byte[] getHashValue() {
        return hashValue;
    }
}
