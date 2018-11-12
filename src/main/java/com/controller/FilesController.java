package com.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.entity.FileType;
import com.entity.Files;
import com.entity.Pages;
import com.entity.Users;
import com.service.FileTypeService;
import com.service.FilesService;

@Controller
@RequestMapping
public class FilesController {
	@Autowired
	private FilesService fileservice;
	@Autowired
	private FileTypeService fileTypeService;

	/**
	 * �ļ��ϴ�
	 */
	@RequestMapping("file/fileUpload")
	public ModelAndView fileUpload(@RequestParam("file") MultipartFile file,
			@RequestParam(value="weight",required=false,defaultValue="0") Integer weight,
			@RequestParam("typename") String typename,
			@RequestParam(value="introduce",required=false,defaultValue="") String introduce
			,HttpSession session) {
		ModelAndView m = new ModelAndView();
		Users u = (Users) session.getAttribute("userInfo");
		if (!file.isEmpty()) {
			try {
				int index = file.getOriginalFilename().indexOf(".");
				String reallyname = file.getOriginalFilename().substring(0, index);
				/**
				 * 
				 */
				long time=System.currentTimeMillis();
				FileUtils.copyInputStreamToFile(file.getInputStream(),
						new File(u.getFilespath(), time + file.getOriginalFilename()));
				fileservice.FileUpload(u.getUid(), reallyname, time + file.getOriginalFilename(),
						typename, weight, introduce);
				/**
				 * 
				 */
			} catch (IOException e) {
				// TODO Auto-generated catch block
				m.addObject("message", "�ϴ�ʧ��");
				System.out.println(e.getMessage());
			}

			m.addObject("message", "�ϴ��ɹ�");

		}
		m.setView(new MappingJackson2JsonView());
		return m;
	}

	/**
	 * �ļ�����
	 */
	@RequestMapping("file/download")
	public ResponseEntity<byte[]> fileDownLoad(String fakename,
			HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		ResponseEntity<byte[]> re = null;
		try {
			/**
			 * css,js,json,gif,png,bmp,jpg,ico,doc,docx,xls,xlsx,txt,swf,pdf
			 **/
			Users u = (Users) session.getAttribute("userInfo");
			String pathString = u.getFilespath() + "\\" + fakename;
			System.out.println(pathString);
			File file = new File(pathString);
			HttpHeaders headers = new HttpHeaders();
			// String filename=URLEncoder.encode(name, "UTF-8");//Ϊ�˽������������������
			String filename = new String(fakename.getBytes("utf-8"), "utf-8");
			byte[] by = FileUtils.readFileToByteArray(file);
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			// URLEncoder.encode(filename, "UTF-8")
			headers.setContentDispositionFormData("attachment", filename);
			headers.setContentLength(by.length);
			re = new ResponseEntity<byte[]>(by, headers, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				request.getRequestDispatcher("/error/404.jsp").forward(request, response);
			} catch (ServletException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return re;
	}

	/**
	 * ����fid��ѯ�ļ�����
	 */
	@RequestMapping("file/selectFile")
	@ResponseBody
	public Map<String, Object> selectFile(@RequestParam("fid") Integer fid) {
		Map<String, Object> map = new HashMap<String, Object>();
		Files f = fileservice.selectFile(fid);
		map.put("file", f);
		return map;
	}

	/**
	 * �ļ���ɾ�����������վ
	 */
	@RequestMapping("file/fileDelete")
	@ResponseBody
	public Map<String, Object> fileDelete(@RequestBody List<Integer> fids,HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			System.out.println(fids);
			//������ɾ����¼
			fileservice.fileDelete(fids);
			map.put("deleteMessage", "ɾ���ɹ�");
		} catch (Exception e) {
			map.put("deleteMessage", "ɾ��ʧ�ܣ�δ֪�Ĵ���");
		}
		return map;
	}

	/**
	 * ɾ���ļ�������ɾ�������fidsΪ�գ�����ջ���վ
	 * 
	 * @param fids
	 * @return
	 */
	@RequestMapping("file/fileDeleteReally")
	@ResponseBody
	public Map<String, Object> fileDeleteReally(@RequestBody List<Integer> fids,
			HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(fids);
		Users u = (Users) session.getAttribute("userInfo");
		try {
			List<Files> list=fileservice.selectFilesByfids(fids);
			List<String> fakenames=new ArrayList<String>();
			for(int i=0;i<fids.size();i++)
			{
				fakenames.add(list.get(i).getFakename());
			}
			//ɾ���ļ����е�����
			deleteFile(u.getFilespath(),fakenames);
			//ɾ�����ݿ��еļ�¼
			fileservice.fileDeleteReally(fids, u.getUid());
			map.put("deleteMessage", "ɾ���ɹ�");
		} catch (Exception e) {
			map.put("deleteMessage", "ɾ��ʧ�ܣ�δ֪�Ĵ���");
		}
		return map;
	}

	/**
	 * �������Ͳ�ѯ����ҳ
	 */
	@RequestMapping("file/selectFilesBytype")
	@ResponseBody
	public Map<String, Object> selectFilesBytypename(@RequestParam("currentpage") Integer currentpage,
			@RequestParam("typename") String typename, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		Users u = (Users) session.getAttribute("userInfo");

		Pages page = fileservice.selectFilesByTypename(currentpage, u.getUid(), typename);
		map.put("page", page);

		// map.put("message", "δ֪����");

		return map;
	}

	/**
	 * �״���Ⱦ�ҵ���ҳ
	 */
	@RequestMapping("file/selectFiles")
	@ResponseBody
	public Map<String, Object> selectFiles(@RequestParam("currentpage") Integer currentpage, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		Users u = (Users) session.getAttribute("userInfo");
		Pages page = fileservice.selectFileByWeight(currentpage, u.getUid());
		List<FileType> filetypes=fileTypeService.selectFileTypes(null, null, u.getUid());
		map.put("page", page);
		map.put("filetypes", filetypes);
		return map;
	}
	
	/**
	 * ģ����ѯ�ļ�
	 */
	@RequestMapping("file/selectFileByVague")
	@ResponseBody
	public Map<String, Object> selectFileByVague(@RequestParam("currentpage") Integer currentpage,
			@RequestParam("vague") String vague, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		Users u = (Users) session.getAttribute("userInfo");
		Pages page = fileservice.selectFileByVague(currentpage, u.getUid(), vague);
		System.out.println(page);
		map.put("page", page);
		return map;
	}
	/**
	 * �����ļ���Ϣ
	 */
	@RequestMapping("file/updateFile")
	@ResponseBody
	public Map<String,Object> updateFile(@RequestParam("typename") String typename,
			@RequestParam("weight") Integer weight,
			@RequestParam(value="introduce",required=false) String introduce,
			@RequestParam("fid") Integer fid,
			HttpSession session){
		Map<String, Object> map = new HashMap<String, Object>();
		Users u = (Users) session.getAttribute("userInfo");
		
		try {
			fileservice.updateFile(fid, typename, introduce,weight,u.getUid());
			map.put("message", "���³ɹ�");
		} catch (Exception e) {
			// TODO: handle exception
			map.put("message", "����ʧ��");
		}
		return map;
	}
	
	/**
	 * ����վ�Ļָ�����
	 */
	@RequestMapping("file/resumeRecyle")
	@ResponseBody
	public Map<String,Object> resumeRecyle(@RequestBody List<Integer> fids){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			fileservice.resumeRecyle(fids);
			map.put("message", "�ָ��ɹ�,�����ҵ��ļ��в鿴");
		} catch (Exception e) {
			// TODO: handle exception
			map.put("message", "δ֪����");
		}
		return map;
	}
	
	/**
	 * �û�����վ����Ⱦ
	 */
	@RequestMapping("file/selectRecycleFiles")
	@ResponseBody
	public Map<String, Object> selectRecycleFiles(@RequestParam("currentpage") Integer currentpage, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		Users u = (Users) session.getAttribute("userInfo");
		Pages page = fileservice.selectFileRecycle(currentpage, u.getUid());
		map.put("page", page);
		return map;
	}
	/**
	 * ɾ���ļ����е��ļ�
	 * @param path
	 * @param fakename
	 */
	private static void deleteFile(String path,List<String> fakenames) {
		for(int i=0;i<fakenames.size();i++) {
		File file=new File(path+"\\"+fakenames.get(i));
		System.out.println(file.exists()&&file.isFile());
		if(file.exists()&&file.isFile())
			file.delete();
		}
	}
}
