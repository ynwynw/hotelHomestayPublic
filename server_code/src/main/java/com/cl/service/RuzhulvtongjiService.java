package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.RuzhulvtongjiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.RuzhulvtongjiView;


/**
 * 入住率统计
 *
 * @author 
 * @email 
 * @date 2024-03-12 18:45:46
 */
public interface RuzhulvtongjiService extends IService<RuzhulvtongjiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<RuzhulvtongjiView> selectListView(Wrapper<RuzhulvtongjiEntity> wrapper);
   	
   	RuzhulvtongjiView selectView(@Param("ew") Wrapper<RuzhulvtongjiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<RuzhulvtongjiEntity> wrapper);
   	

    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<RuzhulvtongjiEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<RuzhulvtongjiEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<RuzhulvtongjiEntity> wrapper);



}

