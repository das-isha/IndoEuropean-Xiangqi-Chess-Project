package newChess;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * @author Isha Das
 * This is the tester class for Project 5
 */
class Project5Tester {

	EuropeanChess europranChessGame = new EuropeanChess();
	SwingEuropeanChessDisplay chessDisplay = new SwingEuropeanChessDisplay();

	SwingChessBoard chessBoard = new SwingChessBoard(chessDisplay, europranChessGame);

	Xiangqi xiangqiGame = new Xiangqi();

	SwingXiangqiDisplay xiangqiDisplay = new SwingXiangqiDisplay();

	SwingChessBoard xiangqiBoard = new SwingChessBoard(xiangqiDisplay, xiangqiGame);

	ChessGame.Side player1 = ChessGame.Side.NORTH;

	ChessGame.Side player2 = ChessGame.Side.SOUTH;

	/**
	 * tests the constructor, addpiece, and hasPiece methods
	 */
	public void testConstructor() {

		SoldierPiece s = new SoldierPiece(xiangqiBoard, player1);
		xiangqiBoard.addPiece(s, 2, 2);
		assertTrue(xiangqiBoard.hasPiece(2, 2) == true);

	}

	// Test for Xiangqi Soldier Piece Legal Non Capture Move
	@Test
	void testIsLegalNonCaptureMoveForSoldier() {
		boolean expectedValue = true;
		boolean actualVaue = false;

		SoldierPiece s = new SoldierPiece(xiangqiBoard, player1);
		xiangqiBoard.addPiece(s, 3, 0);
		actualVaue = s.isLegalNonCaptureMove(4, 0);
		assertEquals(expectedValue, actualVaue);

	}

	// Test for Xiangqi Soldier Piece Legal Capture Move
	@Test
	void testIsLegalCaptureMoveForSoldier() {
		boolean expectedValue = true;
		boolean actualVaue = false;

		SoldierPiece s1 = new SoldierPiece(xiangqiBoard, player1);
		xiangqiBoard.addPiece(s1, 3, 0);
		SoldierPiece s2 = new SoldierPiece(xiangqiBoard, player2);
		xiangqiBoard.addPiece(s2, 4, 0);
		actualVaue = s1.isLegalCaptureMove(4, 0);
		assertEquals(expectedValue, actualVaue);

	}

	// Test for Xiangqi Cannon Piece Legal Non Capture Move
	@Test
	void testIsLegalNonCaptureMoveForCannonPiece() {
		boolean expectedValue = true;
		boolean actualVaue = false;

		SoldierPiece s = new SoldierPiece(xiangqiBoard, player1);
		xiangqiBoard.addPiece(s, 2, 1);
		actualVaue = s.isLegalNonCaptureMove(3, 1);
		assertEquals(expectedValue, actualVaue);

	}

	// Test for Xiangqi Cannon Piece Legal Capture Move
	@Test
	void testIsLegalCaptureMoveForCannonPiece() {
		boolean expectedValue = true;
		boolean actualVaue = false;

		SoldierPiece s1 = new SoldierPiece(xiangqiBoard, player1);
		xiangqiBoard.addPiece(s1, 5, 3);
		SoldierPiece s2 = new SoldierPiece(xiangqiBoard, player2);
		xiangqiBoard.addPiece(s2, 6, 3);
		actualVaue = s1.isLegalCaptureMove(6, 3);
		assertEquals(expectedValue, actualVaue);

	}

	// Test for Xiangqi Legal Non Capture Move for Rook Piece
	@Test
	void testIsLegalNonCaptureMoveForXiangqiRook() {

		RookPiece piece1 = new RookPiece(xiangqiBoard, player1);
		xiangqiBoard.addPiece(piece1, 4, 3);
		boolean expectedValue = true;
		boolean actualVaue = false;

		// Forward Horizontal move
		actualVaue = piece1.isLegalNonCaptureMove(4, 7);

		// Backward Horizontal move
		// actualVaue = piece1.isLegalNonCaptureMove(4, 0);

		// Forward Vertical move
		// actualVaue = piece1.isLegalNonCaptureMove(7, 3);

		// Backward Vertical move
		// actualVaue = piece1.isLegalNonCaptureMove(1, 3);

		// Failed condition

		// actualVaue = p.isLegalNonCaptureMove(2, 7);
		assertEquals(expectedValue, actualVaue);
	}

	// Test for Xiangqi Legal Capture Move for Bishop Piece
	@Test
	void testIsLegalCaptureMoveForXiangqiRook() {
		boolean expectedValue = true;
		boolean actualVaue = false;

		RookPiece piece1 = new RookPiece(xiangqiBoard, player1);
		xiangqiBoard.addPiece(piece1, 4, 4);

		RookPiece piece2 = new RookPiece(xiangqiBoard, player2);

		// Forward legal vertical capture move
		// xiangqiBoard.addPiece(piece2, 7, 4);
		// actualVaue = piece1.isLegalCaptureMove(7, 4);

		// Backward legal vertical capture move
		xiangqiBoard.addPiece(piece2, 1, 4);
		actualVaue = piece1.isLegalCaptureMove(1, 4);

		// Forward legal horizontal capture move
		// xiangqiBoard.addPiece(piece2, 4, 7);
		// actualVaue = piece1.isLegalCaptureMove(4, 7);

		// Backward legal horizontal capture move
		// xiangqiBoard.addPiece(piece2, 4, 1);
		// actualVaue = piece1.isLegalCaptureMove(4, 1);

		assertEquals(expectedValue, actualVaue);

	}

	// Test for Xiangqi Horse Piece Non Capture Move
	@Test
	void testIsLegalNonCaptureMoveForXiangqiHorse() {
		boolean expectedValue = true;
		boolean actualVaue = false;

		HorsePiece p = new HorsePiece(xiangqiBoard, player1);
		xiangqiBoard.addPiece(p, 0, 1);
		actualVaue = p.isLegalNonCaptureMove(2, 2);
		assertEquals(expectedValue, actualVaue);

	}

	// Test for Xiangqi Horse Piece Capture Move
	@Test
	void testIsLegalCaptureMoveForXiangqiHorse() {
		boolean expectedValue = true;
		boolean actualVaue = false;

		HorsePiece p1 = new HorsePiece(xiangqiBoard, player1);
		xiangqiBoard.addPiece(p1, 0, 1);
		SoldierPiece p2 = new SoldierPiece(xiangqiBoard, player2);
		xiangqiBoard.addPiece(p2, 2, 2);
		actualVaue = p1.isLegalCaptureMove(2, 2);
		assertEquals(expectedValue, actualVaue);

	}

	// Test for Xiangqi Elephant Piece Non Capture Move
	@Test
	void testIsLegalNonCaptureMoveForXiangqiElephant() {
		boolean expectedValue = true;
		boolean actualVaue = false;

		ElephantPiece p = new ElephantPiece(xiangqiBoard, player1);
		xiangqiBoard.addPiece(p, 3, 3);
		actualVaue = p.isLegalNonCaptureMove(1, 1);
		assertEquals(expectedValue, actualVaue);

		// To test the rook cannot jump over pieces so all squares on the move (except
		// possibly the last one) must be empty
		SoldierPiece p2 = new SoldierPiece(xiangqiBoard, player2);
		xiangqiBoard.addPiece(p2, 2, 2);
		assertFalse(p.isLegalNonCaptureMove(1, 1) == true);

	}

	// Test for Xiangqi Elephant Piece Capture Move
	@Test
	void testIsLegalCaptureMoveForXiangqiElephant() {
		// boolean expectedValue = true;
		// boolean actualVaue = false;

		ElephantPiece p1 = new ElephantPiece(xiangqiBoard, player1);
		xiangqiBoard.addPiece(p1, 3, 3);
		SoldierPiece p2 = new SoldierPiece(xiangqiBoard, player2);
		xiangqiBoard.addPiece(p2, 1, 1);
		// actualVaue = p1.isLegalNonCaptureMove(1, 1);
		assertFalse(p1.isLegalNonCaptureMove(1, 1) == true);
		// assertEquals(expectedValue, actualVaue);

	}

	// Test for Xiangqi Guard Piece Non Capture Move
	@Test
	void testIsLegalNonCaptureMoveForXiangqiGuard() {
		boolean expectedValue = true;
		boolean actualVaue = false;

		GuardPiece p = new GuardPiece(xiangqiBoard, player1);
		xiangqiBoard.addPiece(p, 0, 5);
		actualVaue = p.isLegalNonCaptureMove(1, 4);
		assertEquals(expectedValue, actualVaue);

	}

	// Test for Xiangqi Elephant Piece Capture Move
	@Test
	void testIsLegalCaptureMoveForXiangqiGuard() {
		// boolean expectedValue = true;
		// boolean actualVaue = false;

		GuardPiece p1 = new GuardPiece(xiangqiBoard, player1);
		xiangqiBoard.addPiece(p1, 0, 5);
		SoldierPiece p2 = new SoldierPiece(xiangqiBoard, player2);
		xiangqiBoard.addPiece(p2, 1, 4);
		// actualVaue = p1.isLegalNonCaptureMove(1, 1);
		assertTrue(p1.isLegalCaptureMove(1, 4) == true);
		// assertEquals(expectedValue, actualVaue);

	}

	// Test for Xiangqi King Piece Non Capture Move
	@Test
	void testIsLegalNonCaptureMoveForXiangqiKing() {

		KingPiece p = new KingPiece(xiangqiBoard, player1);
		xiangqiBoard.addPiece(p, 1, 4);
		// Test for 1 Square Vertical Up Move
		assertTrue(p.isLegalNonCaptureMove(2, 4) == true);

		// Test for 1 Square Vertical Down Move
		xiangqiBoard.addPiece(p, 1, 5);
		assertTrue(p.isLegalNonCaptureMove(0, 5) == true);

		// Test for 1 Square Horizontal Right Move
		xiangqiBoard.addPiece(p, 1, 4);
		assertTrue(p.isLegalNonCaptureMove(1, 5) == true);

		// Test for 1 Square Horizontal Left Move
		xiangqiBoard.addPiece(p, 1, 4);
		assertTrue(p.isLegalNonCaptureMove(1, 3) == true);

		// Test not tomove out of the three center columns of the board nor out of the
		// bottom (or top) three rows of the board

		// Test for Horizontal not move
		xiangqiBoard.addPiece(p, 1, 5);
		assertFalse(p.isLegalNonCaptureMove(1, 7) == true);

		// Test for Horizontal not move
		xiangqiBoard.addPiece(p, 2, 5);
		assertFalse(p.isLegalNonCaptureMove(4, 5) == true);
	}
	
	 
	  // Test for Xiangqi King Piece Capture Move
	  
	  @Test void testIsLegalCaptureMoveForXiangqiKing() { 
		  KingPiece p1 = new KingPiece(xiangqiBoard, player1);
			xiangqiBoard.addPiece(p1, 1,4);
			SoldierPiece p2 = new SoldierPiece(xiangqiBoard, player2);
			xiangqiBoard.addPiece(p2, 2, 4);
			
			assertTrue(p1.isLegalCaptureMove(2, 4) == true);
		 
	  }
	 

	// Test for Chess Legal Non Capture Move for Rook Piece
	@Test
	void testIsLegalNonCaptureMoveForRook() {

		RookPiece piece1 = new RookPiece(chessBoard, player1);
		chessBoard.addPiece(piece1, 4, 3);
		boolean expectedValue = true;
		boolean actualVaue = false;

		// Forward Horizontal move
		actualVaue = piece1.isLegalNonCaptureMove(4, 7);

		// Backward Horizontal move
		// actualVaue = piece1.isLegalNonCaptureMove(4, 0);

		// Forward Vertical move
		// actualVaue = piece1.isLegalNonCaptureMove(7, 3);

		// Backward Vertical move
		// actualVaue = piece1.isLegalNonCaptureMove(1, 3);

		// Failed condition

		// actualVaue = p.isLegalNonCaptureMove(2, 7);
		assertEquals(expectedValue, actualVaue);
	}

	// Test for Chess Legal Capture Move for Bishop Piece
	@Test
	void testIsLegalCaptureMoveForRook() {
		boolean expectedValue = true;
		boolean actualVaue = false;

		RookPiece piece1 = new RookPiece(chessBoard, player1);
		chessBoard.addPiece(piece1, 4, 4);

		RookPiece piece2 = new RookPiece(chessBoard, player2);

		// Forward legal vertical capture move
		// chessBoard.addPiece(piece2, 7, 4);
		// actualVaue = piece1.isLegalCaptureMove(7, 4);

		// Backward legal vertical capture move
		chessBoard.addPiece(piece2, 1, 4);
		actualVaue = piece1.isLegalCaptureMove(1, 4);

		// Forward legal horizontal capture move
		// chessBoard.addPiece(piece2, 4, 7);
		// actualVaue = piece1.isLegalCaptureMove(4, 7);

		// Backward legal horizontal capture move
		// chessBoard.addPiece(piece2, 4, 1);
		// actualVaue = piece1.isLegalCaptureMove(4, 1);

		assertEquals(expectedValue, actualVaue);

	}

	// Test for Chess Legal Vertical NonCapture Move
	@Test
	void testIsLegalVerticalNonCaptureMove() {

		QueenPiece q = new QueenPiece(chessBoard, player1);
		chessBoard.addPiece(q, 4, 3);
		boolean expectedValue = true;
		boolean actualVaue = false;
		// Forward move
		// actualVaue = q.isLegalVerticalNonCaptureMove(7, 3, true, 0);

		// Backward move
		actualVaue = q.isLegalVerticalNonCaptureMove(1, 3, true, 0);

		// Failed condition

		// actualVaue = p.isLegalHorizontalNonCaptureMove(2, 7, true, 0);
		assertEquals(expectedValue, actualVaue);

	}

	// Test for Chess Legal Horizantal NonCapture Move
	@Test
	void testIsLegalHorizontalNonCaptureMove() {

		QueenPiece q = new QueenPiece(chessBoard, player1);
		chessBoard.addPiece(q, 4, 3);
		boolean expectedValue = true;
		boolean actualVaue = false;

		// Forward move for allowed step - 1
		// actualVaue = q.isLegalHorizontalNonCaptureMove(4, 4, true, 1);

		// Backwaed move for max allowed step - 1 (Failed condition - It's not
		// implemented)
		// actualVaue = q.isLegalHorizontalNonCaptureMove(4, 2, true, 1);

		// Forward move for allowed step - 2
		// actualVaue = q.isLegalHorizontalNonCaptureMove(4, 5, true, 2);

		// Backwaed move for max allowed step - 2 (Failed condition - It's not
		// implemented)
		// actualVaue = q.isLegalHorizontalNonCaptureMove(4, 1, true, 2);

		// Forward move for max allowed steps
		actualVaue = q.isLegalHorizontalNonCaptureMove(4, 7, true, 0);

		// Backwaed move for max allowed step(s)
		// actualVaue = q.isLegalHorizontalNonCaptureMove(4, 1, true, 0);

		// Failed condition
		// actualVaue = q.isLegalHorizontalNonCaptureMove(2, 7, true, 0);

		assertEquals(expectedValue, actualVaue);
	}

	// Test for Chess Legal Diagnal NonCapture Move

	@Test
	void testIsLegalDiagnalNonCaptureMove() {

		QueenPiece q = new QueenPiece(chessBoard, player1);
		chessBoard.addPiece(q, 3, 3);
		boolean expectedValue = true;
		boolean actualVaue = false;

		// Forward Diagnal move for max allowed steps
		actualVaue = q.isLegalDiagonalNonCaptureMove(6, 6, true, 0);

		// Backwaed Diagnal move for max allowed step(s)
		// actualVaue = q.isLegalDiagonalNonCaptureMove(0,0, true, 0);

		// Failed condition
		// actualVaue = q.isLegalDiagonalNonCaptureMove(2, 7, true, 0);

		assertEquals(expectedValue, actualVaue);
	}

	// Test for Chess Legal NonCaptureMove For Queen

	@Test
	void testIsLegalNonCaptureMoveForQueen() {

		QueenPiece q = new QueenPiece(chessBoard, player1);
		chessBoard.addPiece(q, 4, 3);
		boolean expectedValue = true;
		boolean actualVaue = false;

		// Forward Horizontal move
		// actualVaue = q.isLegalNonCaptureMove(4, 7);

		// Backward Horizontal move
		// actualVaue = q.isLegalNonCaptureMove(7, 3);

		// Forward Vertical move
		// actualVaue = q.isLegalNonCaptureMove(7, 3);

		// Backward Vertical move
		// actualVaue = q.isLegalNonCaptureMove(1, 3);

		// Forward Diagnal move
		actualVaue = q.isLegalNonCaptureMove(7, 6);

		// Backward Diagnal move
		// actualVaue = q.isLegalNonCaptureMove(1, 0);

		// Failed condition

		// actualVaue = p.isLegalNonCaptureMove(2, 7);
		assertEquals(expectedValue, actualVaue);
	}

	// Test for Chess Legal NonCapture Move for Bishop Piece
	@Test
	void testIsLegalNonCaptureMoveForBishop() {

		BishopPiece piece1 = new BishopPiece(chessBoard, player1);
		chessBoard.addPiece(piece1, 4, 3);
		boolean expectedValue = true;
		boolean actualVaue = false;

		// Forward Diagnal move
		// actualVaue = piece1.isLegalNonCaptureMove(7, 6);

		// Backward Diagnal move
		actualVaue = piece1.isLegalNonCaptureMove(1, 0);

		// Failed condition

		// actualVaue = p.isLegalNonCaptureMove(2, 7);
		assertEquals(expectedValue, actualVaue);
	}

	// Test for Chess Legal NonCapture Move for Bishop Piece
	@Test
	void testIsLegalCaptureMoveForBishop() {
		boolean expectedValue = true;
		boolean actualVaue = false;

		BishopPiece piece1 = new BishopPiece(chessBoard, player1);
		chessBoard.addPiece(piece1, 4, 4);

		BishopPiece piece2 = new BishopPiece(chessBoard, player2);

		// Forward legal diagnal capture move
		// chessBoard.addPiece(piece2, 6, 6);
		// actualVaue = piece1.isLegalCaptureMove(6, 6);

		// Backward legal diagnal capture move
		chessBoard.addPiece(piece2, 1, 1);
		actualVaue = piece1.isLegalCaptureMove(1, 1);

		assertEquals(expectedValue, actualVaue);

	}

	// Test for Chess Legal NonCapture Move for King Piece
	@Test
	void testIsLegalNonCaptureMoveForKing() {

		KingPiece piece1 = new KingPiece(chessBoard, player1);
		chessBoard.addPiece(piece1, 4, 3);
		boolean expectedValue = true;
		boolean actualVaue = false;

		// Forward Horizontal move
		// actualVaue = piece1.isLegalNonCaptureMove(4, 4);

		// Backward Horizontal move
		// actualVaue = piece1.isLegalNonCaptureMove(3, 3);

		// Forward Vertical move
		// actualVaue = piece1.isLegalNonCaptureMove(5, 3);

		// Backward Vertical move
		// actualVaue = piece1.isLegalNonCaptureMove(4, 2);

		// Forward Diagnal move
		// actualVaue = piece1.isLegalNonCaptureMove(5, 4);

		// Backward Diagnal move
		actualVaue = piece1.isLegalNonCaptureMove(3, 2);

		// Failed condition

		// actualVaue = p.isLegalNonCaptureMove(2, 7);
		assertEquals(expectedValue, actualVaue);
	}

	// Test for Chess Legal Capture Move for King Piece
	@Test
	void testIsLegalCaptureMoveForKing() {
		boolean expectedValue = true;
		boolean actualVaue = false;

		KingPiece piece1 = new KingPiece(chessBoard, player1);
		chessBoard.addPiece(piece1, 4, 4);

		KingPiece piece2 = new KingPiece(chessBoard, player2);

		// Forward vertical legal capture move
		// chessBoard.addPiece(piece2, 5,4);
		// actualVaue = piece1.isLegalCaptureMove(5, 4);

		// Backward vertical legal capture move
		// chessBoard.addPiece(piece2, 3, 4);
		// actualVaue = piece1.isLegalCaptureMove(3, 4);

		// Forward horizontal legal capture move
		// chessBoard.addPiece(piece2, 5, 4);
		// actualVaue = piece1.isLegalCaptureMove(5, 4);

		// Backward horizontal legal capture move
		// chessBoard.addPiece(piece2, 3, 4);
		// actualVaue = piece1.isLegalCaptureMove(3, 4);

		// Forward diagnal legal capture move
		// chessBoard.addPiece(piece2, 5, 5);
		// actualVaue = piece1.isLegalCaptureMove(5, 5);

		// Backward diagnal legal capture move
		chessBoard.addPiece(piece2, 3, 3);
		actualVaue = piece1.isLegalCaptureMove(3, 3);

		assertEquals(expectedValue, actualVaue);

	}

	// Test for Chess Legal NonCapture Move for Knight Piece
	@Test
	void testIsLegalNonCaptureMoveForKnight() {

		KnightPiece piece1 = new KnightPiece(chessBoard, player1);
		chessBoard.addPiece(piece1, 4, 3);
		boolean expectedValue = true;
		boolean actualVaue = false;

		// Forward Horizontal and Vertical up move
		// actualVaue = piece1.isLegalNonCaptureMove(5, 5);
		// actualVaue = piece1.isLegalNonCaptureMove(6, 4);

		// Forward Horizontal and Vertical down move
		// actualVaue = piece1.isLegalNonCaptureMove(5, 1);
		// actualVaue = piece1.isLegalNonCaptureMove(6, 2);

		// Backward Horizontal and Vertical up move
		// actualVaue = piece1.isLegalNonCaptureMove(3, 5);
		// actualVaue = piece1.isLegalNonCaptureMove(2, 4);

		/// Backward Horizontal and Vertical down move
		// actualVaue = piece1.isLegalNonCaptureMove(3, 1);
		actualVaue = piece1.isLegalNonCaptureMove(2, 2);

		// Failed condition

		// actualVaue = p.isLegalNonCaptureMove(2, 7);
		assertEquals(expectedValue, actualVaue);
	}

	// Test for Chess Legal Capture Move for Knight Piece
	@Test
	void testIsLegalCaptureMoveForKnight() {
		boolean expectedValue = true;
		boolean actualVaue = true;

		KnightPiece piece1 = new KnightPiece(chessBoard, player1);
		chessBoard.addPiece(piece1, 4, 3);

		PawnPiece pawnpiece = new PawnPiece(chessBoard, player2);

		chessBoard.addPiece(pawnpiece, 5, 4);

		KnightPiece piece2 = new KnightPiece(chessBoard, player2);

		// Forward Horizontal and Vertical up capture move
		// chessBoard.addPiece(piece2, 5,5);
		// actualVaue = piece1.isLegalCaptureMove(5, 5);

		// chessBoard.addPiece(piece2, 6,4);
		// actualVaue = piece1.isLegalCaptureMove(6, 4);

		// Forward Horizontal and Vertical down capture move
		// chessBoard.addPiece(piece2, 5,1);
		// actualVaue = piece1.isLegalCaptureMove(5, 1);

		// chessBoard.addPiece(piece2, 6,2);
		// actualVaue = piece1.isLegalCaptureMove(6, 2);

		// Backward Horizontal and Vertical up move
		// chessBoard.addPiece(piece2, 3,5);
		// actualVaue = piece1.isLegalCaptureMove(3, 5);

		// chessBoard.addPiece(piece2, 2,4);
		// actualVaue = piece1.isLegalCaptureMove(2, 4);

		/// Backward Horizontal and Vertical down move

		chessBoard.addPiece(piece2, 3, 1);
		actualVaue = piece1.isLegalCaptureMove(3, 1);

		// chessBoard.addPiece(piece2, 2,2);
		// actualVaue = piece1.isLegalCaptureMove(2, 2);

		assertEquals(expectedValue, actualVaue);

	}

	// Test for Chess Legal NonCapture Move for Pawn Piece
	@Test
	void testIsLegalNonCaptureMoveForPawn() {

		PawnPiece p = new PawnPiece(chessBoard, player1);
		chessBoard.addPiece(p, 1, 1);
		boolean expectedValue = true;
		boolean actualVaue = false;

		// First move
		actualVaue = p.isLegalNonCaptureMove(3, 1);
		assertEquals(expectedValue, actualVaue);

	}

	// Test for Chess Legal Capture Move for Pawn Piece
	@Test
	void testIsLegalCaptureMoveForPawn() {
		PawnPiece p1 = new PawnPiece(chessBoard, player1);
		chessBoard.addPiece(p1, 3, 1);

		PawnPiece p2 = new PawnPiece(chessBoard, player2);
		chessBoard.addPiece(p2, 4, 2);
		boolean expectedValue = true;
		boolean actualVaue = false;
		actualVaue = p1.isLegalCaptureMove(4, 2);

		assertEquals(expectedValue, actualVaue);

	}

}
