package org.jeecg.modules.demo.smartcity.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.vo.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.query.QueryRuleEnum;
import org.jeecg.common.util.oConvertUtils;
import org.jeecgframework.poi.excel.entity.enmus.ExcelType;
import org.jeecg.modules.demo.smartcity.entity.SsWeeklySummary;
import org.jeecg.modules.demo.smartcity.entity.SsWeeklyRecord;
import org.jeecg.modules.demo.smartcity.entity.SsWorklyPlan;
import org.jeecg.modules.demo.smartcity.entity.SsWeekReport;
import org.jeecg.modules.demo.smartcity.vo.SsWeekReportPage;
import org.jeecg.modules.demo.smartcity.service.ISsWeekReportService;
import org.jeecg.modules.demo.smartcity.service.ISsWeeklySummaryService;
import org.jeecg.modules.demo.smartcity.service.ISsWeeklyRecordService;
import org.jeecg.modules.demo.smartcity.service.ISsWorklyPlanService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.alibaba.fastjson.JSONObject;


 /**
 * @Description: 周报
 * @Author: jeecg-boot
 * @Date:   2025-12-22
 * @Version: V1.0
 */
@Tag(name="周报")
@RestController
@RequestMapping("/smartcity/ssWeekReport")
@Slf4j
public class SsWeekReportController {
	@Autowired
	private ISsWeekReportService ssWeekReportService;
	@Autowired
	private ISsWeeklySummaryService ssWeeklySummaryService;
	@Autowired
	private ISsWeeklyRecordService ssWeeklyRecordService;
	@Autowired
	private ISsWorklyPlanService ssWorklyPlanService;
	
	/**
	 * AI转写接口
	 *
	 * @param content
	 * @return
	 */
	@AutoLog(value = "周报-AI转写")
	@Operation(summary="周报-AI转写")
	@PostMapping(value = "/aiTranslate")
// 1.参数附在url后的形式：/aiTranslate?content="xxx"
//	public Result<String> aiTranslate(@RequestParam(name="content", required = true) String content) {
// 2.使用dto的方式：
// @Data
//public class AiTranslateRequest {
//    private String content;
//}
//  public Result<String> aiTranslate(@RequestBody AiTranslateRequest request) {
// 3.不使用dto，则使用map
//  public Result<String> aiTranslate(@RequestBody Map<String, String> param) {
//
// 4.两种方式都可以：
// @PostMapping(value = "/aiTranslate")
//public Result<String> aiTranslate(@RequestParam(value = "content", required = false) String content,
//                                  @RequestBody(required = false) Map<String, String> body) {
//    String finalContent = content;
//    if (finalContent == null && body != null) {
//        finalContent = body.get("content");
//    }
//
//    if (finalContent == null || finalContent.trim().isEmpty()) {
//        return Result.fail("内容不能为空");
//    }
//
//    // 处理逻辑
//    return Result.ok(result);
//}
	public Result<String> aiTranslate(@RequestBody Map<String, String> param) {
		try {
			// Ollama服务地址
			String ollamaUrl = "http://172.16.0.176:6868/api/generate";
			String content = param.get("content");

			// 构造请求参数
			JSONObject requestBody = new JSONObject();
			requestBody.put("model", "qwen3:8b");
			requestBody.put("prompt", "请将以下工作内容转换为格式化的周报内容，每件事一行，格式如：1）星期一，12月1日，拜访内蒙古医院姚主任。\n\n" + content);
			requestBody.put("stream", false);
			
			// 发送请求到Ollama
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> request = new HttpEntity<>(requestBody.toJSONString(), headers);
			
			ResponseEntity<String> response = restTemplate.postForEntity(ollamaUrl, request, String.class);
			
			if (response.getStatusCode().is2xxSuccessful()) {
				JSONObject jsonResponse = JSONObject.parseObject(response.getBody());
				String result = jsonResponse.getString("response");
				
				// 处理AI返回结果，剔除不必要的段落内容
				if (result != null) {
					// 剔除以```开始和结束的内容块
					//result = result.replaceAll("<think>.*?</think>", "");

                    int thinkStart = result.indexOf("<think>");
                    int thinkEnd = result.indexOf("</think>", thinkStart+1);
                    if (thinkStart >= 0 && thinkEnd >= 0) {
                        result =  result.substring(thinkEnd + 8);
                    }

					// 剔除可能存在的markdown链接格式内容
					result = result.replaceAll("(?i)\\[.*?\\]\\(.*?\\)", "");
					// 剔除多余的空白行和特殊字符
					result = result.replaceAll("\n{3,}", "\n\n");
					result = result.trim();
				}

                log.info("AI转写结果：" + result);
				return Result.OK(result);
			} else {
				return Result.error("AI转写服务调用失败");
			}
		} catch (Exception e) {
			log.error("AI转写异常：", e);
			return Result.error("AI转写异常：" + e.getMessage());
		}
	}
	
	/**
	 * 分页列表查询
	 *
	 * @param ssWeekReport
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "周报-分页列表查询")
	@Operation(summary="周报-分页列表查询")
	@GetMapping(value = "/list")
    //@PermissionData(pageComponent="smartcity/SsWeekReport/SsWeekReportList")
	public Result<IPage<SsWeekReport>> queryPageList(SsWeekReport ssWeekReport,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
        QueryWrapper<SsWeekReport> queryWrapper = QueryGenerator.initQueryWrapper(ssWeekReport, req.getParameterMap());
		Page<SsWeekReport> page = new Page<SsWeekReport>(pageNo, pageSize);
		IPage<SsWeekReport> pageList = ssWeekReportService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param ssWeekReportPage
	 * @return
	 */
	@AutoLog(value = "周报-添加")
	@Operation(summary="周报-添加")
    @RequiresPermissions("smartcity:ss_week_report:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody SsWeekReportPage ssWeekReportPage) {
		SsWeekReport ssWeekReport = new SsWeekReport();
		BeanUtils.copyProperties(ssWeekReportPage, ssWeekReport);
		ssWeekReportService.saveMain(ssWeekReport, ssWeekReportPage.getSsWeeklySummaryList(),ssWeekReportPage.getSsWeeklyRecordList(),ssWeekReportPage.getSsWorklyPlanList());
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param ssWeekReportPage
	 * @return
	 */
	@AutoLog(value = "周报-编辑")
	@Operation(summary="周报-编辑")
    @RequiresPermissions("smartcity:ss_week_report:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody SsWeekReportPage ssWeekReportPage) {
		SsWeekReport ssWeekReport = new SsWeekReport();
		BeanUtils.copyProperties(ssWeekReportPage, ssWeekReport);
		SsWeekReport ssWeekReportEntity = ssWeekReportService.getById(ssWeekReport.getId());
		if(ssWeekReportEntity==null) {
			return Result.error("未找到对应数据");
		}
		ssWeekReportService.updateMain(ssWeekReport, ssWeekReportPage.getSsWeeklySummaryList(),ssWeekReportPage.getSsWeeklyRecordList(),ssWeekReportPage.getSsWorklyPlanList());
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "周报-通过id删除")
	@Operation(summary="周报-通过id删除")
    @RequiresPermissions("smartcity:ss_week_report:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		ssWeekReportService.delMain(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "周报-批量删除")
	@Operation(summary="周报-批量删除")
    @RequiresPermissions("smartcity:ss_week_report:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ssWeekReportService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "周报-通过id查询")
	@Operation(summary="周报-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<SsWeekReport> queryById(@RequestParam(name="id",required=true) String id) {
		SsWeekReport ssWeekReport = ssWeekReportService.getById(id);
		if(ssWeekReport==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(ssWeekReport);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "本周总结通过主表ID查询")
	@Operation(summary="本周总结主表ID查询")
	@GetMapping(value = "/querySsWeeklySummaryByMainId")
	public Result<List<SsWeeklySummary>> querySsWeeklySummaryListByMainId(@RequestParam(name="id",required=true) String id) {
		List<SsWeeklySummary> ssWeeklySummaryList = ssWeeklySummaryService.selectByMainId(id);
		return Result.OK(ssWeeklySummaryList);
	}
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "本周工作记录通过主表ID查询")
	@Operation(summary="本周工作记录主表ID查询")
	@GetMapping(value = "/querySsWeeklyRecordByMainId")
	public Result<List<SsWeeklyRecord>> querySsWeeklyRecordListByMainId(@RequestParam(name="id",required=true) String id) {
		List<SsWeeklyRecord> ssWeeklyRecordList = ssWeeklyRecordService.selectByMainId(id);
		return Result.OK(ssWeeklyRecordList);
	}
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "下周工作计划通过主表ID查询")
	@Operation(summary="下周工作计划主表ID查询")
	@GetMapping(value = "/querySsWorklyPlanByMainId")
	public Result<List<SsWorklyPlan>> querySsWorklyPlanListByMainId(@RequestParam(name="id",required=true) String id) {
		List<SsWorklyPlan> ssWorklyPlanList = ssWorklyPlanService.selectByMainId(id);
		return Result.OK(ssWorklyPlanList);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param ssWeekReport
    */
    @RequiresPermissions("smartcity:ss_week_report:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SsWeekReport ssWeekReport) {

      // Step.1 组装查询条件查询数据
      QueryWrapper<SsWeekReport> queryWrapper = QueryGenerator.initQueryWrapper(ssWeekReport, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //配置选中数据查询条件
      String selections = request.getParameter("selections");
      if(oConvertUtils.isNotEmpty(selections)) {
         List<String> selectionList = Arrays.asList(selections.split(","));
         queryWrapper.in("id",selectionList);
      }
      //Step.2 获取导出数据
      List<SsWeekReport> ssWeekReportList = ssWeekReportService.list(queryWrapper);

      // Step.3 组装pageList
      List<SsWeekReportPage> pageList = new ArrayList<SsWeekReportPage>();
      for (SsWeekReport main : ssWeekReportList) {
          SsWeekReportPage vo = new SsWeekReportPage();
          BeanUtils.copyProperties(main, vo);
          List<SsWeeklySummary> ssWeeklySummaryList = ssWeeklySummaryService.selectByMainId(main.getId());
          vo.setSsWeeklySummaryList(ssWeeklySummaryList);
          List<SsWeeklyRecord> ssWeeklyRecordList = ssWeeklyRecordService.selectByMainId(main.getId());
          vo.setSsWeeklyRecordList(ssWeeklyRecordList);
          List<SsWorklyPlan> ssWorklyPlanList = ssWorklyPlanService.selectByMainId(main.getId());
          vo.setSsWorklyPlanList(ssWorklyPlanList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "周报列表");
      mv.addObject(NormalExcelConstants.CLASS, SsWeekReportPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("周报数据", "导出人:"+sysUser.getRealname(), "周报", ExcelType.XSSF));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
    }

    /**
    * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("smartcity:ss_week_report:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          // 获取上传文件对象
          MultipartFile file = entity.getValue();
          ImportParams params = new ImportParams();
          params.setTitleRows(2);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<SsWeekReportPage> list = ExcelImportUtil.importExcel(file.getInputStream(), SsWeekReportPage.class, params);
              for (SsWeekReportPage page : list) {
                  SsWeekReport po = new SsWeekReport();
                  BeanUtils.copyProperties(page, po);
                  ssWeekReportService.saveMain(po, page.getSsWeeklySummaryList(),page.getSsWeeklyRecordList(),page.getSsWorklyPlanList());
              }
              return Result.OK("文件导入成功！数据行数:" + list.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.OK("文件导入失败！");
    }

  /**
   * 提交周报，将状态设置为1
   *
   * @param id
   * @return
   */
  @AutoLog(value = "周报-提交")
  @Operation(summary="周报-提交")
  @PostMapping(value = "/commit")
  @RequiresPermissions("smartcity:ss_week_report:edit")
//  public Result<String> commit(@RequestParam(name="id", required=true) String id) {
  public Result<String> commit(@RequestBody(required = false) Map<String, String> body) {
    String id = body.get("id");
    if (StringUtils.isBlank(id)) {
        return Result.error("参数错误！");
    }

    try {
      SsWeekReport weekReport = ssWeekReportService.getById(id);
      if (weekReport == null) {
        return Result.error("未找到对应数据");
      }
      
      // 设置状态为1（已提交）
      weekReport.setStatus(1);
      ssWeekReportService.updateById(weekReport);
      
      return Result.OK("提交成功");
    } catch (Exception e) {
      log.error("提交周报异常：", e);
      return Result.error("提交失败：" + e.getMessage());
    }
  }

}
