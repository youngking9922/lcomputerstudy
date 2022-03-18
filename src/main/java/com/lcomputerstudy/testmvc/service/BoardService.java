package com.lcomputerstudy.testmvc.service;

import java.util.ArrayList;
import com.lcomputerstudy.testmvc.dao.BoardDAO;
import com.lcomputerstudy.testmvc.dao.UserDAO;
import com.lcomputerstudy.testmvc.vo.Pagination;
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
	
	public void insertUser(Board board) {
		dao.insertUser(board);
	}
	
	public ArrayList<Board> getBoard(){
		return dao.getBoard();
	}
	
	public ArrayList<Board> getInfo(Board board){
		return dao.getInfo(board);
	}
}
