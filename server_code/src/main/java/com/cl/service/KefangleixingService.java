package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.KefangleixingEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.KefangleixingView;


/**
 * 客房类型
 *
 * @author 
 * @email 
 * @date 2024-03-12 18:45:45
 */
public interface KefangleixingService extends IService<KefangleixingEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<KefangleixingView> selectListView(Wrapper<KefangleixingEntity> wrapper);
   	
   	KefangleixingView selectView(@Param("ew") Wrapper<KefangleixingEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<KefangleixingEntity> wrapper);
   	

}

