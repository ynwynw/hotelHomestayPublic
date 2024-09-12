package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.KefangqingliEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.KefangqingliView;


/**
 * 客房清理
 *
 * @author 
 * @email 
 * @date 2024-03-12 18:45:46
 */
public interface KefangqingliService extends IService<KefangqingliEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<KefangqingliView> selectListView(Wrapper<KefangqingliEntity> wrapper);
   	
   	KefangqingliView selectView(@Param("ew") Wrapper<KefangqingliEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<KefangqingliEntity> wrapper);
   	

}

