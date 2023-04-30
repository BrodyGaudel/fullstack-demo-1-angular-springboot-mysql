package com.brody.iaas.entities;

import com.brody.iaas.enums.NetworkType;
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
public class Network {
    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private NetworkType type;

    @Column(unique = true, nullable = false)
    private String ipAddress;

    @OneToOne(mappedBy = "network")
    private VirtualMachine virtualMachine;
}
