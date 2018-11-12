package com.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.FileType;
import com.mapper.FileTypeMapper;
import com.mapper.FilesMapper;

/**
 * FileType的Service
 * 
 * @author 陈小锋
 *
 */
@Service
public class FileTypeService {
	@Autowired
	private FilesMapper filesmapper;
	@Autowired
	private FileTypeMapper filetypemapper;

	/**
	 * 根据typeid或者typename查一行记录,如果typeid和typename都为空则查询整张表
	 */
	public List<FileType> selectFileTypes(Integer typeid, String typename,Integer uid) {
		return filetypemapper.selectFileTypes(typeid, typename,uid);
	}
	
	/**
	 *新增类型 
	 */
	public boolean addFileType(String typename,Integer uid) {
		List<FileType> list=null;
		//判断该类型是否存在
		list=filetypemapper.selectFileTypes(null, typename,uid);
		//如果不存在，则新增
		if(0==list.size())
		{
			filetypemapper.addFileType(typename,uid);
			//刷新files的缓存
			filesmapper.reloadCache();
			return true;
		}
		//否则返回添加失败
		return false;
	}
	/**
	 * 修改类型名称
	 */
	public boolean updateFiletype(FileType filetype,Integer uid) {
		List<FileType> list=null;
		//判断该类型是否存在
		list=filetypemapper.selectFileTypes(null, filetype.getTypename(),uid);
		//如果不存在，修改
		System.out.println("条数"+list.size());
		if(0==list.size()) {
		//刷新files的缓存
		filesmapper.reloadCache();
		filetypemapper.updateFiletype(filetype);
		return true;
		}
		return false;
	}
	
	/**
	 * 删除类型
	 * 先将该类型下的文件，移动到默认类型，然后再删除该分类
	 */
	public void deleteFiletype(Integer oldtypeid,Integer uid) {
		
		//获取该用户"默认"类型的typeid
		List<FileType> f=filetypemapper.selectFileTypes(null, "默认", uid);
		//移动
		filetypemapper.moveFiles(oldtypeid, f.get(0).getTypeid());
		//删除	
		filetypemapper.deleteFiletype(oldtypeid);
	}
}
