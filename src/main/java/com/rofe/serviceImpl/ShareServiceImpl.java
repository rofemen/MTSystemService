/**
 * @author:郑日枋
 * @time:2018年2月10日 下午6:22:24
 * @filename:ShareServiceImpl.java
 */
package com.rofe.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rofe.common.PageInfo;
import com.rofe.dao.ComentDetailMapper;
import com.rofe.dao.ShareMapper;
import com.rofe.pojo.ComentDetail;
import com.rofe.pojo.Share;
import com.rofe.service.ShareService;

@Service
public class ShareServiceImpl implements ShareService{
	@Autowired
	ShareMapper shareMapper;
	@Autowired
	ComentDetailMapper commentComentDetailMapper;
	@Override
	public int deleteByPrimaryKey(Long id) {
		return shareMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Share record) {
		return shareMapper.insert(record);
	}

	@Override
	public int insertSelective(Share record) {
		return shareMapper.insertSelective(record);
	}

	@Override
	public Share selectByPrimaryKey(Long id) {
		return shareMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Share record) {
		return shareMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Share record) {
		return shareMapper.updateByPrimaryKey(record);
	}

	@Override
	public int publicShare(Long userId,String userType
			,String shareContent,ArrayList<String>sharePics) {
		Share share = new Share();
		share.setSharePublisher(userId);
		share.setShareTime(new Date());
		if("stu".equals(userType)){
			share.setShareType(1);
		}else{
			share.setShareType(0);
		}
		share.setShareContent(shareContent);
		for(int i = 0;i < sharePics.size();i++){
			switch(i){
			 case 0:{
				 String temp = userId + "_" +sharePics.get(i).toString();
				 share.setSharePic1(temp);
				 break;
			 }
			 case 1:{
				 String temp = userId + "_" +sharePics.get(i);
				 share.setSharePic2(temp);
				 break;
			 }
			 case 2:{
				 String temp = userId + "_" +sharePics.get(i);
				 share.setSharePic3(temp);
				 break;
			 }
			 case 3:{
				 String temp = userId + "_" +sharePics.get(i);
				 share.setSharePic4(temp);
				 break;
			 }
			}
		}
		return shareMapper.insertSelective(share);
	}

	@Override
	public ArrayList<Share> getSharesInfo(PageInfo<Share> pageInfo) {
		 ArrayList<Share> list = shareMapper.getSharesInfo(new RowBounds(pageInfo.getFromRecord(), pageInfo
				.getPageSize()));
		 for(Share share : list){
			Long shareId = share.getShareId();
			ArrayList<ComentDetail> cms= commentComentDetailMapper.getCommentDetailByShareId(shareId);
			share.setCms(cms);
		 }
		return list;
	}

	@Override
	public int getAllShareCount() {
		return shareMapper.getAllShareCount();
	}
	
}
