package com.cl.dao;

import com.cl.entity.KefangqingliEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.KefangqingliView;


/**
 * 客房清理
 * 
 * @author 
 * @email 
 * @date 2024-03-12 18:45:46
 */
public interface KefangqingliDao extends BaseMapper<KefangqingliEntity> {
	
	List<KefangqingliView> selectListView(@Param("ew") Wrapper<KefangqingliEntity> wrapper);

	List<KefangqingliView> selectListView(Pagination page,@Param("ew") Wrapper<KefangqingliEntity> wrapper);
	
	KefangqingliView selectView(@Param("ew") Wrapper<KefangqingliEntity> wrapper);
	

}
