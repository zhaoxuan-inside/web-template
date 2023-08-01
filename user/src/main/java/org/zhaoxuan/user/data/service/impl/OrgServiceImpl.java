package org.zhaoxuan.user.data.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhaoxuan.user.data.entity.OrgEntity;
import org.zhaoxuan.user.data.mapper.OrgMapper;
import org.zhaoxuan.user.data.service.OrgService;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class OrgServiceImpl extends ServiceImpl<OrgMapper, OrgEntity>
        implements OrgService {

}
