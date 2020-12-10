package cn.tedu.store.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.bean.Image;

public interface ImageMapper {
	//查询镜像信息
	List<Map<String, Object>> selectinstockByName(@Param("inName")String inName,@Param("inTime1")String inTime1,@Param("inTime2")String inTime2);
	//根据用户名查询角色
	String queryRole(@Param("username")String username);
	//判断是否存在镜像
	List<Map<String, Object>> queryImage(@Param("repository")String repository);
	//添加镜像
	void addinstock(Image image);
	//通过id查询镜像命令
	String queryOrderByid(@Param("id")String id);
	//通过id增加镜像下载次数
	void addTime(@Param("id")String id);
	
	//删除镜像
	void delMainInstockById(@Param("insid2")Integer insid2);
	//批量删除镜像
	void delMainInsstockById(@Param("ids2")Integer[] ids2);
	
	//通过id查询镜像详细
	List<Map<String, Object>> queryinstockById(@Param("id")String id);
	//更新镜像
	void updateimage(Image image);
	//用户登录查询
	List<Map<String, Object>> selectimage(@Param("username")String username, @Param("inName")String inName, @Param("inTime1")String inTime1, @Param("inTime2")String inTime2);
	
	
	
}
