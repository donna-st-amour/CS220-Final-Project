   import java.util.Scanner;
   import java.util.Random;

public class WarCardGame{
   
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
      initializeDeck();
      playerHand = getPlayerHand(deck);
      
     }//end main    
      

      public static String getPlayerName(){
         Scanner keyboard = new Scanner(System.in);
         System.out.println ("Please enter your name.");
         String name = keyboard.nextLine();
         return name;
      }//end getPlayerName method
      
      public static String[]initializeDeck(){  //method to initialize the deck
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
         return deck;
      }//end initializeDeck method
      
      public static String[] getPlayerHand(String[] deck){
         String[] playerDeck = new String[25];
         String[] computerDeck = new String[25];
         Random randomInt = new Random (52);
         int playerHandCount = 0;
         int compHandCount = 0;
         for (int y = 0; y < 52; y ++){
            int z = randomInt.nextInt(51);
            //What does this do?
            
            while (deck[z]!= "0"){
               if (y % 2 == 0){
                  playerDeck[playerHandCount] = deck[z];
                  deck[z] = "0";
                  playerHandCount ++;
               }// end if loop
               
               else if (y % 2 != 0){
                  computerDeck[compHandCount] = deck[z];
                  deck[z] = "0";
                  compHandCount ++;
               }//end else if loop    
            }//end while loop      
         }//end for loop
            
            for (int x = 0; x < 26; x ++){
            System.out.println (playerDeck[x]);
            }//end test for loop
            
            return playerDeck;
      }//end getPlayerHand method

}//end class
