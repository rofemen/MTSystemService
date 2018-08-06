/**
 * @author:郑日枋
 * @time:2018年1月21日 下午10:23:27
 * @filename:ClassInfoController.java
 */
package com.rofe.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rofe.common.PageInfo;
import com.rofe.pojo.ClassInfo;
import com.rofe.pojo.Evaluation;
import com.rofe.pojo.Teacher;
import com.rofe.service.EvaluationService;
import com.rofe.util.CommonTools;


@Controller
@RequestMapping("/evaluationController")
public class EvaluationController {
	@Autowired
	EvaluationService evaluationService;
	
	@ResponseBody
	@RequestMapping("/addInstorEi")
	public HashMap<String,Object> addInstorEi(@RequestBody Evaluation evalution){
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
		int records = evaluationService.insertSelective(evalution);
		if(records != 0){
				map.put("code", "200");
				data.put("message", "教学评价提交成功");
				map.put("data",data);
		}else{
			map.put("code", 201); 
			data.put("message", "教学评价提交失败，请稍后再试");
			map.put("data", data);
		}	
		return map;
	}

	@ResponseBody
	@RequestMapping("/getEiInfoByTcNum")
	public HashMap<String,Object> getEiInfoByTcNum(@RequestBody HashMap<String, Object> params){
		Long tcNum = CommonTools.toLong((int)params.get("tcNum"));
		Long ciCode = 0l;
		if(params.get("ciCode") != null){
			ciCode = CommonTools.toLong((int)params.get("ciCode"));
		}
		int pageSize = (int)params.get("pageSize");
		int currentPage = (int)params.get("currPage");
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
		PageInfo<Evaluation> pageInfo = new PageInfo<>(currentPage,pageSize);
		int count = evaluationService.getEiInfoCountByTcNum(tcNum,ciCode);
		if(count != 0){
			pageInfo.setTotalRecords(count);
			ArrayList<Evaluation> list= evaluationService.getEiInfoByTcNum(pageInfo,tcNum,ciCode);
			map.put("code", "200");
			data.put("message", "获取教学评价成功");
			data.put("list", list);
			data.put("totalSize", count);
			data.put("currPage",currentPage);
			data.put("pageSize",pageSize);
			map.put("data",data);
		}else{
			map.put("code", 201); 
			data.put("message", "暂无课程教学评价");
			map.put("data", data);
		}	
		return map;
	}
}
