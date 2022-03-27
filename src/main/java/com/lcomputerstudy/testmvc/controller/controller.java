package com.lcomputerstudy.testmvc.controller;
//주석
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.lcomputerstudy.testmvc.service.UserService;
import com.lcomputerstudy.testmvc.vo.Pagination;
import com.lcomputerstudy.testmvc.vo.User;

import com.lcomputerstudy.testmvc.vo.Board;
import com.lcomputerstudy.testmvc.service.BoardService;

@WebServlet("*.do")
public class controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");

		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		String view = null;
		
		int usercount = 0;
		int boardcount =0;
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		int page = 1;
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		String pw = null;
		String idx = null;
		
		HttpSession session = null;
		BoardService boardService = null;
		
		ArrayList<Board> comment_list = null;
		ArrayList<Board> search_list = null;
				
		command = checkSession(request,response,command);

		switch (command) {
			case "/user-list.do":
				String reqPage = request.getParameter("page");
				if (reqPage != null)
					page = Integer.parseInt(reqPage);
				
				UserService userService = UserService.getInstance();
				usercount = userService.getUsersCount();
				
				Pagination pagination = new Pagination();
				pagination.setCount(usercount);
				pagination.setPage(page);
				pagination.init();
				
				ArrayList<User> list = userService.getUsers(pagination);
				
				request.setAttribute("list", list);
				request.setAttribute("pagination", pagination);
				
				view = "user/list";
				break;
			case "/user-insert.do":
				view = "user/insert";
				break;
			case "/user-insert-process.do":
				User user = new User();
				user.setU_id(request.getParameter("id"));
				user.setU_pw(request.getParameter("password"));
				user.setU_name(request.getParameter("name"));
				user.setU_tel(request.getParameter("tel1") + "-" + request.getParameter("tel2") + "-" + request.getParameter("tel3"));
				user.setU_age(request.getParameter("age"));
				
				userService = UserService.getInstance();
				userService.insertUser(user);
						
				view = "user/insert-result";
				break;
			case "/user-login.do":
				view = "user/login";
				break;
			case "/user-login-process.do":
				idx = request.getParameter("login_id");
				pw = request.getParameter("login_password");
				
				userService = UserService.getInstance();
				user = userService.loginUser(idx,pw);
				
				if(user!=null) {
					session = request.getSession();
					session.setAttribute("user", user);
					
					view = "user/login-result";
				} else {
					view = "user/login-fail";
				} 
				break;
			case "/logout.do":
				session = request.getSession();
				session.invalidate();
				view = "user/login";
				break;
			case "/access-denied.do":
				view = "user/access-denied";
				break;
			case "/board-list.do":
				String reqPage2 = request.getParameter("page");
				if (reqPage2 != null)
					page = Integer.parseInt(reqPage2);
				
				boardService = BoardService.getInstance();
				boardcount = BoardService.getBoardCount();
				
				Pagination pagination2 = new Pagination();
				pagination2.setCount(boardcount);
				pagination2.setPage(page);
				pagination2.init();
				
				ArrayList<Board> list6 = boardService.getBoard(pagination2);
				request.setAttribute("list", list6);
				request.setAttribute("pagination", pagination2);

				view = "board/list";
				break;
			case "/board-insert-process.do":
				boardService = BoardService.getInstance();
				Board board = new Board();
				board.setTitle(request.getParameter("title"));
				board.setContent(request.getParameter("content"));
				board.setWriter(request.getParameter("writer"));
				String u_idx = request.getParameter("u_idx");
				int iu_idx = Integer.parseInt(u_idx);
				board.setU_idx(iu_idx);
				
				boardService = BoardService.getInstance();
				boardService.insertBoard(board);
						
				view = "board/insert-result";
				break;
			case "/board-write.do":
				view = "board/board_write";	
				break;
			case "/board-detail.do":
				board = new Board();
				board.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
				boardService = BoardService.getInstance();
				ArrayList<Board> list2 = boardService.getInfo(board); 
				comment_list = boardService.getComment(board);
				request.setAttribute("list", list2);
				request.setAttribute("comment_list", comment_list);
				view = "board/board_detail";
				break;
			case "/board-modify.do":
				board = new Board();
				board.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
				boardService = BoardService.getInstance();
				ArrayList<Board> list3 = boardService.getInfo(board);
				request.setAttribute("list",list3);
				view = "board/board_modify";
				break;
			case "/board-modify-process.do":
				board = new Board();
				board.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
				board.setTitle(request.getParameter("title"));
				board.setContent(request.getParameter("content"));
				
				boardService = BoardService.getInstance();
				boardService.modifyUser(board);
				view = "board/modify-result";
				break;
			case "/board-delete-process.do":
				board = new Board();
				board.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
				
				boardService = BoardService.getInstance();
				boardService.deleteUser(board);
				view = "board/delete-result";
				break;
			case "/board-write-reply.do":
				board = new Board();
				board.setGroup(Integer.parseInt(request.getParameter("group")));
				board.setDepth(Integer.parseInt(request.getParameter("depth")));
				board.setOrder(Integer.parseInt(request.getParameter("order")));
				request.setAttribute("board", board);
				view = "board/write_reply";
				break;
			case "/board-reply-insert-process.do":
				board = new Board();
				boardService = BoardService.getInstance();
				board.setTitle(request.getParameter("title"));
				board.setContent(request.getParameter("content"));
				board.setWriter(request.getParameter("writer"));
				board.setU_idx(Integer.parseInt(request.getParameter("u_idx")));
				board.setGroup(Integer.parseInt(request.getParameter("group")));
				board.setDepth(Integer.parseInt(request.getParameter("depth"))+1);
				board.setOrder(Integer.parseInt(request.getParameter("order"))+1);
				
				boardService.insertReply(board);
				view = "board/insert-result";
				break;
			case "/board-comment-insert.do":
				board = new Board();
				boardService = BoardService.getInstance();
				board.setB_idx(Integer.parseInt(request.getParameter("c_board_idx")));
				board.setC_board_idx(Integer.parseInt(request.getParameter("c_board_idx")));
				board.SetComment(request.getParameter("c_content"));
				board.setC_uidx(Integer.parseInt(request.getParameter("c_uidx")));
				board.setWriter(request.getParameter("c_writer"));
				board.setC_group(Integer.parseInt(request.getParameter("c_group")));
				board.setC_order(Integer.parseInt(request.getParameter("c_order"))+1);
				board.setC_depth(Integer.parseInt(request.getParameter("c_depth")));
				boardService.insertComment(board);
				
				comment_list = boardService.getComment(board);
				request.setAttribute("comment_list", comment_list);
				
				view = "board/aj-comment-list";
				break;
			case "/comment-comment-insert-ajax.do":
				board = new Board();
				boardService= BoardService.getInstance();
				board.setB_idx(Integer.parseInt(request.getParameter("c_board_idx")));
				board.setC_board_idx(Integer.parseInt(request.getParameter("c_board_idx")));
				board.SetComment(request.getParameter("c_content"));
				board.setC_uidx(Integer.parseInt(request.getParameter("c_uidx")));
				board.setWriter(request.getParameter("c_writer"));
				board.setC_group(Integer.parseInt(request.getParameter("c_group")));
				board.setC_order(Integer.parseInt(request.getParameter("c_order"))+1);
				board.setC_depth(Integer.parseInt(request.getParameter("c_depth"))+1);
				boardService.insertComment_reply_ajax(board);
				
				comment_list = boardService.getComment(board);
				request.setAttribute("comment_list", comment_list);
				
				view = "board/aj-comment-list";
				break;
			case "/board-search.do":
				board = new Board();
				boardService = BoardService.getInstance();
				board.setBoard_search_option(Integer.parseInt(request.getParameter("search_option")));
				board.setBoard_serarch_txt(request.getParameter("search_txt"));
				boardService.searchBoard(board);
				
				search_list = boardService.searchBoard(board);
				request.setAttribute("search_list",search_list);
				
				String reqPage3 = request.getParameter("page");
				if (reqPage3 != null)
					page = Integer.parseInt(reqPage3);
				
				boardService = BoardService.getInstance();
				boardcount = BoardService.getBoardCount();
				
				Pagination pagination3 = new Pagination();
				pagination3.setCount(boardcount);
				pagination3.setPage(page);
				pagination3.init();
				boardService.getBoard(pagination3);
				
				request.setAttribute("pagination", pagination3);
				view = "board/aj-search-list";
				
			break;
		}
		RequestDispatcher rd = request.getRequestDispatcher(view+".jsp");
		rd.forward(request, response);
	}
	
	String checkSession(HttpServletRequest request, HttpServletResponse response, String command) {
		HttpSession session = request.getSession();
		
		String[] authList = {
		
				"/user-detail.do"
				,"/user-edit.do"
				,"/user-edit-process.do"
				,"/logout.do"
			};
		
		for (String item : authList) {
			if (item.equals(command)) {
				if (session.getAttribute("user") == null) {
					command = "/access-denied.do";
				}
			}
		}
		return command;
	}

}
