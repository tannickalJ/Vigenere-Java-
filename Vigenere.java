
import java.util.Vector;
import java.util.Scanner;
import java.util.HashMap;

public class Vigenere {

    public static void main(String[] args) {

        int state = 1;
        int choice = 0;
        Scanner in = new Scanner(System.in);
        while (state == 1) {

            choice = display(state);

            if (choice == 1) {
                // Encryption
                String input = "";
                String inputKey = "";
                System.out.println();
                System.out.println("Your Message(without spaces): ");
                input = in.nextLine();
                System.out.println();
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
            } else if (choice == 2) {
                // Decryption

                System.out.println();
                System.out.println("*****Decryption*****");
                String text = "";
                String unlockKey = "";
                System.out.println("Encrypted Message: ");
                text = in.nextLine();
                System.out.println();
                System.out.println("Decryption Key(without spaces): ");
                unlockKey = in.nextLine();

                // Convert entered ciphertext and key into lowercase
                String cipherText = text.toLowerCase();
                String unlock = unlockKey.toLowerCase();

                // Call decryption function for decrypted message
                char[] decipher = new char[cipherText.length()];
                decipher = decryption(cipherText, unlock);
                System.out.println();

                // The Decrypted message
                System.out.println("Decrypted Message: ");
                for (int v = 0; v < decipher.length; v++) {
                    System.out.print(decipher[v]);
                }

                System.out.println();
                System.out.println();
            } else if (choice == 3) {
                in.close();
                System.out.println("Goodbye");
                break;
            } else {
                System.out.println();
                System.out.println("Error >:( ");
                System.out.println("| Please choose one of the following options. | ");
                System.out.println("V                                             V ");
                System.out.println();

            }


        }
    }


    public static int display(int state) {

        int choice = 3;

        if (state == 1) {
            Scanner in = new Scanner(System.in);
            System.out.println("***************************************************");
            System.out.println("|      ***Welcome to the Vigenere Cipher!***      |");
            System.out.println("|Please select from one of the following options: |");
            System.out.println("|1. Encrypt                                       |");
            System.out.println("|2. Decrypt                                       |");
            System.out.println("|3. Exit                                          |");
            System.out.println("***************************************************");

            System.out.print("Your choice: ");
            choice = in.nextInt();
            in.close();
        }

        return choice;
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


    public static char[] decryption(String cipherText, String someKey) {

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
                if (tally >= cipherText.length()) {
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
        char[] decipherText = new char[cipherText.length()];

        // Hash Map for mapping values to keys, where each number maps to a letter
        HashMap<Integer, Character> values = new HashMap<Integer, Character>();
        for (int u = 0; u < alphabet.length(); u++) {
            values.put(u, alphabet.charAt(u));
        }

        int temp = 0;
        while (temp != cipherText.length()) {

            // Get value of each letter in ciphertext and cipherKey
            value1 = letters.get(cipherText.charAt(temp));
            value2 = letters.get(decipherKey.get(temp));

            // Calculate and retrieve value for new letter
            calc = ((value1 - value2) + 26) % 26;
            newLetter = values.get(calc);
            decipherText[temp] = newLetter;
            temp++;

        }


        return decipherText;
    }

}

 
