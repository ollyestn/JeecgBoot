package org.jeecg.modules.demo.smartcity.mapper;

import java.util.List;
import org.jeecg.modules.demo.smartcity.entity.SsWeeklyRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 本周工作记录
 * @Author: jeecg-boot
 * @Date:   2025-12-22
 * @Version: V1.0
 */
public interface SsWeeklyRecordMapper extends BaseMapper<SsWeeklyRecord> {

	/**
	 * 通过主表id删除子表数据
	 *
	 * @param mainId 主表id
	 * @return boolean
	 */
	public boolean deleteByMainId(@Param("mainId") String mainId);

  /**
   * 通过主表id查询子表数据
   *
   * @param mainId 主表id
   * @return List<SsWeeklyRecord>
   */
	public List<SsWeeklyRecord> selectByMainId(@Param("mainId") String mainId);
}
