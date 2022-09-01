/**************************************************************************
Frank Hulse
8/29/22
Java Cipher 2.0
___________________________________________________________________________
This program is designed to use a CLI to encrypt and decrypt small peices 
of text.  A modified vigenere cipher is used here.  Non-letters are removed.

CipherText = key(i % keyLength) + plainText(i) 
PlainText = CipherText(i) - key(i % keyLength)
___________________________________________________________________________
Future plans:
-Update program to use better cipher technology.
-Implement a gui.
	-Simple java swing for now?
	-Online version (JavaScript?) should be different program
-Encrypt entire files?
__________________________________________________________________________
To Do:
Update to 2.1
-learn how to fight this type of cipher.
	-freq analysis shouldnt be an issue, what is?
	-how long does it take to brute force?
		-how does this compare with longer key lengths?
			
Update to 3.0
	-Add simple java gui
		-Is swing still a thing?  if no what to use?
**************************************************************************/

package javaCipher;
import java.util.Scanner;

public class javaCipher
{	
	//ENC Algorithm
	public static StringBuffer encrypt(String key, String text)
	{
		//Create a stringBuffer for output.  Easier to work with chars than string
		StringBuffer output = new StringBuffer();
		
		int l = key.length();
		
		//loop through text for encryption
		for (int i = 0; i < text.length(); i++)
		{
			//Get characters to add
			char t = text.charAt(i);
			char k = key.charAt(i % l);
			
			//System.out.print("t: " + t + " ");
			//System.out.print("k: " + k + " ");
			
			//Add and convert to (0-25)
			int ans = (t + k) % 26;
			//System.out.print("ans: " + ans + " ");
			
			//Add back to A
			ans += 'A';
			
			char ch = (char)ans;
			
			//System.out.println("ch: " + ch);
			output.append(ch);
		}
		
		return output;
	}
	
	//DEC Algorithm
	public static StringBuffer decrypt(String key, String text)
	{
		//Create a stringBuffer for output.  Easier to work with chars than string
		StringBuffer output = new StringBuffer();
		
		int l = key.length();
		
		//loop through text for encryption
		for (int i = 0; i < text.length(); i++)
		{
			//Get characters to subtract
			char t = text.charAt(i);
			char k = key.charAt(i % l);
			
			//System.out.print("t: " + (int)t + " ");
			//System.out.print("k: " + k + " ");
			
			//Add and convert to (0-25)
			int ans = ((t - k) + 26) % 26;
			//System.out.print("ans: " + ans + " ");
			
			//Add back to A
			ans += 'A';
				
			char ch = (char)ans;
				
			//System.out.println("ch: " + ch);
			output.append(ch);
		}
			
		return output;
		}
	
	//Method to get key input details from user
	public static String inputKey()
	{
		//Create a scanner object
		Scanner s1 = new Scanner(System.in);
		
		//get user input.
		System.out.print("Enter Key: ");
		String k = s1.nextLine();
		
		//Clean up input
		k = k.toUpperCase();
		k = k.replaceAll("\\s", "");
		k = k.replaceAll("[0-9]", "");
		
		return k;
	}
	
	//Method to get key input details from user
	public static String inputText()
	{
		//Create a scanner object
		Scanner sc = new Scanner(System.in);
			
		//get user input.
		System.out.print("Enter text: ");
		String t = sc.nextLine();
		
		//clean up input
		t = t.toUpperCase();
		t = t.replaceAll("\\s", "");
		t = t.replaceAll("[0-9]", "");
	
		
		return t;
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
			
			//Force user to enter an Int.  If something else is entered get error message.
			while (!s.hasNextInt()) 
			{
				System.out.println("ERROR: Invalid menu option please try again.");
			    System.out.print("Press 1 to Encrypt, 2 to Decrypt, or 0 to exit: ");
			    s.nextLine();
			}
			int response = s.nextInt();
			
			
			//Exit program
			if (response == 0)
			{
				System.out.println("Exiting System...");
				break;
			}
			
			//Encrypt
			else if (response == 1) 
			{				
				//output encrypted string
				System.out.println("Output: " + encrypt(inputKey(), inputText() ));	
				
				//cleaning up format of output text
				System.out.println();
			}
			
			//Decrypt
			else if (response == 2) 
			{				
				//output encrypted string
				System.out.println("Output: " + decrypt(inputKey(), inputText() ));	
				
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
