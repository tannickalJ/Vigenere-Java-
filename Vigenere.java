import java.util.Vector;
import java.util.Scanner;
import java.util.HashMap;

public class Vigenere {

	public static void main(String[] args) {

		// Encryption

		String input = "";
		String inputKey = "";
		Scanner in = new Scanner(System.in);
		System.out.println("Your Message(without spaces): ");
		input = in.nextLine();
		System.out.println("Encryption Key(without spaces): ");
		inputKey = in.nextLine();

		// Convert entered plaintext and key to lowercase
		String plainText = input.toLowerCase();
		String someKey = inputKey.toLowerCase();

		// Call encryption function for encrypted message
		char[] cipher = new char[plainText.length()];
		cipher = encryption(plainText, someKey);
		System.out.println();

		// The Encrypted message
		System.out.println("Encrypted Message: ");
		for (int v = 0; v < cipher.length; v++) {
			System.out.print(cipher[v]);
		}

		System.out.println();
		System.out.println();

		// Decryption

		System.out.println("*****Decryption*****");
		String unlockKey = "";
		System.out.println("Key(without spaces): ");
		unlockKey = in.nextLine();

		// Convert entered key into lowercase
		String unlock = unlockKey.toLowerCase();

		// Call decryption function for decrypted message
		decryption(cipher, unlock);

	}

	
	
	public static char[] encryption(String plainText, String someKey) {

		// Input vector filled with the plaintext
		Vector<Character> vigenere = new Vector<Character>();

		for (int i = 0; i < plainText.length(); i++) {
			vigenere.add(plainText.charAt(i));
		}

		// Hash map of the alphabet
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		HashMap<Character, Integer> letters = new HashMap<Character, Integer>();

		// Convert each letter into a char
		char[] alphas = new char[26];
		for (int j = 0; j < alphabet.length(); j++) {
			alphas[j] = alphabet.charAt(j);
		}

		// Fill hash map
		int count = 0;
		for (int k = 0; k < alphas.length; k++) {
			letters.put(alphas[k], count);
			// Each letter is given a number from 0-25
			count++;
		}

		// Cipher key vector implementation
		Vector<Character> cipherKey = new Vector<Character>();

		// Key is repeated until it matches plaintext length
		int tally = 0;
		// Fill cipherKey vector until plaintext length
		int isFull = 0;
		int m;
		while (isFull == 0) {

			for (m = 0; m < someKey.length(); m++) {
				cipherKey.add(someKey.charAt(m));
				tally++;

				// Check if plaintext length reached
				if (tally >= plainText.length()) {
					isFull = 1;
					break;
				}

			}
		}

		// Cipher text calculations
		int value1 = 0;
		int value2 = 0;
		int calc = 0;
		char newLetter;
		// Array to hold ciphertext
		char[] cipherText = new char[plainText.length()];

		// Hash Map for mapping values to keys, where each number maps to a letter
		HashMap<Integer, Character> values = new HashMap<Integer, Character>();
		for (int u = 0; u < alphabet.length(); u++) {
			values.put(u, alphabet.charAt(u));
		}

		int temp = 0;
		while (temp != plainText.length()) {

			// Get value of each letter in plaintext and cipherKey
			value1 = letters.get(plainText.charAt(temp));
			value2 = letters.get(cipherKey.get(temp));

			// Calculate and retrieve value for new letter
			calc = (value1 + value2) % 26;
			newLetter = values.get(calc);
			cipherText[temp] = newLetter;
			temp++;

		}

		return cipherText;

	}

	
	
	public static void decryption(char[] cipherText, String someKey) {

		// Hash map of the alphabet
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		HashMap<Character, Integer> letters = new HashMap<Character, Integer>();

		// Convert each letter into a char
		char[] alphas = new char[26];
		for (int j = 0; j < alphabet.length(); j++) {
			alphas[j] = alphabet.charAt(j);
		}

		// Fill hash map
		int count = 0;
		for (int k = 0; k < alphas.length; k++) {
			letters.put(alphas[k], count);
			// Each letter is given a number from 0-25
			count++;
		}

		// Cipher key vector implementation
		Vector<Character> decipherKey = new Vector<Character>();

		// Key is repeated until it matches ciphertext length
		int tally = 0;
		// Fill decipherKey vector until ciphertext length
		int isFull = 0;
		int m;
		while (isFull == 0) {

			for (m = 0; m < someKey.length(); m++) {
				decipherKey.add(someKey.charAt(m));
				tally++;

				// Check if ciphertext length reached
				if (tally >= cipherText.length) {
					isFull = 1;
					break;
				}

			}
		}

		// Cipher text calculations
		int value1 = 0;
		int value2 = 0;
		int calc = 0;
		char newLetter;
		// Array to hold ciphertext
		char[] decipherText = new char[cipherText.length];

		// Hash Map for mapping values to keys, where each number maps to a letter
		HashMap<Integer, Character> values = new HashMap<Integer, Character>();
		for (int u = 0; u < alphabet.length(); u++) {
			values.put(u, alphabet.charAt(u));
		}

		int temp = 0;
		while (temp != cipherText.length) {

			// Get value of each letter in ciphertext and cipherKey
			value1 = letters.get(cipherText[temp]);
			value2 = letters.get(decipherKey.get(temp));

			// Calculate and retrieve value for new letter
			calc = ((value1 - value2) + 26) % 26;
			newLetter = values.get(calc);
			decipherText[temp] = newLetter;
			temp++;

		}

		// The Decrypted Message
		System.out.println("Decrypted Message: ");
		for (int v = 0; v < decipherText.length; v++) {
			System.out.print(decipherText[v]);
		}

	}

}
