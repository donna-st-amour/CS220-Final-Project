   import java.util.Scanner;
   import java.util.Random;

public class WarCardGame2{
   
   public static void main(String[]args){
      String PlayerName;
      char Suit;
      int CardValue;
      String[] deck = new String[52];
      int a = 25; //number of cards in player's deck
      int b = 25; //number of cards in computer's deck
      String[] playerHand = new String[a];
      String[] computerHand= new String[b];
      
      PlayerName = getPlayerName();
      deck = initializeDeck();
      playerHand = getPlayerHand(deck);
      computerHand = getComputerHand(deck);
      
     }//end main    
      

      public static String getPlayerName(){
         Scanner keyboard = new Scanner(System.in);
         System.out.println ("Please enter your name.");
         String name = keyboard.nextLine();
         return name;
      }//end getPlayerName method
      
      public static String[] initializeDeck(){  //method to initialize the deck
         String[] deck = new String[52];
         for (int i=2; i<15; i++){
            deck[i-2] = i + "H";
         }//end hearts loop
         
         for (int j=2; j<15; j++){
            deck[j+11] = j + "S";
         }//end spades loop
         
         for (int k = 2; k < 15; k++){
            deck[k + 24] = k + "D";
         }//end diamonds loop
         
         for (int m = 2; m < 15; m++){
            deck[m + 37] = m + "C";
         }//end clubs loop
         

         
         /* The next lines are for testing purposes */
         
         for (int x = 0; x < 52; x ++){
            System.out.println (deck[x]);
         }//end test for loop
         System.out.println();
         return deck;
      }//end initializeDeck method
      
      public static String[] getPlayerHand(String[] deck){
         String[] playerDeck = new String[26];
         Random randomInt = new Random (52);//creates an instance of the Random class
         int z;//used to store the random int
         for (int y = 0; y < 26; y ++){
            z = randomInt.nextInt(51);//gets the next random int between 0 and 51
            while(deck[z] == "0")
               z = randomInt.nextInt(51);
            while (deck[z]!= "0"){
               playerDeck[y] = deck[z];
                  deck[z] = "0";
            }//end while loop      
         }//end for loop
            
            for (int x = 0; x < 26; x ++){
            System.out.println (playerDeck[x]);
            }//end test for loop
           System.out.println();
            
            return playerDeck;
      }//end getPlayerHand method
      
      public static String[] getComputerHand(String[] deck){
         String[] computerDeck = new String[26];
         String[] newDeck = new String[26];
         for (int y = 0; y < 52; y ++){
            for(int index = 0; index < 26; index++){
               while(deck[y] != "0"){
                  newDeck[index] = deck[y];
               }//end while
            }//end inner for
         }//end outer for
         
         //for testing
         for(int index = 0;index < 26; index++){
            System.out.println(newDeck[index]);
         }//end for
         System.out.println();
                           
         
         
            Random randomInt = new Random (25);
            int z = randomInt.nextInt(25);//gets the next random int between 0 and 25
            for(int y = 0; y < 26; y++){
               while(newDeck[z] == "0"){
                  z = randomInt.nextInt(25);
               }//end first while loop
               while (newDeck[z]!= "0"){
                  computerDeck[y] = newDeck[z];
                     newDeck[z] = "0";
               }//end while loop      
            }//end for loop                   
            for (int x = 0; x < 26; x ++){
            System.out.println (computerDeck[x]);
            }//end test for loop
            System.out.println();
            return computerDeck;
      }//end getComputerHand method

}//end class