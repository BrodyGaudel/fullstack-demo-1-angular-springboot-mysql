package com.brody.iaas.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Processors {
    @Id
    private String id;

    @Column(nullable = false)
    private Integer numberOfProcessors;

    @Column(nullable = false)
    private Integer numberOfCorPerProcessors;

    @OneToOne(mappedBy = "processors")
    private VirtualMachine virtualMachine;
}
