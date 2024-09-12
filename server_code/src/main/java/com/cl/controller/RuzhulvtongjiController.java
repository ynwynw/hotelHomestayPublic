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

import com.cl.entity.RuzhulvtongjiEntity;
import com.cl.entity.view.RuzhulvtongjiView;

import com.cl.service.RuzhulvtongjiService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 入住率统计
 * 后端接口
 * @author 
 * @email 
 * @date 2024-03-12 18:45:46
 */
@RestController
@RequestMapping("/ruzhulvtongji")
public class RuzhulvtongjiController {
    @Autowired
    private RuzhulvtongjiService ruzhulvtongjiService;



    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,RuzhulvtongjiEntity ruzhulvtongji,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date riqistart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date riqiend,
		HttpServletRequest request){
        EntityWrapper<RuzhulvtongjiEntity> ew = new EntityWrapper<RuzhulvtongjiEntity>();
                if(riqistart!=null) ew.ge("riqi", riqistart);
                if(riqiend!=null) ew.le("riqi", riqiend);

		PageUtils page = ruzhulvtongjiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, ruzhulvtongji), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,RuzhulvtongjiEntity ruzhulvtongji, 
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date riqistart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date riqiend,
		HttpServletRequest request){
        EntityWrapper<RuzhulvtongjiEntity> ew = new EntityWrapper<RuzhulvtongjiEntity>();
                if(riqistart!=null) ew.ge("riqi", riqistart);
                if(riqiend!=null) ew.le("riqi", riqiend);

		PageUtils page = ruzhulvtongjiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, ruzhulvtongji), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( RuzhulvtongjiEntity ruzhulvtongji){
       	EntityWrapper<RuzhulvtongjiEntity> ew = new EntityWrapper<RuzhulvtongjiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( ruzhulvtongji, "ruzhulvtongji")); 
        return R.ok().put("data", ruzhulvtongjiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(RuzhulvtongjiEntity ruzhulvtongji){
        EntityWrapper< RuzhulvtongjiEntity> ew = new EntityWrapper< RuzhulvtongjiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( ruzhulvtongji, "ruzhulvtongji")); 
		RuzhulvtongjiView ruzhulvtongjiView =  ruzhulvtongjiService.selectView(ew);
		return R.ok("查询入住率统计成功").put("data", ruzhulvtongjiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        RuzhulvtongjiEntity ruzhulvtongji = ruzhulvtongjiService.selectById(id);
		ruzhulvtongji = ruzhulvtongjiService.selectView(new EntityWrapper<RuzhulvtongjiEntity>().eq("id", id));
        return R.ok().put("data", ruzhulvtongji);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        RuzhulvtongjiEntity ruzhulvtongji = ruzhulvtongjiService.selectById(id);
		ruzhulvtongji = ruzhulvtongjiService.selectView(new EntityWrapper<RuzhulvtongjiEntity>().eq("id", id));
        return R.ok().put("data", ruzhulvtongji);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody RuzhulvtongjiEntity ruzhulvtongji, HttpServletRequest request){
    	ruzhulvtongji.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(ruzhulvtongji);
        ruzhulvtongjiService.insert(ruzhulvtongji);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody RuzhulvtongjiEntity ruzhulvtongji, HttpServletRequest request){
    	ruzhulvtongji.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(ruzhulvtongji);
        ruzhulvtongjiService.insert(ruzhulvtongji);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody RuzhulvtongjiEntity ruzhulvtongji, HttpServletRequest request){
        //ValidatorUtils.validateEntity(ruzhulvtongji);
        ruzhulvtongjiService.updateById(ruzhulvtongji);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        ruzhulvtongjiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	





    /**
     * （按值统计）
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}")
    public R value(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName,HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("yColumn", yColumnName);
        EntityWrapper<RuzhulvtongjiEntity> ew = new EntityWrapper<RuzhulvtongjiEntity>();
        List<Map<String, Object>> result = ruzhulvtongjiService.selectValue(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }

    /**
     * （按值统计(多)）
     */
    @RequestMapping("/valueMul/{xColumnName}")
    public R valueMul(@PathVariable("xColumnName") String xColumnName,@RequestParam String yColumnNameMul, HttpServletRequest request) {
        String[] yColumnNames = yColumnNameMul.split(",");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String,Object>>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        EntityWrapper<RuzhulvtongjiEntity> ew = new EntityWrapper<RuzhulvtongjiEntity>();
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = ruzhulvtongjiService.selectValue(params, ew);
            for(Map<String, Object> m : result) {
                for(String k : m.keySet()) {
                    if(m.get(k) instanceof Date) {
                        m.put(k, sdf.format((Date)m.get(k)));
                    }
                }
            }
            result2.add(result);
        }
        return R.ok().put("data", result2);
    }

    /**
     * （按值统计）时间统计类型
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}/{timeStatType}")
    public R valueDay(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType,HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("yColumn", yColumnName);
        params.put("timeStatType", timeStatType);
        EntityWrapper<RuzhulvtongjiEntity> ew = new EntityWrapper<RuzhulvtongjiEntity>();
        List<Map<String, Object>> result = ruzhulvtongjiService.selectTimeStatValue(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }

    /**
     * （按值统计）时间统计类型(多)
     */
    @RequestMapping("/valueMul/{xColumnName}/{timeStatType}")
    public R valueMulDay(@PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType,@RequestParam String yColumnNameMul,HttpServletRequest request) {
        String[] yColumnNames = yColumnNameMul.split(",");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("timeStatType", timeStatType);
        List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String,Object>>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        EntityWrapper<RuzhulvtongjiEntity> ew = new EntityWrapper<RuzhulvtongjiEntity>();
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = ruzhulvtongjiService.selectTimeStatValue(params, ew);
            for(Map<String, Object> m : result) {
                for(String k : m.keySet()) {
                    if(m.get(k) instanceof Date) {
                        m.put(k, sdf.format((Date)m.get(k)));
                    }
                }
            }
            result2.add(result);
        }
        return R.ok().put("data", result2);
    }

    /**
     * 分组统计
     */
    @RequestMapping("/group/{columnName}")
    public R group(@PathVariable("columnName") String columnName,HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("column", columnName);
        EntityWrapper<RuzhulvtongjiEntity> ew = new EntityWrapper<RuzhulvtongjiEntity>();
        List<Map<String, Object>> result = ruzhulvtongjiService.selectGroup(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }




    /**
     * 总数量
     */
    @RequestMapping("/count")
    public R count(@RequestParam Map<String, Object> params,RuzhulvtongjiEntity ruzhulvtongji, HttpServletRequest request){
        EntityWrapper<RuzhulvtongjiEntity> ew = new EntityWrapper<RuzhulvtongjiEntity>();
        int count = ruzhulvtongjiService.selectCount(MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, ruzhulvtongji), params), params));
        return R.ok().put("data", count);
    }


}
