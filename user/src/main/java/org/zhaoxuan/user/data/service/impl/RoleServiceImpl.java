package org.zhaoxuan.user.data.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhaoxuan.user.data.entity.RoleEntity;
import org.zhaoxuan.user.data.mapper.RoleMapper;
import org.zhaoxuan.user.data.service.RoleService;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity>
        implements RoleService {

}
