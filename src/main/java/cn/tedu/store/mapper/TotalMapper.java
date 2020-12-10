package cn.tedu.store.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


public interface TotalMapper {
	//首页信息
	List<Map<String, Object>> privatetop();
	List<Map<String, Object>> publictop();
	//计算当前库存总数
	Map<String, Object> imageNum();
	//计算当天下载数目
	Map<String, Object> downToday();
}
