package is.ru.tictactoe;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static spark.Spark.*;
import spark.*;

public class TicTacToe {
    int x;
    char grid[];
    int player;
    
    public TicTacToe() 
    {
        grid = new char[9];
        player = 1;
        initializedGrid();
    }

    public void initializedGrid()
    {
        for (int i = 0; i < 9; i++){
            grid[i] = '-';
        }
    }

    // Checks who is winner
    public boolean checkForWin(){
        if((winningRow() == true || winningColumn() == true) || winningDiagonal() == true){
            return true;
        }
        return false;
    }

    // initialize player
    public void initializePlayer()
    {
        player = 1;
    }


    // Checks if there is a winner in a row

    private boolean winningRow(){
        
        if(grid[0] != '-' && grid[0] == grid[1] && grid[1] == grid[2]){
            return true;
        }
        if(grid[3] != '-' && grid[3] == grid[4] && grid[4] == grid[5]){
            return true;
        }
        if(grid[6] != '-' && grid[6] == grid[7] && grid[7] == grid[8]){
            return true;
        }
        return false;
    }

    // Checks if there is a winner in a column
    private boolean winningColumn(){
        if(grid[0] != '-' && grid[0] == grid[3] && grid[3] == grid[6]){
            return true;
        }
        if(grid[1] != '-' && grid[1] == grid[4] && grid[4] == grid[7]){
            return true;
        }
        if(grid[2] != '-' &&grid[2] == grid[5] && grid[5] == grid[8]){
            return true;
        }
        return false;
    }
    // Checks if there is a winner diagonally
    private boolean winningDiagonal(){
        if(grid[0] != '-' && grid[0] == grid[4] && grid[4] == grid[8]){
            return true;
        }       
        if(grid[2] != '-' && grid[2] == grid[4] && grid[4] == grid[6]){
            return true;
        }
        return false;
    }

    // function to check if the input is valid
    public boolean checkIfValidInput(){
        // check if the grid is whithin the limit of the array
        if ( x > 8 || x < 0){
            return false;
        }
        // check if the grid is empty
        if ( grid[x] != '-'){
            return false;
        }
        return true;
    }

    //check for tie
    public boolean checkForTie() {
        for (int i = 0; i < 9; i++){
            if (grid[i] == '-') return false;
        }
        return true;
    }
     
    public void changePlayer(){
        if (player == 1) player = 2;
        else player = 1;
    }

    public void insert(){
        if(player == 1){
            grid[x] = 'X';  
        }
        else{
            grid[x] = 'O';
        }
    }
   
    public void play(){
        Scanner in = new Scanner(System.in);
        boolean validInput;
        
        while(!checkForWin() && !checkForTie()){
            validInput = false;

            do{
           
            x = in.nextInt();
            if(checkIfValidInput()){
                insert();
                validInput = true;
            };
            }while (!validInput);   
            changePlayer();         
        }

        if(checkForWin())
        {
            changePlayer();
            System.out.println();
            System.out.println("Player " + player + " WON!!!!! ");
        }
        else if(checkForTie())
        {
            System.out.println("We have a TIE!!!!!! ");
        }
        in.close();
    }
    

    public static void main(String[] args) 
    {
        staticFileLocation("/public");
        setPort(Integer.valueOf(System.getenv("PORT")));
        final TicTacToe tictactoe = new TicTacToe();

        post(new Route("/add") 
        {
            @Override
            public Object handle(Request request, Response response) 
            {
                //System.out.println("Pálmi er awesome");
                Integer gridId = Integer.valueOf(request.queryParams("gridId"));
                tictactoe.x = gridId;
                if(!tictactoe.checkIfValidInput()){
                    return "0";
                }
                else{
                    tictactoe.insert();
                    tictactoe.changePlayer();
                    if ( tictactoe.player == 2 ){
                        return "X";
                    }
                    else {
                        return "Y";
                    }
                }
            }
        });

        post(new Route("/win") 
        {
            @Override
            public Object handle(Request request, Response response) 
            {
                Integer gridId = Integer.valueOf(request.queryParams("gridId"));

                if(tictactoe.checkForWin()){
                    tictactoe.initializedGrid();
                    if(tictactoe.player == 1){
                        // player y wins
                        tictactoe.initializePlayer();
                        return "player2";
                    }
                    else{
                        // player x wins
                        tictactoe.initializePlayer();
                        return "player1";
                    }
                }
                else if(tictactoe.checkForTie()){
                    tictactoe.initializePlayer();
                    tictactoe.initializedGrid();
                    return "Tie";
                }
                else{
                    return "keepPlaying";
                }
            }
        });

    }
}