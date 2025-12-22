package org.jeecg.modules.demo.smartcity.service.impl;

import org.jeecg.modules.demo.smartcity.entity.SsWorklyPlan;
import org.jeecg.modules.demo.smartcity.mapper.SsWorklyPlanMapper;
import org.jeecg.modules.demo.smartcity.service.ISsWorklyPlanService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 下周工作计划
 * @Author: jeecg-boot
 * @Date:   2025-12-22
 * @Version: V1.0
 */
@Service
public class SsWorklyPlanServiceImpl extends ServiceImpl<SsWorklyPlanMapper, SsWorklyPlan> implements ISsWorklyPlanService {
	
	@Autowired
	private SsWorklyPlanMapper ssWorklyPlanMapper;
	
	@Override
	public List<SsWorklyPlan> selectByMainId(String mainId) {
		return ssWorklyPlanMapper.selectByMainId(mainId);
	}
}
