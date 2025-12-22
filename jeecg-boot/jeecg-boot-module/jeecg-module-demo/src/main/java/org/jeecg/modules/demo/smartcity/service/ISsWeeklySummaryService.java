package org.jeecg.modules.demo.smartcity.service;

import org.jeecg.modules.demo.smartcity.entity.SsWeeklySummary;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 本周总结
 * @Author: jeecg-boot
 * @Date:   2025-12-22
 * @Version: V1.0
 */
public interface ISsWeeklySummaryService extends IService<SsWeeklySummary> {

	/**
	 * 通过主表id查询子表数据
	 *
	 * @param mainId 主表id
	 * @return List<SsWeeklySummary>
	 */
	public List<SsWeeklySummary> selectByMainId(String mainId);
}
