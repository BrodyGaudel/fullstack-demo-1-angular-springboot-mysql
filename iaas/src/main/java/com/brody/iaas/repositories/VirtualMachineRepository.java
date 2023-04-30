package com.brody.iaas.repositories;

import com.brody.iaas.entities.VirtualMachine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VirtualMachineRepository extends JpaRepository<VirtualMachine, String> {
    @Query("select v from VirtualMachine v where v.isRunning = ?1")
    List<VirtualMachine> findByIsRunning(Boolean isRunning);

    @Query("select v from VirtualMachine v where v.name like :kw or v.description like :kw")
    List<VirtualMachine> search(@Param("kw") String kw);

    Page<VirtualMachine> findByNameContains(String kw, Pageable pageable);
}
