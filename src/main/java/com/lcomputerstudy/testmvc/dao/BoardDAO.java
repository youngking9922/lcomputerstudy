package com.lcomputerstudy.testmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.lcomputerstudy.testmvc.database.DBConnection;
import com.lcomputerstudy.testmvc.vo.Pagination;
import com.lcomputerstudy.testmvc.vo.Board;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class BoardDAO {
	
	private static BoardDAO dao = null;
	
	private BoardDAO() {
		
	}
	
	public static BoardDAO getInstance() {
		if(dao==null) {
			dao = new BoardDAO();
		}
		return dao;
	}
	
	public ArrayList<Board> getBoard(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Board> list =null;
		
		try {
			conn = DBConnection.getConnection();
			String query = "select * from board";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			list = new ArrayList<Board>();
			
			while(rs.next()) {
				Board board = new Board();
				board.setTitle(rs.getString("b_title"));
				board.setContent(rs.getString("b_content"));
				board.setWriter(rs.getString("b_writer"));
				board.setDate(rs.getString("b_date"));
				board.setB_idx(rs.getInt("b_idx"));
				board.setView_count(Integer.parseInt(rs.getString("view_count")));
				list.add(board);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public void insertUser(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat date = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
		
		try {
			
			conn=DBConnection.getConnection();
			String sql = "insert into board(b_title,b_content,b_date,b_writer,u_idx) values(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,board.getTitle());
			pstmt.setString(2,board.getContent());
			pstmt.setString(3,date.format(timestamp));
			pstmt.setString(4,board.getWriter());
			pstmt.setInt(5,board.getU_idx());
			pstmt.executeQuery();
		} catch(Exception ex) {
			System.out.println("SQLException : " +ex.getMessage());
		} finally {
			
		}
	}
	
	public ArrayList<Board> getInfo(Board board) {
		Connection conn =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Board> list = null;
		int b_idx = board.getB_idx();
		
		try {
			conn = DBConnection.getConnection();
			String query = "select * from board where b_idx = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, b_idx);
			rs = pstmt.executeQuery();
			list = new ArrayList<Board>();
			
			while(rs.next()) {
				board.setTitle(rs.getString("b_title"));
				board.setContent(rs.getString("b_content"));
				board.setWriter(rs.getString("b_writer"));
				board.setDate(rs.getString("b_date"));
				board.setB_idx(rs.getInt("b_idx"));
				board.setView_count(Integer.parseInt(rs.getString("view_count")));
				list.add(board);
			}
			pstmt.close();
			rs.close();
			String query2 = "update board set view_count = view_count+1 where b_idx=?";
			pstmt = conn.prepareStatement(query2);
			pstmt.setInt(1,b_idx);
			rs = pstmt.executeQuery();

			
		} catch(Exception ex) {
			System.out.println("SQLException : " + ex.getMessage());
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
