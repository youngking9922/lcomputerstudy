package com.SpringTest2.example.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.SpringTest2.example.domain.Board;
import com.SpringTest2.example.mapper.BoardMapper;

@Service("BoardServiceImpl")
public class BoardServiceImpl implements BoardService{
	
	@Autowired BoardMapper boardmapper;
	@Override
	public List<Board> selectBoardList() {
		return boardmapper.selectBoardList();
	}

}
