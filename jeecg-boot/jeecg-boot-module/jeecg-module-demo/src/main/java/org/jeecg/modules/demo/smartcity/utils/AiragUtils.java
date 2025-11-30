package org.jeecg.modules.demo.smartcity.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.modules.airag.llm.entity.AiragKnowledge;
import org.jeecg.modules.airag.llm.entity.AiragKnowledgeDoc;
import org.jeecg.modules.airag.llm.service.IAiragKnowledgeDocService;
import org.jeecg.modules.airag.llm.service.IAiragKnowledgeService;

public class AiragUtils {

    public IAiragKnowledgeDocService airagKnowledgeDocService;

    private IAiragKnowledgeService airagKnowledgeService;

    private String knowId;

    // 构造函数
    public AiragUtils(IAiragKnowledgeDocService airagKnowledgeDocService, IAiragKnowledgeService airagKnowledgeService) {
        this.airagKnowledgeDocService = airagKnowledgeDocService;
        this.airagKnowledgeService = airagKnowledgeService;
    }

    public boolean AddFiles(String filePath, String knowledgeName){
        if (filePath==null || filePath.isEmpty())
            return false;

        // 根据名称查询
        QueryWrapper<AiragKnowledge> queryWrapper = new QueryWrapper<AiragKnowledge>();
        queryWrapper.ge("name", knowledgeName);
        AiragKnowledge airagKnowledge = airagKnowledgeService.getOne(queryWrapper);
        if (airagKnowledge==null){
            return false;
        }

        knowId = airagKnowledge.getId();

        String[] files = filePath.split(",");
        for (String file : files) {
            AddFile(file);
        }

        return true;
    }

    public boolean AddFile(String file){
        if (file==null || file.isEmpty())
            return false;

        String fileName = file;
        int idx = fileName.lastIndexOf("/");
        if (idx != -1) {
            fileName = file.substring(idx+1);
        }

        idx = fileName.lastIndexOf(".");
        if (idx != -1) {
            fileName = fileName.substring(0, idx);
        }

        JSONObject jo =  new JSONObject();
        jo.put("filePath", file);

        AiragKnowledgeDoc airagKnowledgeDoc = new AiragKnowledgeDoc();
        airagKnowledgeDoc.setTitle(fileName);
        airagKnowledgeDoc.setType("file");
        airagKnowledgeDoc.setMetadata(JSON.toJSONString(jo));
        // 知识库id写死：1993080661791707138 = “销服知识”
        airagKnowledgeDoc.setKnowledgeId(knowId); // "1993080661791707138"
        airagKnowledgeDocService.editDocument(airagKnowledgeDoc);

        return true;
    }
}
