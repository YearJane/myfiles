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
 * �ļ���Service
 * @author ��С��
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
   *���ļ��ϴ�
   */
   public void FileUpload(Integer uid,String reallyname,String fakename,String typename,
		   Integer weight,String introduce) {
	   //�����ļ�����
	   Files files=new Files();
	   //����typename��ѯ���ļ���typeid
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
    * ����fid��ѯ�ļ���Ϣ
    */
   public Files selectFile(Integer fid) {
	   Files f=filesmapper.selectFile(fid);
	   /**
	    * ��˽������Ҫ���Σ�������
	    */
	   return f;
   }
   
   /**
    * �ļ���ɾ�����������վ���ɽ�������ɾ��
    */
   public void fileDelete(List<Integer> fids) {
	   filesmapper.fileDelete(fids);
   }
   
   /**
    * �ļ���ɾ��������ɾ�������fidsΪnull ����ջ���վ
    */
   public void fileDeleteReally(List<Integer> fids,Integer uid) {
	   filesmapper.fileDeleteReally(fids,uid);
   }
   
   /**
    * ��ҳ��ѯ�������ļ����Ͳ�ѯ
    */
   public Pages selectFilesByTypename(Integer currentpage,Integer uid,String typename) {
	   Pages pages=new Pages();
	   //����typename��ѯ���ļ���typeid
	   List<FileType> list=fileTypeMapper.selectFileTypes(null, typename,uid);
	   FileType filetype=list.get(0);
	   //��ѯ�û����ܼ�¼����
	   Integer totalrecords=filesmapper.selectTotalFiles(uid,filetype.getTypeid());
	   //��ʼ����
	   Integer fontindex=(currentpage-1)*totalrecords;
	   //��ҳ��
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
    * �״ν����ҵ��ļ�������Ȩ������
    */
   public Pages selectFileByWeight(Integer currentpage,Integer uid) {
	   Pages pages=new Pages();
	   //��ѯ�û����ܼ�¼����
	   Integer totalrecords=filesmapper.selectTotalFiles(uid,null);
	   //��ʼ����
	   Integer fontindex=(currentpage-1)*totalrecords;
	   //��ҳ��
	   Integer numpages=0;
	   //��ѯ��ҳ������
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
    * ģ����ѯ����ҳ
    */
   public Pages selectFileByVague (Integer currentpage,Integer uid,String vague) {
	   Pages pages=new Pages();
	   //��ѯ�û����ܼ�¼����
	   Integer totalrecords=filesmapper.selectTotalFiles1(uid,vague);
	   //��ʼ����
	   Integer fontindex=(currentpage-1)*totalrecords;
	   //��ҳ��
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
    * ����վ�Ļָ�����
    */
   public void resumeRecyle(List<Integer> fids) {
	   filesmapper.resumeRecyle(fids);
   }
   /**
    * ��Ⱦ����վ
    */
   public Pages selectFileRecycle(Integer currentpage,Integer uid) {
	   Pages pages=new Pages();
	   //��ѯ�û�����վ���ܼ�¼����
	   Integer totalrecords=filesmapper.selectTotalFilesRecycle(uid);
	   //��ʼ����
	   Integer fontindex=(currentpage-1)*totalrecords;
	   //��ҳ��
	   Integer numpages=0;
	   //��ѯ��ҳ������
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
	 * ������ѯfids��Files
	 */
   public List<Files> selectFilesByfids(List<Integer> fids){
	   
	   return filesmapper.selectFilesByfids(fids);
   }
   /**
    * �����ļ���Ϣ
    */
   public void updateFile(Integer fid,String typename,String introduce,Integer weight,Integer uid) {
	   Files f=new Files();
	   //����typename��ѯ���ļ���typeid
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
