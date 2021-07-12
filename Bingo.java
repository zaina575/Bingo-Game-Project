import java.io.*;
import java.util.*;
public class Bingo{

public static void main (String[]args) throws IOException{

int [][] bingoCard = new int [5][5]; //array for bingo card
ArrayList<Integer> numberPicked = new ArrayList<Integer>(); //arrayList to store all the random numbers picked
fillCard(bingoCard); //calling the fill card method
printCard(bingoCard); //calling the print card method
playGame(numberPicked, bingoCard); //calling the play game method
printCard(bingoCard); //printing the card after the game is done

}

//filling Card using input file
//Precondition: The 5 X 5 array that stores the bingo card should not have any values stored.
//Postcondition: The 5 X 5 array will be intialized with 25 integers of input from the external file.
public static void fillCard(int [][] bingoCard) throws IOException{
    
	Scanner fill = new Scanner(new File("bingo.in")); //scanner for inputting the "bingo.in" file
	//for loop to fill the bingo card up
	for(int i = 0; i < bingoCard.length; i++){
	for(int j = 0; j < bingoCard[i].length; j++)
	{
		bingoCard[i][j] = fill.nextInt();
	}	
	}			
	}		

//print the card method
//Precondition: The 5 X 5 array for the bingo card is initalized with 25 integers from the external file.
//Postcondition: The bingo card will be printed in a neat format where the user can see all the values in the bingo card.
public static void printCard(int [][] bingoCard){
System.out.println("YOUR BINGO CARD: ");
System.out.println();
System.out.println("    B" + "\t" + "    I" + "\t" + "    N" + "\t" + "    G" + "\t" + "    O");
System.out.println("-----------------------------------------");
for(int i = 0; i<bingoCard.length ; i++){
for(int j = 0; j<bingoCard[i].length; j++){
System.out.print("|");

//if the bingoCard has zeros in them print X's instead
if(bingoCard[i][j] == 0){
  System.out.print("   " + "X");
  if(j<=4) System.out.printf("\t");
  if(j==4) System.out.print("|");
}

//else print card normally
else{
System.out.printf("%4d", bingoCard[i][j]);
    if(j<=4) System.out.printf("\t");	
    if(j==4) System.out.print("|");
}
}

System.out.println();
System.out.println("-----------------------------------------");

}
}


//main driver method for playing Game
//Precondition: The ArrayList is unintialized and the bingo card array is filled with 25 integers from the external file.
//Postcondition: All the random numbers generated, the number of times it took the player to win bingo,and how they won bingo will be printed out.
public static void playGame(ArrayList<Integer> numberPicked, int [][] bingoCard){
//generate a number till a win is found
while(checkForWin(bingoCard)  == 0){
generateNumber(numberPicked, bingoCard);
}

//print the following once a win is found
if(checkForWin(bingoCard) != 0){
System.out.println("BINGO NUMBERS PICKED AT RANDOM FROM BIN: ");
//printing out 8 per line
int counter = 0; //counts number of integers that are printed on a line so far

for(int i = 0; i < numberPicked.size(); i ++){
{
System.out.print(numberPicked.get(i) + "\t");
counter++;

//go to the next line if there are 8 elements 
if(counter==8){
System.out.println();
counter = 0;
}
}
}

System.out.println();

//swtich statement for determining what type of win was made
switch(checkForWin(bingoCard)){
case 1:
System.out.println("YOU WIN WITH A HORIZONTAL BINGO AFTER " + numberPicked.size() + " PICKS!");
break;
case 2:
System.out.println("YOU WIN WITH A VERTICAL BINGO AFTER " + numberPicked.size() + " PICKS!");
break;
case 3:
System.out.println("YOU WIN WITH A DIAGONAL BINGO AFTER " + numberPicked.size() + " PICKS!");
break;
}}
}




//generating number method
//Precondition:The ArrayList is unintialized and the bingo card array is filled with 25 integers from the external file.
//Postcondition: A random number will be generated that will be added to the ArrayList that is not repeated from before.
public static void generateNumber(ArrayList<Integer> numberPicked, int [][] bingoCard){
int currentrandom = (int)(Math.random()*75+1);//variable to store random number

//generate a random number until it doesn't repeat
while((numberPicked.contains(currentrandom) == true)){

currentrandom = (int)(Math.random()*75+1);

}

numberPicked.add(currentrandom);//add it to arrayList once it is different

for(int i = 0; i < bingoCard.length; i++){
for(int j = 0; j < bingoCard[i].length; j++){
if(bingoCard[i][j] == currentrandom){
	bingoCard[i][j] = 0;
}
}
}
}

//check for win method 
//Precondition: The bingoCard is intialized with 25 integers from the external file.
//Postcondition: An integer will be returned that represents the type of win or no win at all so far. 
public static int checkForWin(int [][] bingoCard){
int HORIZONTAL = 1; //horizontal variable 
int VERTICAL = 2; //vertical variable 
int DIAGONAL = 3; //diagonal variable 
int NOWIN = 0; //if no win found
int sum; //sum variable

//summing for horizontal summations to check if there is a win
for(int i = 0; i < bingoCard.length; i ++){
    sum = 0;
    for(int j = 0; j < bingoCard[i].length; j++){
 	sum+= bingoCard[i][j];}
	if(sum == 0) {
	return HORIZONTAL;

	}  
}

//summing for vertical summations to check if there is a win
for (int j = 0; j < bingoCard[0].length; j++){
sum = 0;
for(int i = 0; i<bingoCard.length; i++){
  sum+= bingoCard[i][j];}
    if(sum == 0){
    return VERTICAL;
	}	
} 

//summing diagonal sum from top left hand corner to the bottom right corner of the board

int length = 0; //variable to ensure that it runs through the whole diagonal 
sum= 0;
for(int i = 0; i < bingoCard.length; i++){
	sum+= bingoCard[i][i];
	length++;
	if(length == 5) {
	if(sum == 0){
	return DIAGONAL;}
	else 
	sum = 0;
}
}


//summing diagonal sum from the bottom left corner to the top right corner of the board
int length1 = 0; //variable to ensure that it runs through the whole diagonal (similar to the one above)
sum = 0;
for(int i = 0; i < bingoCard.length; i++){
	sum+=  bingoCard[i][bingoCard.length-i-1];
	length1++;
	if(length1 == 5){
        if(sum == 0){
        return DIAGONAL;
	}
	else sum = 0;
}	
}
return NOWIN; //if no win returned than zero is returned
}
}









