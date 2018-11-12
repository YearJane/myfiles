package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.Files;

/**
 * 文件的mapper
 * 
 * @author 陈小锋
 *
 */
public interface FilesMapper {
	/**
	 * 文件的上传
	 */
	public void fileUpload(@Param("file") Files file);

	/**
	 * 根据文件的id查询文件内容
	 */
	public Files selectFile(@Param("fid") Integer fid);

	/**
	 * 文件的删除,该删除并不是真正的删除，而是将文件移入回收站
	 */
	public void fileDelete(@Param("fids") List<Integer> fids);

	/**
	 * 文件的删除，真正的删除该文件，无法恢复
	 */
	public void fileDeleteReally(@Param("fids") List<Integer> fids, @Param("uid") Integer uid);
	
	/**
	 * 根据类型查文件，分页
	 */
	public List<Files> selectFileBytype(@Param("fontindex")Integer fontindex,@Param("pagesize") Integer pagesize,
			@Param("uid") Integer uid,@Param("typeid") Integer typeid);
	
	/**
	 * 查询用户的总记录条数
	 */
	public Integer selectTotalFiles(@Param("uid") Integer uid,@Param("typeid") Integer typeid);
	
	/**
	 * 查询用户的总记录条数
	 */
	public Integer selectTotalFiles1(@Param("uid") Integer uid,@Param("vague") String vague);
	
	/**
	 * 首次渲染我的文件页面,分页，根据权重排序
	 */
	public List<Files> selectFileByWeight(@Param("fontindex")Integer fontindex,@Param("pagesize") Integer pagesize,
			@Param("uid") Integer uid);
	
	/**
	 * 根据搜索栏的模糊查询，分页
	 */
	public List<Files> selectFileByVague(@Param("fontindex")Integer fontindex,@Param("pagesize") Integer pagesize,
			@Param("uid") Integer uid,@Param("vague") String vague);
	
	/**
	 * 回收站文件恢复功能
	 */
	public void resumeRecyle(@Param("fids") List<Integer> fids) ;
	
	
	/**
	 * 渲染回收站 分页，根据权重排序
	 */
	public List<Files> selectFileRecycle(@Param("fontindex")Integer fontindex,@Param("pagesize") Integer pagesize,
			@Param("uid") Integer uid);
	
	/**
	 * 查询用户回收站记录数
	 */
	public Integer selectTotalFilesRecycle(@Param("uid") Integer uid);
	/**
	 * 更新文件信息
	 */
	public void updateFile(@Param("file") Files file);
	
	/**
	 * 批量查询fids的Files
	 */
	public List<Files> selectFilesByfids(@Param("fids") List<Integer> fids);
	
	/**
	 * 用次刷新二级缓存
	 */
	public void reloadCache();
	
	

}
