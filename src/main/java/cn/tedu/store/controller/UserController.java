package cn.tedu.store.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.remoting.RemoteTimeoutException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.bean.PageQueryBean;
import cn.tedu.store.bean.ResponseResult;
import cn.tedu.store.bean.User;
import cn.tedu.store.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	@Resource
	private IUserService userService;

	// 显示视图
	@RequestMapping("/showRegister.do")
	public String showRegister() {
		return "register";
	}

	/**
	 * 显示login.jsp页面的控制器方法
	 * 
	 */
	@RequestMapping("/showLogin.do")
	public String showLogin() {
		return "login";
	}

	// 异步请求，验证用户名
	@RequestMapping("/checkUsername.do")
	@ResponseBody
	public String checkUsername(String username, String phone,String email) {
		// 1.调用业务层方法
		boolean b = userService.checkUsername(username);
		if (b) {
			return "0";
		} 
		return "success";

	}



	// 实现登录功能
	@RequestMapping("/login.do")
	@ResponseBody
	public ResponseResult<Void> login(String username, String password,HttpSession session) {
		ResponseResult<Void> rr = null;
		User user = new User();
		try {
			user = userService.login(username, password);
			rr = new ResponseResult<>(1, "登录成功");
			session.setAttribute("user", user);

		} catch (RuntimeException e) {
			rr = new ResponseResult<>(0, e.getMessage());
		}
		return rr;

	}

	// 退出的功能
	@RequestMapping("/exit.do")
	public String exit(HttpSession session) {
		// 1.让session失效
		session.invalidate();
		// 2.重定向到index.jsp
		return "redirect:../main/showIndex.do";
	}

	// 修改密码页面
	@RequestMapping("/updatePassword.do")
	@ResponseBody
	public String updatePassword(String username, String newPwd, String oldPwd) {
		// 先查询旧密码是否正确
		String old = userService.queryoldPwd(username);
		if (old.equals(oldPwd)) {
			/* 密码输入正确时更改密码 */
			userService.updateuserNwd(username, newPwd);
		} else {
			return "fail";
		}
		return "success";
	}

}
