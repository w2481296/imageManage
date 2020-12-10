package cn.tedu.store.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 主页面以及内部嵌套跳转页面
 * 
 * */
@Controller
@RequestMapping("/main")
public class MainController {
	//界面首页导航
	@RequestMapping("/showIndex.do")
	public String showIndex() {
		return "redirect:/index.jsp";
	}
	//定义主显示页面
	@RequestMapping("/showIndexx.do")
	public String showIndexx() {
		return "indexx";
	}
	
	// 跳转镜像管理页面
	@RequestMapping("/showIndex3.do")
	public String showIndex3() {
		return "InstrumentAdd";
	}
	// 跳转镜像添加页面
	@RequestMapping("/showIndex19.do")
	public String showIndex19() {
		return "Mainmusic2-add";
	}
	// 跳转镜像镜像编辑页面
	@RequestMapping("/showIndex20.do")
	public String showIndex20() {
		return "Mainmusic-show";
	}

	// 定义修改密码页面
	@RequestMapping("/showIndex12.do")
	public String showIndex12() {
		return "ChangePwd";
	}

	
}
