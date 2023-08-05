package org.zhaoxuan.common.entity.user;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("org")
@SuppressWarnings({"unused", "replace"})
public class OrgEntity {

    // @ApiModelProperty("id")
    private long id;
    // @ApiModelProperty("组织名称")
    private String name;
    // @ApiModelProperty("上级组织ID")
    private long superiorId;
    // @ApiModelProperty("上级组织ID")
    private List<Long> subordinateIds;

}
