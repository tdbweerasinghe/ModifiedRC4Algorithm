/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConditionalEntropy;

public class TestConditionalEntropy {
    
    
    public static void main (String args[]){
        double conditionalEntropy;
        
        double[] array1, array2 = new double[5];
        array1 = null;
        array2 = null;
        
        for (int i = 0; i<5; i++) {
            array1[i] = i+1;
            array2[i] = i+2;
        }
        
        
        conditionalEntropy = Entropy.calculateConditionalEntropy(array1, array2);
        
        System.out.println("Conditional Entropy: " + conditionalEntropy);
    }
    
}
