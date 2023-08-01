package org.zhaoxuan.user.data.entity;

import com.mybatisflex.annotation.Table;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("organization")
public class OrgEntity {

    private long id;

    private String name;

    private long superiorId;

    private List<Long> subordinateIds;

}
