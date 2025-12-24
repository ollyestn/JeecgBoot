package org.jeecg.modules.demo.smartcity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.modules.demo.smartcity.dataauth.CustomDataPermissionHandler;
import org.jeecg.modules.demo.smartcity.entity.SsWeekReport;
import org.jeecg.modules.demo.smartcity.entity.SsWeeklySummary;
import org.jeecg.modules.demo.smartcity.entity.SsWeeklyRecord;
import org.jeecg.modules.demo.smartcity.entity.SsWorklyPlan;
import org.jeecg.modules.demo.smartcity.mapper.SsWeeklySummaryMapper;
import org.jeecg.modules.demo.smartcity.mapper.SsWeeklyRecordMapper;
import org.jeecg.modules.demo.smartcity.mapper.SsWorklyPlanMapper;
import org.jeecg.modules.demo.smartcity.mapper.SsWeekReportMapper;
import org.jeecg.modules.demo.smartcity.service.ISsWeekReportService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;
import java.util.Map;

/**
 * @Description: 周报
 * @Author: jeecg-boot
 * @Date:   2025-12-22
 * @Version: V1.0
 */
@Service
public class SsWeekReportServiceImpl extends ServiceImpl<SsWeekReportMapper, SsWeekReport> implements ISsWeekReportService {

    @Autowired
    private SsWeekReportMapper ssWeekReportMapper;
    @Autowired
    private SsWeeklySummaryMapper ssWeeklySummaryMapper;
    @Autowired
    private SsWeeklyRecordMapper ssWeeklyRecordMapper;
    @Autowired
    private SsWorklyPlanMapper ssWorklyPlanMapper;
    @Autowired
    private CustomDataPermissionHandler dataPermissionHandler;

    @Override
    public IPage<SsWeekReport> page(Page<SsWeekReport> page, QueryWrapper<SsWeekReport> queryWrapper) {
        // 先添加数据权限条件
        addDataPermissionConditions(queryWrapper);

        return super.page(page, queryWrapper);
    }

    @Override
    public List<SsWeekReport> list(QueryWrapper<SsWeekReport> queryWrapper) {
        // 先添加数据权限条件
        addDataPermissionConditions(queryWrapper);

        return super.list(queryWrapper);
    }

    /**
     * 将源wrapper的条件复制到目标wrapper
     */
    private void addDataPermissionConditions(QueryWrapper<SsWeekReport> wrapper) {
        // 获取当前登录用户
        LoginUser currentUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        if (currentUser == null) return;

        String username = currentUser.getUsername();
        boolean isAdmin = dataPermissionHandler.isSysAdmin(username);

        if (isAdmin) {
            wrapper.and(wq -> wq
                    .eq("status", 1)
                    .or()
                    .eq("create_by", username)
            );
        }
        else {
            wrapper.eq("create_by", username);
        }
    }

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveMain(SsWeekReport ssWeekReport, List<SsWeeklySummary> ssWeeklySummaryList,List<SsWeeklyRecord> ssWeeklyRecordList,List<SsWorklyPlan> ssWorklyPlanList) {
		ssWeekReportMapper.insert(ssWeekReport);
		if(ssWeeklySummaryList!=null && ssWeeklySummaryList.size()>0) {
			for(SsWeeklySummary entity:ssWeeklySummaryList) {
				//外键设置
				entity.setOwnerId(ssWeekReport.getId());
				ssWeeklySummaryMapper.insert(entity);
			}
		}
		if(ssWeeklyRecordList!=null && ssWeeklyRecordList.size()>0) {
			for(SsWeeklyRecord entity:ssWeeklyRecordList) {
				//外键设置
				entity.setOwnerId(ssWeekReport.getId());
				ssWeeklyRecordMapper.insert(entity);
			}
		}
		if(ssWorklyPlanList!=null && ssWorklyPlanList.size()>0) {
			for(SsWorklyPlan entity:ssWorklyPlanList) {
				//外键设置
				entity.setOwnerId(ssWeekReport.getId());
				ssWorklyPlanMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateMain(SsWeekReport ssWeekReport,List<SsWeeklySummary> ssWeeklySummaryList,List<SsWeeklyRecord> ssWeeklyRecordList,List<SsWorklyPlan> ssWorklyPlanList) {
		ssWeekReportMapper.updateById(ssWeekReport);
		
		//1.先删除子表数据
		ssWeeklySummaryMapper.deleteByMainId(ssWeekReport.getId());
		ssWeeklyRecordMapper.deleteByMainId(ssWeekReport.getId());
		ssWorklyPlanMapper.deleteByMainId(ssWeekReport.getId());
		
		//2.子表数据重新插入
		if(ssWeeklySummaryList!=null && ssWeeklySummaryList.size()>0) {
			for(SsWeeklySummary entity:ssWeeklySummaryList) {
				//外键设置
				entity.setOwnerId(ssWeekReport.getId());
				ssWeeklySummaryMapper.insert(entity);
			}
		}
		if(ssWeeklyRecordList!=null && ssWeeklyRecordList.size()>0) {
			for(SsWeeklyRecord entity:ssWeeklyRecordList) {
				//外键设置
				entity.setOwnerId(ssWeekReport.getId());
				ssWeeklyRecordMapper.insert(entity);
			}
		}
		if(ssWorklyPlanList!=null && ssWorklyPlanList.size()>0) {
			for(SsWorklyPlan entity:ssWorklyPlanList) {
				//外键设置
				entity.setOwnerId(ssWeekReport.getId());
				ssWorklyPlanMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delMain(String id) {
		ssWeeklySummaryMapper.deleteByMainId(id);
		ssWeeklyRecordMapper.deleteByMainId(id);
		ssWorklyPlanMapper.deleteByMainId(id);
		ssWeekReportMapper.deleteById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			ssWeeklySummaryMapper.deleteByMainId(id.toString());
			ssWeeklyRecordMapper.deleteByMainId(id.toString());
			ssWorklyPlanMapper.deleteByMainId(id.toString());
			ssWeekReportMapper.deleteById(id);
		}
	}
	
}
