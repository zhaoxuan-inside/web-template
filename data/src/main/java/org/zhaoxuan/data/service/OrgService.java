package org.zhaoxuan.data.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.zhaoxuan.pojo.entity.user.OrgEntity;
import org.zhaoxuan.pojo.request.PageParamRequest;
import org.zhaoxuan.pojo.request.PageResponse;
import org.zhaoxuan.pojo.request.user.OrgRequest;

import java.util.List;

public interface OrgService extends IService<OrgEntity> {
    void addOrg(List<OrgEntity> list);

    void removeOrg(List<Long> ids);

    void modifyOrg(List<OrgEntity> list);

    PageResponse<List<OrgEntity>> pageByConditions(PageParamRequest page, OrgRequest orgParam);


}
