/**
 * @author:郑日枋
 * @time:2018年2月21日 下午10:12:34
 * @filename:EmailService.java
 */
package com.rofe.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.rofe.common.PageInfo;
import com.rofe.pojo.EmailMessage;

public interface EmailService{
	int deleteByPrimaryKey(int id);

	int insert(EmailMessage record);

	int insertSelective(EmailMessage record);

	EmailMessage selectByPrimaryKey(int id);

	int updateByPrimaryKeySelective(EmailMessage record);

	int updateByPrimaryKey(EmailMessage record);
	
	ArrayList<EmailMessage> getReceiveMessage(PageInfo<EmailMessage> pageInfo,int idNum,String userType);

    ArrayList<EmailMessage> getSendMessage(PageInfo<EmailMessage> pageInfo,int sender,String userType);

    int getReceiveMessageCount(int receer,String userType);
    
    int getSendMessageCount(int sender,String userType);
    
    int batchDelEmInfo(ArrayList<Long> messageList);
}
