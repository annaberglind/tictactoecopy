package is.ru.tictactoe;


import static org.junit.Assert.assertEquals;


import org.junit.Test;


public class TicTacToeTest {

    @Test
    public void testConstructorGrid() {
    	TicTacToe test = new TicTacToe();
    	// test the length of the array grid
        assertEquals(9, test.grid.length);
    }
    
    @Test
    public void testConstructorPlayer() {
    	// Player one should start
    	TicTacToe test = new TicTacToe();
        assertEquals(1, test.player);
    }

    @Test
    public void testIsGridFullOfLines() {
    	TicTacToe test = new TicTacToe();
    	for (int i = 0; i < 9; i++){
			assertEquals("-", Character.toString(test.grid[i]));
		}
    }

    
    @Test
    public void testIsWinningRow() {
        TicTacToe test = new TicTacToe();

        //checking if grid full of '-' returns false
        assertEquals(false, test.checkForWin());

        for(int i = 0; i < 3; i++){
            test.grid[i] = 'X';
        }
        //checking if grid with first row full of 'X'
        //returns true
        assertEquals(true, test.checkForWin());

        test.initializedGrid();
        for(int i = 3; i < 6; i++){
            test.grid[i] = 'X';
        }
        //checking if grid with second row full of 'X'
        //returns true
        assertEquals(true, test.checkForWin());

        test.initializedGrid(); 
        for(int i = 6; i < 9; i++){
            test.grid[i] = 'X';
        }
        //checking if grid with third row full of 'X'
        //returns true
        assertEquals(true, test.checkForWin());

        test.initializedGrid();
        //checking if grid with no row full of sama symbol
        //returns false
        assertEquals(false, test.checkForWin());
    }

    @Test
    public void testIsWinningColumn() {
        //testing the function WinningColumn
        TicTacToe test = new TicTacToe();

        //check if grid full of '-' return false
        assertEquals(false, test.checkForWin());
            
        for(int i = 0; i < 9; i = i + 3){
            test.grid[i] = 'X';
        }
        //checking if grid with first column full of 'X'
        //returns true
        assertEquals(true, test.checkForWin());

        test.initializedGrid();
        for(int i = 1; i < 9; i = i + 3){
            test.grid[i] = 'X';
        }
        //checking if grid with second column full of 'X'
        //returns true
        assertEquals(true, test.checkForWin());

        test.initializedGrid(); 
        for(int i = 2; i < 9; i = i + 3){
            test.grid[i] = 'X';
        }
        //checking if grid with third column full of 'X'
        //returns true
        assertEquals(true, test.checkForWin());

        test.initializedGrid();
        //checking if grid with no column full of same
        //symbol returns false
        assertEquals(false, test.checkForWin());
    }


    @Test
    public void testIsWinningDiagonal() {
        //testing the function WinningColumn

        TicTacToe test = new TicTacToe();

        //Check if initialized grid returns false
        assertEquals(false, test.checkForWin());

        for(int i = 0; i < 9; i = i + 4){
            test.grid[i] = 'X';
        }
        //Check if returns true if win in 0-4-8 diagonal
        assertEquals(true, test.checkForWin());

        test.initializedGrid();
        for(int i = 2; i < 7; i = i + 2){
            test.grid[i] = 'X';
        }

        //Check if returns true if win in 2-4-6 diagonal
        assertEquals(true, test.checkForWin());

        test.initializedGrid();  
        //Check is returns false if no win in neither diagonal
        assertEquals(false, test.checkForWin());
    }
    


    // test if the input is in valid size (valid grid)
    @Test
    public void testCheckIfValidSize() {
    	TicTacToe test = new TicTacToe();
    	test.x = 10;
    	assertEquals(false, test.checkIfValidInput()); 
   	}

   	//check whether the grid is empty or not 
   	@Test
    public void testCheckIfValidCharInPos() {
    	TicTacToe test = new TicTacToe();
    	// put in dummie data
    	test.grid[0] = 'X';
    	test.grid[1] = 'O';
    	test.grid[2] = '-';

    	test.grid[3] = '-';
    	test.grid[4] = 'X';
    	test.grid[5] = '-';

    	test.grid[6] = 'O';
    	test.grid[7] = 'X';
    	test.grid[8] = 'O';

    	test.x = 0;
    	assertEquals(false, test.checkIfValidInput());
    	test.x = 1;
    	assertEquals(false, test.checkIfValidInput());
    	test.x = 2;
    	assertEquals(true, test.checkIfValidInput());

    	test.x = 3;
    	assertEquals(true, test.checkIfValidInput());
    	test.x = 4;
    	assertEquals(false, test.checkIfValidInput());
    	test.x = 5;
    	assertEquals(true, test.checkIfValidInput());

    	test.x = 6;
    	assertEquals(false, test.checkIfValidInput());
    	test.x = 7;
    	assertEquals(false, test.checkIfValidInput());
    	test.x = 8;
    	assertEquals(false, test.checkIfValidInput());
   	}



   	@Test
    public void testChangePlayer() {
    	TicTacToe test = new TicTacToe();
    	test.player = 1;
    	test.changePlayer();
    	assertEquals(2, test.player);
    	test.changePlayer();
    	assertEquals(1, test.player); 
   	}

    @Test
    public void testInsert() {
        TicTacToe test = new TicTacToe();
        //player1 inserts a symbol
        test.player = 1;
        test.x = 3;
        if(test.checkIfValidInput()){
            test.insert();
        }
        assertEquals("X", Character.toString(test.grid[3]));
        // change players an let player2 insert a symbol
        test.changePlayer();
        test.x = 4;
        if(test.checkIfValidInput()){
            test.insert();
        }
        assertEquals("O", Character.toString(test.grid[4]));
        // know let player one insert in grid that is not empty
        // the function check if valid should prohibit that so it 
        // should maintain the char O in grid[4]
        test.changePlayer();
        test.x = 4;
        if(test.checkIfValidInput()){
            test.insert();
        }
        assertEquals("O", Character.toString(test.grid[4]));
    }
} 
