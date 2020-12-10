package cn.tedu.store.service;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sun.xml.internal.ws.model.wsdl.WSDLBoundOperationImpl;

import cn.tedu.store.bean.User;
import cn.tedu.store.ex.PasswordNotMatchException;
import cn.tedu.store.ex.UserNotFoundException;
import cn.tedu.store.ex.UsernameAlreadyExistException;
import cn.tedu.store.mapper.UserMapper;


@Service
public class UserService implements IUserService {

	@Resource
	private UserMapper userMapper;


	public void addUser(User user) {
		// 1.���ó־ò�ķ���selectByUsername(user.getUsername());����user1����
		// 2.����û��������������insertUser(user)����,
		// ��Ϊ�գ����û����Ѵ��ڣ��׳��쳣UsernameAlreadyExistException
		User user1 = userMapper.selectByUsername(user.getUsername());
		if (user1 == null) {
			userMapper.insert(user);
		} else {
			throw new UsernameAlreadyExistException("�û����Ѵ��ڣ�");
		}
	}



	public boolean checkUsername(String name) {
		return userMapper.selectByUsername(name) != null;
	}
	// ʵ�ֵ�¼����
	public User login(String username, String password) {
		// 1.���ó־ò㷽��ByUsername;����user����
		// 2.�ж϶����Ƿ���ڣ��������׳��쳣
		// 3.�����ж�����
		User user = userMapper.selectByUsername(username);
		if (user == null) {
			throw new UserNotFoundException("�ʺŲ�����");
		} else {
			if (user.getPassword().equals(password)) {
				return user;
			} else {
				throw new PasswordNotMatchException("���벻ƥ��");
			}
		}

	}

	//��ѯ����������û���
	@Override
	public String queryoldPwd(String username) {
		return userMapper.queryoldPwd(username);
	}

	@Override
	public void updateuserNwd(String username, String newPwd) {
		userMapper.updateuserNwd(username,newPwd);
	}




	

}