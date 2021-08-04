package newChess;
import javax.swing.JOptionPane;

/**
 * @author Isha Das
 * This class is an interface represneting the chess client
 * Run to launch the game 
 */
public class ChessClient {
	
	//Helper method to return a game string that will read the input and catch any exceptions using JOptionPane
	private static String readGameInput() {
		
		String inputString = "";
		do {
			try {
				inputString = JOptionPane.showInputDialog("Which game you want to play? Enter chess or xiangqi");
			}
			catch(Exception e) {
				inputString = JOptionPane.showInputDialog("Which game you want to play? Enter chess or xiangqi");
			}
		}while(!(inputString.equals("chess") || inputString.equals("xiangqi")));
    
		return inputString;
	}

	//Helper method to return a display string that will read the input and catch any exceptions using JOptionPane
	private static String readDisplayInput() {
		
		String inputString = "";
		do {
			try {
				inputString = JOptionPane.showInputDialog("Which display you want to use? Enter swing or fx");
			}
			catch(Exception e) {
				inputString = JOptionPane.showInputDialog("Which display you want to use? Enter swing or fx");
			}
		}while(!(inputString.equals("swing") || inputString.equals("fx")));
    
		return inputString;
	}
	
	public static void main(String[] args) {
		
		//Read input for game
		String gameText = readGameInput();

		String[] appArgs = new String[] {gameText};
		
		//Read inputs for display
		String displayText = readDisplayInput();
		
		if(displayText.equals("fx")) {
			JavaFXChessBoard chessBoard = new JavaFXChessBoard();
			chessBoard.launchApp(appArgs);
		} else if(gameText.equals("chess") && displayText.equals("swing")) {
			EuropeanChess chessGame = new EuropeanChess();
			SwingEuropeanChessDisplay chessDisplay = new SwingEuropeanChessDisplay();
			SwingChessBoard chessBoard = new SwingChessBoard(chessDisplay, chessGame);
			chessGame.startGame(chessBoard);
		} else if(gameText.equals("xiangqi") && displayText.equals("swing")) {
			Xiangqi chessGame = new Xiangqi();
			SwingXiangqiDisplay chessDisplay = new SwingXiangqiDisplay();
			SwingChessBoard chessBoard = new SwingChessBoard(chessDisplay, chessGame);
			chessGame.startGame(chessBoard);
		}
	}
}
