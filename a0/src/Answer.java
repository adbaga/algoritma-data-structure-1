import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

public class Answer {

   public static void main (String[] param) {

      // TODO!!! Solutions to small problems 
      //   that do not need an independent method!
    
      // conversion double -> String
      double doub = 13.888;
      String strValOfDoub = String.valueOf(doub);
      System.out.println(strValOfDoub);
      System.out.println(strValOfDoub.getClass().getName());
      

      // conversion String -> int
      int int1;


      
      try { 
         
         int1 = Integer.parseInt("638");

         System.out.println(int1);
      
      } catch (NumberFormatException e){
         e.printStackTrace();
      }
      

      // "hh:mm:ss"
      Format frmt = new SimpleDateFormat("HH.mm.ss");
      String date = frmt.format(new Date());
      System.out.println("Time : "+date);

      // cos 45 deg
      double num = 45;
      double rad = Math.toRadians(num);
      double cosVal = Math.cos(rad);
      System.out.println("Cos 45 = "+cosVal);

      // table of square roots

      String firstString = "ABcd12";
      String result = reverseCase (firstString);
      System.out.println ("\"" + firstString + "\" -> \"" + result + "\"");

      // reverse string
      String input = "java let's go";
      StringBuilder output = new StringBuilder();
      output.append(input);
      output.reverse();
      System.out.println(output);


      String s = "How  many	 words   here";
      int nw = countWords (s);
      System.out.println (s + "\t" + String.valueOf (nw));

      // pause. COMMENT IT OUT BEFORE JUNIT-TESTING!

      final int LSIZE = 100;
      ArrayList<Integer> randList = new ArrayList<Integer> (LSIZE);
      Random generaator = new Random();
      for (int i=0; i<LSIZE; i++) {
         randList.add (Integer.valueOf (generaator.nextInt(1000)));
      }

      // minimal element

      // HashMap tasks:
      //    create
      //    print all keys
      //    remove a key
      //    print all pairs

      System.out.println ("Before reverse:  " + randList);
      reverseList (randList);
      System.out.println ("After reverse: " + randList);

      System.out.println ("Maximum: " + maximum (randList));
   }

   /** Finding the maximal element.
    * @param a Collection of Comparable elements
    * @return maximal element.
    * @throws NoSuchElementException if <code> a </code> is empty.
    */
   static public <T extends Object & Comparable<? super T>>
         T maximum (Collection<? extends T> a) 
            throws NoSuchElementException {
      return Collections.max(a); // TODO!!! Your code here
   }

   /** Counting the number of words. Any number of any kind of
    * whitespace symbols between words is allowed.
    * @param text text
    * @return number of words in the text
    */
   public static int countWords (String text) {

      if(text == null || text.isEmpty() ){

         return 0;
      }

      int wordCount = 0;

      boolean isWord = false;
      int endOfLine = text.length() - 1;
      char[] characters = text.toCharArray();


      for (int i = 0; i < characters.length; i++) {

         if(Character.isLetter(characters[i]) && i != endOfLine){

            isWord = true;

         }

         else if(!Character.isLetter(characters[i]) && isWord){


            isWord = false;
            wordCount++;

         }

         else if(Character.isLetter(characters[i]) && i == endOfLine){

            wordCount++;

         }


      }

      return wordCount;




   }

   /** Case-reverse. Upper -> lower AND lower -> upper.
    * @param s string
    * @return processed string
    */
   public static String reverseCase (String s) {

      if(s == null || s.isEmpty() ){

         return null;
      }

      int wordCount = 0;
      char[] characters = s.toCharArray();

      int endOfWord = s.length() - 1;

      for(int i=0; i < characters.length; i++){

         if(Character.isLetter(characters[i]) && Character.isUpperCase(characters[i]) ){

            characters[i] = Character.toLowerCase(characters[i]);
         }

         else if(Character.isLetter(characters[i]) && Character.isLowerCase(characters[i]) ){

            characters[i] = Character.toUpperCase(characters[i]);
         }
      }

      String output = new String(characters);


      return output ; // TODO!!! Your code here
   }

   /** List reverse. Do not create a new list.
    * @param list list to reverse
    */
   public static <T extends Object> void reverseList (List<T> list)
      throws UnsupportedOperationException {


      Collections.reverse(list);
//
//         // TODO!!! Your code here
   }
}
