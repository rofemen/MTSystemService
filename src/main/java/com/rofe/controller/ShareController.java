/**
 * @author:郑日枋
 * @time:2017年10月19日 下午1:07:47
 * @filename:LoginController.java
 */
package com.rofe.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rofe.common.PageInfo;
import com.rofe.pojo.ComentDetail;
import com.rofe.pojo.Share;
import com.rofe.service.CommentDetailService;
import com.rofe.service.ShareService;
import com.rofe.util.CommonTools;

@Controller
@RequestMapping("/shareController")
public class ShareController {
	@Autowired
	ShareService shareService;
	@Autowired
	CommentDetailService commentDetailService;
	@RequestMapping("/publicShare")
	@ResponseBody
	public HashMap<String,Object> publicShare(@RequestBody HashMap<String, Object> params){
		String shareContent = (String)params.get("shareContent");
		String userType = (String)params.get("userType");
		ArrayList<String> sharePics= (ArrayList<String>)params.get("sharePics");
		Long userId = CommonTools.toLong((int)params.get("userId"));
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
		int result = shareService.publicShare(userId,userType,shareContent,sharePics);
		if(result != 0){
			map.put("code",200);
			data.put("message","发布成功");
			map.put("data",data);
		}else{
			map.put("code",201);
			data.put("message","发布失败，请稍后重试");
			map.put("data",data);
		}
		return map;
	}
	
	@RequestMapping("/getShareInfo")
	@ResponseBody
	public HashMap<String,Object> getShareInfo(@RequestBody HashMap<String, Object> params){
		int pageSize = (int)params.get("pageSize");
		int currentPage = (int)params.get("currPage");
		int total = shareService.getAllShareCount();
		PageInfo<Share> pageInfo = new PageInfo<>(currentPage,pageSize);
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
		ArrayList<Share> list= shareService.getSharesInfo(pageInfo);
		data.put("total", total);
		map.put("code",200);
		data.put("message","获取成功");
		data.put("list",list);
		map.put("data",data);
		return map;
	}
	
	@RequestMapping("/publicCommentDetail")
	@ResponseBody
	public HashMap<String,Object> publicCommentDetail(@RequestBody HashMap<String, Object> params){
		String commentContent = (String)params.get("commentContent");
		int senderId = (int)params.get("senderId");
		int receiverId = (int)params.get("receId");
		Long shareId = CommonTools.toLong((int)params.get("shareId"));
		ComentDetail commentDetail = new ComentDetail();
		commentDetail.setCmContent(commentContent);
		commentDetail.setCmReceId(receiverId);
		commentDetail.setCmSendId(senderId);
		commentDetail.setCmShareId(shareId);
		commentDetail.setCmTime(new Date());
		int result = commentDetailService.insertSelective(commentDetail);
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
		if(result == 1){
			map.put("code",200);
			data.put("message","评论成功");
			map.put("data",data);
		}else{
			map.put("code",201);
			data.put("message","评论失败，请稍后重试");
			map.put("data",data);
		}
		return map;
	}
	
	@RequestMapping("/deleteCommentDetail")
	@ResponseBody
	public HashMap<String,Object> deleteCommentDetail(@RequestBody HashMap<String, Object> params){
		Long cmId = CommonTools.toLong((int)params.get("cmId"));
		int result = commentDetailService.deleteByPrimaryKey(cmId);
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> data = new HashMap<>();
		if(result == 1){
			map.put("code",200);
			data.put("message","删除成功");
			map.put("data",data);
		}else{
			map.put("code",201);
			data.put("message","删除失败，请稍后重试");
			map.put("data",data);
		}
		return map;
	}
}
