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

	// ��ʾ��ͼ
	@RequestMapping("/showRegister.do")
	public String showRegister() {
		return "register";
	}

	/**
	 * ��ʾlogin.jspҳ��Ŀ���������
	 * 
	 */
	@RequestMapping("/showLogin.do")
	public String showLogin() {
		return "login";
	}

	// �첽������֤�û���
	@RequestMapping("/checkUsername.do")
	@ResponseBody
	public String checkUsername(String username, String phone,String email) {
		// 1.����ҵ��㷽��
		boolean b = userService.checkUsername(username);
		if (b) {
			return "0";
		} 
		return "success";

	}



	// ʵ�ֵ�¼����
	@RequestMapping("/login.do")
	@ResponseBody
	public ResponseResult<Void> login(String username, String password,HttpSession session) {
		ResponseResult<Void> rr = null;
		User user = new User();
		try {
			user = userService.login(username, password);
			rr = new ResponseResult<>(1, "��¼�ɹ�");
			session.setAttribute("user", user);

		} catch (RuntimeException e) {
			rr = new ResponseResult<>(0, e.getMessage());
		}
		return rr;

	}

	// �˳��Ĺ���
	@RequestMapping("/exit.do")
	public String exit(HttpSession session) {
		// 1.��sessionʧЧ
		session.invalidate();
		// 2.�ض���index.jsp
		return "redirect:../main/showIndex.do";
	}

	// �޸�����ҳ��
	@RequestMapping("/updatePassword.do")
	@ResponseBody
	public String updatePassword(String username, String newPwd, String oldPwd) {
		// �Ȳ�ѯ�������Ƿ���ȷ
		String old = userService.queryoldPwd(username);
		if (old.equals(oldPwd)) {
			/* ����������ȷʱ�������� */
			userService.updateuserNwd(username, newPwd);
		} else {
			return "fail";
		}
		return "success";
	}

}
