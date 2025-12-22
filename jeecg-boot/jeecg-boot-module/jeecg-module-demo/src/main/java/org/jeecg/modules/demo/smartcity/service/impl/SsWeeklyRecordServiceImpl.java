package org.jeecg.modules.demo.smartcity.service.impl;

import org.jeecg.modules.demo.smartcity.entity.SsWeeklyRecord;
import org.jeecg.modules.demo.smartcity.mapper.SsWeeklyRecordMapper;
import org.jeecg.modules.demo.smartcity.service.ISsWeeklyRecordService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 本周工作记录
 * @Author: jeecg-boot
 * @Date:   2025-12-22
 * @Version: V1.0
 */
@Service
public class SsWeeklyRecordServiceImpl extends ServiceImpl<SsWeeklyRecordMapper, SsWeeklyRecord> implements ISsWeeklyRecordService {
	
	@Autowired
	private SsWeeklyRecordMapper ssWeeklyRecordMapper;
	
	@Override
	public List<SsWeeklyRecord> selectByMainId(String mainId) {
		return ssWeeklyRecordMapper.selectByMainId(mainId);
	}
}
