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
 * @Description: 同业交流统计
 * @Author: jeecg-boot
 * @Date:   2025-11-16
 * @Version: V1.0
 */
@Data
@TableName("industry_exchange")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(description="同业交流统计")
public class IndustryExchange implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键")
    private java.lang.String id;
	/**公司名称*/
	@Excel(name = "公司名称", width = 15)
    @Schema(description = "公司名称")
    private java.lang.String otherCompany;
	/**行业*/
	@Excel(name = "行业", width = 15)
    @Schema(description = "行业")
    private java.lang.String industry;
	/**文件类型*/
	@Excel(name = "文件类型", width = 15, dicCode = "file_type")
	@Dict(dicCode = "file_type")
    @Schema(description = "文件类型")
    private java.lang.String fileType;
	/**附件*/
	@Excel(name = "附件", width = 15)
    @Schema(description = "附件")
    private java.lang.String files;
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
	/**备注*/
	@Excel(name = "备注", width = 15)
    @Schema(description = "备注")
    private java.lang.String memo;
}
