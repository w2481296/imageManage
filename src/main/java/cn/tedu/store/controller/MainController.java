package cn.tedu.store.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * ��ҳ���Լ��ڲ�Ƕ����תҳ��
 * 
 * */
@Controller
@RequestMapping("/main")
public class MainController {
	//������ҳ����
	@RequestMapping("/showIndex.do")
	public String showIndex() {
		return "redirect:/index.jsp";
	}
	//��������ʾҳ��
	@RequestMapping("/showIndexx.do")
	public String showIndexx() {
		return "indexx";
	}
	
	// ��ת�������ҳ��
	@RequestMapping("/showIndex3.do")
	public String showIndex3() {
		return "InstrumentAdd";
	}
	// ��ת�������ҳ��
	@RequestMapping("/showIndex19.do")
	public String showIndex19() {
		return "Mainmusic2-add";
	}
	// ��ת������༭ҳ��
	@RequestMapping("/showIndex20.do")
	public String showIndex20() {
		return "Mainmusic-show";
	}

	// �����޸�����ҳ��
	@RequestMapping("/showIndex12.do")
	public String showIndex12() {
		return "ChangePwd";
	}

	
}
