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
 * @Description: 投标统计
 * @Author: jeecg-boot
 * @Date:   2025-11-12
 * @Version: V1.0
 */
@Data
@TableName("tender_statistics")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(description="投标统计")
public class TenderStatistics implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键")
    private java.lang.String id;
	/**项目名称*/
	@Excel(name = "项目名称", width = 15)
    @Schema(description = "项目名称")
    private java.lang.String projectName;
	/**项目类型*/
	@Excel(name = "项目类型", width = 15)
    @Schema(description = "项目类型")
    private java.lang.String projectType;
	/**项目分类*/
	@Excel(name = "项目分类", width = 15)
    @Schema(description = "项目分类")
    private java.lang.String projectCategory;
	/**投标时间*/
	@Excel(name = "投标时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Schema(description = "投标时间")
    private java.util.Date biddingDate;
	/**开标时间*/
	@Excel(name = "开标时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Schema(description = "开标时间")
    private java.util.Date bidOpeningDate;
	/**客户信息*/
	@Excel(name = "客户信息", width = 15)
    @Schema(description = "客户信息")
    private java.lang.String customerInfo;
	/**竞争对手信息*/
	@Excel(name = "竞争对手信息", width = 15)
    @Schema(description = "竞争对手信息")
    private java.lang.String competitorInfo;
	/**报价*/
	@Excel(name = "报价", width = 15)
    @Schema(description = "报价")
    private java.lang.Double quotation;
	/**技术方案得分*/
	@Excel(name = "技术方案得分", width = 15)
    @Schema(description = "技术方案得分")
    private java.lang.Double technicalScore;
	/**商务条款响应度*/
	@Excel(name = "商务条款响应度", width = 15)
    @Schema(description = "商务条款响应度")
    private java.lang.Double responsiveBusinessTerms;
	/**中标情况*/
	@Excel(name = "中标情况", width = 15)
    @Schema(description = "中标情况")
    private java.lang.String biddingSituation;
	/**中标金额*/
	@Excel(name = "中标金额", width = 15)
    @Schema(description = "中标金额")
    private java.lang.String winBidAmount;
	/**未中标原因*/
	@Excel(name = "未中标原因", width = 15)
    @Schema(description = "未中标原因")
    private java.lang.String reasonNotWinning;
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
