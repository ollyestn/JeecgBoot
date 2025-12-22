package org.jeecg.modules.demo.smartcity.service.impl;

import org.jeecg.modules.demo.smartcity.entity.SsWeeklySummary;
import org.jeecg.modules.demo.smartcity.mapper.SsWeeklySummaryMapper;
import org.jeecg.modules.demo.smartcity.service.ISsWeeklySummaryService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 本周总结
 * @Author: jeecg-boot
 * @Date:   2025-12-22
 * @Version: V1.0
 */
@Service
public class SsWeeklySummaryServiceImpl extends ServiceImpl<SsWeeklySummaryMapper, SsWeeklySummary> implements ISsWeeklySummaryService {
	
	@Autowired
	private SsWeeklySummaryMapper ssWeeklySummaryMapper;
	
	@Override
	public List<SsWeeklySummary> selectByMainId(String mainId) {
		return ssWeeklySummaryMapper.selectByMainId(mainId);
	}
}
