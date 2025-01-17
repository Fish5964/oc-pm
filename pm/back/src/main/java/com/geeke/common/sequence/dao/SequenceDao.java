package com.geeke.common.sequence.dao;

import com.geeke.common.sequence.entity.Sequence;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 序号dao接口
 * 
 * @author lys
 * @date 2021/4/30
 */
@Mapper
public interface SequenceDao {
	
	Sequence getByPrefix(@Param("companyId") String companyId, @Param("code") String code, @Param("prefix")String prefix);
	
	int insert(Sequence sequence);
	
	int nextSeqById(Sequence sequence);
	

}
