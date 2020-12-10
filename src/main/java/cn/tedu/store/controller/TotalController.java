package cn.tedu.store.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.bean.PageQueryBean;
import cn.tedu.store.mapper.TotalMapper;

@Controller
@RequestMapping("/total")
public class TotalController extends BaseController {
	@Autowired(required = false)
	public TotalMapper totalMapper;

	//������ҳ�������
	@RequestMapping("/showindexinfo.do")
	@ResponseBody
	public List<Map<String, Object>> showindexinfo() {
		List<Map<String, Object>> machiningList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> machiningList1 = new ArrayList<Map<String, Object>>();
		Map<String, Object> map=new HashMap<String, Object>();
		Map<String, Object> map1=new HashMap<String, Object>();
		machiningList=totalMapper.privatetop();//˽��top5
		machiningList1=totalMapper.publictop();//����top5
		map=totalMapper.imageNum();//����������
		map1=totalMapper.downToday();//����������������
		int i;
		for(i=0;i<machiningList1.size();i++){
			machiningList.add(machiningList1.get(i));
		}
		machiningList.add(map);
		machiningList.add(map1);
		return machiningList;
	}

}
