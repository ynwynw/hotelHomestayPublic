package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.DiscusskefangxinxiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.DiscusskefangxinxiView;


/**
 * 客房信息评论表
 *
 * @author 
 * @email 
 * @date 2024-03-12 18:45:46
 */
public interface DiscusskefangxinxiService extends IService<DiscusskefangxinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscusskefangxinxiView> selectListView(Wrapper<DiscusskefangxinxiEntity> wrapper);
   	
   	DiscusskefangxinxiView selectView(@Param("ew") Wrapper<DiscusskefangxinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscusskefangxinxiEntity> wrapper);
   	

}

