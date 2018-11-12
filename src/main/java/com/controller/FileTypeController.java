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
 * fileType的控制器
 * @author 陈小锋
 *
 */
@Controller
@RequestMapping
public class FileTypeController {
	@Autowired
	private FileTypeService fileTypeService;
	/**
	  * 根据typeid或者typename查一行记录,如果typeid和typename都为空则查询整张表
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
	 * 添加类型
	 */
	@RequestMapping("file/addFiletype")
	public ModelAndView addFileType(@RequestParam("typename") String typename,HttpSession session) {
		Users u=(Users) session.getAttribute("userInfo");
		ModelAndView m=new ModelAndView();
		String addMessage="";
		if(fileTypeService.addFileType(typename,u.getUid()))
			addMessage="添加成功";
		else
			addMessage="添加失败";
		m.addObject("addMessage", addMessage);
		m.setView(new MappingJackson2JsonView());
		return m;
	}
	
	/**
	 * 修改类型名称
	 */
	@RequestMapping("file/updateFiletype")
	public ModelAndView updateFiletype(FileType filetype,HttpSession session) {
		Users u=(Users) session.getAttribute("userInfo");
		String updateMessage="";
		ModelAndView m=new ModelAndView();
		if(fileTypeService.updateFiletype(filetype,u.getUid()))
			updateMessage="修改成功";
		else
			updateMessage="修改失败，该分类已经存在";
		m.addObject("updateMessage", updateMessage);
		m.setView(new MappingJackson2JsonView());
		return m;
	}
	
	/**
	 * 类型的删除
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
			map.put("message", "删除成功，该文件夹下面的文件会被移动到默认文件夹");
		//} catch (Exception e) {
			// TODO: handle exception
			//map.put("message", "未知错误");
		//}
		return map;
	}
}
