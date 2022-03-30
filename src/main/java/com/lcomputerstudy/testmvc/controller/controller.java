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
import com.lcomputerstudy.testmvc.vo.Search;
import com.lcomputerstudy.testmvc.vo.User;

import com.lcomputerstudy.testmvc.vo.Board;
import com.lcomputerstudy.testmvc.service.BoardService;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


@WebServlet("*.do")
public class controller extends HttpServlet {
	private static final String CHARSET = "utf-8";
    //private static final String ATTACHES_DIR = "C:\\attaches";
	private static final String ATTACHES_DIR = "C:\\Users\\l3-morning\\Documents\\work10\\lcomputerstudy\\src\\main\\webapp\\img";
    private static final int LIMIT_SIZE_BYTES = 1024 * 1024;
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
		int searchcount = 0;
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
		ArrayList<Board> file_list = null;
				
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
				
				request.setAttribute("usercount",usercount);
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
				
				response.setContentType("text/html; charset=UTF-8");
		        request.setCharacterEncoding(CHARSET);
		        PrintWriter out = response.getWriter();

		        File attachesDir = new File(ATTACHES_DIR);
		 
		 
		        DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
		        fileItemFactory.setRepository(attachesDir);
		        fileItemFactory.setSizeThreshold(LIMIT_SIZE_BYTES);
		        ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
		 
		        String  test = "";
		        String filetest ="";
		        try {
		            List<FileItem> items = fileUpload.parseRequest(request);
		            for (FileItem item : items) {
		                if (item.isFormField()) {
		                    //System.out.printf("파라미터 명 : %s, 파라미터 값 :  %s \n", item.getFieldName(), item.getString(CHARSET));
		                    test += item.getString(CHARSET)+",";
		                } else {
		                    /*System.out.printf("파라미터 명 : %s, 파일 명 : %s,  파일 크기 : %s bytes \n", item.getFieldName(),
		                            item.getName(), item.getSize());*/
		                    if (item.getSize() > 0) {
		                        String separator = File.separator;
		                        int index =  item.getName().lastIndexOf(separator);
		                        String fileName = item.getName().substring(index  + 1);
		                        File uploadFile = new File(ATTACHES_DIR +  separator + fileName);
		                        item.write(uploadFile);
		                        filetest += fileName+",";
		                    }
		                }
		            }

		            out.println("<h1>파일 업로드 완료</h1>");
		 
		 
		        } catch (Exception e) {
		            // 파일 업로드 처리 중 오류가 발생하는 경우
		            e.printStackTrace();
		            out.println("<h1>파일 업로드 중 오류가  발생하였습니다.</h1>");
		        }
		        
		        String p_txt = test;
		        String[] p_array = p_txt.split(",");
		         
		        List<String> p_list = new ArrayList<>();
		        for (int i = 0; i < p_array.length; i++) {
		        	p_list.add(p_array[i]);
		        }
		        
		        String f_txt = filetest;
		        String[] f_array = f_txt.split(",");
		         
		        List<String> f_list = new ArrayList<>();
		        for (int i = 0; i < f_array.length; i++) {
		        	f_list.add(f_array[i]);
		        }
		        
				board.setTitle(p_list.get(0));
				board.setContent(p_list.get(1));
				board.setWriter(p_list.get(2));
				board.setU_idx(Integer.parseInt(p_list.get(3)));
				board.setFile1(f_list.get(0));
				board.setFile2(f_list.get(1));
				
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
				file_list = boardService.getFile(board);
				request.setAttribute("list", list2);
				request.setAttribute("comment_list", comment_list);
				request.setAttribute("file_list", file_list);
				view = "board/board_detail";
				break;
			case "/board-modify.do":
				board = new Board();
				board.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
				boardService = BoardService.getInstance();
				ArrayList<Board> list3 = boardService.getFile(board);
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
				Search search = new Search();
				
				boardService = BoardService.getInstance();
				search.setType(Integer.parseInt(request.getParameter("search_option")));
				search.setKeyword(request.getParameter("search_txt"));
				
				searchcount = BoardService.getSelectBoardCount(search);
				String reqPage3 = request.getParameter("page");
				if (reqPage3 != null)
					page = Integer.parseInt(reqPage3);

				Pagination pagination3 = new Pagination();
				pagination3.setCount(searchcount);
				pagination3.setPage(page);
				pagination3.setSearch(search);
				pagination3.setSearch_type(Integer.parseInt(request.getParameter("search_option")));
				pagination3.setSearch_txt(request.getParameter("search_txt"));
				pagination3.init();
				
				search_list = boardService.searchBoard(pagination3);
				
				request.setAttribute("search_list",search_list);
				request.setAttribute("pagination", pagination3);
				view = "board/aj-search-list";
			break;
			case "/board-search-pagination.do":
				Search search2 = new Search();
				
				boardService = BoardService.getInstance();
				search2.setType(Integer.parseInt(request.getParameter("search_option")));
				search2.setKeyword(request.getParameter("search_txt"));
				
				searchcount = BoardService.getSelectBoardCount(search2);
				String page1 = request.getParameter("page");
				if (page1 != null)
					page = Integer.parseInt(page1);

				Pagination pagination4 = new Pagination();
				pagination4.setCount(searchcount);
				pagination4.setPage(page);
				pagination4.setSearch(search2);
				pagination4.setSearch_type(Integer.parseInt(request.getParameter("search_option")));
				pagination4.setSearch_txt(request.getParameter("search_txt"));
				pagination4.init();
				
				search_list = boardService.searchBoard(pagination4);
				
				request.setAttribute("search_list",search_list);
				request.setAttribute("pagination", pagination4);
				view = "board/aj-search-result-list";
			break;
			case "/user-add-admin.do":
				user = new User();
				user.setU_idx(Integer.parseInt(request.getParameter("user_idx")));
				userService = UserService.getInstance();
				userService.addAdmin(user);
				
				view = "user/list";
			break;
			case "/user-delete-admin.do":
				user = new User();
				user.setU_idx(Integer.parseInt(request.getParameter("user_idx")));
				userService = UserService.getInstance();
				userService.deleteAdmin(user);
				
				view = "user/list";
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
