import java.io.*;

@SuppressWarnings("ALL")
public class Main {

    public static BufferedReader bufferedReader;



    public static void main(String[] args) {
        createUserInterfaceAndGetInput();


        // System.out.println("Hello world!");

    }

    private static void createUserInterfaceAndGetInput() {


        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print(" \n");

        System.out.print("( # please enter an number and click enter ) \n\n");

        printBox("Choose one Option to Use Further");

        System.out.print("Enter 1 to Encode Single PhoneNumber \n");
        System.out.print("Enter 2 to Feed New PhoneNumber /  Word to the Program Manually  \n");
        System.out.print("Enter 3 to Encode the Pre-Defined PhoneNumbers \n");
        System.out.print("Enter 4 to Exit \n");

        System.out.print("Enter : ");


        String str= null;              //reads string
        try {
            str = bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //System.out.print("You have Selected: "+str);
        switch (str) {
            case "1" -> getPhnNumberToEncode();
            case "2" -> getInputWriteInSelectedFile();
            case "3" -> readFromPhoneNumbersTextFile();
            case "4" -> System.out.print("Exiting");
            //readFromDictionaryTextFile();

            //System.out.print("Exiting");

            default -> {
            }
        }


    }

    private static void getPhnNumberToEncode() {


        String wordUserTyped = getPhoneNumberFromUser();

        if ((wordUserTyped.matches("[0-9]+")  && (wordUserTyped.length()<=10))){

            getEncodedWordForPhoneNumber(wordUserTyped,true);

        }
        else{
            wordUserTyped = getPhoneNumberFromUser();

        }

        getEncodedWordForPhoneNumber(wordUserTyped,true);


    }

    private static void getEncodedWordForPhoneNumber(String phoneNumber, boolean isSingleNumber) {


        System.out.println("");

        System.out.println(phoneNumber);
        //CASE 1:
        String encodedWord=  Phonenumberencoder.get_encoded_Word(phoneNumber);

        //CASE 2:
        if(encodedWord.equals("no matches") || encodedWord.equals("")) {

            encodedWord = getEncodedWordUsingCase2(phoneNumber);
        }

        //CASE 3:
        if(encodedWord.equals("no matches") || encodedWord.equals("")) {

            encodedWord = getEncodedWordUsingCase3(phoneNumber);
        }

        if(encodedWord.equals("")) {
            encodedWord ="no matches";
        }


        System.out.println(encodedWord);

        if(isSingleNumber){
            createUserInterfaceAndGetInput();
        }

    }


    private static String getEncodedWordUsingCase2(String phoneNumber) {
        String encodedWord = "no matches";
        String encodedFirstHalfWord="";String encodedSecondHalfWord="";

        int phnNumberLength ;int countTOExpelDigits_PhnNumber = 2;
        String phnNumber_FirstHalf;String phnNumber_SecondHalf;
        phnNumberLength = phoneNumber.length();
        if(phnNumberLength>=6){

            while ((encodedFirstHalfWord.equals("") || encodedFirstHalfWord.equals("no matches")) || (encodedSecondHalfWord.equals("") || encodedSecondHalfWord.equals("no matches"))){

                if(countTOExpelDigits_PhnNumber<= (phnNumberLength-2)) {
                    phnNumber_FirstHalf = phoneNumber.substring(0, (phnNumberLength - countTOExpelDigits_PhnNumber));
                    encodedFirstHalfWord = Phonenumberencoder.get_encoded_Word(phnNumber_FirstHalf);
                    phnNumber_SecondHalf = phoneNumber.substring((phnNumberLength - countTOExpelDigits_PhnNumber), phnNumberLength);
                    encodedSecondHalfWord = Phonenumberencoder.get_encoded_Word(phnNumber_SecondHalf);


                }
                else{

                    encodedFirstHalfWord = "no matches";
                    encodedSecondHalfWord ="no matches";
                    break;
                }
                countTOExpelDigits_PhnNumber++;
            }





            if(encodedFirstHalfWord.equals("no matches") || encodedSecondHalfWord.equals("no matches")){
                encodedWord = "no matches";
                // System.out.println(encodedWord);
            }
            else{

                encodedWord = encodedFirstHalfWord + "-"+ encodedSecondHalfWord;
                // System.out.println(encodedWord);


            }

        }
        else{
            // System.out.println(encodedWord);

        }
        return encodedWord;
    }



    private static String getEncodedWordUsingCase3(String phoneNumber) {
        String encodedWord = "no matches";
        String encodedFirstHalfWord="";String encodedSecondHalfWord="";

        int phnNumberLength ;int countTOExpelDigits_PhnNumber = 2;
        String phnNumber_FirstHalf;String phnNumber_SecondHalf = "";
        phnNumberLength = phoneNumber.length();

        encodedFirstHalfWord = "";
        encodedSecondHalfWord ="";

        int countToRemoveDigits_PhnNumber = 1;
        phnNumberLength = phoneNumber.length();


        while ((encodedFirstHalfWord.equals("") || encodedFirstHalfWord.equals("no matches")) || (encodedSecondHalfWord.equals("") || encodedSecondHalfWord.equals("no matches"))) {
            if(countToRemoveDigits_PhnNumber <= (phnNumberLength)) {

                phnNumber_FirstHalf = phoneNumber.substring(0, (phnNumberLength - countToRemoveDigits_PhnNumber));
                if(phnNumber_FirstHalf.equals("")){
                    encodedFirstHalfWord = "";

                }
                else{
                    encodedFirstHalfWord = Phonenumberencoder.get_encoded_Word(phnNumber_FirstHalf);

                }


                if(countToRemoveDigits_PhnNumber ==1 ){
                    phnNumber_SecondHalf = phoneNumber.substring((phnNumberLength - countToRemoveDigits_PhnNumber), phnNumberLength);

                    if(!encodedFirstHalfWord.equals("no matches") && (!encodedFirstHalfWord.equals(""))){

                        encodedWord = encodedFirstHalfWord + "-"+ phnNumber_SecondHalf ;



                        break;

                    }
                    else{
                        encodedFirstHalfWord =  getEncodedWordUsingCase2(phnNumber_FirstHalf);
                        if(!encodedFirstHalfWord.equals("no matches") && (!encodedFirstHalfWord.equals(""))) {

                            encodedWord = encodedFirstHalfWord + "-" + phnNumber_SecondHalf;

                            break;
                        }
                        else{
                            encodedWord = "no matches";
                            countToRemoveDigits_PhnNumber++;

                        }

                    }

                }
                else{
                    String firstDigit_Of_PhnNumberSecondHalf ;

                    phnNumber_SecondHalf = phoneNumber.substring((phnNumberLength - countToRemoveDigits_PhnNumber), phnNumberLength);

                    firstDigit_Of_PhnNumberSecondHalf = phnNumber_SecondHalf.substring(0,1);
                    phnNumber_SecondHalf = phnNumber_SecondHalf.substring(1, phnNumber_SecondHalf.length());
                    encodedSecondHalfWord = Phonenumberencoder.get_encoded_Word(phnNumber_SecondHalf);

                    if(countToRemoveDigits_PhnNumber==phnNumberLength){
                        if (!encodedSecondHalfWord.equals("") && !encodedSecondHalfWord.equals("no matches")) {


                            encodedWord = firstDigit_Of_PhnNumberSecondHalf + "-" + encodedSecondHalfWord;

                            break;
                        }
                        else{
                            encodedSecondHalfWord =  getEncodedWordUsingCase2(phnNumber_SecondHalf);
                            if ((!encodedSecondHalfWord.equals("") && !encodedSecondHalfWord.equals("no matches"))) {

                                encodedWord = firstDigit_Of_PhnNumberSecondHalf + "-" + encodedSecondHalfWord;
                                // System.out.println(encodedWord);
                                break;
                            }
                            else{
                                encodedWord = "no matches";

                            }
                        }
                    }
                    else {


                        if ((!encodedFirstHalfWord.equals("") && !encodedFirstHalfWord.equals("no matches")) && (!encodedSecondHalfWord.equals("") && !encodedSecondHalfWord.equals("no matches"))) {

                            encodedWord = encodedFirstHalfWord + "-" + firstDigit_Of_PhnNumberSecondHalf + "-" + encodedSecondHalfWord;

                            break;
                        }
                        else{
                            encodedWord = "no matches";

                        }

                    }
                }



                countToRemoveDigits_PhnNumber++;

            }
            else{
                encodedWord ="no matches";
                // System.out.println(encodedWord);

                break;
            }
        }
        return encodedWord;
    }





    private static void getInputWriteInSelectedFile() {
        printBox("Do you need Add Phone Number or Word For Dictionary");

        System.out.print("Please Enter 1 to Add New Phone Number and Encode it : \n");
        System.out.print("Please Enter 2 to Add New Word in Dictionary : \n");

        String userInput= null;              //reads string
        try {
            userInput = bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //System.out.print("You have Selected: "+str);
        switch (userInput) {
            case "1" -> {
                String wordUserTyped = getPhoneNumberFromUser();
                wordUserTyped = wordUserTyped.replaceAll("[^0-9]","");
                if ((wordUserTyped.matches("[0-9]+")  && (wordUserTyped.length()<=10))){

                    writeNewPhoneNumberInPhoneNumberFile(wordUserTyped);

                }
                else{
                    wordUserTyped = getPhoneNumberFromUser();

                }




            }
            case "2" -> {
                String wordUserTyped = getWordForDictionaryFromUser();


                if ((wordUserTyped.matches("[a-zA-Z]+"))) {
                    writeNewWordInDictionary(wordUserTyped);

                }
                else{
                    wordUserTyped = getWordForDictionaryFromUser();

                }
            }

            default -> {
            }
        }

    }

    private static String getWordForDictionaryFromUser() {
        System.out.print("Enter the Word which you need to add in the Dictionary File : \n");
        String wordUserTyped= null;              //reads string
        try {
            wordUserTyped = bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        if ((wordUserTyped.matches("[a-zA-Z]+")  )){

        }
        else{
            printBox("Sorry !! Please Enter Alphabets Only");
            System.out.print("Please enter the Word again : \n");

            wordUserTyped = getPhoneNumberFromUser();

        }


        return wordUserTyped;
    }

    private static void writeNewPhoneNumberInPhoneNumberFile(String phoneNumber) {
        File file_toWrite  = new File((config_Helper.phoneNumbers_FileName));

        //  File file_toWrite  = new File(Objects.requireNonNull(Main.class.getResource(config_Helper.phoneNumbers_FileName)).getPath());

        FileWriter fr = null;
        BufferedWriter buffer = null;
        try {
            // System.out.println(file);
            fr = new FileWriter(file_toWrite,true);
            buffer = new BufferedWriter(fr);

            buffer.write(phoneNumber+"\n");
            buffer.flush();
            fr.flush();
            System.out.print(phoneNumber+" is Successfully added in the Phone Number File ");
            getEncodedWordForPhoneNumber(phoneNumber,true);

        } catch (IOException e) {
            System.out.print(phoneNumber+" is Failed to  add in the Phone Number File ");

            e.printStackTrace();
        }
        finally {
            //close resources
            try {
                if(fr != null){
                    fr.close();
                } else {
                    System.out.println("fr has not been initialized!");
                }



                if(buffer != null){
                    buffer.close();
                } else {
                    System.out.println("Buffer has not been initialized!");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }



    private static String getPhoneNumberFromUser() {
        System.out.print("( phone number can be less than or equals 10 digits ) \n");
        System.out.print("Enter the Phone Number  : \n");
        String wordUserTyped= null;              //reads string
        try {
            wordUserTyped = bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        wordUserTyped = wordUserTyped.replaceAll("[^10-9]","");

        if ((wordUserTyped.matches("[0-9]+")  && (wordUserTyped.length()<=10))){

        }
        else{
            printBox("Sorry !! Input Should be Numbers & can be less than or equals 10 Digits");
            System.out.print("Please enter the Phone Number again : \n");

            wordUserTyped = getPhoneNumberFromUser();

        }


        return  wordUserTyped;
    }



    private static void readFromPhoneNumbersTextFile() {

        File file  = new File((config_Helper.phoneNumbers_FileName));

        //  File file  = new File(Objects.requireNonNull(Main.class.getResource(config_Helper.phoneNumbers_FileName)).getPath());

        try {
            File directoryInput = new File(String.valueOf(file));
            BufferedReader br = new BufferedReader(new FileReader(directoryInput));
            String phoneNumber ;
            while ((phoneNumber = br.readLine()) != null) {

                getEncodedWordForPhoneNumber(phoneNumber,false);

            }
            br.close();
            createUserInterfaceAndGetInput();

        } catch (IOException e) {
            e.printStackTrace();

        }


    }


    private static void readFromDictionaryTextFile() {


        File file  = new File((config_Helper.dictionary_FileName));

        try {
            File directoryInput = new File(String.valueOf(file));
            BufferedReader br = new BufferedReader(new FileReader(directoryInput));
            String Phrase ;
            while ((Phrase = br.readLine()) != null) {
                System.out.println(Phrase);

            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();

        }
        finally {
            createUserInterfaceAndGetInput();

        }

    }


    private static void writeNewWordInDictionary(String word) {
        File file_toWrite  = new File((config_Helper.dictionary_FileName));

        //File file_toWrite  = new File(Objects.requireNonNull(Main.class.getResource(config_Helper.dictionary_FileName)).getPath());

        FileWriter fr = null;
        BufferedWriter buffer = null;
        try {
            // System.out.println(file);
            fr = new FileWriter(file_toWrite,true);
            buffer = new BufferedWriter(fr);
            buffer.write(word+"\n");
            buffer.flush();
            fr.flush();
            System.out.print(word+" is Successfully added in the Dictionary File ");

        } catch (IOException e) {
            System.out.print(word+" is Failed to  add in the Dictionary5 File ");

            e.printStackTrace();
        }
        finally {
            //close resources
            try {
                if(fr != null){
                    fr.close();
                } else {
                    System.out.println("fr has not been initialized!");
                }



                if(buffer != null){
                    buffer.close();
                } else {
                    System.out.println("Buffer has not been initialized!");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        readFromDictionaryTextFile();



    }




    private static int getMaxLength(String... strings) {
        int len = Integer.MIN_VALUE;
        for (String str : strings) {
            len = Math.max(str.length(), len);
        }
        return len;
    }

    private static String padString(String str, int len) {
        StringBuilder sb = new StringBuilder(str);
        return sb.append(fill(' ', len - str.length())).toString();
    }
    private static String fill(char ch, int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(ch);
        }
        return sb.toString();
    }

    public static void printBox(String... strings) {
        int maxBoxWidth = getMaxLength(strings);
        String line = "+" + fill('-', maxBoxWidth + 2) + "+";
        System.out.println(line);
        for (String str : strings) {
            System.out.printf("| %s |%n", padString(str, maxBoxWidth));
        }
        System.out.println(line);
    }


}
