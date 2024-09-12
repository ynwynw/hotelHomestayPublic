package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.KefangxinxiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.KefangxinxiView;


/**
 * 客房信息
 *
 * @author 
 * @email 
 * @date 2024-03-12 18:45:45
 */
public interface KefangxinxiService extends IService<KefangxinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<KefangxinxiView> selectListView(Wrapper<KefangxinxiEntity> wrapper);
   	
   	KefangxinxiView selectView(@Param("ew") Wrapper<KefangxinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<KefangxinxiEntity> wrapper);
   	

    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<KefangxinxiEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<KefangxinxiEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<KefangxinxiEntity> wrapper);



}

