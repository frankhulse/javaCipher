/**************************************************************************
Frank Hulse
8/29/22
Java Cipher 1.3
___________________________________________________________________________
This program is designed to use a CLI to encrypt and decrypt small peices 
of text.  Shift cipher is used here.  Non-letters are removed, numbers kept
___________________________________________________________________________
Future plans:
-Update program to use better cipher technology.
	-keyword cipher functionality for ease of use
-Implement a gui.
	-Simple java swing for now.
	-Online version (JavaScript?) should be different program
-Encrypt entire files?
__________________________________________________________________________
To Do:
Update to 2.0
	-Implement "keyword" cipher
	-plain + keyword(i) = enc
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
	//Shift Algorithm.  Works for both enc and dec
	public static StringBuffer encrypt(int key, String text)
	{
		//Create a stringBuffer for output.  Easier to work with chars than string
		StringBuffer output = new StringBuffer();
		
		//loop through text for encryption
		for (int i = 0; i < text.length(); i++)
		{
			//Get char at position i
			char c = text.charAt(i);
			int x = (int)c;
			
			//Check if number, leave as is
			if (Character.isDigit(c))
			{
				output.append(c);
				//System.out.println("number");
				continue;
			}
			
			//Check uppercase, perform shift leave uppercase.
			if (Character.isUpperCase(c))
            {
				//Convert to int, add key, back to char
				char ch = (char)((x + key - 65) % 26 + 65);
				output.append(ch);
				//System.out.println("UPPER");
				continue;
            }
			
			//Char is lowercase, perform shift.
			if (Character.isLowerCase(c))
            {
				//Convert to int, add key, back to char
                char ch = (char)((c + key - 97) % 26 + 97);
                output.append(ch);
                //System.out.println("lower");
                continue;
            }
			
			//All other chars are excluded because we want to remove them.
			
		}
		return output;
	}
	
	//Method to get key input details from user
	public static int inputKey()
	{
		//Create a scanner object
		Scanner s1 = new Scanner(System.in);
		
		//get user input
		System.out.print("Enter key/ shift size: ");
		
		//force user to input int
		while (!s1.hasNextInt()) 
		{
		      System.out.println("Input is not a number.  ");
		      System.out.print("Enter key/ shift size: ");
		      s1.nextLine();
		}
		int k = s1.nextInt();
		
		return k;
	}
	
	//Method to get key input details from user
	public static String inputText()
	{
		//Create a scanner object
		Scanner sc = new Scanner(System.in);
			
		//get user input.  We do not need to clean because encrypt() does that for text
		System.out.print("Enter text: ");
		String t = sc.nextLine();
			
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
				System.out.println("Output: " + encrypt(26 - inputKey(), inputText() ));	
				
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
