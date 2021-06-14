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
public class MBRCJavaProject_HW1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("========================================================================");
        System.out.println("""
                           A multi-threaded program for communicating messages between a sender object 
                           thread and a receiver object thread using a "Message Buffer and Response 
                           Connector (MBRC)" object in an object-oriented language (Java, C++, or C#).""");
        System.out.println("------------------------------------------------------------------------");
        System.out.println("""
                           The program will compute addition and multiplication operations on integer 10
                           follow the instruction in the message that sent from the Sender to the Receiver.""");
        System.out.println("========================================================================");
        //Create obejects
        MBRC q = new MBRC();
        KeyExchange k = new KeyExchange();
        Sender p1 = new Sender(q, k);
        Receiver c1 = new Receiver(q, k);
        //Start Threads
        p1.start();
        c1.start();
        
    }

}
