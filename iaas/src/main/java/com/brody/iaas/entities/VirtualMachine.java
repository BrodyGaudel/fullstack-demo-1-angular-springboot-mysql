package com.brody.iaas.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class VirtualMachine {
    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    private String description;

    private Integer memory;

    private Boolean isRunning;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "os", referencedColumnName = "id")
    private OperatingSystem operatingSystem;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cpu", referencedColumnName = "id")
    private Processors processors;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "net", referencedColumnName = "id")
    private Network network;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "disk", referencedColumnName = "id")
    private HardDisk hardDisk;

    private Date creation;

    private Date lastModification;

}
