package websocket.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.alibaba.fastjson.JSONObject;
import com.rofe.pojo.EmailMessage;
import com.rofe.pojo.Student;
import com.rofe.pojo.Teacher;
import com.rofe.service.EmailService;
import com.rofe.service.StudentService;
import com.rofe.service.TeacherService;

public class SystemWebSocketHandler implements WebSocketHandler {

	public static final Map<String, WebSocketSession> usersMap = new ConcurrentHashMap<String, WebSocketSession>();;
	public static final Map<String, ArrayList<String>> messageList = new HashMap<String, ArrayList<String>>();

	/**
	 * 连接时
	 * @param session
	 * @throws Exception
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		String[] infos = session.getUri().toString().split("=");
		if (infos.length == 2) {
			String userId = infos[1];
			if (userId != null) {
				usersMap.put(userId, session);
			}
			// 如果存在用戶信息则在连接后进行推送
			ArrayList<String> list = messageList.get(userId);
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					String message = list.get(i);
					if (message != null) {
						JSONObject jsonObject = (JSONObject) JSONObject.parse(message);
						String type = jsonObject.getString("type");
						String types[] = type.split("-");
						String targetType = types[1];
						String targetNum = jsonObject.getString("num");
						String targetUserId = targetNum+"-"+targetType;
						TextMessage textMessage = new TextMessage(message);
						sendTxtMessage(targetUserId,textMessage,jsonObject);
					}
				}
				messageList.remove(userId);
			}
		}
	}

	// 连接方消息的推送
	@Override
	public void handleMessage(WebSocketSession session,WebSocketMessage<?> message) throws Exception {
		EmailService emailService = (EmailService) ContextLoader.getCurrentWebApplicationContext().getBean("emailServiceImpl");
		JSONObject jsonObject = (JSONObject) JSONObject.parse(message.getPayload().toString());
		String type = jsonObject.getString("type");
		String types[] = type.split("-");
		String targetType = types[1];
		String targetNum = jsonObject.getString("num");
		boolean isExist = false;
		if("tc".equals(targetType)){
			TeacherService teacherService = (TeacherService) ContextLoader.getCurrentWebApplicationContext().getBean("teacherServiceImpl");
			Teacher tc = teacherService.selectByTcNumOrUsername(Long.parseLong(targetNum), null);
			if(tc != null){
				isExist = true;
			}
		}else{
			StudentService studentService = (StudentService) ContextLoader.getCurrentWebApplicationContext().getBean("studentServiceImp");
			Student stu = studentService.selectByStuNumOrUsername(Long.parseLong(targetNum), null);
			if(stu != null){
				isExist = true;
			}
		}
		if(isExist){
			String userId = targetNum+"-"+targetType;
			TextMessage textMessage = new TextMessage(message.getPayload()
			 .toString());
			sendTxtMessage(userId,textMessage,jsonObject);
			EmailMessage em = new EmailMessage();
			em.setEmType(type);
			em.setEmContent(jsonObject.getString("content"));
			em.setEmReceiver(Integer.parseInt(targetNum));
			em.setEmSender(jsonObject.getIntValue("senderNum"));
			em.setEmTheme(jsonObject.getString("theme"));
			em.setEmTime(new Date());
			emailService.insertSelective(em);
			jsonObject.put("callback", 0);
			TextMessage callBackTx = new TextMessage(jsonObject.toJSONString());
			String sendId = jsonObject.getString("senderNum")+"-"+types[0];
			sendTxtMessage(sendId,callBackTx,jsonObject);
		}else{
			jsonObject.put("callback", -1);
			TextMessage callBackTx = new TextMessage(jsonObject.toJSONString());
			String sendId = jsonObject.getString("senderNum")+"-"+types[0];
			sendTxtMessage(sendId,callBackTx,jsonObject);
		}
		
		
	}

	@Override
	public void handleTransportError(WebSocketSession session,
			Throwable exception) throws Exception {
		if (session.isOpen()) {
			session.close();
		}
		String userId = session.getUri().toString().split("=")[1];
		if (userId != null) {
			usersMap.remove(userId);
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session,
			CloseStatus closeStatus) throws Exception {
		
		String[] infos = session.getUri().toString().split("=");
		if (infos.length == 2) {
			String userId = infos[1];
			if (userId != null) {
				usersMap.remove(userId);
			}
		}
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

	public static void sendTxtMessage(String userId,TextMessage textMessage,JSONObject jsonObject) {

		WebSocketSession userSession = SystemWebSocketHandler.usersMap
				.get(userId);
		String jsonString = jsonObject.toJSONString();
		// 用户仍旧连接中
		if (userSession != null) {
			try {
				if (userSession.isOpen()) {
					userSession.sendMessage(textMessage);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 用户断开了连接
		else {
			ArrayList<String> messageList = SystemWebSocketHandler.messageList
					.get(userId);
			if (messageList == null) {
				messageList = new ArrayList<>();
				SystemWebSocketHandler.messageList.put(userId, messageList);
			}
			messageList.add(jsonString);
		}
	}

	
	
}