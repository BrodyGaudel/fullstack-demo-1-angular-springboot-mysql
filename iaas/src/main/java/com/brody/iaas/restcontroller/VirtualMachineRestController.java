package com.brody.iaas.restcontroller;

import com.brody.iaas.dtos.VirtualMachineDTO;
import com.brody.iaas.exceptions.VmNotFoundException;
import com.brody.iaas.service.VirtualMachineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class VirtualMachineRestController {

    private final VirtualMachineService service;

    public VirtualMachineRestController(VirtualMachineService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public VirtualMachineDTO createNewVm(@RequestBody VirtualMachineDTO virtualMachineDTO){
        return service.createNewVm(virtualMachineDTO);
    }

    @PutMapping("/update")
    public VirtualMachineDTO updateVm(@RequestBody VirtualMachineDTO virtualMachineDTO) throws VmNotFoundException{
        return service.updateVm(virtualMachineDTO);
    }

    @PutMapping("/modify")
    public List<VirtualMachineDTO> updateAllVms(@RequestBody List<VirtualMachineDTO> virtualMachineDTOS){
        return service.updateAllVms(virtualMachineDTOS);
    }

    @GetMapping("/get/{id}")
    public VirtualMachineDTO findVmById(@PathVariable(name = "id") String id) throws VmNotFoundException{
        return service.findVmById(id);
    }

    @GetMapping("/list")
    public List<VirtualMachineDTO> findAllVm(){
        return service.findAllVm();
    }

    @GetMapping("/running")
    public List<VirtualMachineDTO> findAllVmRunning(){
        return service.findAllVmRunning();
    }

    @GetMapping("/search")
    public List<VirtualMachineDTO> searchVm(@RequestParam(name = "keyword", defaultValue = "") String keyword){
        return service.searchVm("%"+keyword+"%");
    }

    @GetMapping("/find")
    public List<VirtualMachineDTO> findAllVmByPage(@RequestParam(name = "keyword", defaultValue = "") String kw,
                                                   @RequestParam(name ="page", defaultValue = "0") int page,
                                                   @RequestParam(name ="size", defaultValue = "5") int size){
        return service.findAllVmByPage(kw, page, size);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteVmById(@PathVariable(name = "id") String id){
        service.deleteVmById(id);
    }

    @DeleteMapping("/clear")
    public void deleteAllVm(){
        service.deleteAllVm();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
