/**
 * @author:郑日枋
 * @time:2018年1月27日 下午2:32:19
 * @filename:ScheduleInfoServiceImpl.java
 */
package com.rofe.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rofe.dao.ScheduleinfoMapper;
import com.rofe.pojo.Evaluation;
import com.rofe.pojo.Scheduleinfo;
import com.rofe.service.EvaluationService;
import com.rofe.service.ScheduleInfoService;
import com.rofe.util.CommonTools;

@Service
public class ScheduleInfoServiceImpl implements ScheduleInfoService{

	@Autowired
	ScheduleinfoMapper scheduleinfoMapper;
	@Autowired
	EvaluationService evaluationService;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return scheduleinfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Scheduleinfo record) {
		return scheduleinfoMapper.insert(record);
	}

	@Override
	public int insertSelective(Scheduleinfo record) {
		return scheduleinfoMapper.insertSelective(record);
	}

	@Override
	public Scheduleinfo selectByPrimaryKey(Long id) {
		return scheduleinfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Scheduleinfo record) {
		return scheduleinfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Scheduleinfo record) {
		return scheduleinfoMapper.updateByPrimaryKey(record);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Scheduleinfo> getTcSchedule(Long tcUserId) {
		ArrayList<Scheduleinfo> list = scheduleinfoMapper.getTcSchedule(tcUserId);
		if(list.size() == 0){
			for(int i = 0; i < 15;i++){
				list.add(new Scheduleinfo(i));
			}
		}else{
			ArrayList<Integer> indexList = new ArrayList<>();
			for(Scheduleinfo tempInfo : list){
				if(tempInfo != null){
					indexList.add(tempInfo.getSiIndex());
				}
			}
			for(int i = 0; i < 15; i++){
					if(indexList.contains(i)){
						continue;
					}else{
						list.add(new Scheduleinfo(i));
					}
			}
		}
		System.out.println(list);
		Collections.sort(list, new Comparator(){
	        @Override
	        public int compare(Object o1, Object o2) {
	        	Scheduleinfo s1=(Scheduleinfo)o1;
	        	Scheduleinfo s2=(Scheduleinfo)o2;
	            return s1.getSiIndex()-s2.getSiIndex();
	        }       
		});
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Scheduleinfo> updateScheduleByList(ArrayList<LinkedHashMap<String, Object>> list,Long siClCode,Long tcNum) {
		ArrayList<Scheduleinfo> result = new ArrayList<>();
		if(siClCode != 0){
			scheduleinfoMapper.deleteTcSchedule(siClCode);
		}else{
			return result;
		}
		if(list != null && list.size() != 0){
			
			for(int i = 0; i < list.size();i++){
				LinkedHashMap<String, Object> tempMap = list.get(i);
				Long siCiCode = CommonTools.toLong((Integer)tempMap.get("siCiCode"));
				 if(siCiCode != 0){
					 Scheduleinfo tempScheduleinfo =  Scheduleinfo.getObject(tempMap);
					 tempScheduleinfo.setSiIndex(i);
					 scheduleinfoMapper.insertSelective(tempScheduleinfo);
				 }

			}
			if(tcNum!=0){
				result = getTcSchedule(tcNum);
			}

			Collections.sort(result, new Comparator(){
		        @Override
		        public int compare(Object o1, Object o2) {
		        	Scheduleinfo s1=(Scheduleinfo)o1;
		        	Scheduleinfo s2=(Scheduleinfo)o2;
		            return s1.getSiIndex()-s2.getSiIndex();
		        }       
			});
		}
		return result;
	}

	@Override
	public Long isScheduleExist(Long tcUserId, int siIndex) {
		return null;
	}

	@Override
	public int deleteTcSchedule(Long siClCode) {
		return scheduleinfoMapper.deleteTcSchedule(siClCode);
	}

	@Override
	public ArrayList<Scheduleinfo> getTeachedCi(Long tcUserId,Long stuId) {
		ArrayList<Evaluation> eiList= evaluationService.selectByStuId(stuId);
		ArrayList<Scheduleinfo> siList = scheduleinfoMapper.getTcSchedule(tcUserId);
		//去掉重复的
		ArrayList<Scheduleinfo> newList = new ArrayList<Scheduleinfo>(); 
        for (Scheduleinfo siItem:siList) {
        	int isJoin = 0;
        	for(Scheduleinfo temp:newList){
        		if(siItem.getSiCiCode().equals(temp.getSiCiCode()) ){
        			isJoin = 1;
        		}
        	}
        	if(isJoin==0){
        		newList.add(siItem);
        	}
       }
        siList = newList;
        ArrayList<Scheduleinfo> resultList = new ArrayList<Scheduleinfo>();
		for(int i = 0;i < siList.size();i++){
			Scheduleinfo si = siList.get(i);
			boolean isEi = false;
			for(Evaluation ei :eiList){
				int ciCode = ei.getEiCiCode();
				if(ciCode == 0){
					continue;
				}
				if(si.getSiCiCode() == ciCode){
					isEi = true;
				}
			}
			if(!isEi){
				resultList.add(si);
			}
		}
		return resultList;
	}

	@Override
	public ArrayList<Scheduleinfo> getStudentSchedule(Long stuId) {
		ArrayList<Scheduleinfo> list = scheduleinfoMapper.getStudentSchedule(stuId);
		if(list.size() == 0){
			for(int i = 0; i < 15;i++){
				list.add(new Scheduleinfo(i));
			}
		}else{
			ArrayList<Integer> indexList = new ArrayList<>();
			for(Scheduleinfo tempInfo : list){
				if(tempInfo != null){
					indexList.add(tempInfo.getSiIndex());
				}
			}
			for(int i = 0; i < 15; i++){
					if(indexList.contains(i)){
						continue;
					}else{
						list.add(new Scheduleinfo(i));
					}
			}
		}
		Collections.sort(list, new Comparator(){
	        @Override
	        public int compare(Object o1, Object o2) {
	        	Scheduleinfo s1=(Scheduleinfo)o1;
	        	Scheduleinfo s2=(Scheduleinfo)o2;
	            return s1.getSiIndex()-s2.getSiIndex();
	        }       
		});
		return list;
	}

	@Override
	public ArrayList<Scheduleinfo> getTeacherSchedule(Long tcId) {
		ArrayList<Scheduleinfo> list = scheduleinfoMapper.getTeacherSchedule(tcId);
		if(list.size() == 0){
			for(int i = 0; i < 15;i++){
				list.add(new Scheduleinfo(i));
			}
		}else{
			ArrayList<Integer> indexList = new ArrayList<>();
			for(Scheduleinfo tempInfo : list){
				if(tempInfo != null){
					indexList.add(tempInfo.getSiIndex());
				}
			}
			for(int i = 0; i < 15; i++){
					if(indexList.contains(i)){
						continue;
					}else{
						list.add(new Scheduleinfo(i));
					}
			}
		}
		Collections.sort(list, new Comparator(){
	        @Override
	        public int compare(Object o1, Object o2) {
	        	Scheduleinfo s1=(Scheduleinfo)o1;
	        	Scheduleinfo s2=(Scheduleinfo)o2;
	            return s1.getSiIndex()-s2.getSiIndex();
	        }       
		});
		return list;
	}

	@Override
	public ArrayList<Scheduleinfo> getClassSchedule(Long siClCode) {
		ArrayList<Scheduleinfo> list = scheduleinfoMapper.getClassSchedule(siClCode);
		if(list.size() == 0){
			for(int i = 0; i < 15;i++){
				list.add(new Scheduleinfo(i));
			}
		}else{
			ArrayList<Integer> indexList = new ArrayList<>();
			for(Scheduleinfo tempInfo : list){
				if(tempInfo != null){
					indexList.add(tempInfo.getSiIndex());
				}
			}
			for(int i = 0; i < 15; i++){
					if(indexList.contains(i)){
						continue;
					}else{
						list.add(new Scheduleinfo(i));
					}
			}
		}
		Collections.sort(list, new Comparator(){
	        @Override
	        public int compare(Object o1, Object o2) {
	        	Scheduleinfo s1=(Scheduleinfo)o1;
	        	Scheduleinfo s2=(Scheduleinfo)o2;
	            return s1.getSiIndex()-s2.getSiIndex();
	        }       
		});
		return list;
	}
}
