package cn.tedu.store.service;

import java.util.List;
import java.util.Map;

import cn.tedu.store.bean.User;

public interface IUserService {
	void addUser(User user);
	boolean checkUsername(String name);
	User login(String username,String password);
	String queryoldPwd(String username);
	void updateuserNwd(String username, String newPwd);
	
}
