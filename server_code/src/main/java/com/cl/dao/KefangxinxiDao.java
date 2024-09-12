package com.cl.dao;

import com.cl.entity.KefangxinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.KefangxinxiView;


/**
 * 客房信息
 * 
 * @author 
 * @email 
 * @date 2024-03-12 18:45:45
 */
public interface KefangxinxiDao extends BaseMapper<KefangxinxiEntity> {
	
	List<KefangxinxiView> selectListView(@Param("ew") Wrapper<KefangxinxiEntity> wrapper);

	List<KefangxinxiView> selectListView(Pagination page,@Param("ew") Wrapper<KefangxinxiEntity> wrapper);
	
	KefangxinxiView selectView(@Param("ew") Wrapper<KefangxinxiEntity> wrapper);
	

    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<KefangxinxiEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<KefangxinxiEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<KefangxinxiEntity> wrapper);



}
