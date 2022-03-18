package com.lcomputerstudy.testmvc.controller;

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
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		int page = 1;
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		String pw = null;
		String idx = null;
		
		HttpSession session = null;
		BoardService boardService = null;
				
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
				boardService = BoardService.getInstance();
				ArrayList<Board> list1 = boardService.getBoard();
				request.setAttribute("list", list1);
				
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
				String b_idx = (request.getParameter("b_idx"));
				int ib_idx = Integer.parseInt(b_idx);
				board.setB_idx(ib_idx);
				
				boardService = BoardService.getInstance();
				boardService.insertUser(board);
						
				view = "user/insert-result";
				break;
			case "/board-write.do":
				view = "board/board_write";	
				break;
			case "/board-detail.do":
				board = new Board();
				board.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
				boardService = BoardService.getInstance();
				ArrayList<Board> list2 = boardService.getInfo(board); 
				request.setAttribute("list", list2);
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
		
		}
		RequestDispatcher rd = request.getRequestDispatcher(view+".jsp");
		rd.forward(request, response);
	}
	
	String checkSession(HttpServletRequest request, HttpServletResponse response, String command) {
		HttpSession session = request.getSession();
		
		String[] authList = {
				"/user-list.do"
				,"/user-insert.do"
				,"/user-insert-process.do"
				,"/user-detail.do"
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
