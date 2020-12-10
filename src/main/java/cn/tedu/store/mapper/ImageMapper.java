package cn.tedu.store.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.bean.Image;

public interface ImageMapper {
	//��ѯ������Ϣ
	List<Map<String, Object>> selectinstockByName(@Param("inName")String inName,@Param("inTime1")String inTime1,@Param("inTime2")String inTime2);
	//�����û�����ѯ��ɫ
	String queryRole(@Param("username")String username);
	//�ж��Ƿ���ھ���
	List<Map<String, Object>> queryImage(@Param("repository")String repository);
	//��Ӿ���
	void addinstock(Image image);
	//ͨ��id��ѯ��������
	String queryOrderByid(@Param("id")String id);
	//ͨ��id���Ӿ������ش���
	void addTime(@Param("id")String id);
	
	//ɾ������
	void delMainInstockById(@Param("insid2")Integer insid2);
	//����ɾ������
	void delMainInsstockById(@Param("ids2")Integer[] ids2);
	
	//ͨ��id��ѯ������ϸ
	List<Map<String, Object>> queryinstockById(@Param("id")String id);
	//���¾���
	void updateimage(Image image);
	//�û���¼��ѯ
	List<Map<String, Object>> selectimage(@Param("username")String username, @Param("inName")String inName, @Param("inTime1")String inTime1, @Param("inTime2")String inTime2);
	
	
	
}
