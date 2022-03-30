package com.lcomputerstudy.testmvc.service;
import java.util.ArrayList;
import com.lcomputerstudy.testmvc.dao.BoardDAO;
import com.lcomputerstudy.testmvc.vo.Pagination;
import com.lcomputerstudy.testmvc.vo.Search;
import com.lcomputerstudy.testmvc.vo.Board;

public class BoardService {
	private static BoardService service = null;
	private static BoardDAO dao = null;
	
	private BoardService() {
		
	}
	
	public static BoardService getInstance() {
		if(service==null) {
			service = new BoardService();
			dao = BoardDAO.getInstance();
		}
		return service;
	}
	
	public void insertBoard(Board board) {
		dao.insertBoard(board);
	}
	
	public ArrayList<Board> getBoard(Pagination pagination){
		return dao.getBoard(pagination);
	}
	
	public ArrayList<Board> getInfo(Board board){
		return dao.getInfo(board);
	}
	
	public void modifyUser(Board board) {
		dao.modifyUser(board);
	}
	
	public void deleteUser(Board board) {
		dao.deleteUser(board);
	}
	
	public static int getBoardCount() {
		return dao.getBoardCount();
	}
	
	public static int getSelectBoardCount(Search search) {
		return dao.getSelectBoardCount(search);
	}
	
	public void insertReply(Board board) {
		dao.insertReply(board);
	}
	
	public void insertComment(Board board) {
		dao.insertComment(board);
	}
	
	public ArrayList<Board> getComment(Board board) {
		return dao.getComment(board);
	}
	
	public void insertComment_reply_ajax(Board board) {
		dao.insertComment_reply_ajax(board);
	}
	
	public ArrayList<Board> searchBoard(Pagination pagination) {
		return dao.searchBoard(pagination);
	}
	
	public ArrayList<Board> getFile(Board board){
		return dao.getFile(board);
	}
	
	
}
