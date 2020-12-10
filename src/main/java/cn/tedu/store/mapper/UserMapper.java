package cn.tedu.store.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.bean.User;
/**
 * �ĸ������е���ͽӿڡ� 
 * @author ����
 * ��ʼʱ�䣺
 *
 */
public interface UserMapper {
	/**
	 *����һ���û���Ϣ 
	 */
	void insert(User user);
	/**
	 * �����û�����ѯ,�ѽ����װ��user����
	 * */
	User selectByUsername(String username);
	
	/**
	 * �޸ĸ�����Ϣ
	 */
	void changePassword(@Param("username")String username,@Param("newPwd")String  newPwd);
	/**
	 * ����id��ѯ�û�
	 * */
	String queryoldPwd(@Param("username")String username);
	void updateuserNwd(@Param("username")String username, @Param("newPwd")String newPwd);
	List<Map<String, Object>> selectmenu(@Param("id")Integer id);

}