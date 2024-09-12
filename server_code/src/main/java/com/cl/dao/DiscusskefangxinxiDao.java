package com.cl.dao;

import com.cl.entity.DiscusskefangxinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.DiscusskefangxinxiView;


/**
 * 客房信息评论表
 * 
 * @author 
 * @email 
 * @date 2024-03-12 18:45:46
 */
public interface DiscusskefangxinxiDao extends BaseMapper<DiscusskefangxinxiEntity> {
	
	List<DiscusskefangxinxiView> selectListView(@Param("ew") Wrapper<DiscusskefangxinxiEntity> wrapper);

	List<DiscusskefangxinxiView> selectListView(Pagination page,@Param("ew") Wrapper<DiscusskefangxinxiEntity> wrapper);
	
	DiscusskefangxinxiView selectView(@Param("ew") Wrapper<DiscusskefangxinxiEntity> wrapper);
	

}
