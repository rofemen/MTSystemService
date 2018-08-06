/**
 * @author:郑日枋
 * @time:2018年2月10日 下午6:20:49
 * @filename:ShareService.java
 */
package com.rofe.service;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;

import com.rofe.common.PageInfo;
import com.rofe.pojo.Share;
import com.rofe.pojo.Teacher;

public interface ShareService extends BaseService<Share>{
	public int publicShare(Long userId,String userType
			,String shareContent,ArrayList<String>sharePics);
	public ArrayList<Share> getSharesInfo(PageInfo<Share> pageInfo);
	public int getAllShareCount();
	
}
