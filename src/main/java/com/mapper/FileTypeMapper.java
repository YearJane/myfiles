package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.FileType;

/**
 * filetype��mapper
 * @author ��С��
 *
 */
public interface FileTypeMapper {
	/**
	 * ����typeid����typename��һ�м�¼,���typeid��typename��Ϊ�����ѯ���ű�
	 */
	public List<FileType> selectFileTypes(@Param("typeid") Integer typeid,@Param("typename") String typename,@Param("uid") Integer uid);
	
	/**
	 * ��������
	 */
	public void addFileType(@Param("typename") String typename,@Param("uid") Integer uid);
	
	/**
	 * �޸���������
	 */
	public void updateFiletype(@Param("filetype") FileType filetype);
	
	/**
	 * ɾ������,������ļ��ᱻ���뵽Ĭ�Ϸ�������
	 */
	public void moveFiles(@Param("oldtypeid") Integer oldtypeid,@Param("newtypeid") Integer newtypeid);
	
	/**
	 * ɾ������
	 */
	public void deleteFiletype(@Param("typeid") Integer typeid);
	
	/**
	 * ����typeid��Filetype
	 */
	public FileType selectFiletypeBytypeid(@Param("typeid") Integer typeid);
}
