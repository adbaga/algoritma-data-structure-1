public class Sheep {

   enum Animal {sheep, goat};
   
   public static void main (String[] param) {
      // for debugging
      Animal[] farm = new Sheep.Animal [10];

      for (int i=0; i < farm.length; i++) {
         double rnd = Math.random();
         if (rnd < 1./2.) {
            farm[i] = Animal.sheep;

         } else {
            farm[i] = Animal.goat;

         }
      }

      System.out.println(java.util.Arrays.toString(farm));
      Sheep.reorder (farm);
      System.out.println(java.util.Arrays.toString(farm));


   }
   
   public static void reorder (Animal[] animals) {
      // TODO!!! Your program here



      int f = 0; //f for first
      int l = animals.length - 1; //l for last


      for(int i=0; i <= l; i++){

         if(f<l){

            if(animals[i] == Animal.goat){
               Animal tmp = animals[i];
               animals[i] = animals[f];
               animals[f] = tmp;

               f++; //increment f so we have new beginning

            }

            else  {

               Animal tmp = animals[i];
               animals[i] = animals[l];
               animals[l] = tmp;

               l--; //let's decrement l so we have new ending
               i--; //in case that the last element we swap was sheep

            }




         }

      }




         }
      }


