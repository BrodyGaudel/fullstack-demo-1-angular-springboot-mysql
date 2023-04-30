package com.brody.iaas.entities;

import com.brody.iaas.enums.HardDiskType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class HardDisk {
    @Id
    private String id;

    @Column(nullable = false)
    private Integer stockage;

    @Enumerated(EnumType.STRING)
    private HardDiskType type;

    @OneToOne(mappedBy = "hardDisk")
    private VirtualMachine virtualMachine;
}
