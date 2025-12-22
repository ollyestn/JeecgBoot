package org.jeecg.modules.demo.smartcity.controller;

import java.util.Arrays;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.airag.llm.service.IAiragKnowledgeDocService;
import org.jeecg.modules.airag.llm.service.IAiragKnowledgeService;
import org.jeecg.modules.airag.llm.service.IAiragKnowledgeTreeService;
import org.jeecg.modules.demo.smartcity.entity.ExternalReception;
import org.jeecg.modules.demo.smartcity.service.IExternalReceptionService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.modules.demo.smartcity.utils.AiragUtils;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;
 /**
 * @Description: 客户接待统计
 * @Author: jeecg-boot
 * @Date:   2025-11-16
 * @Version: V1.0
 */
@Tag(name="客户接待统计")
@RestController
@RequestMapping("/smartcity/externalReception")
@Slf4j
public class ExternalReceptionController extends JeecgController<ExternalReception, IExternalReceptionService> {
	@Autowired
	private IExternalReceptionService externalReceptionService;

     @Autowired
     private IAiragKnowledgeDocService airagKnowledgeDocService;

     @Autowired
     private IAiragKnowledgeService airagKnowledgeService;

     @Autowired
     private IAiragKnowledgeTreeService airagKnowledgeTreeService;

	/**
	 * 分页列表查询
	 *
	 * @param externalReception
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "客户接待统计-分页列表查询")
	@Operation(summary="客户接待统计-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<ExternalReception>> queryPageList(ExternalReception externalReception,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {


        QueryWrapper<ExternalReception> queryWrapper = QueryGenerator.initQueryWrapper(externalReception, req.getParameterMap());
		Page<ExternalReception> page = new Page<ExternalReception>(pageNo, pageSize);
		IPage<ExternalReception> pageList = externalReceptionService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param externalReception
	 * @return
	 */
	@AutoLog(value = "客户接待统计-添加")
	@Operation(summary="客户接待统计-添加")
	@RequiresPermissions("smartcity:external_reception:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody ExternalReception externalReception) {
		externalReceptionService.save(externalReception);

        AiragUtils airagUtils = new AiragUtils(airagKnowledgeDocService, airagKnowledgeService, airagKnowledgeTreeService);
        airagUtils.AddFiles(externalReception.getVisitFiles(), "客户接待统计");

		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param externalReception
	 * @return
	 */
	@AutoLog(value = "客户接待统计-编辑")
	@Operation(summary="客户接待统计-编辑")
	@RequiresPermissions("smartcity:external_reception:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody ExternalReception externalReception) {
		externalReceptionService.updateById(externalReception);

        AiragUtils airagUtils = new AiragUtils(airagKnowledgeDocService, airagKnowledgeService, airagKnowledgeTreeService);
        airagUtils.AddFiles(externalReception.getVisitFiles(), "客户接待统计");

		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户接待统计-通过id删除")
	@Operation(summary="客户接待统计-通过id删除")
	@RequiresPermissions("smartcity:external_reception:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		externalReceptionService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "客户接待统计-批量删除")
	@Operation(summary="客户接待统计-批量删除")
	@RequiresPermissions("smartcity:external_reception:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.externalReceptionService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "客户接待统计-通过id查询")
	@Operation(summary="客户接待统计-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<ExternalReception> queryById(@RequestParam(name="id",required=true) String id) {
		ExternalReception externalReception = externalReceptionService.getById(id);
		if(externalReception==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(externalReception);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param externalReception
    */
    @RequiresPermissions("smartcity:external_reception:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ExternalReception externalReception) {
        return super.exportXls(request, externalReception, ExternalReception.class, "客户接待统计");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("smartcity:external_reception:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, ExternalReception.class);
    }

}
