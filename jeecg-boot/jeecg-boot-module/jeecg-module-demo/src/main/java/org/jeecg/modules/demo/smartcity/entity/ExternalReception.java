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
 * @Description: 对外接待
 * @Author: jeecg-boot
 * @Date:   2025-11-12
 * @Version: V1.0
 */
@Data
@TableName("external_reception")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(description="对外接待")
public class ExternalReception implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键")
    private java.lang.String id;
	/**来访日期*/
	@Excel(name = "来访日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Schema(description = "来访日期")
    private java.util.Date visitDate;
	/**来访单位全称*/
	@Excel(name = "来访单位全称", width = 15)
    @Schema(description = "来访单位全称")
    private java.lang.String visitOrgName;
	/**来访主要领导及职务*/
	@Excel(name = "来访主要领导及职务", width = 15)
    @Schema(description = "来访主要领导及职务")
    private java.lang.String leadersAndPositions;
	/**我司参加接待领导及职务*/
	@Excel(name = "我司参加接待领导及职务", width = 15)
    @Schema(description = "我司参加接待领导及职务")
    private java.lang.String ourLeadersAndPositions;
	/**来访目的*/
	@Excel(name = "来访目的", width = 15)
    @Schema(description = "来访目的")
    private java.lang.String visitPurpose;
	/**实际效果（简述）*/
	@Excel(name = "实际效果（简述）", width = 15)
    @Schema(description = "实际效果（简述）")
    private java.lang.String actualEffect;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @Schema(description = "备注")
    private java.lang.String note;
	/**所属部门*/
    @Schema(description = "所属部门")
    private java.lang.String sysOrgCode;
	/**创建人*/
    @Schema(description = "创建人")
    private java.lang.String createBy;
	/**更新人*/
    @Schema(description = "更新人")
    private java.lang.String updateBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建日期")
    private java.util.Date createTime;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Schema(description = "更新日期")
    private java.util.Date updateTime;
}
