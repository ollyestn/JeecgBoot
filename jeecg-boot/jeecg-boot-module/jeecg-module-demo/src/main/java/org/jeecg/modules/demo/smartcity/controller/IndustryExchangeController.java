package org.jeecg.modules.demo.smartcity.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.query.QueryRuleEnum;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.smartcity.entity.IndustryExchange;
import org.jeecg.modules.demo.smartcity.service.IIndustryExchangeService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

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
@RequestMapping("/smartcity/industryExchange")
@Slf4j
public class IndustryExchangeController extends JeecgController<IndustryExchange, IIndustryExchangeService> {
	@Autowired
	private IIndustryExchangeService industryExchangeService;
	
	/**
	 * 分页列表查询
	 *
	 * @param industryExchange
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "同业交流统计-分页列表查询")
	@Operation(summary="同业交流统计-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<IndustryExchange>> queryPageList(IndustryExchange industryExchange,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {


        // 自定义查询规则
        Map<String, QueryRuleEnum> customeRuleMap = new HashMap<>();
        // 自定义多选的查询规则为：LIKE_WITH_OR
        customeRuleMap.put("fileType", QueryRuleEnum.LIKE_WITH_OR);
        QueryWrapper<IndustryExchange> queryWrapper = QueryGenerator.initQueryWrapper(industryExchange, req.getParameterMap(),customeRuleMap);
		Page<IndustryExchange> page = new Page<IndustryExchange>(pageNo, pageSize);
		IPage<IndustryExchange> pageList = industryExchangeService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param industryExchange
	 * @return
	 */
	@AutoLog(value = "同业交流统计-添加")
	@Operation(summary="同业交流统计-添加")
	@RequiresPermissions("smartcity:industry_exchange:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody IndustryExchange industryExchange) {
		industryExchangeService.save(industryExchange);

		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param industryExchange
	 * @return
	 */
	@AutoLog(value = "同业交流统计-编辑")
	@Operation(summary="同业交流统计-编辑")
	@RequiresPermissions("smartcity:industry_exchange:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody IndustryExchange industryExchange) {
		industryExchangeService.updateById(industryExchange);
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
	@RequiresPermissions("smartcity:industry_exchange:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		industryExchangeService.removeById(id);
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
	@RequiresPermissions("smartcity:industry_exchange:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.industryExchangeService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<IndustryExchange> queryById(@RequestParam(name="id",required=true) String id) {
		IndustryExchange industryExchange = industryExchangeService.getById(id);
		if(industryExchange==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(industryExchange);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param industryExchange
    */
    @RequiresPermissions("smartcity:industry_exchange:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, IndustryExchange industryExchange) {
        return super.exportXls(request, industryExchange, IndustryExchange.class, "同业交流统计");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("smartcity:industry_exchange:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, IndustryExchange.class);
    }

}
