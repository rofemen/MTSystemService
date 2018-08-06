package com.rofe.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.rofe.pojo.EmailMessage;

public interface EmailMessageMapper {
    int deleteByPrimaryKey(Integer emId);

    int insert(EmailMessage record);

    int insertSelective(EmailMessage record);

    EmailMessage selectByPrimaryKey(Integer emId);

    int updateByPrimaryKeySelective(EmailMessage record);
    
    int updateByPrimaryKeyWithBLOBs(EmailMessage record);

    int updateByPrimaryKey(EmailMessage record);
    
    ArrayList<EmailMessage> getSendMessage(RowBounds rowBounds,@Param("sender")int sender,
			@Param("userType")String userType);
    
    ArrayList<EmailMessage> getReceiveMessage(RowBounds rowBounds,@Param("receer")int receer,
			@Param("userType")String userType);
    
    int getReceiveMessageCount(@Param("receer")int receer,
			@Param("userType")String userType);
    
    int getSendMessageCount(@Param("sender")int sender,
			@Param("userType")String userType);
    
    int batchDelEmInfo(ArrayList<Long> messageList);
}