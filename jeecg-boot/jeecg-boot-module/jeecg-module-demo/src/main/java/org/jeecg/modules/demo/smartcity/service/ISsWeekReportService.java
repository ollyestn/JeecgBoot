package org.jeecg.modules.demo.smartcity.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.demo.smartcity.entity.SsWeeklySummary;
import org.jeecg.modules.demo.smartcity.entity.SsWeeklyRecord;
import org.jeecg.modules.demo.smartcity.entity.SsWorklyPlan;
import org.jeecg.modules.demo.smartcity.entity.SsWeekReport;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 周报
 * @Author: jeecg-boot
 * @Date:   2025-12-22
 * @Version: V1.0
 */
public interface ISsWeekReportService extends IService<SsWeekReport> {

    IPage<SsWeekReport> page(Page<SsWeekReport> page, QueryWrapper<SsWeekReport> queryWrapper);

    List<SsWeekReport> list(QueryWrapper<SsWeekReport> queryWrapper);

    /**
	 * 添加一对多
	 *
	 * @param ssWeekReport
	 * @param ssWeeklySummaryList
	 * @param ssWeeklyRecordList
	 * @param ssWorklyPlanList
	 */
	public void saveMain(SsWeekReport ssWeekReport,List<SsWeeklySummary> ssWeeklySummaryList,List<SsWeeklyRecord> ssWeeklyRecordList,List<SsWorklyPlan> ssWorklyPlanList) ;
	
	/**
	 * 修改一对多
	 *
   * @param ssWeekReport
   * @param ssWeeklySummaryList
   * @param ssWeeklyRecordList
   * @param ssWorklyPlanList
	 */
	public void updateMain(SsWeekReport ssWeekReport,List<SsWeeklySummary> ssWeeklySummaryList,List<SsWeeklyRecord> ssWeeklyRecordList,List<SsWorklyPlan> ssWorklyPlanList);
	
	/**
	 * 删除一对多
	 *
	 * @param id
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 *
	 * @param idList
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
