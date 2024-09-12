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

import com.cl.entity.KefangqingliEntity;
import com.cl.entity.view.KefangqingliView;

import com.cl.service.KefangqingliService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 客房清理
 * 后端接口
 * @author 
 * @email 
 * @date 2024-03-12 18:45:46
 */
@RestController
@RequestMapping("/kefangqingli")
public class KefangqingliController {
    @Autowired
    private KefangqingliService kefangqingliService;



    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,KefangqingliEntity kefangqingli,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yuangong")) {
			kefangqingli.setYuangonggonghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<KefangqingliEntity> ew = new EntityWrapper<KefangqingliEntity>();

		PageUtils page = kefangqingliService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kefangqingli), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,KefangqingliEntity kefangqingli, 
		HttpServletRequest request){
        EntityWrapper<KefangqingliEntity> ew = new EntityWrapper<KefangqingliEntity>();

		PageUtils page = kefangqingliService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kefangqingli), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( KefangqingliEntity kefangqingli){
       	EntityWrapper<KefangqingliEntity> ew = new EntityWrapper<KefangqingliEntity>();
      	ew.allEq(MPUtil.allEQMapPre( kefangqingli, "kefangqingli")); 
        return R.ok().put("data", kefangqingliService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(KefangqingliEntity kefangqingli){
        EntityWrapper< KefangqingliEntity> ew = new EntityWrapper< KefangqingliEntity>();
 		ew.allEq(MPUtil.allEQMapPre( kefangqingli, "kefangqingli")); 
		KefangqingliView kefangqingliView =  kefangqingliService.selectView(ew);
		return R.ok("查询客房清理成功").put("data", kefangqingliView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        KefangqingliEntity kefangqingli = kefangqingliService.selectById(id);
		kefangqingli = kefangqingliService.selectView(new EntityWrapper<KefangqingliEntity>().eq("id", id));
        return R.ok().put("data", kefangqingli);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        KefangqingliEntity kefangqingli = kefangqingliService.selectById(id);
		kefangqingli = kefangqingliService.selectView(new EntityWrapper<KefangqingliEntity>().eq("id", id));
        return R.ok().put("data", kefangqingli);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody KefangqingliEntity kefangqingli, HttpServletRequest request){
    	kefangqingli.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(kefangqingli);
        kefangqingliService.insert(kefangqingli);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody KefangqingliEntity kefangqingli, HttpServletRequest request){
    	kefangqingli.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(kefangqingli);
        kefangqingliService.insert(kefangqingli);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody KefangqingliEntity kefangqingli, HttpServletRequest request){
        //ValidatorUtils.validateEntity(kefangqingli);
        kefangqingliService.updateById(kefangqingli);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        kefangqingliService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	








}
