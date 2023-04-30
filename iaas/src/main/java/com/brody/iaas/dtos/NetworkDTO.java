package com.brody.iaas.dtos;

import com.brody.iaas.enums.NetworkType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class NetworkDTO {
    private String id;
    private NetworkType type;
    private String ipAddress;
}
