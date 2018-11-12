package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.entity.FileType;
import com.entity.Users;
import com.service.FileTypeService;

/**
 * fileType�Ŀ�����
 * @author ��С��
 *
 */
@Controller
@RequestMapping
public class FileTypeController {
	@Autowired
	private FileTypeService fileTypeService;
	/**
	  * ����typeid����typename��һ�м�¼,���typeid��typename��Ϊ�����ѯ���ű�
	  */
	@RequestMapping("file/selectFileTypes")
	public ModelAndView selectFileTypes(HttpSession session) {
		Users u=(Users) session.getAttribute("userInfo");
		ModelAndView m=new ModelAndView();
		List<FileType> filetypeList=fileTypeService.selectFileTypes(null, null,u.getUid());
		m.addObject("filetypeList", filetypeList);
		m.setView(new MappingJackson2JsonView());
		return m;
	}
	/**
	 * �������
	 */
	@RequestMapping("file/addFiletype")
	public ModelAndView addFileType(@RequestParam("typename") String typename,HttpSession session) {
		Users u=(Users) session.getAttribute("userInfo");
		ModelAndView m=new ModelAndView();
		String addMessage="";
		if(fileTypeService.addFileType(typename,u.getUid()))
			addMessage="��ӳɹ�";
		else
			addMessage="���ʧ��";
		m.addObject("addMessage", addMessage);
		m.setView(new MappingJackson2JsonView());
		return m;
	}
	
	/**
	 * �޸���������
	 */
	@RequestMapping("file/updateFiletype")
	public ModelAndView updateFiletype(FileType filetype,HttpSession session) {
		Users u=(Users) session.getAttribute("userInfo");
		String updateMessage="";
		ModelAndView m=new ModelAndView();
		if(fileTypeService.updateFiletype(filetype,u.getUid()))
			updateMessage="�޸ĳɹ�";
		else
			updateMessage="�޸�ʧ�ܣ��÷����Ѿ�����";
		m.addObject("updateMessage", updateMessage);
		m.setView(new MappingJackson2JsonView());
		return m;
	}
	
	/**
	 * ���͵�ɾ��
	 * @param typeid
	 * @param session
	 * @return
	 */
	@RequestMapping("file/deleteFiletype")
	@ResponseBody
	public Map<String,Object> deleteFiletype(@RequestParam("typeid") Integer typeid,HttpSession session){
		Map<String, Object> map = new HashMap<String, Object>();
		Users u=(Users) session.getAttribute("userInfo");
		//try {
			fileTypeService.deleteFiletype(typeid, u.getUid());
			map.put("message", "ɾ���ɹ������ļ���������ļ��ᱻ�ƶ���Ĭ���ļ���");
		//} catch (Exception e) {
			// TODO: handle exception
			//map.put("message", "δ֪����");
		//}
		return map;
	}
}
