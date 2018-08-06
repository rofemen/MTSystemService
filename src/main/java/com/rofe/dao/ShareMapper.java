package com.rofe.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;

import com.rofe.pojo.Share;

public interface ShareMapper extends BaseMapper<Share>{
   public int getAllShareCount();
   public ArrayList<Share> getSharesInfo(RowBounds rowBounds);
   public int deleteByPublisher(Long stuUserid);
}