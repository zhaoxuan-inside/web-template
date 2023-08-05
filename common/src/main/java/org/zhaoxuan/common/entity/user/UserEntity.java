package org.zhaoxuan.common.entity.user;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    private long id;
    private String name;
    private String age;
    private String gender;
    private String mobile;
    private String email;
    private long roleId;
    private long orgId;

}
