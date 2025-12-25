package com.CoadingShuttle.demo.Controller;


import com.CoadingShuttle.demo.Dto.DepartmentDto;
import com.CoadingShuttle.demo.Exception.ResourceNotFoundException;
import com.CoadingShuttle.demo.Services.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;

@RestController
@RequestMapping("Department")
public class DepartmentController {

    final private DepartmentService departmentServicel;

    public DepartmentController(DepartmentService departmentServicel) {
        this.departmentServicel = departmentServicel;
    }

    //
    @GetMapping("/{DepartmentID}")
    public ResponseEntity<DepartmentDto> getDepatmentByid(@PathVariable("DepartmentID") Long departmentid) throws ResourceNotFoundException {
        // to-do
        Optional<DepartmentDto> employeeDto =  departmentServicel.findByID(departmentid);
        return     employeeDto
                .map(employeeDto1 -> ResponseEntity.ok(employeeDto1))
                .orElseThrow(()->new ResourceNotFoundException("Employee not found with id : "+departmentid));

    }

    @GetMapping()
    public ResponseEntity<List<DepartmentDto>> getAllDepatmentid(){
        // to-do
        return ResponseEntity.ok(departmentServicel.findAllDepartment());
    }


    @PostMapping()
    public ResponseEntity<DepartmentDto> CreateDepatment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto savedDepartment =  departmentServicel.createNewDepartment(departmentDto);
        return new  ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }


    @PutMapping("/{DepartmentID}")
    public ResponseEntity<DepartmentDto> PutDepartment(@RequestBody DepartmentDto departmentDto,@PathVariable("DepartmentID") long DepartmentID) throws ResourceNotFoundException {
        // to-do
        return ResponseEntity.ok(departmentServicel.updateDepartmentById(DepartmentID,departmentDto));
    }

    @DeleteMapping("/{DepartmentID}")
    public ResponseEntity<Boolean> DeleteDepartmentById(@PathVariable("DepartmentID") Long DepartmentID) throws ResourceNotFoundException {
        // to-do
        boolean gotDeleted = departmentServicel.deleteEmployeeByID(DepartmentID);
        if(gotDeleted){
            return ResponseEntity.ok(true);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }


    @PatchMapping("/{DepartmentID}")
    public ResponseEntity<DepartmentDto> DeleteDepartmentById(@RequestBody Map<String, Object> updates, @PathVariable("DepartmentID") Long DepartmentID){
        DepartmentDto departmentDto1 =  departmentServicel.updatePartialDepartmentById( updates,DepartmentID);
        if(departmentDto1 == null){
            return  ResponseEntity.notFound().build();
        }
        else{
            return  ResponseEntity.ok(departmentDto1);

        }    }
    }




