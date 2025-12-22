package org.jeecg.modules.demo.smartcity.service;

import org.jeecg.modules.demo.smartcity.entity.SsWorklyPlan;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 下周工作计划
 * @Author: jeecg-boot
 * @Date:   2025-12-22
 * @Version: V1.0
 */
public interface ISsWorklyPlanService extends IService<SsWorklyPlan> {

	/**
	 * 通过主表id查询子表数据
	 *
	 * @param mainId 主表id
	 * @return List<SsWorklyPlan>
	 */
	public List<SsWorklyPlan> selectByMainId(String mainId);
}
