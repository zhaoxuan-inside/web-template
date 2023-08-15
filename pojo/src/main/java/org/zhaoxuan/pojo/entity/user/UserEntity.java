package org.zhaoxuan.pojo.entity.user;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "用户信息")
@JsonInclude(JsonInclude.Include.NON_NULL)
@TableName(value = "\"user\"", autoResultMap = true)
public class UserEntity {

    @TableId
    @Schema(description = "id")
    private Long id;
    @Schema(description = "账号")
    private String account;
    @Schema(description = "昵称")
    private String name;
    @Schema(description = "密码")
    private String passwd;
    @Schema(description = "角色ID")
    private Long roleId;
    @Schema(description = "归属组织ID")
    private Long orgId;
    @Schema(description = "邮箱")
    private String email;
    @Schema(description = "联系电话")
    private String mobile;

}
