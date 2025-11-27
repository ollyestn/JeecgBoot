package org.jeecg.modules.demo.smartcity.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.query.QueryRuleEnum;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.airag.llm.entity.AiragKnowledgeDoc;
import org.jeecg.modules.airag.llm.service.IAiragKnowledgeDocService;
import org.jeecg.modules.demo.smartcity.entity.XfIndustryExchange;
import org.jeecg.modules.demo.smartcity.service.IXfIndustryExchangeService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.modules.demo.smartcity.utils.AiragUtils;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;
 /**
 * @Description: 同业交流统计
 * @Author: jeecg-boot
 * @Date:   2025-11-16
 * @Version: V1.0
 */
@Tag(name="同业交流统计")
@RestController
@RequestMapping("/smartcity/xfIndustryExchange")
@Slf4j
public class XfIndustryExchangeController extends JeecgController<XfIndustryExchange, IXfIndustryExchangeService> {
	@Autowired
	private IXfIndustryExchangeService xfIndustryExchangeService;

    @Autowired
    private IAiragKnowledgeDocService airagKnowledgeDocService;

	/**
	 * 分页列表查询
	 *
	 * @param xfIndustryExchange
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "同业交流统计-分页列表查询")
	@Operation(summary="同业交流统计-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<XfIndustryExchange>> queryPageList(XfIndustryExchange xfIndustryExchange,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {


        QueryWrapper<XfIndustryExchange> queryWrapper = QueryGenerator.initQueryWrapper(xfIndustryExchange, req.getParameterMap());
		Page<XfIndustryExchange> page = new Page<XfIndustryExchange>(pageNo, pageSize);
		IPage<XfIndustryExchange> pageList = xfIndustryExchangeService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param xfIndustryExchange
	 * @return
	 */
	@AutoLog(value = "同业交流统计-添加")
	@Operation(summary="同业交流统计-添加")
	@RequiresPermissions("smartcity:xf_industry_exchange:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody XfIndustryExchange xfIndustryExchange) {
		xfIndustryExchangeService.save(xfIndustryExchange);

        // 同业交流统计
        AiragUtils airagUtils = new AiragUtils(airagKnowledgeDocService);
        airagUtils.AddFiles(xfIndustryExchange.getFiles());

        return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param xfIndustryExchange
	 * @return
	 */
	@AutoLog(value = "同业交流统计-编辑")
	@Operation(summary="同业交流统计-编辑")
	@RequiresPermissions("smartcity:xf_industry_exchange:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody XfIndustryExchange xfIndustryExchange) {
		xfIndustryExchangeService.updateById(xfIndustryExchange);

        // 同业交流统计
        AiragUtils airagUtils = new AiragUtils(airagKnowledgeDocService);
        airagUtils.AddFiles(xfIndustryExchange.getFiles());

		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "同业交流统计-通过id删除")
	@Operation(summary="同业交流统计-通过id删除")
	@RequiresPermissions("smartcity:xf_industry_exchange:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		xfIndustryExchangeService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "同业交流统计-批量删除")
	@Operation(summary="同业交流统计-批量删除")
	@RequiresPermissions("smartcity:xf_industry_exchange:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.xfIndustryExchangeService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "同业交流统计-通过id查询")
	@Operation(summary="同业交流统计-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<XfIndustryExchange> queryById(@RequestParam(name="id",required=true) String id) {
		XfIndustryExchange xfIndustryExchange = xfIndustryExchangeService.getById(id);
		if(xfIndustryExchange==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(xfIndustryExchange);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param xfIndustryExchange
    */
    @RequiresPermissions("smartcity:xf_industry_exchange:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, XfIndustryExchange xfIndustryExchange) {
        return super.exportXls(request, xfIndustryExchange, XfIndustryExchange.class, "同业交流统计");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("smartcity:xf_industry_exchange:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, XfIndustryExchange.class);
    }

}
