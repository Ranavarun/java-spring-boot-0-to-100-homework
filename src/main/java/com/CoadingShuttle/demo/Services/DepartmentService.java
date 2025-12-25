package com.CoadingShuttle.demo.Services;

import com.CoadingShuttle.demo.Dto.DepartmentDto;
import com.CoadingShuttle.demo.Exception.ResourceNotFoundException;
import com.CoadingShuttle.demo.Repository.DepartmentRepository;
import com.CoadingShuttle.demo.entitites.DepartmentEntities;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapperconfig;

    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapperconfig) {
        this.departmentRepository = departmentRepository;
        this.modelMapperconfig = modelMapperconfig;
    }


   public Optional<DepartmentDto> findByID(Long DepartmentID){
        return  departmentRepository.findById(DepartmentID)
                .map(dp->modelMapperconfig.map(dp,DepartmentDto.class));
   }

   public List<DepartmentDto> findAllDepartment(){
        List<DepartmentEntities> departmentEntitiesList = departmentRepository.findAll();
        return departmentEntitiesList
                .stream()
                .map((dp)->modelMapperconfig.map(dp,DepartmentDto.class))
                .collect(Collectors.toList());
   }

   public DepartmentDto createNewDepartment(DepartmentDto departmentDto){
        DepartmentEntities departmentEntities = modelMapperconfig.map(departmentDto,DepartmentEntities.class);
       return modelMapperconfig.map(departmentRepository.save(departmentEntities),DepartmentDto.class);
   }

   public void isExistByDepartmentID(Long DepartmentID){
       boolean exist = departmentRepository.existsById(DepartmentID);
       if(!exist) throw new ResourceNotFoundException("Department not found with id : "+DepartmentID);
   }

    public boolean deleteEmployeeByID(long DepartmentID)  {
        isExistByDepartmentID(DepartmentID);
        departmentRepository.deleteById(DepartmentID);
        return true;

    }


    public DepartmentDto updateDepartmentById(Long DeparmentID, DepartmentDto departmentDto)  {
        isExistByDepartmentID(DeparmentID);
        DepartmentEntities departmentEntities=modelMapperconfig.map(departmentDto,DepartmentEntities.class);
        departmentEntities.setId(DeparmentID);
        DepartmentEntities savedDepartment = departmentRepository.save(departmentEntities);
        return modelMapperconfig.map(savedDepartment, DepartmentDto.class);
    }


    public DepartmentDto updatePartialDepartmentById(Map<String, Object> updates,Long DepartmentID){
        isExistByDepartmentID(DepartmentID);
        DepartmentEntities departmentEntities = departmentRepository.findById(DepartmentID).get();
        updates.forEach((field,value)->{
            Field fieldToBeUpdated = ReflectionUtils.findRequiredField(DepartmentEntities.class,field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated,departmentEntities,value);
        });
        return  modelMapperconfig.map(departmentRepository.save(departmentEntities),DepartmentDto.class);
    }

}
