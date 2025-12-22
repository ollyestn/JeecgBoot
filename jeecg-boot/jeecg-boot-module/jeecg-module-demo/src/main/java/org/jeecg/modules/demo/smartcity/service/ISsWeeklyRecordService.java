package org.jeecg.modules.demo.smartcity.service;

import org.jeecg.modules.demo.smartcity.entity.SsWeeklyRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 本周工作记录
 * @Author: jeecg-boot
 * @Date:   2025-12-22
 * @Version: V1.0
 */
public interface ISsWeeklyRecordService extends IService<SsWeeklyRecord> {

	/**
	 * 通过主表id查询子表数据
	 *
	 * @param mainId 主表id
	 * @return List<SsWeeklyRecord>
	 */
	public List<SsWeeklyRecord> selectByMainId(String mainId);
}
