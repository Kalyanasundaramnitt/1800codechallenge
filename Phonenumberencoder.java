import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Phonenumberencoder {
    public static HashMap<String, String> dictionary_AlphabetsHashmap = new HashMap<>();

    // This method returns encoded word from the dictionary
    public static  String get_encoded_Word(String number) {

        dictionary_AlphabetsHashmap.put("1","A,B,C");
        dictionary_AlphabetsHashmap.put("2","A,B,C");
        dictionary_AlphabetsHashmap.put("3","D,E,F");
        dictionary_AlphabetsHashmap.put("4","G,H,I");
        dictionary_AlphabetsHashmap.put("5","J,K,L");
        dictionary_AlphabetsHashmap.put("6","M,N,O");
        dictionary_AlphabetsHashmap.put("7","P,Q,R,S");
        dictionary_AlphabetsHashmap.put("8","T,U,V");
        dictionary_AlphabetsHashmap.put("9","W,X,Y,Z");
        dictionary_AlphabetsHashmap.put("0","W,X,Y,Z");



        HashMap<String, String> allocatedAlphabetsHashmap ;
        allocatedAlphabetsHashmap  = getAllocatedAlphabets(number);
     // System.out.println("allocatedAlphabetsHashmap "+allocatedAlphabetsHashmap);
     // System.out.println("number "+number);
        String encodedWord  = "";
       // System.out.println(number);
      //  System.out.println(allocatedAlphabetsHashmap);
        try{
            encodedWord = Phrasegenerator.getencodedPhrase(allocatedAlphabetsHashmap,number);
        }
        catch (Exception e){
            e.printStackTrace();
        }
     // System.out.println("encodedWord "+encodedWord);
        return encodedWord;
    }

    public static HashMap<String, String> getAllocatedAlphabets(String number) {
        List<String> phoneNumber_inArray = new ArrayList<>(number.length());
        HashMap<String, String> allocatedAlphabetsHashmap = new HashMap<>();
        for (int i = 0; i < number.length(); i++) {
            phoneNumber_inArray.add(String.valueOf(number.charAt(i)));
        }

        for (String phoneNumberSingleDigit : phoneNumber_inArray) {
            //for (char phoneNumberSingleDigit : phoneNumber_inArray) {
            //  System.out.println(phoneNumberSingleDigit);
            String allocated_Alpha;

            try {
                allocated_Alpha = dictionary_AlphabetsHashmap.get(String.valueOf(phoneNumberSingleDigit));

            } catch (Exception e) {
                allocated_Alpha = "";
                e.printStackTrace();
            }

           // System.out.println(allocated_Alpha);
            try {
                allocatedAlphabetsHashmap.put(String.valueOf(phoneNumberSingleDigit), allocated_Alpha);
            } catch (Exception e) {
                e.printStackTrace();
            }



        }

     return  allocatedAlphabetsHashmap;
    }
}
