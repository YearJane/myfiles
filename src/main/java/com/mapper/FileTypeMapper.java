package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.FileType;

/**
 * filetype的mapper
 * @author 陈小锋
 *
 */
public interface FileTypeMapper {
	/**
	 * 根据typeid或者typename查一行记录,如果typeid和typename都为空则查询整张表
	 */
	public List<FileType> selectFileTypes(@Param("typeid") Integer typeid,@Param("typename") String typename,@Param("uid") Integer uid);
	
	/**
	 * 新增类型
	 */
	public void addFileType(@Param("typename") String typename,@Param("uid") Integer uid);
	
	/**
	 * 修改类型名称
	 */
	public void updateFiletype(@Param("filetype") FileType filetype);
	
	/**
	 * 删除分类,该类的文件会被移入到默认分类下面
	 */
	public void moveFiles(@Param("oldtypeid") Integer oldtypeid,@Param("newtypeid") Integer newtypeid);
	
	/**
	 * 删除类型
	 */
	public void deleteFiletype(@Param("typeid") Integer typeid);
	
	/**
	 * 根据typeid查Filetype
	 */
	public FileType selectFiletypeBytypeid(@Param("typeid") Integer typeid);
}
