package cn.tedu.store.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.tedu.store.bean.Image;
import cn.tedu.store.bean.PageQueryBean;
import cn.tedu.store.bean.ResponseResult;
import cn.tedu.store.mapper.ImageMapper;

@Controller
@RequestMapping("/image")
public class ImageController extends BaseController {
	@Autowired(required = false)
	public ImageMapper imageMapper;

	/**
	 * <pre>
	 * selectinstruByName(查询镜像库)
	 * &#64;param username,inName，inTime1，inTime2
	 * 首先判断有无登录用户，无则只能查询公有镜像
	 * 有登录用户则：
	 * 1.根据用户名查询用户身份
	 * 2.当是管理员时，只能查询公有镜像
	 * 3.当是用户时，则能查询自己的镜像和公有镜像。
	 * &#64;param response
	 * &#64;return
	 * </pre>
	 */
	@RequestMapping("/selectinstockByName.do")
	@ResponseBody
	public PageQueryBean selectinstockByName(String username,String inName, String inTime1, String inTime2,
			HttpServletResponse response) {
		List<Map<String, Object>> machiningList = new ArrayList<Map<String, Object>>();
		String role;
		if(username==""){
			machiningList = imageMapper.selectinstockByName(inName, inTime1, inTime2);
		}else {
			role=imageMapper.queryRole(username);
			if(role.equals("0")){//公有镜像
				machiningList = imageMapper.selectinstockByName(inName, inTime1, inTime2);
			}else if(role.equals("1")){//私有加公有
				machiningList = imageMapper.selectimage(username,inName, inTime1, inTime2);
			}
		}
		
		
		
		PageQueryBean pageQueryBean = new PageQueryBean();
		pageQueryBean.setItems(machiningList);
		pageQueryBean.setTotalRows(machiningList.size());
		return pageQueryBean;
	}

	/**
	 * <pre>
	 * addinstock(添加镜像)
	 * &#64;param image，username
	 * &#64;param response
	 * &#64;return
	 * </pre>
	 */
	@RequestMapping("/addinstock.do")
	@ResponseBody
	public String addinstock(Image image, String username) {
		List<Map<String, Object>> machiningList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> machiningList1 = new ArrayList<Map<String, Object>>();
		// 通过用户名查询角色
		String role = imageMapper.queryRole(username);
		String newimage = image.getRepository() + image.getTag();
		System.out.println("当前角色：" + role);
		// 查询镜像是否存在
		machiningList1 = imageMapper.queryImage(image.getRepository());
		String oldimage;

		int i;
		for (i = 0; i < machiningList1.size(); i++) {
			oldimage = machiningList1.get(i).get("repository").toString() + machiningList1.get(i).get("tag").toString();
			if (oldimage.equals(newimage)) {
				return "error";
			}
		}
		// 根据用户设置权限
		if (role.equals("1")) {
			image.setStatus("1");
		} else if (role.equals("0")) {
			image.setStatus("0");
		}
		image.setDownload("0");
		// 生成下载指令
		String downorder = "docker pull " + image.getRepository() + ":" + image.getTag();
		image.setDownorder(downorder);
		image.setCreateUser(username);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		image.setCreateDate(df.format(new Date()));
		// 添加镜像表
		System.out.println("镜像2：" + image.toString());
		imageMapper.addinstock(image);
		return "ok";
	}

	/**
	 * <pre>
	 * queryOrderByid(通过id查询镜像下载命令)
	 * &#64;param id
	 * &#64;param response
	 * &#64;return
	 * </pre>
	 */
	@RequestMapping("/queryOrderByid.do")
	@ResponseBody
	public String selectinstockByName(String id, HttpServletResponse response) {
		return imageMapper.queryOrderByid(id);// 镜像id查询镜像命令
	}

	/**
	 * <pre>
	 * queryinstockById(id查询镜像详细)
	 * &#64;param id
	 * &#64;param response
	 * &#64;return
	 * </pre>
	 */
	@RequestMapping("/queryinstockById.do")
	@ResponseBody
	public List<Map<String, Object>> queryinstockById(String id, HttpServletResponse response) {
		List<Map<String, Object>> machiningList = new ArrayList<Map<String, Object>>();
		machiningList = imageMapper.queryinstockById(id);
		return machiningList;
	}

	/**
	 * <pre>
	 * addTime(通过id增加下载次数)
	 * &#64;param id
	 * &#64;param response
	 * &#64;return
	 * </pre>
	 */
	@RequestMapping("/addTime.do")
	@ResponseBody
	public void addTime(String id, HttpServletResponse response) {
		imageMapper.addTime(id);// 镜像id
	}

	/**
	 * <pre>
	 * selectinstruByName(通过镜像Id 删除镜像)    
	 * &#64;param id
	 * &#64;param response
	 * &#64;return
	 * </pre>
	 */
	@RequestMapping("/delMainInstockById.do")
	@ResponseBody
	public String delMainInstockById(String insid, HttpServletResponse response) {
		Integer insid2 = Integer.valueOf(insid);
		imageMapper.delMainInstockById(insid2);
		return "success";
	}

	/**
	 * <pre>
	 * selectinstruByName(通过Ids 批量删除鏡像)    
	 * &#64;param id
	 * &#64;param response
	 * &#64;return
	 * </pre>
	 */
	@RequestMapping("/delMainInsstockById.do")
	@ResponseBody
	public String delMainInsstockById(String ids, HttpServletResponse response) {
		String[] aStrings = ids.split(",");
		Integer[] ids2 = new Integer[aStrings.length];
		for (int i = 0; i < ids2.length; i++) {
			ids2[i] = Integer.parseInt(aStrings[i]);
		}
		imageMapper.delMainInsstockById(ids2);
		return "success";
	}

	/**
	 * <pre>
	 * updateimage(修改镜像信息)    
	 * &#64;image response
	 * &#64;return
	 * </pre>
	 */

	@RequestMapping("/updateimage.do")
	@ResponseBody
	public void updateimage(Image image) {
		imageMapper.updateimage(image);
	}

}
