package com.brody.iaas.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class VirtualMachineDTO {
    private String id;
    private String name;
    private String description;
    private Integer memory;
    private Boolean isRunning;
    private OperatingSystemDTO operatingSystem;
    private ProcessorsDTO processors;
    private NetworkDTO network;
    private HardDiskDTO hardDisk;
    private Date creation;
    private Date lastModification;
}
