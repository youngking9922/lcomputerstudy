package com.lcomputerstudy.testmvc.dao;
//주석
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.lcomputerstudy.testmvc.database.DBConnection;
import com.lcomputerstudy.testmvc.vo.Pagination;
import com.lcomputerstudy.testmvc.vo.User;
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
	
	public ArrayList<Board> getBoard(Pagination pagination){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Board> list = null;
		int pageNum = pagination.getPageNum();
		
		try {
			conn = DBConnection.getConnection();
			String query = new StringBuilder()
					.append("SELECT 		@ROWNUM := @ROWNUM - 1 AS ROWNUM,\n")
					.append("				ta.*\n")
					.append("FROM 			board ta,\n")
					.append("				(SELECT @rownum := (SELECT	COUNT(*)-?+1 FROM board ta)) tb\n")
					.append("order by `order` ")
					.append("LIMIT			?, ?\n")
					
					.toString();
	       	pstmt = conn.prepareStatement(query);
	       	pstmt.setInt(1, pageNum);
	       	pstmt.setInt(2, pageNum);
	       	pstmt.setInt(3, Pagination.perPage);
	        rs = pstmt.executeQuery();
	        list = new ArrayList<Board>();

	        while(rs.next()){     
	        	Board board = new Board();
	        	String count = " ";
	        	for (int i=0; i<Integer.parseInt(rs.getString("depth")); i++) {
	        		count +="ㄴ";
	        	}
	        	
				board.setTitle(count+rs.getString("b_title"));
				board.setContent(rs.getString("b_content"));
				board.setWriter(rs.getString("b_writer"));
				board.setDate(rs.getString("b_date"));
				board.setB_idx(rs.getInt("b_idx"));
				board.setView_count(Integer.parseInt(rs.getString("view_count")));
				list.add(board);
	        }
			
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public int getBoardCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count=0;
		
		try {
			conn = DBConnection.getConnection();
			String query = "SELECT COUNT(*) count FROM board";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				count=rs.getInt("count");
			}
			
		} catch (Exception e) {
			
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return count;
	}
	
	public void insertBoard(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat date = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
		
		try {
			
			conn=DBConnection.getConnection();
			String sql = "insert into board (b_title,b_content,b_date,b_writer,u_idx,view_count,`group`,`order`,depth) value(?,?,?,?,?,0,0,1,0)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,board.getTitle());
			pstmt.setString(2,board.getContent());
			pstmt.setString(3,date.format(timestamp));
			pstmt.setString(4,board.getWriter());
			pstmt.setInt(5,board.getU_idx());
			pstmt.executeUpdate();
			pstmt.close();
			
			sql = "update board set `group` = last_insert_id() where b_idx = last_insert_id()";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
				board.setU_idx(rs.getInt("u_idx"));
				board.setView_count(Integer.parseInt(rs.getString("view_count")));
				board.setGroup(rs.getInt("group"));
				board.setDepth(rs.getInt("depth"));
				board.setOrder(rs.getInt("order"));
				list.add(board);
			}
			pstmt.close();
			rs.close();
			
			String query2 = "update board set view_count = view_count+1 where b_idx=?";
			pstmt = conn.prepareStatement(query2);
			pstmt.setInt(1,b_idx);
			rs = pstmt.executeQuery();
	
		} catch(Exception ex) {
			ex.printStackTrace();
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
	

	
	public void modifyUser(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnection.getConnection();
			String sql = "update board set b_title = ? , b_content = ? where b_idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getB_idx());
			pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("SQLException : " +ex.getMessage());
		} finally {
			try {
				if (pstmt!=null) pstmt.close();
				if (conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deleteUser(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnection.getConnection();
			String sql = "delete from board where b_idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getB_idx());
			pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("SQLException : "+ex.getMessage());
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void insertReply(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnection.getConnection();
			String sql = "insert into board (b_title,b_content,b_date,b_writer,u_idx,view_count,`group`,`order`,depth) value(?,?,now(),?,?,0,?,?,?)";
			pstmt =conn.prepareStatement(sql);
			pstmt.setString(1,board.getTitle());
			pstmt.setString(2,board.getContent());
			pstmt.setString(3,board.getWriter());
			pstmt.setInt(4,board.getU_idx());
			pstmt.setInt(5, board.getGroup());
			pstmt.setInt(6, board.getOrder());
			pstmt.setInt(7, board.getDepth());
			pstmt.executeUpdate();
			pstmt.close();
						
			sql = "update board set `order` = `order`+1 where `group` = ? and `order` >= ? and b_idx <> last_insert_id() ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getGroup());
			pstmt.setInt(2, board.getOrder());
			pstmt.executeUpdate();
			pstmt.close();
			
			
		} catch(Exception ex) {
			System.out.println("SQLException:"+ex.getMessage());
			ex.printStackTrace();
		} finally{
			try {
				if(pstmt!=null)pstmt.close();
				if(conn !=null)conn.close();
			} catch(SQLException e) { 
				e.printStackTrace();
			}
		}
	}
	
	public void insertComment(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "insert into comment (c_board_idx,c_content,c_uidx,c_writer,c_group,c_order,c_depth) value(?,?,?,?,?,?,?)";
			pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1,board.getC_board_idx());
			pstmt.setString(2,board.getComment());
			pstmt.setInt(3,board.getC_uidx());
			pstmt.setString(4, board.getWriter());
			pstmt.setInt(5, board.getC_group());
			pstmt.setInt(6, board.getC_order());
			pstmt.setInt(7, board.getC_depth());
			pstmt.executeUpdate();
			pstmt.close();
			
			sql = "update comment set c_group = last_insert_id() where c_idx = last_insert_id()";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt.close();
			
		}catch(Exception ex){
			System.out.println("SQLException: " + ex.getMessage());
		}finally{
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}catch	(SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<Board> getComment(Board board) {
		Connection conn =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Board> comment_list = null;
		int b_idx = board.getB_idx();
		int num=0;
		
		try {
			conn = DBConnection.getConnection();
			comment_list = new ArrayList<Board>();
			
			String query3 = "select * from comment where c_board_idx = ? order by c_order ";
			pstmt = conn.prepareStatement(query3);
			pstmt.setInt(1, b_idx);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Board vo = new Board();
				String count = " ";
				
				num++;
	        	for (int i=0; i<Integer.parseInt(rs.getString("c_depth")); i++) {
	        		count +="ㄴ";
	        	}
	        	
				vo.SetComment(count+rs.getString("c_content"));
				vo.setC_idx(Integer.parseInt(rs.getString("c_idx")));
				vo.setC_board_idx(Integer.parseInt(rs.getString("c_board_idx")));
				vo.setC_uidx(Integer.parseInt(rs.getString("c_uidx")));
				vo.setC_group(Integer.parseInt(rs.getString("c_group")));
				vo.setC_order(Integer.parseInt(rs.getString("c_order")));
				vo.setC_depth(Integer.parseInt(rs.getString("c_depth")));
				vo.setWriter(rs.getString("c_writer"));
				vo.setNum(num);
				comment_list.add(vo);
				
			}
			pstmt.close();
			rs.close();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return comment_list;
	}
	
	public void insertComment_reply_ajax(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnection.getConnection();
			String sql = "insert into comment (c_board_idx,c_content,c_uidx,c_writer,c_group,c_order,c_depth) value(?,?,?,?,?,?,?)";
			pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1,board.getC_board_idx());
			pstmt.setString(2,board.getComment());
			pstmt.setInt(3,board.getC_uidx());
			pstmt.setString(4, board.getWriter());
			pstmt.setInt(5, board.getC_group());
			pstmt.setInt(6, board.getC_order());
			pstmt.setInt(7, board.getC_depth());
			pstmt.executeUpdate();
			pstmt.close();
			
			sql = "update comment set c_order = c_order+1 where c_group = ? and c_order >= ? and c_idx <> last_insert_id() ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getC_group());
			pstmt.setInt(2, board.getC_order());
			pstmt.executeUpdate();
			pstmt.close();
						
			
		} catch(Exception ex) {
			System.out.println("SQLException:"+ex.getMessage());
			ex.printStackTrace();
		} finally{
			try {
				if(pstmt!=null)pstmt.close();
				if(conn !=null)conn.close();
			} catch(SQLException e) { 
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<Board> searchBoard(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int option = board.getBoard_search_option();
		ArrayList<Board> searchList = null;
		ResultSet rs= null;
		
		try {
			searchList = new ArrayList<Board>();
			Board se = new Board();
			
			if (option==1) {
				conn = DBConnection.getConnection();
				String sql = "select * from board where b_title = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, board.getBoard_serarch_txt());
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					se.setB_idx(Integer.parseInt(rs.getString("b_idx")));
					se.setTitle(rs.getString("b_title"));
					se.setDate(rs.getString("b_date"));
					se.setWriter(rs.getString("b_writer"));
					se.setView_count(Integer.parseInt(rs.getString("view_count")));
				}
				pstmt.close();
			}
			if (option==2) {
							
			}
			if (option==3) {
				
			}
			
		} catch (Exception ex){
			ex.printStackTrace();
		} finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return searchList;
	}	
}
