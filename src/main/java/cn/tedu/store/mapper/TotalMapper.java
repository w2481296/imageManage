package cn.tedu.store.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


public interface TotalMapper {
	//��ҳ��Ϣ
	List<Map<String, Object>> privatetop();
	List<Map<String, Object>> publictop();
	//���㵱ǰ�������
	Map<String, Object> imageNum();
	//���㵱��������Ŀ
	Map<String, Object> downToday();
}
