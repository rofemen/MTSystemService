package com.rofe.dao;

import java.util.ArrayList;

import com.rofe.pojo.ComentDetail;

public interface ComentDetailMapper extends BaseMapper<ComentDetail>{
	ArrayList<ComentDetail> getCommentDetailByShareId(Long shareId);
	int deleteBySendIdOrReceId(Long userId);
}