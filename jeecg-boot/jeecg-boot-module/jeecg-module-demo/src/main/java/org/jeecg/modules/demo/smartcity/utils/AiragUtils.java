package org.jeecg.modules.demo.smartcity.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.jeecg.modules.airag.llm.entity.AiragKnowledgeDoc;
import org.jeecg.modules.airag.llm.service.IAiragKnowledgeDocService;

public class AiragUtils {

    public IAiragKnowledgeDocService airagKnowledgeDocService;

    // 构造函数
    public AiragUtils(IAiragKnowledgeDocService airagKnowledgeDocService) {
        this.airagKnowledgeDocService = airagKnowledgeDocService;
    }

    public boolean AddFiles(String filePath){
        if (filePath==null || filePath.isEmpty())
            return false;

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
        airagKnowledgeDoc.setKnowledgeId("1993080661791707138");
        airagKnowledgeDocService.editDocument(airagKnowledgeDoc);

        return true;
    }
}
