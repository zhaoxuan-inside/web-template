package org.zhaoxuan.user.biz;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.zhaoxuan.pojo.entity.user.OrgEntity;
import org.zhaoxuan.pojo.request.PageParamRequest;
import org.zhaoxuan.pojo.request.PageResponse;
import org.zhaoxuan.pojo.request.user.*;

import java.util.List;

public interface OrgBiz {
    void addOrg(@RequestBody BatchRequest<OrgEntity> request);

    void removeOrg(@RequestBody BatchRequest<Long> request);

    void modifyOrg(@RequestBody BatchRequest<OrgEntity> request);

    PageResponse<List<OrgEntity>> orgPage(
            @RequestParam(required = false) PageParamRequest page,
            @RequestParam(required = false) OrgRequest orgParam);
}
