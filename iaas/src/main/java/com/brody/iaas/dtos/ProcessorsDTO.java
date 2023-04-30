package com.brody.iaas.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProcessorsDTO {
    private String id;
    private Integer numberOfProcessors;
    private Integer numberOfCorPerProcessors;
}
