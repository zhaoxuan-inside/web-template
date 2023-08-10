package org.zhaoxuan.pojo.entity.user;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("org")
public class OrgEntity {

    private long id;

    private String name;

    private long superiorId;

    private List<Long> subordinateIds;

}
