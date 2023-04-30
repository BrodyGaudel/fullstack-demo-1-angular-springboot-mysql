package com.brody.iaas.dtos;

import com.brody.iaas.enums.HardDiskType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class HardDiskDTO {
    private String id;
    private Integer stockage;
    private HardDiskType type;
}
