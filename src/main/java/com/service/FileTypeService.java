package com.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.FileType;
import com.mapper.FileTypeMapper;
import com.mapper.FilesMapper;

/**
 * FileType��Service
 * 
 * @author ��С��
 *
 */
@Service
public class FileTypeService {
	@Autowired
	private FilesMapper filesmapper;
	@Autowired
	private FileTypeMapper filetypemapper;

	/**
	 * ����typeid����typename��һ�м�¼,���typeid��typename��Ϊ�����ѯ���ű�
	 */
	public List<FileType> selectFileTypes(Integer typeid, String typename,Integer uid) {
		return filetypemapper.selectFileTypes(typeid, typename,uid);
	}
	
	/**
	 *�������� 
	 */
	public boolean addFileType(String typename,Integer uid) {
		List<FileType> list=null;
		//�жϸ������Ƿ����
		list=filetypemapper.selectFileTypes(null, typename,uid);
		//��������ڣ�������
		if(0==list.size())
		{
			filetypemapper.addFileType(typename,uid);
			//ˢ��files�Ļ���
			filesmapper.reloadCache();
			return true;
		}
		//���򷵻����ʧ��
		return false;
	}
	/**
	 * �޸���������
	 */
	public boolean updateFiletype(FileType filetype,Integer uid) {
		List<FileType> list=null;
		//�жϸ������Ƿ����
		list=filetypemapper.selectFileTypes(null, filetype.getTypename(),uid);
		//��������ڣ��޸�
		System.out.println("����"+list.size());
		if(0==list.size()) {
		//ˢ��files�Ļ���
		filesmapper.reloadCache();
		filetypemapper.updateFiletype(filetype);
		return true;
		}
		return false;
	}
	
	/**
	 * ɾ������
	 * �Ƚ��������µ��ļ����ƶ���Ĭ�����ͣ�Ȼ����ɾ���÷���
	 */
	public void deleteFiletype(Integer oldtypeid,Integer uid) {
		
		//��ȡ���û�"Ĭ��"���͵�typeid
		List<FileType> f=filetypemapper.selectFileTypes(null, "Ĭ��", uid);
		//�ƶ�
		filetypemapper.moveFiles(oldtypeid, f.get(0).getTypeid());
		//ɾ��	
		filetypemapper.deleteFiletype(oldtypeid);
	}
}
