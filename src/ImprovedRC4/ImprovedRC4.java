/*Modified by Tharindu Weerasinghe*/
package ImprovedRC4;

public class ImprovedRC4
{
    public static void ImprovedRC4Crypt(StringBuffer inp, StringBuffer key1, StringBuffer key2) {

			int s1[] = new int[1000];
			int s2[] = new int[1000];
			int k1[] = new int[256];
			int k2[] = new int[256];
			int i = 0, j1 = 0, j2 = 0, x = 0;
			char temp = 0, generatedKey1, generatedKey2;

		//KSA - Start
		for (i = 0; i < 256; i++)           
		{
			s1 [i] = i;
			s2 [i] = i;
		}		
		
                j1 = 0;
        //Get k1[i mod l] - Start
		if (key1.length() > 0) {
			for (i = 0; i < 256; i++) {
				if (j1 == key1.length()) {
					j1 = 0;
				}
				k1[i] = key1.charAt(j1++);                
			}
		}
		//Get k1[i mod l] - End 

		for (i = 0; i < 256; i++) {
					j1 = (j1 + s1[i] + k1[i]) % 256;
					temp = (char) s1[i];
					s1[i] = s1[j1];
					s1[j1] = temp;
				}
		
                j2 = 0;
		//Get k2[i mod l] - Start
		if (key2.length() > 0) {
			for (i = 0; i < 256; i++) {
				if (j2 == key2.length()) {
					j2 = 0;
				}
				k2[i] = key2.charAt(j2++);                
			}
		}
		//Get k2[i mod l] - End  
		
		for (i = 0; i < 256; i++) {
					j2 = (j2 + s2[i] + k2[i]) % 256;
					temp = (char) s2[i];
					s2[i] = s2[j2];
					s2[j2] = temp;
		}
		//KSA-End		
		
		//PRGA - Start
		i = j1 = j2 = 0;
		for (x = 0; x < inp.length(); x++) {		
                    i = i+1;
                    if (i < 256 && j1 < 256 && j2 < 256) {
                        j1 = j1+s1[i];
                        
                        //First Swapping in PRGA
                        temp = (char) s1[i];                                          
                        s1[i] = s1[j1];
                        s1[j1] = temp;

                        j2=j2+s2[i];

                        //Second Swapping in PRGA
                        temp = (char) s2[i];                        
                        s2[i] = s2[j2];
                        s2[j2] = temp;

                        generatedKey1= (char) s1[(s1 [i] + s1 [j1]) % 256];
                        generatedKey2= (char) s2[(s2 [i] + s2 [j2]) % 256 ];
                        

                        //Final swapping of PRGA - Need to study this and implement
                        
                        temp = (char)s1[s2[j1]];
                        s1[s2[j1]] = s1[s2 [j2]];
                        s1[s2 [j2]] = temp;
                        
                        
                        temp = (char)s2[s1[j1]];
                        s2[s1 [j2]] = s2[s1[j1]];
                        s2[s1[j1]] = temp;

                        //XOR with the input!
                        inp.setCharAt(x, (char) (inp.charAt(x)^ generatedKey1));
                        inp.setCharAt(x, (char) (inp.charAt(x)^ generatedKey2));
                        //PRGA - End
                        }                  
                }             
        }
    
    public static void MuchImprovedRC4Crypt(StringBuffer inp, StringBuffer key1, StringBuffer key2) {

			int s1[] = new int[1000];
			int s2[] = new int[1000];
			int k1[] = new int[256];
			int k2[] = new int[256];
			int i = 0, j1 = 0, j2 = 0, x = 0;
			char temp = 0, generatedKey1, generatedKey2;

		//KSA - Start
		for (i = 0; i < 256; i++)           
		{
			s1 [i] = i;
			s2 [i] = i;
		}		
		
                j1 = 0;
        //Get k1[i mod l] - Start
		if (key1.length() > 0) {
			for (i = 0; i < 256; i++) {
				if (j1 == key1.length()) {
					j1 = 0;
				}
				k1[i] = key1.charAt(j1++);                
			}
		}
		//Get k1[i mod l] - End 

		for (i = 0; i < 256; i++) {
					j1 = (j1 + s1[i] + k1[i]) % 256;
					temp = (char) s1[i];
					s1[i] = s1[j1];
					s1[j1] = temp;
				}
		
                j2 = 0;
		//Get k2[i mod l] - Start
		if (key2.length() > 0) {
			for (i = 0; i < 256; i++) {
				if (j2 == key2.length()) {
					j2 = 0;
				}
				k2[i] = key2.charAt(j2++);                
			}
		}
		//Get k2[i mod l] - End  
		
		for (i = 0; i < 256; i++) {
					j2 = (j2 + s2[i] + k2[i]) % 256;
					temp = (char) s2[i];
					s2[i] = s2[j2];
					s2[j2] = temp;
		}
		//KSA-End		
		
		//PRGA - Start
		i = j1 = j2 = 0;
		for (x = 0; x < inp.length(); x++) {		
                    i = i+1;
                    if (i < 256 && j1 < 256 && j2 < 256) {
                        j1 = j1+s1[i];
                        
                        //First Swapping in PRGA
                        temp = (char) s1[i];                                          
                        s1[i] = s1[j1];
                        s1[j1] = temp;

                        j2=j2+s2[i];

                        //Second Swapping in PRGA
                        temp = (char) s2[i];                        
                        s2[i] = s2[j2];
                        s2[j2] = temp;

                        generatedKey1= (char) s1[(s1 [i] + s1 [j1]) % 256];
                        generatedKey2= (char) s2[(s2 [i] + s2 [j2]) % 256 ];
                        
                        
                        //Final swapping of PRGA 
                        
                        temp = (char)s1[s2[j1]];
                        s1[s2[j1]] = s1[s2 [j2]];
                        s1[s2 [j2]] = temp;
                        
                        //swap ( s2[s1[j1]] , s2[s1 [j2]] );
                        temp = (char)s2[s1[j1]];
                        s2[s1 [j2]] = s2[s1[j1]];
                        s2[s1[j1]] = temp;

                        //XOR with the input!
                        inp.setCharAt(x, (char) (inp.charAt(x)^ generatedKey1 ^ j1));
                        inp.setCharAt(x, (char) (inp.charAt(x)^ generatedKey2 ^ j2));
                        //PRGA - End
                        }                  
                }             
        }
 }

