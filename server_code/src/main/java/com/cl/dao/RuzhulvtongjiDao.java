package com.cl.dao;

import com.cl.entity.RuzhulvtongjiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.RuzhulvtongjiView;


/**
 * 入住率统计
 * 
 * @author 
 * @email 
 * @date 2024-03-12 18:45:46
 */
public interface RuzhulvtongjiDao extends BaseMapper<RuzhulvtongjiEntity> {
	
	List<RuzhulvtongjiView> selectListView(@Param("ew") Wrapper<RuzhulvtongjiEntity> wrapper);

	List<RuzhulvtongjiView> selectListView(Pagination page,@Param("ew") Wrapper<RuzhulvtongjiEntity> wrapper);
	
	RuzhulvtongjiView selectView(@Param("ew") Wrapper<RuzhulvtongjiEntity> wrapper);
	

    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<RuzhulvtongjiEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<RuzhulvtongjiEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<RuzhulvtongjiEntity> wrapper);



}
