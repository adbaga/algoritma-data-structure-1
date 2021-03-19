import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class LongStack {

   private LinkedList<Long> lifoStack;

   public static void main (String[] argum) {

      long rpn2 = interpret("5 - 2 ");
      System.out.println(rpn2);

   }

   LongStack() {
      this.lifoStack = new LinkedList<>();
   }

   LongStack(LinkedList<Long> lifoStack) {

      this.lifoStack = lifoStack;
   }




   @Override
   public Object clone() throws CloneNotSupportedException {
      if(this.getClass() != new LongStack().getClass()){
         throw new CloneNotSupportedException("Clone Not Supported");
      }

      return new LongStack((LinkedList<Long>) this.lifoStack.clone());
   }

   public boolean stEmpty() {

      return this.lifoStack.isEmpty();
   }

   public void push (long a) {

      this.lifoStack.push(a);
   }

   public long pop() {

      LinkedList<Long> stack = this.lifoStack;



      if(stack.size() > 0){

         return this.lifoStack.pop();


      }else{

         throw new RuntimeException("The stack is empty");
      }





   }


   public void op (String s) {

      LinkedList<Long> stack = this.lifoStack;


//      try {
//      if(s.equals(" ") || s.equals("\t")){
//         return;
//      } else if(s.equals("+")){
//         stack.push( stack.pop() + stack.pop());
//      }else if(s.equals("-")){
//         stack.push(-stack.pop() + stack.pop());
//      }else if(s.equals("*")){
//         stack.push(stack.pop() * stack.pop());
//      }else if(s.equals("/")){
//
//         long r1 = stack.pop();
//         long r2 = stack.pop();
//         stack.push(r2/r1);
//      }else{
//
//         String message = "Postfix contains illegal symbol" + s;
//
//         throw new NumberFormatException(message);
//      }
//      } catch (NoSuchElementException e) {
//         throw new NoSuchElementException("Postfix does not have enough number to perform operations");
//      }





            switch (s) {
               case (" "):
                  break;
               case "\t":
                  break;
               case "+":
                  stack.push(stack.pop() + stack.pop());
                  break;
               case "-":
                  stack.push(-stack.pop() + stack.pop());
                  break;
               case "*":
                  stack.push(stack.pop() * stack.pop());
                  break;
               case "/":
                  long r2 = stack.pop();
                  long r1 = stack.pop();
                  stack.push( r1 / r2);
                  break;
               default:

                  try {
                     Long num = Long.parseLong(s);
                     stack.push(num);
                     break;
                  }catch(NumberFormatException e){

                     String message = "Illegal character" + s + " is included in the string";
                     e.printStackTrace();

                     throw new NumberFormatException(message);
                  }


            }



      }











   public long tos() {

      if(this.lifoStack.size() > 0){

         return this.lifoStack.getFirst();
      }else{

         throw new RuntimeException("The stack is empty");
      }

   }

   @Override
   public boolean equals (Object o) {
      return this.lifoStack.equals(((LongStack)o).lifoStack);
   }

   @Override
   public String toString() {
      StringBuilder s = new StringBuilder();
      Iterator<Long> i = this.lifoStack.descendingIterator();

      while (i.hasNext()) {
         s.append(i.next());
         s.append(" ");
      }
      return s.toString();
   }

   public static long interpret (String pol) {

      LongStack stack = new LongStack();

      LinkedList<Character> postfix = new LinkedList<>();


      for(int k = 0; k < pol.length(); k++){

         postfix.add(pol.charAt(k));

         if(k == pol.length() -1){
            postfix.add(' ');
         }


      }


      int l = postfix.size();
      for(int i = 0; i < l-1; i++) {

         //fpointer = first pointer
         //sPointer = second pointer

         char fPointer = postfix.get(i);
         char sPointer = postfix.get(i+1);


         if ((fPointer == '-' && sPointer >= '0' && sPointer <= '9')|| (fPointer >= '0' && sPointer <= '9')) {

            StringBuilder buffer = new StringBuilder();
            buffer.append(fPointer);
            i++;

            while(sPointer >= '0' && sPointer <= '9'){
               buffer.append(sPointer);
               i++;
               sPointer = postfix.get(i);
            }

            try {
               long elem = Long.parseLong(buffer.toString());
               stack.push(elem);
            } catch (NumberFormatException e){

               String message = "Illegal character " + buffer + " is included in the string " + pol;
               throw new NumberFormatException(message);
            }



         }

         else {

            if(fPointer == ' ' || fPointer == '\t' || fPointer == '*' || fPointer == '/' || fPointer == '-' || fPointer == '+'){
               String s = String.valueOf(fPointer);

               try{
                  stack.op(s);
               }catch(NoSuchElementException e){
                  throw new NoSuchElementException("Postfix does not have enough number to perform operations " + pol);
               }

            }else{

               String errorMessage = "Postfix contains illegal symbol " + fPointer + " in postfix " + pol;
               throw new IllegalArgumentException(errorMessage);

            }



         }
      }

      if (stack.lifoStack.size() == 1) {
         return stack.pop();
      } else if(stack.stEmpty()){
         throw new RuntimeException("Postfix is empty");
      }
         else {
         throw new RuntimeException("Postfix contains too many numbers in " + pol);
      }


   }






}

//sources:
//https://git.wut.ee/i231/home3/src/branch/master/src/LongStack.java
//From this repository I used it to understand the algortihm of
// how the string builder works here and handling numbers with more than one digit
//and toString method. I added modifications on and implements more error handling than filters
//element that are pushed into the stack



//https://www.geeksforgeeks.org/evaluate-the-value-of-an-arithmetic-expression-in-reverse-polish-notation-in-java/
//From this code I implemented the operation method and add more exception handling and handling
//of space and tab
