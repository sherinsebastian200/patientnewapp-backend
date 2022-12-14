package com.example.patient_backend.controller;

import com.example.patient_backend.dao.PatientDao;
import com.example.patient_backend.model.Patients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PatientController {
    @Autowired
    private PatientDao dao;

    @CrossOrigin(origins = "*")
    @PostMapping("/")
    public String Welcomepage()
    {
        return "Welcome to Patient website";
    }
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/add",consumes = "application/json",produces = "application/json")
    public String Addpatients(@RequestBody Patients p) {
        System.out.println(p.getName().toString());
        System.out.println(p.getId());
        System.out.println(p.getAddress().toString());
        System.out.println(p.getContact());
        System.out.println(p.getDoa().toString());
        System.out.println(p.getImage().toString());
        System.out.println(p.getDname());
        dao.save(p);
        return "Patients added successfully";
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/view")
    public List<Patients> ViewPatient() {
        return (List<Patients>) dao.findAll();
    }



    @CrossOrigin(origins = "*")
    @PostMapping(path = "/search",consumes = "application/json",produces = "application/json")
    public List<Patients> SearchPatient(@RequestBody Patients p)
    {
        String pid=String.valueOf(p.getPid());
        System.out.println(pid);
        return (List<Patients>) dao.SearchPatient(p.getPid());

    }
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/delete",consumes ="application/json",produces = "application/json")
    public Map<String,String> DeletePatient(@RequestBody Patients p)
    {
        String id=String.valueOf(p.getId());
        System.out.println(id);
        dao.DeletePatient(p.getId());
        HashMap<String,String> map=new HashMap<>();
        map.put("status","success");
        return map;
    }
}