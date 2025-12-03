package org.jeecg.modules.demo.smartcity.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.modules.airag.llm.entity.AiragKnowledge;
import org.jeecg.modules.airag.llm.entity.AiragKnowledgeDoc;
import org.jeecg.modules.airag.llm.entity.AiragKnowledgeTree;
import org.jeecg.modules.airag.llm.service.IAiragKnowledgeDocService;
import org.jeecg.modules.airag.llm.service.IAiragKnowledgeService;
import org.jeecg.modules.airag.llm.service.IAiragKnowledgeTreeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AiragUtils {

    public IAiragKnowledgeDocService airagKnowledgeDocService;

    private IAiragKnowledgeService airagKnowledgeService;

    private IAiragKnowledgeTreeService airagKnowledgeTreeService;

//    private String knowId;

    //@Autowired
    //private ISysDictItemService sysDictItemService;

    // 构造函数
    public AiragUtils(IAiragKnowledgeDocService airagKnowledgeDocService, IAiragKnowledgeService airagKnowledgeService, IAiragKnowledgeTreeService airagKnowledgeTreeService) {
        this.airagKnowledgeDocService = airagKnowledgeDocService;
        this.airagKnowledgeService = airagKnowledgeService;
        this.airagKnowledgeTreeService = airagKnowledgeTreeService;
    }

    public boolean AddFiles(String filePath, String knowledgeName){
        if (filePath==null || filePath.isEmpty())
            return false;

        // 根据名称查询
        AiragKnowledge airagKnowledge = getKnowledgeByName(knowledgeName);
        if (airagKnowledge==null){
            return false;
        }

        String knowId = airagKnowledge.getId();

        AiragKnowledgeTree airagKnowledgeTree = getKnowledgeTreeByName(knowledgeName, knowId);
        if (airagKnowledgeTree==null){
            return false;
        }

        String nodeId = airagKnowledgeTree.getId();

        String[] files = filePath.split(",");
        for (String file : files) {
            AddFile(file, knowId, nodeId);
        }

        return true;
    }

    public boolean AddFile(String file, String knowId, String nodeId){
        if (file==null || file.isEmpty())
            return false;

        String fileName = file;
        int idx = fileName.lastIndexOf("/");
        if (idx != -1) {
            fileName = file.substring(idx+1);
        }

//        idx = fileName.lastIndexOf(".");
//        if (idx != -1) {
//            fileName = fileName.substring(0, idx);
//        }

        List<AiragKnowledgeDoc> docs = airagKnowledgeDocService.list(new QueryWrapper<AiragKnowledgeDoc>().eq("title", fileName).eq("knowledge_id", knowId).eq("node_id", nodeId));
        if (!docs.isEmpty()){
            return false;
        }

        JSONObject jo =  new JSONObject();
        jo.put("filePath", file);

        AiragKnowledgeDoc airagKnowledgeDoc = new AiragKnowledgeDoc();
        airagKnowledgeDoc.setTitle(fileName);
        airagKnowledgeDoc.setType("file");
        airagKnowledgeDoc.setMetadata(JSON.toJSONString(jo));
        // 知识库id写死：1993080661791707138 = “销服知识”
        airagKnowledgeDoc.setKnowledgeId(knowId); // "1993080661791707138"
        airagKnowledgeDoc.setNodeId(nodeId);
        airagKnowledgeDocService.editDocument(airagKnowledgeDoc);

        return true;
    }

    public AiragKnowledge getKnowledgeByName(String knowledgeName){
        // 根据名称查询
        QueryWrapper<AiragKnowledge> queryWrapper = new QueryWrapper<AiragKnowledge>();
        queryWrapper.eq("name", knowledgeName);
        List<AiragKnowledge> lst = airagKnowledgeService.list(queryWrapper);
        if (!lst.isEmpty()){
            return lst.get(0);
        }
        AiragKnowledge airagKnowledge = new AiragKnowledge();
        airagKnowledge.setName(knowledgeName);
        airagKnowledge.setDescr(knowledgeName);
        airagKnowledge.setEmbedId("1990221926264905729");  // ollama -- 1990221926264905729, 重新配置了会改变
        airagKnowledge.setStatus("enable");
        if (airagKnowledgeService.save(airagKnowledge)) {
            // 查询
            List<AiragKnowledgeTree> lsts = airagKnowledgeTreeService.list(new QueryWrapper<AiragKnowledgeTree>().eq("name", knowledgeName).eq("pid", "0"));
            if (lsts.isEmpty()){
                AiragKnowledgeTree airagKnowledgeTree = new AiragKnowledgeTree();
                airagKnowledgeTree.setKnowledgeId(airagKnowledge.getId());
                airagKnowledgeTree.setName(airagKnowledge.getName());
                airagKnowledgeTree.setLevel(1);
                airagKnowledgeTree.setPid("0");
                airagKnowledgeTreeService.save(airagKnowledgeTree);
            }

            return airagKnowledge;
        }

        return null;
    }

    public AiragKnowledgeTree getKnowledgeTreeByName(String knowledgeName, String knowId){
        AiragKnowledgeTree airagKnowledgeTree = null;
        // 查询
        List<AiragKnowledgeTree> lsts = airagKnowledgeTreeService.list(new QueryWrapper<AiragKnowledgeTree>().eq("name", knowledgeName).eq("pid", "0"));
        if (lsts.isEmpty()){
            airagKnowledgeTree = new AiragKnowledgeTree();
            airagKnowledgeTree.setKnowledgeId(knowId);
            airagKnowledgeTree.setName(knowledgeName);
            airagKnowledgeTree.setLevel(1);
            airagKnowledgeTree.setPid("0");
            airagKnowledgeTreeService.save(airagKnowledgeTree);
        }
        else {
            airagKnowledgeTree = lsts.get(0);
        }

        return airagKnowledgeTree;
    }
}
