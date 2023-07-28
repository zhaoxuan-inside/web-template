package org.zhaoxuan.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings({"unused", "replace"})
public class OrganizationEntity {

    private long id;
    private long upper;
    private List<Long> lower;
    private String name;
    private long currUserIds;

}
