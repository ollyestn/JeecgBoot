package org.jeecg.modules.demo.smartcity.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableLogic;
import org.jeecg.common.constant.ProvinceCityArea;
import org.jeecg.common.util.SpringContextUtils;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 培训统计
 * @Author: jeecg-boot
 * @Date:   2025-11-04
 * @Version: V1.0
 */
@Data
@TableName("sales_training")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(description="培训统计")
public class SalesTraining implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键")
    private java.lang.String id;
	/**时间*/
	@Excel(name = "时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Schema(description = "时间")
    private java.util.Date trainingTime;
	/**课题*/
	@Excel(name = "课题", width = 15)
    @Schema(description = "课题")
    private java.lang.String trainingTopic;
	/**主要内容*/
	@Excel(name = "主要内容", width = 15)
    @Schema(description = "主要内容")
    private java.lang.String mainContent;
	/**组织形式*/
	@Excel(name = "组织形式", width = 15)
    @Schema(description = "组织形式")
    private java.lang.String traingType;
	/**主讲人*/
	@Excel(name = "主讲人", width = 15)
    @Schema(description = "主讲人")
    private java.lang.String presenter;
	/**参加人员*/
	@Excel(name = "参加人员", width = 15)
    @Schema(description = "参加人员")
    private java.lang.String participants;
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
}
