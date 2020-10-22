/*
    who: Mehmet Akif Pinarci
    what: CS1400.03
    when: 9/30/2020
    why: Project 1: Encryption/Decryption Utility
*/
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;
class Cipher{
    public static void main(String[] args) throws IOException {
        
        String userAnswer = prompt();
        String userPassword;
        String fileName;
        Scanner input = new Scanner(System.in);

        if (userAnswer.toLowerCase().equals("a")) {
            System.out.print("Input your message: ");
            String userMessage = input.nextLine();

            do{
                System.out.print("Input your password (must be greaterr than 8 characters): ");
                userPassword = input.nextLine();
            } while (userPassword.length()<8);

            String encryptedString = encrypt(userMessage, userPassword);
            System.out.print("Enter filename: ");
            fileName = input.nextLine();
            writeFile(fileName, encryptedString);
            System.exit(0);
        } 

        else if (userAnswer.toLowerCase().equals("b")) {
            File file1;
            do{
                System.out.print("Input your password (must be greaterr than 8 characters): ");
                userPassword = input.nextLine();
            } while (userPassword.length() < 8);

            do{
                System.out.print("Enter filename: ");
                fileName = input.nextLine();
                file1 = new File(fileName);
                if (!file1.exists()){
                    System.out.println("That file doesn’t exist, Please enter the correct filename or press ctrl-c to exit");
                }
            }while(!file1.exists());
            
            String encryptedString = readFile(fileName);
            decrypt(encryptedString, userPassword);
            System.exit(0);
        } 

        else if (userAnswer.toLowerCase().equals("c")) {
            System.out.println("You chosen exit");
            System.exit(0);
        }
    }

    public static String prompt() {
        String userInput;

        do {
            System.out.println("Welcome to My Encryption/Decryption Program\nPlease enter the letter of you chosen operation:\n\ta) Encrypt a message\n\tb) Decrypt a message\n\tc) Exit");
            System.out.print("Enter option: ");
            Scanner keyboard = new Scanner(System.in);
            userInput = keyboard.nextLine();        
        } while (!checker(userInput));

        return userInput;
    }
    public static boolean checker(String iu){

        if ( (!iu.toLowerCase().equals("a")) && (!iu.toLowerCase().equals("b")) && (!iu.toLowerCase().equals("c")) ){
            System.out.println("Invalid choice please try again!");

            return false;
        }

        else{
            return true;
        }
    }
    public static String encrypt(String message, String password){
        String chiphertext = "0x";
        String hexChar;
        for(int i = 0; i < message.length(); i++){
            hexChar = Integer.toHexString((message.charAt(i)) ^ password.charAt(i % (password.length())));
            if (hexChar.length()<2){
                chiphertext = chiphertext + "0" + hexChar;
            }
            else{
                chiphertext += hexChar;
            }

        }
        
        return chiphertext;
                    
    }
    public static void decrypt(String chipertext, String password){
        String message = "";
        char hextoChar;
        for (int i = 2, j = 4, k = 0; j < chipertext.length() + 1 ; i += 2,j += 2, k++ ){
            hextoChar = (char)(Integer.parseInt(chipertext.substring(i, j), 16) ^ password.charAt(k % (password.length())));
            message += hextoChar;
        }
        System.out.println(message);

    }
    public static boolean writeFile(String filename, String text) throws IOException{

        PrintWriter outFile = new PrintWriter(filename);
        outFile.println(text);
        outFile.close();

        return true;

    }
     public static String readFile(String filename) throws IOException{

        File file = new File(filename);
        Scanner inputFile = new Scanner(file);
        String text = inputFile.nextLine();

        return text;
     }
}