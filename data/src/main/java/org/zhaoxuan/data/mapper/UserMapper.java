package org.zhaoxuan.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.zhaoxuan.common.entity.user.UserEntity;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity>,
        MPJBaseMapper<UserEntity> {

}