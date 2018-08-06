/**
 * @author:郑日枋
 * @time:2018年2月21日 下午10:13:43
 * @filename:EmailServiceImpl.java
 */
package com.rofe.serviceImpl;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rofe.common.PageInfo;
import com.rofe.dao.EmailMessageMapper;
import com.rofe.pojo.EmailMessage;
import com.rofe.pojo.Student;
import com.rofe.pojo.Teacher;
import com.rofe.service.EmailService;
import com.rofe.service.StudentService;
import com.rofe.service.TeacherService;
import com.rofe.util.CommonTools;

@Service
public class EmailServiceImpl implements EmailService{
	
	@Autowired
	EmailMessageMapper emailMessageMapper;
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	TeacherService teacherService;
	
	@Override
	public int deleteByPrimaryKey(int id) {
		return emailMessageMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(EmailMessage record) {
		return emailMessageMapper.insert(record);
	}

	@Override
	public int insertSelective(EmailMessage record) {
		return emailMessageMapper.insertSelective(record);
	}

	@Override
	public EmailMessage selectByPrimaryKey(int id) {
		return emailMessageMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(EmailMessage record) {
		return emailMessageMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(EmailMessage record) {
		return emailMessageMapper.updateByPrimaryKey(record);
	}

	@Override
	public ArrayList<EmailMessage> getReceiveMessage(PageInfo<EmailMessage> pageInfo, int idNum, String userType) {
		ArrayList<EmailMessage> list = emailMessageMapper.getReceiveMessage(new RowBounds(pageInfo.getFromRecord(), pageInfo
				.getPageSize()),idNum, userType);
		for(int i = 0; i<list.size(); i++){
			EmailMessage em = list.get(i);
			String type = em.getEmType();
			String types[] = type.split("-");
			if("tc".equals(types[0])){
				Teacher tc = teacherService.selectByTcNumOrUsername(CommonTools.toLong(em.getEmSender()), null);
				em.setSenderName(tc.getTcName());
			}else{
				Student stu = studentService.selectByStuNumOrUsername(CommonTools.toLong(em.getEmSender()), null);
				em.setSenderName(stu.getStuName());
			}
			if("tc".equals(types[1])){
				Teacher tc = teacherService.selectByTcNumOrUsername(CommonTools.toLong(em.getEmReceiver()), null);
				em.setReceiverName(tc.getTcName());
			}else{
				Student stu = studentService.selectByStuNumOrUsername(CommonTools.toLong(em.getEmReceiver()), null);
				em.setReceiverName(stu.getStuName());
			}
		}
		return list;
	}

	@Override
	public ArrayList<EmailMessage> getSendMessage(PageInfo<EmailMessage> pageInfo,int sender, String userType) {
		ArrayList<EmailMessage> list = emailMessageMapper.getSendMessage(new RowBounds(pageInfo.getFromRecord(), pageInfo
				.getPageSize()),sender, userType);
		for(int i = 0; i<list.size(); i++){
			EmailMessage em = list.get(i);
			String type = em.getEmType();
			String types[] = type.split("-");
			if("tc".equals(types[0])){
				Teacher tc = teacherService.selectByTcNumOrUsername(CommonTools.toLong(em.getEmSender()), null);
				em.setSenderName(tc.getTcName());
			}else{
				Student stu = studentService.selectByStuNumOrUsername(CommonTools.toLong(em.getEmSender()), null);
				em.setSenderName(stu.getStuName());
			}
			if("tc".equals(types[1])){
				Teacher tc = teacherService.selectByTcNumOrUsername(CommonTools.toLong(em.getEmReceiver()), null);
				em.setReceiverName(tc.getTcName());
			}else{
				Student stu = studentService.selectByStuNumOrUsername(CommonTools.toLong(em.getEmReceiver()), null);
				em.setReceiverName(stu.getStuName());
			}
		}
		return list;
	}

	@Override
	public int getReceiveMessageCount(int receer, String userType) {
		return emailMessageMapper.getReceiveMessageCount(receer, userType);
	}

	@Override
	public int getSendMessageCount(int sender, String userType) {
		return emailMessageMapper.getSendMessageCount(sender,userType);
	}

	@Override
	public int batchDelEmInfo(ArrayList<Long> messageList) {
		return emailMessageMapper.batchDelEmInfo(messageList);
	}
	
}
