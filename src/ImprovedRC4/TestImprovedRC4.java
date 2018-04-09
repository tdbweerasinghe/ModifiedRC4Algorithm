package ImprovedRC4;

import FirstModification.*;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.SecureRandom;

public class TestImprovedRC4 {
    private static Object key;
    
    public static void main (String args[]) throws FileNotFoundException, IOException
    {
        long startTime, endTime, timeElapsed;
        StringBuffer strBufferMsg;        
        StringBuffer key1, key2;        
        
        SecureRandom random     = new SecureRandom();        
        key1                    = new StringBuffer(new BigInteger(640, random).toString(32));
        key2                    = new StringBuffer(new BigInteger(640, random).toString(32));
        FileInputStream fstream = new FileInputStream("E:/Education/test.txt");
        // Get the object of DataInputStream
        DataInputStream in  = new DataInputStream(fstream);
        BufferedReader br   = new BufferedReader(new InputStreamReader(in));
        String strLine      = null;
        String strNewLine   = null;

        //Read File Line By Line
        while ((strLine = br.readLine()) != null) {
            if (strNewLine == null) {
                strNewLine = strLine;
            }
            else {
                strNewLine = strNewLine + strLine;
            }
        }
        //Close the input stream
        in.close();
        br.close();
        String strMsg       = strNewLine;
        strBufferMsg        = new StringBuffer(strMsg);
        //Get the current system time in nanoseconds
        startTime = System.nanoTime();
        
        //Improved RC4 - Start
        //Encrypt
        ImprovedRC4.ImprovedRC4Crypt(strBufferMsg, key1, key2);
         //Get the current system time in nanoseconds
        endTime = System.nanoTime();
        //Calculate the elapsed time in nanoseconds
        timeElapsed = CalculateTimeDifference.GetTimeElapsed(startTime, endTime);        
        //Print the encryption time:
        System.out.println("Encryption time of Improved RC4: " + timeElapsed +"ms");
        //Calculate secrecy:
        double secrecy1 = SecrecyCalculator.calculateSecrecy(key1.toString().getBytes(), strBufferMsg.toString().getBytes(), 1);
        double secrecy2 = SecrecyCalculator.calculateSecrecy(key2.toString().getBytes(), strBufferMsg.toString().getBytes(), 1);
        //Print secrecy:
        System.out.println("Secrecy value of Imrpoved RC4 w.r.t Key1: " + secrecy1);
        System.out.println("Secrecy value of Imrpoved RC4 w.r.t Key2: " + secrecy2);
        //Decrypt
        ImprovedRC4.ImprovedRC4Crypt(strBufferMsg, key1, key2);
        //Print the recovered text:
        //System.out.println("Recovered: " + strBufferMsg);        
        System.out.println("****************************");        
        //Improved RC4 - End
        
        
        //Much Improved RC4 - Start
        //Get the current system time in nanoseconds
        startTime = System.nanoTime();       
        //Encrypt
        ImprovedRC4.MuchImprovedRC4Crypt(strBufferMsg, key1, key2);
         //Get the current system time in nanoseconds
        endTime = System.nanoTime();
        //Calculate the elapsed time in nanoseconds
        timeElapsed = CalculateTimeDifference.GetTimeElapsed(startTime, endTime);        
        //Print the encryption time:
        System.out.println("Encryption time of Much Improved RC4: " + timeElapsed +"ms");
        //Calculate secrecy:
        double secrecy1_1 = SecrecyCalculator.calculateSecrecy(key1.toString().getBytes(), strBufferMsg.toString().getBytes(), 1);
        double secrecy1_2 = SecrecyCalculator.calculateSecrecy(key2.toString().getBytes(), strBufferMsg.toString().getBytes(), 1);
        //Print secrecy:
        System.out.println("Secrecy value of Much Imrpoved RC4 w.r.t Key1: " + secrecy1_1);
        System.out.println("Secrecy value of Much Imrpoved RC4 w.r.t Key2: " + secrecy1_2);
        //Decrypt
        ImprovedRC4.MuchImprovedRC4Crypt(strBufferMsg, key1, key2);
        //Print the recovered text:
        //System.out.println("Recovered: " + strBufferMsg);        
        System.out.println("****************************");
        //Much Improved RC4 - End
        
        //Modified RC4 (key1)- Start
        //Get the current system time in nanoseconds
        startTime = System.nanoTime();
        //Encrypt
        FirstModification.OriginalAndModifiedRC4.ModifiedRC4Crypt(strBufferMsg, key1);
        //Get the current system time in nanoseconds
        endTime = System.nanoTime();
        //Calculate the elapsed time in nanoseconds
        timeElapsed = CalculateTimeDifference.GetTimeElapsed(startTime, endTime);        
        //Print the encryption time:
        System.out.println("Encryption time of Modified RC4: " + timeElapsed +"ms");
        //Calculate secrecy:
        double secrecy1_1_1 = SecrecyCalculator.calculateSecrecy(key1.toString().getBytes(), strBufferMsg.toString().getBytes(), 1);
        //Print secrecy:
        System.out.println("Secrecy value of Modified RC4 w.r.t Key1: " + secrecy1_1_1);
        //Decrypt
        FirstModification.OriginalAndModifiedRC4.ModifiedRC4Crypt(strBufferMsg, key1);
        //Print the recovered text:
        //System.out.println("Recovered: " + strBufferMsg); 
        //Modified RC4 (key1)- End
        
        //Modified RC4 (key2)- Start
        //Get the current system time in nanoseconds
        startTime = System.nanoTime();
        //Encrypt
        FirstModification.OriginalAndModifiedRC4.ModifiedRC4Crypt(strBufferMsg, key2);
        //Get the current system time in nanoseconds
        endTime = System.nanoTime();
        //Calculate the elapsed time in nanoseconds
        timeElapsed = CalculateTimeDifference.GetTimeElapsed(startTime, endTime);        
        //Print the encryption time:
        System.out.println("Encryption time of Modified RC4: " + timeElapsed +"ms");
        //Calculate secrecy:
        double secrecy1_2_2 = SecrecyCalculator.calculateSecrecy(key1.toString().getBytes(), strBufferMsg.toString().getBytes(), 1);
        //Print secrecy:
        System.out.println("Secrecy value of Modified RC4 w.r.t Key2: " + secrecy1_2_2);
        //Decrypt
        FirstModification.OriginalAndModifiedRC4.ModifiedRC4Crypt(strBufferMsg, key1);
        //Print the recovered text:
        //System.out.println("Recovered: " + strBufferMsg); 
        //Modified RC4 (key2)- End
        System.out.println("****************************");
        
        //Original RC4 (key1) - Start
        //Get the current system time in nanoseconds
        startTime = System.nanoTime();
        //Encrypt
        FirstModification.OriginalAndModifiedRC4.RC4Crypt(strBufferMsg, key1);
        //Get the current system time in nanoseconds
        endTime = System.nanoTime();
        //Calculate the elapsed time in nanoseconds
        timeElapsed = CalculateTimeDifference.GetTimeElapsed(startTime, endTime);        
        //Print the encryption time:
        System.out.println("Encryption time of Original RC4: " + timeElapsed +"ms");
        //Calculate secrecy:
        double secrecy2_1 = SecrecyCalculator.calculateSecrecy(key1.toString().getBytes(), strBufferMsg.toString().getBytes(), 1);
        //Print secrecy:
        System.out.println("Secrecy value of Original RC4 w.r.t Key1: " + secrecy2_1);
        //Decrypt
        FirstModification.OriginalAndModifiedRC4.ModifiedRC4Crypt(strBufferMsg, key1);
        //Print the recovered text:
        //System.out.println("Recovered: " + strBufferMsg); 
        //Original RC4 (key1) - End
        
         //Original RC4 (key2) - Start
        //Get the current system time in nanoseconds
        startTime = System.nanoTime();
        //Encrypt
        FirstModification.OriginalAndModifiedRC4.RC4Crypt(strBufferMsg, key2);
        //Get the current system time in nanoseconds
        endTime = System.nanoTime();
        //Calculate the elapsed time in nanoseconds
        timeElapsed = CalculateTimeDifference.GetTimeElapsed(startTime, endTime);        
        //Print the encryption time:
        System.out.println("Encryption time of Original RC4: " + timeElapsed +"ms");
        //Calculate secrecy:
        double secrecy2_2 = SecrecyCalculator.calculateSecrecy(key2.toString().getBytes(), strBufferMsg.toString().getBytes(), 1);
        //Print secrecy:
        System.out.println("Secrecy value of Original RC4 w.r.t Key2: " + secrecy2_2);
        //Decrypt
        FirstModification.OriginalAndModifiedRC4.ModifiedRC4Crypt(strBufferMsg, key1);
        //Print the recovered text:
        //System.out.println("Recovered: " + strBufferMsg); 
        //Original RC4 (key2) - End
    }
    
}
