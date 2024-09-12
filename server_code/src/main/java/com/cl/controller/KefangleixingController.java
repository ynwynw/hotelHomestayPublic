package com.cl.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.cl.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cl.annotation.IgnoreAuth;

import com.cl.entity.KefangleixingEntity;
import com.cl.entity.view.KefangleixingView;

import com.cl.service.KefangleixingService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 客房类型
 * 后端接口
 * @author 
 * @email 
 * @date 2024-03-12 18:45:45
 */
@RestController
@RequestMapping("/kefangleixing")
public class KefangleixingController {
    @Autowired
    private KefangleixingService kefangleixingService;



    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,KefangleixingEntity kefangleixing,
		HttpServletRequest request){
        EntityWrapper<KefangleixingEntity> ew = new EntityWrapper<KefangleixingEntity>();

		PageUtils page = kefangleixingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kefangleixing), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,KefangleixingEntity kefangleixing, 
		HttpServletRequest request){
        EntityWrapper<KefangleixingEntity> ew = new EntityWrapper<KefangleixingEntity>();

		PageUtils page = kefangleixingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kefangleixing), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( KefangleixingEntity kefangleixing){
       	EntityWrapper<KefangleixingEntity> ew = new EntityWrapper<KefangleixingEntity>();
      	ew.allEq(MPUtil.allEQMapPre( kefangleixing, "kefangleixing")); 
        return R.ok().put("data", kefangleixingService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(KefangleixingEntity kefangleixing){
        EntityWrapper< KefangleixingEntity> ew = new EntityWrapper< KefangleixingEntity>();
 		ew.allEq(MPUtil.allEQMapPre( kefangleixing, "kefangleixing")); 
		KefangleixingView kefangleixingView =  kefangleixingService.selectView(ew);
		return R.ok("查询客房类型成功").put("data", kefangleixingView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        KefangleixingEntity kefangleixing = kefangleixingService.selectById(id);
		kefangleixing = kefangleixingService.selectView(new EntityWrapper<KefangleixingEntity>().eq("id", id));
        return R.ok().put("data", kefangleixing);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        KefangleixingEntity kefangleixing = kefangleixingService.selectById(id);
		kefangleixing = kefangleixingService.selectView(new EntityWrapper<KefangleixingEntity>().eq("id", id));
        return R.ok().put("data", kefangleixing);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody KefangleixingEntity kefangleixing, HttpServletRequest request){
    	kefangleixing.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(kefangleixing);
        kefangleixingService.insert(kefangleixing);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody KefangleixingEntity kefangleixing, HttpServletRequest request){
    	kefangleixing.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(kefangleixing);
        kefangleixingService.insert(kefangleixing);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody KefangleixingEntity kefangleixing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(kefangleixing);
        kefangleixingService.updateById(kefangleixing);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        kefangleixingService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	








}
