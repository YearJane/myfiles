package com.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.FileType;
import com.entity.Files;
import com.entity.Pages;
import com.mapper.FileTypeMapper;
import com.mapper.FilesMapper;

/**
 * 文件的Service
 * @author 陈小锋
 *
 */
@Service
public class FilesService {
	//private static final Integer pagesize=1;
	@Autowired
	private FilesMapper filesmapper;
	@Autowired
	private FileTypeMapper fileTypeMapper;
  /**
   *单文件上传
   */
   public void FileUpload(Integer uid,String reallyname,String fakename,String typename,
		   Integer weight,String introduce) {
	   //创建文件对象
	   Files files=new Files();
	   //根据typename查询该文件的typeid
	   List<FileType> list=fileTypeMapper.selectFileTypes(null, typename,uid);
	   FileType filetype=list.get(0);
	   
	   SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm");
	   files.setFakename(fakename);
	   files.setFiletype(filetype);
	   files.setIntime(f.format(new Date()));
	   files.setIntroduce(introduce);
	   files.setReallyname(reallyname);
	   files.setWeight(weight);
	   files.setUid(uid);
	   
	   filesmapper.fileUpload(files);
	   
   }
   
   
   /**
    * 根据fid查询文件信息
    */
   public Files selectFile(Integer fid) {
	   Files f=filesmapper.selectFile(fid);
	   /**
	    * 隐私内容需要屏蔽，待完善
	    */
	   return f;
   }
   
   /**
    * 文件的删除，移入回收站，可进行批量删除
    */
   public void fileDelete(List<Integer> fids) {
	   filesmapper.fileDelete(fids);
   }
   
   /**
    * 文件的删除，彻底删除，如果fids为null 则清空回收站
    */
   public void fileDeleteReally(List<Integer> fids,Integer uid) {
	   filesmapper.fileDeleteReally(fids,uid);
   }
   
   /**
    * 分页查询，根据文件类型查询
    */
   public Pages selectFilesByTypename(Integer currentpage,Integer uid,String typename) {
	   Pages pages=new Pages();
	   //根据typename查询该文件的typeid
	   List<FileType> list=fileTypeMapper.selectFileTypes(null, typename,uid);
	   FileType filetype=list.get(0);
	   //查询用户的总记录条数
	   Integer totalrecords=filesmapper.selectTotalFiles(uid,filetype.getTypeid());
	   //起始索引
	   Integer fontindex=(currentpage-1)*totalrecords;
	   //总页数
	   Integer numpages=0;
	   List<Files> list1=null;
	   list1=filesmapper.selectFileBytype(fontindex, totalrecords, uid, filetype.getTypeid());
	   System.out.println(list1.size());
	   pages.setCurrentpage(currentpage);
	   pages.setFontindex(fontindex);
	   pages.setLastindex(0);
	   if(0==list1.size()){
	   pages.setList(null);
	   }
	   pages.setList(list1);
	   pages.setNumpages(numpages);
	   pages.setPagesize(totalrecords);
	   pages.setTotalrecords(totalrecords);
	   
	   return pages;
   }
   
   /**
    * 首次进入我的文件，依照权重排列
    */
   public Pages selectFileByWeight(Integer currentpage,Integer uid) {
	   Pages pages=new Pages();
	   //查询用户的总记录条数
	   Integer totalrecords=filesmapper.selectTotalFiles(uid,null);
	   //起始索引
	   Integer fontindex=(currentpage-1)*totalrecords;
	   //总页数
	   Integer numpages=0;
	   //查询该页的内容
	   List<Files> list1=null;
	   list1=filesmapper.selectFileByWeight(fontindex, totalrecords, uid);
	   System.out.println(list1.size());
	   pages.setCurrentpage(currentpage);
	   pages.setFontindex(fontindex);
	   pages.setLastindex(0);
	   if(0==list1.size()){
	   pages.setList(null);
	   }
	   pages.setList(list1);
	   pages.setNumpages(numpages);
	   pages.setPagesize(totalrecords);
	   pages.setTotalrecords(totalrecords);
	   
	   return pages;
   }
   
   /**
    * 模糊查询，分页
    */
   public Pages selectFileByVague (Integer currentpage,Integer uid,String vague) {
	   Pages pages=new Pages();
	   //查询用户的总记录条数
	   Integer totalrecords=filesmapper.selectTotalFiles1(uid,vague);
	   //起始索引
	   Integer fontindex=(currentpage-1)*totalrecords;
	   //总页数
	   Integer numpages=0;
	   List<Files> list1=null;
	   list1=filesmapper.selectFileByVague(fontindex, totalrecords, uid, vague);
	   System.out.println(list1.size());
	   pages.setCurrentpage(currentpage);
	   pages.setFontindex(fontindex);
	   pages.setLastindex(0);
	   if(0==list1.size()){
	   pages.setList(null);
	   }
	   pages.setList(list1);
	   pages.setNumpages(numpages);
	   pages.setPagesize(totalrecords);
	   pages.setTotalrecords(totalrecords);
	   
	   return pages;
   }
   
   /**
    * 回收站的恢复功能
    */
   public void resumeRecyle(List<Integer> fids) {
	   filesmapper.resumeRecyle(fids);
   }
   /**
    * 渲染回收站
    */
   public Pages selectFileRecycle(Integer currentpage,Integer uid) {
	   Pages pages=new Pages();
	   //查询用户回收站的总记录条数
	   Integer totalrecords=filesmapper.selectTotalFilesRecycle(uid);
	   //起始索引
	   Integer fontindex=(currentpage-1)*totalrecords;
	   //总页数
	   Integer numpages=0;
	   //查询该页的内容
	   List<Files> list1=null;
	   list1=filesmapper.selectFileRecycle(fontindex, totalrecords, uid);
	   System.out.println(list1.size());
	   pages.setCurrentpage(currentpage);
	   pages.setFontindex(fontindex);
	   pages.setLastindex(0);
	   if(0==list1.size()){
	   pages.setList(null);
	   }
	   pages.setList(list1);
	   pages.setNumpages(numpages);
	   pages.setPagesize(totalrecords);
	   pages.setTotalrecords(totalrecords);
	   
	   return pages;
   }

	/**
	 * 批量查询fids的Files
	 */
   public List<Files> selectFilesByfids(List<Integer> fids){
	   
	   return filesmapper.selectFilesByfids(fids);
   }
   /**
    * 更新文件信息
    */
   public void updateFile(Integer fid,String typename,String introduce,Integer weight,Integer uid) {
	   Files f=new Files();
	   //根据typename查询该文件的typeid
	   List<FileType> list=fileTypeMapper.selectFileTypes(null, typename,uid);
	   FileType filetype=list.get(0);
	   filetype.setTypeid(filetype.getTypeid());
	   f.setFid(fid);
	   f.setFiletype(filetype);
	   f.setIntroduce(introduce);
	   f.setWeight(weight);
	   filesmapper.updateFile(f);
   }
}
