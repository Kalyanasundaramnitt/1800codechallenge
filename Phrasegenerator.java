import java.io.*;
import java.util.*;

public class Phrasegenerator {

    public static String encodedWord = "no matches";
    public static String firstEncodedWord = "";

    public static List<String> phoneNumber_inArray = new ArrayList<>();
    public static List<String> possible_FirstLettersOfPhrase = new ArrayList<>();
    public static HashMap<String, List<String>> set_of_Possible_PhrasesFrom_Dictionary = new HashMap<>();
    public static HashMap<String, List<String>> set_of_Possible_Phrases = new HashMap<>();
    public static List<String> set_of_Possible_PhrasesKeyCombination = new ArrayList<>();
    public static HashMap<String, List<String>> filtered_set_of_Possible_Phrases = new HashMap<>();
    public static List<String> filtered_set_of_Possible_PhrasesKeyCombination = new ArrayList<>();
    public static HashMap<String, String> allocatedAlphabetsHashmap = new HashMap<>();

    public static String firstDigit_PhoneNumber="";

    // This method returns encodedphrase for the string
    public static  String getencodedPhrase(HashMap<String, String> allocatedAlphabets_Hashmap, String number) {
        filtered_set_of_Possible_Phrases.clear();
        filtered_set_of_Possible_PhrasesKeyCombination.clear();
        set_of_Possible_PhrasesKeyCombination.clear();
        set_of_Possible_Phrases.clear();
        possible_FirstLettersOfPhrase.clear();
        phoneNumber_inArray.clear();
        encodedWord = "no matches";
        firstEncodedWord = "";
        allocatedAlphabetsHashmap.clear();
        phoneNumber_inArray = new ArrayList<>(number.length());

        allocatedAlphabetsHashmap = allocatedAlphabets_Hashmap;

        for (int i = 0; i < number.length(); i++) {
            phoneNumber_inArray.add(String.valueOf(number.charAt(i)));
        }
        if(phoneNumber_inArray.size()>0){
            firstDigit_PhoneNumber = phoneNumber_inArray.get(0);
        }

        String possible_Firstletters = "";
        possible_Firstletters = allocatedAlphabetsHashmap.get(firstDigit_PhoneNumber);
        try{
            possible_Firstletters = possible_Firstletters.replace(",","");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        for (int i = 0; i < possible_Firstletters.length(); i++) {
            possible_FirstLettersOfPhrase.add(String.valueOf(possible_Firstletters.charAt(i)));
        }

        set_of_Possible_PhrasesFrom_Dictionary = getSetOfPossiblePhrasesFromDictionary(possible_FirstLettersOfPhrase,number);
     // System.out.println("number "+number);
     // System.out.println("possible_FirstLettersOfPhrase "+possible_FirstLettersOfPhrase);
     // System.out.println("set_of_Possible_PhrasesFrom_Dictionary "+set_of_Possible_PhrasesFrom_Dictionary);

        for(int k =0 ;k < possible_FirstLettersOfPhrase.size(); k++) {
            String letterFrompossible_FirstLettersOfPhrase = possible_FirstLettersOfPhrase.get(k);
         // System.out.println("letterFrompossible_FirstLettersOfPhrase "+letterFrompossible_FirstLettersOfPhrase);
            String combinedletters ="";

            for(int phnNumber_iterator =1 ; phnNumber_iterator<phoneNumber_inArray.size();
                phnNumber_iterator++){
                String digits_in_phonenumber ="" ;String possible_letters = "";
                digits_in_phonenumber = phoneNumber_inArray.get(phnNumber_iterator);
                if(filtered_set_of_Possible_Phrases.size()>0){
                    set_of_Possible_Phrases = filtered_set_of_Possible_Phrases;
                    set_of_Possible_PhrasesKeyCombination = filtered_set_of_Possible_PhrasesKeyCombination;
                }
                possible_letters = allocatedAlphabetsHashmap.get(digits_in_phonenumber);
             // System.out.println("possible_letters "+possible_letters);

                try{
                    possible_letters = possible_letters.replace(",","");
                }
                catch (Exception e){
                    e.printStackTrace();
                }


                for (int i = 0; i < possible_letters.length(); i++) {
                    String letter = String.valueOf(possible_letters.charAt(i));
                 // System.out.println("letter "+letter);

                    if(phnNumber_iterator==1){

                        List<String> possiblePhrasesArrayFromHashmap = new ArrayList<>();


                        possiblePhrasesArrayFromHashmap = set_of_Possible_PhrasesFrom_Dictionary.get(letterFrompossible_FirstLettersOfPhrase);

                        combinedletters = letterFrompossible_FirstLettersOfPhrase+letter;
                     // System.out.println("combinedletters "+combinedletters);
                     // System.out.println("possiblePhrasesArrayFromHashmap "+possiblePhrasesArrayFromHashmap);

                        List<String> possiblePhrasesArray = new ArrayList<>();
                        if(possiblePhrasesArrayFromHashmap!=null && !possiblePhrasesArrayFromHashmap.isEmpty()) {

                            for (int m = 0; m < possiblePhrasesArrayFromHashmap.size(); m++) {
                                String possiblePhrasefromArray = possiblePhrasesArrayFromHashmap.get(m);
                                if (String.valueOf(possiblePhrasefromArray).toUpperCase().startsWith(combinedletters)) {

                                    if (!possiblePhrasesArray.contains(possiblePhrasefromArray)) {
                                        possiblePhrasesArray.add(possiblePhrasefromArray);

                                    }
                                    //Hereeeeeeeee
                                    if (!set_of_Possible_PhrasesKeyCombination.contains(combinedletters)) {

                                        set_of_Possible_PhrasesKeyCombination.add(combinedletters);
                                        set_of_Possible_Phrases.put(combinedletters, possiblePhrasesArray);

                                    }
                                 // System.out.println("set_of_Possible_Phrases "+set_of_Possible_Phrases);
                                 // System.out.println("set_of_Possible_PhrasesKeyCombination "+set_of_Possible_PhrasesKeyCombination);
                                }
                            }
                        }

                    }
                    else{
                     // System.out.println("set_of_Possible_PhrasesKeyCombination "+set_of_Possible_PhrasesKeyCombination);
                        for(int l =0 ;l< set_of_Possible_PhrasesKeyCombination.size(); l++) {
                            String possiblePhrasesKey = set_of_Possible_PhrasesKeyCombination.get(l);
                         // System.out.println("possiblePhrasesKey "+possiblePhrasesKey);

                            List<String> possiblePhrasesArrayFromHashmap = new ArrayList<>();


                            possiblePhrasesArrayFromHashmap = set_of_Possible_Phrases.get(possiblePhrasesKey);
                            combinedletters = possiblePhrasesKey + letter;
                         // System.out.println("possiblePhrasesKey + combinedletters "+combinedletters);

                            List<String> possiblePhrasesArray = new ArrayList<>();
                            if (possiblePhrasesArrayFromHashmap != null && !possiblePhrasesArrayFromHashmap.isEmpty()) {
                                for (int m = 0; m < possiblePhrasesArrayFromHashmap.size(); m++) {
                                    String possiblePhrasefromArray = possiblePhrasesArrayFromHashmap.get(m);

                                    if (String.valueOf(possiblePhrasefromArray).toUpperCase().startsWith(combinedletters)) {
                                        if (!possiblePhrasesArray.contains(possiblePhrasefromArray)) {
                                            possiblePhrasesArray.add(possiblePhrasefromArray);
                                        }
                                        //hereeee
                                        if (!filtered_set_of_Possible_PhrasesKeyCombination.contains(combinedletters)) {

                                            filtered_set_of_Possible_Phrases.put(combinedletters, possiblePhrasesArray);
                                            filtered_set_of_Possible_PhrasesKeyCombination.add(combinedletters);
                                        }
                                     // System.out.println("filtered_set_of_Possible_Phrases "+filtered_set_of_Possible_Phrases);
                                     // System.out.println("filtered_set_of_Possible_PhrasesKeyCombination "+filtered_set_of_Possible_PhrasesKeyCombination);

                                    }

                                }
                            }

                        }

                    }
                }


                if(phoneNumber_inArray.size()-1 == phnNumber_iterator){

                    encodedWord  = getencodedPhrase_fromFilteredArray(filtered_set_of_Possible_Phrases,filtered_set_of_Possible_PhrasesKeyCombination,phoneNumber_inArray);

                }
            }
        }
        return encodedWord;
    }

    private static String getencodedPhrase_fromFilteredArray(HashMap<String, List<String>> set_of_possible_phrases, List<String> set_of_possible_phrasesKeyCombination, List<String> phoneNumber_inArray) {
    String encodedWord = "no matches";
    int total_PhnNumbr_count = 0;
        List<String> full_set_of_possible_phrases_Local = new ArrayList<>();
        List<String> other_possible_phrases_Min_Length = new ArrayList<>();

        total_PhnNumbr_count = phoneNumber_inArray.size();


        for(int i =0 ; i< set_of_possible_phrasesKeyCombination.size(); i++){

            String phraseKey = "";
            phraseKey = set_of_possible_phrasesKeyCombination.get(i);
            List<String> set_of_possible_phrases_Local = new ArrayList<>();
            List<String>  pharseKey_letters_inArray = new ArrayList<>();
            List<String>  possible_letters_inArray = new ArrayList<>();

            if(phraseKey.length() == total_PhnNumbr_count){
                set_of_possible_phrases_Local = set_of_possible_phrases.get(phraseKey);
                if(set_of_possible_phrases_Local!= null && !set_of_possible_phrases_Local.isEmpty()){
                    int no_of_matching_char = 0;
                    for (int m = 0; m < phraseKey.length(); m++) {
                        pharseKey_letters_inArray.add(String.valueOf(phraseKey.charAt(m)));
                    }
                    for (int m = 0; m < pharseKey_letters_inArray.size(); m++) {
                        possible_letters_inArray.clear();
                        String lettersFromPhrase = pharseKey_letters_inArray.get(m);
                        String digitFromPhnNumberArray = phoneNumber_inArray.get(m);
                        String  possible_letters = allocatedAlphabetsHashmap.get(digitFromPhnNumberArray);
                        try{
                            possible_letters = possible_letters.replace(",","");
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                        for (int n = 0; n < possible_letters.length(); n++) {
                            possible_letters_inArray.add(String.valueOf(possible_letters.charAt(n)));
                        }
                       if(possible_letters_inArray.contains(lettersFromPhrase)){
                           no_of_matching_char =no_of_matching_char+1;

                       }
                       if(pharseKey_letters_inArray.size()-1 == m){
                           if(no_of_matching_char==total_PhnNumbr_count){
                               full_set_of_possible_phrases_Local.addAll(set_of_possible_phrases_Local);

                           }
                       }
                    }


                }

            }
          /*  else if(phraseKey.length() < total_PhnNumbr_count){
                List<String> second_set_of_possible_phrases_Local = new ArrayList<>();

                set_of_possible_phrases_Local = set_of_possible_phrases.get(phraseKey);
                for(int k =0; k<set_of_possible_phrases_Local.size(); k++){
                    String otherPhrase = set_of_possible_phrases_Local.get(k);

                    if(phraseKey.toUpperCase().equals(otherPhrase.toUpperCase())){

                        second_set_of_possible_phrases_Local.add(otherPhrase);
                    }
                }
                other_possible_phrases_Min_Length.addAll(second_set_of_possible_phrases_Local);


            }



           */

            if(set_of_possible_phrasesKeyCombination.size()-1 == i){
                if(full_set_of_possible_phrases_Local.size()>0){

                    encodedWord = getRandomElement(full_set_of_possible_phrases_Local);
                }
              /*
                else{

                    for(int k =0; k< other_possible_phrases_Min_Length.size();k++){
                        int lengthofPhrase = total_PhnNumbr_count;
                        String firstphrase = "";
                        firstphrase = other_possible_phrases_Min_Length.get(k);

                        if(firstphrase.length()<lengthofPhrase){

                        }
                    }

                }


               */
            }




        }



    return encodedWord;
    }

    public static String getRandomElement(List<String> list)
    {
      //  System.out.println("list:  "+list);

        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }


    private static HashMap<String, List<String>>
          getSetOfPossiblePhrasesFromDictionary(List<String> possible_firstLettersOfPhrase, String number) {

        List<String> set_of_PhrasesFromDict = new ArrayList<>();

        set_of_PhrasesFromDict = GetPhrasesListFromDictionary();

         HashMap<String, List<String>> set_of_Possible_Phrases_hashmap = new HashMap<>();


        for(int i=0 ;i < possible_firstLettersOfPhrase.size();i++){
            List<String> set_of_Possible_PhrasesArray = new ArrayList<>();
            String possibleletter = possible_firstLettersOfPhrase.get(i);

            for(int j=0; j<set_of_PhrasesFromDict.size(); j++){
                String phraseFromDict = set_of_PhrasesFromDict.get(j);
                char firstLetterOFphraseFromDict = phraseFromDict.charAt(0);
                if(phraseFromDict.length()<=number.length()){
                    if(String.valueOf(firstLetterOFphraseFromDict).toUpperCase().equals(possibleletter)){
                        set_of_Possible_PhrasesArray.add(phraseFromDict);
                    }
                }
                if(set_of_PhrasesFromDict.size()-1 == j){
                    set_of_Possible_Phrases_hashmap.put(possibleletter,set_of_Possible_PhrasesArray);
                   // System.out.println(set_of_Possible_Phrases_hashmap);

                }
            }





        }



    return set_of_Possible_Phrases_hashmap;
    }

    private static List<String> GetPhrasesListFromDictionary() {
        List<String> set_of_PhrasesFromDict = new ArrayList<>();
        File file  = new File((config_Helper.dictionary_FileName));

      //  File file  = new File(Objects.requireNonNull(Main.class.getResource(config_Helper.dictionary_FileName)).getPath());

        try {
            File directoryInput = new File(String.valueOf(file));
            BufferedReader br = new BufferedReader(new FileReader(directoryInput));
            String Phrase = null;
            while ((Phrase = br.readLine()) != null) {
                //  System.out.println(Phrase);

                set_of_PhrasesFromDict.add(Phrase);



            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return set_of_PhrasesFromDict;
    }



}
