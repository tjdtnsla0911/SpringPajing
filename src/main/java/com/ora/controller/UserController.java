package com.ora.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ora.service.UserService;
import com.ora.vo.UserStart;

import oracle.jdbc.proxy.annotation.Post;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	
	public String encryptSHA256(String str) {
		System.out.println("비번바꾸러옴");
		System.out.println("받은 str = "+str);
	    String SHA = null;
	    try {
	        MessageDigest sh = MessageDigest.getInstance("SHA-256"); // 이 부분을 SHA-1으로 바꿔도 된다!
	        sh.update(str.getBytes()); 
	        byte byteData[] = sh.digest();
	        StringBuffer sb = new StringBuffer(); 
	        for(int i = 0 ; i < byteData.length ; i++){
	            sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
	        }
	        SHA = sb.toString();
	    } catch(NoSuchAlgorithmException e){
	        e.printStackTrace(); 
	    }
	    System.out.println("리턴전의 sha = "+SHA);
	    return SHA;
	}


	
	
@GetMapping("/user/login")
public String login() {
	System.out.println("로그인 하러갑니다");
	return "/user/login";
}
@GetMapping("/user/join")
public String join() {
	System.out.println("회원가입하러갑시다");
	return "/user/join";
}

@PostMapping("/user/loginstart")
public String loginStart(UserStart userStart,HttpServletRequest request) throws Exception{
	System.out.println("로그인하러옴");
	System.out.println("받은 userStart =" +userStart);
	String dd =encryptSHA256(userStart.getPassword());
	userStart.setPassword(dd);
	UserStart userstart2 = new UserStart();
	userstart2 = userService.login(userStart);
	System.out.println("받은 userstart2= "+userstart2);
	if(userstart2 == null ||userStart.equals("")) {
		System.out.println("로그인실패에옴");
		return "/";
	}
	System.out.println("로그인성공");
	HttpSession session = request.getSession();
	session.setAttribute("loginOK", userstart2);
	System.out.println("세션까지성공");
	return "redirect:/list";
	
	}
@PostMapping("/user/joinStart")
public @ResponseBody String joinStart(UserStart userStart) throws Exception{
	System.out.println("userStart에왔습니다");
	System.out.println("받은 값 = "+userStart);
	String dd =encryptSHA256(userStart.getPassword());
	userStart.setPassword(dd);
	System.out.println("뱉은 dd = "+userStart.getPassword());
	userService.join(userStart);
	System.out.println("문제없이 인서트끝난듯? ");
	return "ddd";
}
//로그아웃
@GetMapping("/user/logout")
public String logout(HttpSession session) {
	System.out.println("로그아웃하러옴");
	session.invalidate();
	return "redirect:/list";
}


}
