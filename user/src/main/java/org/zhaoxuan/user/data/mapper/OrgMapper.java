package org.zhaoxuan.user.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.zhaoxuan.user.data.entity.OrgEntity;

@Mapper
public interface OrgMapper extends BaseMapper<OrgEntity>,
        MPJBaseMapper<OrgEntity> {

}