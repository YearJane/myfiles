package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.Files;

/**
 * �ļ���mapper
 * 
 * @author ��С��
 *
 */
public interface FilesMapper {
	/**
	 * �ļ����ϴ�
	 */
	public void fileUpload(@Param("file") Files file);

	/**
	 * �����ļ���id��ѯ�ļ�����
	 */
	public Files selectFile(@Param("fid") Integer fid);

	/**
	 * �ļ���ɾ��,��ɾ��������������ɾ�������ǽ��ļ��������վ
	 */
	public void fileDelete(@Param("fids") List<Integer> fids);

	/**
	 * �ļ���ɾ����������ɾ�����ļ����޷��ָ�
	 */
	public void fileDeleteReally(@Param("fids") List<Integer> fids, @Param("uid") Integer uid);
	
	/**
	 * �������Ͳ��ļ�����ҳ
	 */
	public List<Files> selectFileBytype(@Param("fontindex")Integer fontindex,@Param("pagesize") Integer pagesize,
			@Param("uid") Integer uid,@Param("typeid") Integer typeid);
	
	/**
	 * ��ѯ�û����ܼ�¼����
	 */
	public Integer selectTotalFiles(@Param("uid") Integer uid,@Param("typeid") Integer typeid);
	
	/**
	 * ��ѯ�û����ܼ�¼����
	 */
	public Integer selectTotalFiles1(@Param("uid") Integer uid,@Param("vague") String vague);
	
	/**
	 * �״���Ⱦ�ҵ��ļ�ҳ��,��ҳ������Ȩ������
	 */
	public List<Files> selectFileByWeight(@Param("fontindex")Integer fontindex,@Param("pagesize") Integer pagesize,
			@Param("uid") Integer uid);
	
	/**
	 * ������������ģ����ѯ����ҳ
	 */
	public List<Files> selectFileByVague(@Param("fontindex")Integer fontindex,@Param("pagesize") Integer pagesize,
			@Param("uid") Integer uid,@Param("vague") String vague);
	
	/**
	 * ����վ�ļ��ָ�����
	 */
	public void resumeRecyle(@Param("fids") List<Integer> fids) ;
	
	
	/**
	 * ��Ⱦ����վ ��ҳ������Ȩ������
	 */
	public List<Files> selectFileRecycle(@Param("fontindex")Integer fontindex,@Param("pagesize") Integer pagesize,
			@Param("uid") Integer uid);
	
	/**
	 * ��ѯ�û�����վ��¼��
	 */
	public Integer selectTotalFilesRecycle(@Param("uid") Integer uid);
	/**
	 * �����ļ���Ϣ
	 */
	public void updateFile(@Param("file") Files file);
	
	/**
	 * ������ѯfids��Files
	 */
	public List<Files> selectFilesByfids(@Param("fids") List<Integer> fids);
	
	/**
	 * �ô�ˢ�¶�������
	 */
	public void reloadCache();
	
	

}
