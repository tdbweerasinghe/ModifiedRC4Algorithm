//Reference: http://binaryworld.net/Main/CodeDetail.aspx?CodeId=3705
package FirstModification;

import java.io.*;
import java.math.BigInteger;
import java.security.SecureRandom;

public class OriginalAndModifiedRC4 {

    public static void main (String args[]) throws FileNotFoundException, IOException{
        long startTime, endTime, timeElapsed;
        double secrecy;
        StringBuffer strBufferMsg;        
        StringBuffer key;        
        
        SecureRandom random     = new SecureRandom();        
        key                     = new StringBuffer(new BigInteger(640, random).toString(32));
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
        
        /*---------------RC4 Related---------------*/
        //Get the current system time in nanoseconds
        startTime = System.nanoTime();
        //Encrypt
        RC4Crypt(strBufferMsg, key);
        //Get the current system time in nanoseconds
        endTime = System.nanoTime();
        //Calculate the elapsed time in nanoseconds
        timeElapsed = CalculateTimeDifference.GetTimeElapsed(startTime, endTime);        
        //Print the encryption time:
        System.out.println("Encryption time of RC4: " + timeElapsed +"ms");
        //Calculate secrecy:
        secrecy = SecrecyCalculator.calculateSecrecy(key.toString().getBytes(), strBufferMsg.toString().getBytes(), 1);
        //Print secrecy:
        System.out.println("Secrecy value of RC4: " + secrecy);
        //Decrypt
        RC4Crypt(strBufferMsg, key);
        //Print the recovered text:
        System.out.println("Recovered: " + strBufferMsg);
        
        /*---------------Modified RC4 Related---------------*/
        //Get the current system time in nanoseconds
        startTime = System.nanoTime();
        //Encrypt
        ModifiedRC4Crypt(strBufferMsg, key);
        //Get the current system time in nanoseconds
        endTime = System.nanoTime();
        //Calculate the elapsed time in nanoseconds
        timeElapsed = CalculateTimeDifference.GetTimeElapsed(startTime, endTime);        
        //Print the encryption time:
        System.out.println("Encryption time of modified RC4: " + timeElapsed +"ms");
        //Calculate secrecy:
        secrecy = SecrecyCalculator.calculateSecrecy(key.toString().getBytes(), strBufferMsg.toString().getBytes(), 1);
        //Print secrecy:
        System.out.println("Secrecy value of modified RC4: " + secrecy);
        //Decrypt
        ModifiedRC4Crypt(strBufferMsg, key);
        //Print the recovered text:
        System.out.println("Recovered: " + strBufferMsg);
    }


     public static void RC4Crypt(StringBuffer inp, StringBuffer key) {

        int Sbox1[] = new int[256];
        int Sbox2[] = new int[256];
        int i = 0, j = 0, t = 0, x = 0;
        char temp = 0, k;

        //KSA - Start
        for (i = 0; i < 256; i++) {
            Sbox1[i] = i;
        }

        j = 0;
        //This code segment is to get the K[i mod l] - Start
        if (key.length() > 0) {
            for (i = 0; i < 256; i++) {
                if (j == key.length()) {
                    j = 0;
                }
                Sbox2[i] = key.charAt(j++);                
            }
        }
        //This code segment is to get the K[i mod l] - End        
        j = 0;
        
        for (i = 0; i < 256; i++) {
            j = (j + Sbox1[i] + Sbox2[i]) % 256;
            temp = (char) Sbox1[i];
            Sbox1[i] = Sbox1[j];
            Sbox1[j] = temp;
        }
        //KSA - End

        //PRGA - Start
        i = j = 0;
        for (x = 0; x < inp.length(); x++) {            
            i = (i + 1) % 256;           
            j = (j + Sbox1[i]) % 256;

            //Scramble SBox1 #1 further so the encryption routine will repeat itself at great intervals
            temp = (char) Sbox1[i];
            Sbox1[i] = Sbox1[j];
            Sbox1[j] = temp;

            //Get ready to create pseudo random byte for the encryption key
            t = (Sbox1[i] + Sbox1[j]) % 256;

            //Get the generated key
            k = (char) Sbox1[t];

            //XOR with the data and encryption is done!           
            inp.setCharAt(x, (char) (inp.charAt(x) ^ k));
            
        }
        //PRGA - End
    }

    public static void ModifiedRC4Crypt(StringBuffer inp, StringBuffer key) {
        int Sbox1[] = new int[256];
        int Sbox2[] = new int [256];
        int i = 0, j = 0, t = 0, x = 0;
        char temp = 0, k;

        //KSA - Start
        for (i = 0; i < 256; i++) {
            Sbox1[i] = i;
        }

        j = 0;
        //This code segment is to get the K[i mod l] - Start
        if (key.length() > 0) {
            for (i = 0; i < 256; i++) {
                if (j == key.length()) {
                    j = 0;
                }
                Sbox2[i] = key.charAt(j++);                
            }
        }
        //This code segment is to get the K[i mod l] - End

        j = 0;
        
        for (i = 0; i < 256; i++) {
            j = (j + Sbox1[i] + Sbox2[i]) % 256;
            temp = (char) Sbox1[i];
            Sbox1[i] = Sbox1[j];
            Sbox1[j] = temp;
        }
        //KSA - End

        //PRGA - Start
        i = j = 0;
        for (x = 0; x < inp.length(); x++) {            
            i = (i + 1) % 256;            
            j = (j + Sbox1[i]) % 256;

            //Scramble SBox1 #1 further so the encryption routine will repeat itself at great intervals
            temp = (char) Sbox1[i];
            Sbox1[i] = Sbox1[j];
            Sbox1[j] = temp;

            //Get ready to create pseudo random byte for the encryption key
            t = (Sbox1[i] + Sbox1[j]) % 256;

            //Get the generated key
            k = (char) Sbox1[t];

            //XOR with the data and encryption is done!
            //Tharindu(Phase-1) - Start
            //inp.setCharAt(x, (char) (inp.charAt(x) ^ k));
            inp.setCharAt(x, (char) (inp.charAt(x) ^ k ^ j));
            //Tharindu(Phase-1) - End
            //PRGA - End
        }
    }

}
