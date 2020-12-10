package cn.tedu.store.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.bean.User;
/**
 * 哪个工程中的类和接口。 
 * @author 名字
 * 开始时间：
 *
 */
public interface UserMapper {
	/**
	 *插入一个用户信息 
	 */
	void insert(User user);
	/**
	 * 根据用户名查询,把结果封装成user对象
	 * */
	User selectByUsername(String username);
	
	/**
	 * 修改个人信息
	 */
	void changePassword(@Param("username")String username,@Param("newPwd")String  newPwd);
	/**
	 * 根据id查询用户
	 * */
	String queryoldPwd(@Param("username")String username);
	void updateuserNwd(@Param("username")String username, @Param("newPwd")String newPwd);
	List<Map<String, Object>> selectmenu(@Param("id")Integer id);

}
