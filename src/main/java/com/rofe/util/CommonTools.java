/**
 * @author:郑日枋
 * @time:2017年10月18日 下午10:34:26
 * @filename:PasswordEncrypt.java
 */
package com.rofe.util;

import java.util.Collections;
import java.util.Comparator;

import org.apache.poi.xdgf.usermodel.section.CombinedIterable;
import org.apache.shiro.crypto.hash.SimpleHash;

import com.rofe.pojo.Scheduleinfo;

public class CommonTools {
	public static String encrypt(String target,String salt,int Iterations) {  
		SimpleHash hash = new SimpleHash(CommonData.AlgorithmName, target, salt , Iterations);  
		String encodedPassword = hash.toHex();
		return encodedPassword;
	}
	public static boolean isEmpty(String tagget){
		if(tagget != null && !"".equals(tagget)){
			return false;
		}
		return true;
	}
	public static Long toLong(Integer target){
		if(target == null){
			return 0L;
		}else{
			return Long.valueOf(target.toString());
		}
		
	}

	public static void main(String args[]){
		System.out.println(CommonTools.encrypt("5aad7248e9354f404efb4f50101c69cc", "rofe", 2));
	}
}
