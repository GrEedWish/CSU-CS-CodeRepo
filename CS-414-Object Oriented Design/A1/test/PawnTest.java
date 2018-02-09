package A1.test;

import java.util.Arrays;
import java.util.ArrayList;
import java.lang.StringBuilder;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

import A1.src.ChessBoard;
import A1.src.ChessPiece;
import A1.src.Pawn;
import A1.src.Color;
import A1.src.IllegalPositionException;

public class PawnTest{
	private ChessBoard chessBoard;
	private Pawn piece;

	@Before
	public void init(){
		chessBoard = new ChessBoard();
		chessBoard.initialize();
	}

	@Test
	public void ChessPieceTest(){
		// Test Constructor
		// a1 is initial position for all
		piece = new Pawn(chessBoard, Color.WHITE);
		assertTrue(piece.getColor() == Color.WHITE);
		assertEquals(piece.getPosition(), "a1");
		piece = new Pawn(chessBoard, Color.BLACK);
		assertTrue(piece.getColor() == Color.BLACK);
		assertEquals(piece.getPosition(), "a1");
	}

	@Test
	public void getColorTest(){
		// Test getColor method
		// Simple test
		piece = new Pawn(chessBoard, Color.WHITE);
		assertTrue(piece.getColor() == Color.WHITE);
		piece = new Pawn(chessBoard, Color.BLACK);
		assertTrue(piece.getColor() == Color.BLACK);
	}

	@Test
	public void getPositionTest(){
		// Test getPosition method
		// Initially always return "a1"
		// Test more in setPositionTest
		piece = new Pawn(chessBoard, Color.WHITE);
		assertEquals(piece.getPosition(), "a1");
		piece = new Pawn(chessBoard, Color.BLACK);
		assertEquals(piece.getPosition(), "a1");
	}
	
	@Test
	public void setPositionTest(){
		piece = new Pawn(chessBoard, Color.WHITE);
		try{
			// Test legal position
			piece.setPosition("a8");
			assertEquals(piece.getPosition(), "a8");
			piece.setPosition("b7");
			assertEquals(piece.getPosition(), "b7");
			piece.setPosition("g3");
			assertEquals(piece.getPosition(), "g3");
		} catch (IllegalPositionException ie){
			fail("IllegalPositionException");
		}

		try{
			// Test illegal position : Occupied by same type
			piece.setPosition("a2");
			fail("IllegalPositionException");
		} catch (IllegalPositionException ie){
			assertTrue(true);
		}
		try{
			// Test illegal position : Occupied by same type
			piece.setPosition("g1");
			fail("IllegalPositionException");
		} catch (IllegalPositionException ie){
			assertTrue(true);
		}

		try{
			// Test illegal position : Illegal Position
			piece.setPosition("a0");
			fail("IllegalPositionException");
		} catch (IllegalPositionException ie){
			assertTrue(true);
		}
		try{
			// Test illegal position : Illegal Position
			piece.setPosition("k10");
			fail("IllegalPositionException");
		} catch (IllegalPositionException ie){
			assertTrue(true);
		}
		try{
			// Test illegal position : Illegal Position
			piece.setPosition("23");
			fail("IllegalPositionException");
		} catch (IllegalPositionException ie){
			assertTrue(true);
		}
	}

	@Test
	public void toStringTest(){
		// Test WHITE color piece
		piece = new Pawn(chessBoard, Color.WHITE);
		assertTrue(piece.toString() == "\u2659");
		assertFalse(piece.toString() == "\u265F");

		// Test BLACK color piece
		piece = new Pawn(chessBoard, Color.BLACK);
		assertFalse(piece.toString() == "\u2659");
		assertTrue(piece.toString() == "\u265F");
	}

	// https://stackoverflow.com/questions/2989987/how-can-i-check-if-two-arraylist-differ-i-dont-care-whats-changed
	private boolean isTwoArrayListsWithSameValues(ArrayList<String> list1, ArrayList<String> list2){
        //null checking
        if(list1==null && list2==null)
            return true;
        if((list1 == null && list2 != null) || (list1 != null && list2 == null))
            return false;

        if(list1.size()!=list2.size())
            return false;
        for(String itemList1: list1){
            if(!list2.contains(itemList1))
                return false;
        }
        return true;
    }

	@Test
	public void legalMovesTest(){
		// Restore Initial State
		// Test WHITE Color
		chessBoard.initialize();
		piece = new Pawn(chessBoard, Color.WHITE);
		assertTrue(piece.legalMoves().isEmpty());
		// assertTrue(arrayList2String(piece.legalMoves()) == "");

		try{
			ChessPiece chesspiece = chessBoard.getPiece("h2");
			ArrayList<String> h2 = new ArrayList<>(Arrays.asList("h3", "h4"));
			assertTrue(isTwoArrayListsWithSameValues(h2, chesspiece.legalMoves()));
		} catch (IllegalPositionException ie){
			fail("IllegalPositionException");
		}

		try{
			piece.setPosition("a3");
			ArrayList<String> a3 = new ArrayList<>(Arrays.asList("a4"));
			assertTrue(isTwoArrayListsWithSameValues(a3, piece.legalMoves()));
		} catch (IllegalPositionException ie){
			fail("IllegalPositionException");
		}

		try{
			piece.setPosition("g8");
			assertTrue(piece.legalMoves().isEmpty());
		} catch (IllegalPositionException ie){
			fail("IllegalPositionException");
		}

		try{
			piece.setPosition("d6");
			ArrayList<String> d6 = new ArrayList<>(Arrays.asList("c7", "e7"));
			assertTrue(isTwoArrayListsWithSameValues(d6, piece.legalMoves()));
		} catch (IllegalPositionException ie){
			fail("IllegalPositionException");
		}

		// Test BLACK Color
		chessBoard.initialize();
		piece = new Pawn(chessBoard, Color.BLACK);
		assertTrue(piece.legalMoves().isEmpty());

		try{
			ChessPiece chesspiece = chessBoard.getPiece("d7");
			ArrayList<String> d7 = new ArrayList<>(Arrays.asList("d6", "d5"));
			assertTrue(isTwoArrayListsWithSameValues(d7, chesspiece.legalMoves()));
		} catch (IllegalPositionException ie){
			fail("IllegalPositionException");
		}
		
		try{
			piece.setPosition("a2");
			ArrayList<String> a2 = new ArrayList<>(Arrays.asList("b1"));
			assertTrue(isTwoArrayListsWithSameValues(a2, piece.legalMoves()));
		} catch (IllegalPositionException ie){
			fail("IllegalPositionException");
		}
		try{
			piece.setPosition("h1");
			assertTrue(piece.legalMoves().isEmpty());
		} catch (IllegalPositionException ie){
			fail("IllegalPositionException");
		}
		try{
			piece.setPosition("e6");
			ArrayList<String> e6 = new ArrayList<>(Arrays.asList("e5"));
			assertTrue(isTwoArrayListsWithSameValues(e6, piece.legalMoves()));
		} catch (IllegalPositionException ie){
			fail("IllegalPositionException");
		}
	}
}