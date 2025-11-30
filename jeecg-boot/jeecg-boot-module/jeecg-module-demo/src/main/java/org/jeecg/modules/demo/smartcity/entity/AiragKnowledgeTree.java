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
 * @Description: 知识库目录
 * @Author: jeecg-boot
 * @Date:   2025-12-01
 * @Version: V1.0
 */
@Data
@TableName("airag_knowledge_tree")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(description="知识库目录")
public class AiragKnowledgeTree implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键")
    private java.lang.String id;
	/**节点名称*/
	@Excel(name = "节点名称", width = 15)
    @Schema(description = "节点名称")
    private java.lang.String name;
	/**父节点*/
	@Excel(name = "父节点", width = 15)
    @Schema(description = "父节点")
    private java.lang.String pid;
	/**层级*/
	@Excel(name = "层级", width = 15)
    @Schema(description = "层级")
    private java.lang.Integer level;
	/**知识库id*/
	@Excel(name = "知识库id", width = 15)
    @Schema(description = "知识库id")
    private java.lang.String knowledgeId;
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
