package com.brody.iaas.dtos;

import com.brody.iaas.enums.OperatingSystemType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OperatingSystemDTO {
    private String id;
    private String name;
    private OperatingSystemType type;
    private Integer size;
}
