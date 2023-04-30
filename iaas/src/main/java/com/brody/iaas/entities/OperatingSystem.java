package com.brody.iaas.entities;

import com.brody.iaas.enums.OperatingSystemType;
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
public class OperatingSystem {
    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private OperatingSystemType type;

    @Column(nullable = false)
    private Integer size;

    @OneToOne(mappedBy = "operatingSystem")
    private VirtualMachine virtualMachine;
}
