package org.jeecg.modules.demo.smartcity.vo;

import java.util.List;
import org.jeecg.modules.demo.smartcity.entity.SsWeekReport;
import org.jeecg.modules.demo.smartcity.entity.SsWeeklySummary;
import org.jeecg.modules.demo.smartcity.entity.SsWeeklyRecord;
import org.jeecg.modules.demo.smartcity.entity.SsWorklyPlan;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelEntity;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecg.common.constant.ProvinceCityArea;
import org.jeecg.common.util.SpringContextUtils;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @Description: 周报
 * @Author: jeecg-boot
 * @Date:   2025-12-22
 * @Version: V1.0
 */
@Data
@Schema(description="周报")
public class SsWeekReportPage {

	/**主键*/
	@Schema(description = "主键")
    private java.lang.String id;
	/**姓名*/
	@Excel(name = "姓名", width = 15)
	@Schema(description = "姓名")
    private java.lang.String name;
	/**日期*/
	@Excel(name = "日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@Schema(description = "日期")
    private java.util.Date day;
	/**周*/
	@Excel(name = "周", width = 15)
	@Schema(description = "周")
    private java.lang.Integer week;
	/**创建人*/
	@Schema(description = "创建人")
    private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Schema(description = "创建日期")
    private java.util.Date createTime;
	/**更新人*/
	@Schema(description = "更新人")
    private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Schema(description = "更新日期")
    private java.util.Date updateTime;
	/**所属部门*/
	@Schema(description = "所属部门")
    private java.lang.String sysOrgCode;

	@ExcelCollection(name="本周总结")
	@Schema(description = "本周总结")
	private List<SsWeeklySummary> ssWeeklySummaryList;
	@ExcelCollection(name="本周工作记录")
	@Schema(description = "本周工作记录")
	private List<SsWeeklyRecord> ssWeeklyRecordList;
	@ExcelCollection(name="下周工作计划")
	@Schema(description = "下周工作计划")
	private List<SsWorklyPlan> ssWorklyPlanList;

}
