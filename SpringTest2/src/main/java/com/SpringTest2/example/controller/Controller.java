
package com.SpringTest2.example.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.SpringTest2.example.domain.Board;
import com.SpringTest2.example.domain.User;
import com.SpringTest2.example.service.BoardService;
import com.SpringTest2.example.service.UserService;

@org.springframework.stereotype.Controller
public class Controller {
	
	@Autowired UserService userservice;
	@Autowired BoardService boardservice;
	
	@RequestMapping("/")
	public String home(Model model) {
		
		List<Board> list = boardservice.selectBoardList();
		model.addAttribute("list", list);

		return "/index";
	}
	
	@RequestMapping("/beforeSignup") 
	public String beforeSignUp() {
		return "/signup";
	}
	
	@RequestMapping("/signup")
	   public String signup(User user) {

	      String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
	      

	      user.setPassword(encodedPassword);
	      user.setAccountNonExpired(true);
	      user.setEnabled(true);
	      user.setAccountNonLocked(true);
	      user.setCredentialsNonExpired(true);
	      user.setAuthorities(AuthorityUtils.createAuthorityList("ROLE_USER"));   
	
	      userservice.createUser(user);

	      userservice.createAuthorities(user);
	      
	      return "/login";
	   }
	
	@RequestMapping(value="/login")
	public String beforeLogin(Model model) {
		return "/login";
	}
	
	@Secured({"ROLE_ADMIN"})
	   @RequestMapping(value="/admin")
	   public String admin(Model model) {
	      return "/admin";
	   }
	   
	   @Secured({"ROLE_USER"})
	   @RequestMapping(value="/user/info")
	   public String userInfo(Model model) {
	      
	      return "/user_info";
	   }
	   
	   @RequestMapping(value="/denied")
	   public String denied(Model model) {
	      return "/denied";
	   }
	
}

