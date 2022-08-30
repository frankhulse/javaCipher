/**************************************************************************
Frank Hulse
8/29/22
Java Cipher 1.0
___________________________________________________________________________
This program is designed to use a CLI to encrypt and decrypt small peices 
of text.  Shift cipher is used here.  Non-letters are kept as is.
___________________________________________________________________________
Future plans:
-Update program to use better cipher technology.
	-keyword cipher functionality for ease of use
-Implement a gui.
	-Simple java swing for now.
	-Online version (JavaScript?) should be different program
-Enc entire files?
**************************************************************************/

package javaCipher;
import java.util.Scanner;

public class javaCipher
{
	//Shift Algorithm.  Works for both enc and dec
	public static StringBuffer encrypt(String text, int key)
	{
		//Create a stringBuffer for output.  Easier to work with chars than string
		StringBuffer output = new StringBuffer();
		
		//loop through text for encryption
		for (int i = 0; i < text.length(); i++)
		{
			//Get char at position i
			char c = text.charAt(i);
			
			//Check if non letter, leave as non-letter
			if ( (int)c < 65 || (int)c > 122 || ((int)c > 90 && (int)c < 97) ) 
			{
				output.append(c);
				continue;
			}
			
			//Check uppercase, perform shift leave uppercase.
			if (Character.isUpperCase(c))
            {
				//Convert to int, add key, back to char
				char ch = (char)(((int)c + key - 65) % 26 + 65);
				output.append(ch);
            }
			
			//Char is lowercase, perform shift.
			else
            {
				//Convert to int, add key, back to char
                char ch = (char)((c + key - 97) % 26 + 97);
                output.append(ch);
            }
			
		}
		return output;
	}
	
	//Main runner class
	public static void main(String[] args) 
	{
		//Create a scanner object
		Scanner s = new Scanner(System.in);

		//infinate loop to keep running enc and dec until user enters 0
		while (true) 
		{
			System.out.print("Press 1 to Encrypt, 2 to Decrypt, or 0 to exit: ");
			int response = s.nextInt();  // Read user input
			
			//Exit program
			if (response < 1)
			{
				System.out.println("Exiting System...");
				break;
			}
			
			//Encrypt
			else if (response == 1) 
			{
				//get key size
				System.out.print("Enter key/ shift size: ");
				int key = s.nextInt();
								
				//Move scanner to next line
				s.nextLine();
				
				//Get input text
				System.out.print("Enter text to be encrypted: ");
				String text = s.nextLine();
				
				//cleaning up format of output text
				System.out.println();
				
				//Output params
				System.out.println("Key: " + key);
				System.out.println("Text: " + text);
				
				//output encrypted string
				System.out.println("Output: " + encrypt(text, key));	
				
				//cleaning up format of output text
				System.out.println();
			}
			
			//Decrypt
			else if (response == 2) 
			{
				//get key size
				System.out.print("Enter key/ shift size: ");
				int key = s.nextInt();
								
				//Move scanner to next line
				s.nextLine();
				
				//Get input text
				System.out.print("Enter text to be encrypted: ");
				String text = s.nextLine();
				
				//cleaning up format of output text
				System.out.println();
				
				//Output params
				System.out.println("Key: " + key);
				System.out.println("Text: " + text);
				
				//get correct key size
				key = key % 26;
				
				//output encrypted string
				System.out.println("Output: " + encrypt(text, 26 - key));	
				
				//cleaning up format of output text
				System.out.println();
			}
			
			//catch all other responses
			else
			{
				//output error response
				System.out.println("ERROR: Invalid menu option please try again.");
			}
			
		}
		s.close();
	}

}
