package org.zhaoxuan.data.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEneity {

    private long id;
    private String name;
    private String age;
    private String gender;
    private String mobile;
    private String email;
    private long roleId;
    private long orgId;

}
