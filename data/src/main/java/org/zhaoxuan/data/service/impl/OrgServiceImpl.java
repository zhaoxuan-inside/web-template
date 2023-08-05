package org.zhaoxuan.data.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhaoxuan.common.entity.user.OrgEntity;
import org.zhaoxuan.data.mapper.OrgMapper;
import org.zhaoxuan.data.service.OrgService;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class OrgServiceImpl extends ServiceImpl<OrgMapper, OrgEntity>
        implements OrgService {

}
