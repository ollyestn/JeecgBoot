package org.jeecg.modules.airag.llm.controller;

import java.util.Arrays;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.airag.llm.entity.AiragKnowledgeTree;
import org.jeecg.modules.airag.llm.service.IAiragKnowledgeTreeService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;

 /**
 * @Description: 知识库目录
 * @Author: jeecg-boot
 * @Date:   2025-12-01
 * @Version: V1.0
 */
@Tag(name="知识库目录")
@RestController
@RequestMapping("/smartcity/airagKnowledgeTree")
@Slf4j
public class AiragKnowledgeTreeController extends JeecgController<AiragKnowledgeTree, IAiragKnowledgeTreeService> {
	@Autowired
	private IAiragKnowledgeTreeService airagKnowledgeTreeService;
	
	/**
	 * 分页列表查询
	 *
	 * @param airagKnowledgeTree
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "知识库目录-分页列表查询")
	@Operation(summary="知识库目录-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<AiragKnowledgeTree>> queryPageList(AiragKnowledgeTree airagKnowledgeTree,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {


        QueryWrapper<AiragKnowledgeTree> queryWrapper = QueryGenerator.initQueryWrapper(airagKnowledgeTree, req.getParameterMap());
		Page<AiragKnowledgeTree> page = new Page<AiragKnowledgeTree>(pageNo, pageSize);
		IPage<AiragKnowledgeTree> pageList = airagKnowledgeTreeService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param airagKnowledgeTree
	 * @return
	 */
	@AutoLog(value = "知识库目录-添加")
	@Operation(summary="知识库目录-添加")
	@RequiresPermissions("smartcity:airag_knowledge_tree:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody AiragKnowledgeTree airagKnowledgeTree) {
		airagKnowledgeTreeService.save(airagKnowledgeTree);

		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param airagKnowledgeTree
	 * @return
	 */
	@AutoLog(value = "知识库目录-编辑")
	@Operation(summary="知识库目录-编辑")
	@RequiresPermissions("smartcity:airag_knowledge_tree:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody AiragKnowledgeTree airagKnowledgeTree) {
		airagKnowledgeTreeService.updateById(airagKnowledgeTree);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "知识库目录-通过id删除")
	@Operation(summary="知识库目录-通过id删除")
	@RequiresPermissions("smartcity:airag_knowledge_tree:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		airagKnowledgeTreeService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "知识库目录-批量删除")
	@Operation(summary="知识库目录-批量删除")
	@RequiresPermissions("smartcity:airag_knowledge_tree:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.airagKnowledgeTreeService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "知识库目录-通过id查询")
	@Operation(summary="知识库目录-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<AiragKnowledgeTree> queryById(@RequestParam(name="id",required=true) String id) {
		AiragKnowledgeTree airagKnowledgeTree = airagKnowledgeTreeService.getById(id);
		if(airagKnowledgeTree==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(airagKnowledgeTree);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param airagKnowledgeTree
    */
    @RequiresPermissions("smartcity:airag_knowledge_tree:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, AiragKnowledgeTree airagKnowledgeTree) {
        return super.exportXls(request, airagKnowledgeTree, AiragKnowledgeTree.class, "知识库目录");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("smartcity:airag_knowledge_tree:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, AiragKnowledgeTree.class);
    }

    /**
     * 根据知识库ID获取目录树结构
     *
     * @param knowledgeId 知识库ID
     * @return
     */
    @Operation(summary="根据知识库ID获取目录树结构")
    @GetMapping(value = "/tree")
    public Result<List<AiragKnowledgeTree>> queryTreeByKnowledgeId(@RequestParam(name="knowledgeId",required=true) String knowledgeId) {
        QueryWrapper<AiragKnowledgeTree> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("knowledge_id", knowledgeId);
        queryWrapper.orderByAsc("level", "pid");
        List<AiragKnowledgeTree> treeList = airagKnowledgeTreeService.list(queryWrapper);
        return Result.OK(treeList);
    }
}