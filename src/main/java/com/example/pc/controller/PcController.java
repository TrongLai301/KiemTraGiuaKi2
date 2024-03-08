package com.example.pc.controller;

import com.example.pc.model.PcModel;
import com.example.pc.repository.IRepositoryPc;
import com.example.pc.model.PcModel;
import com.example.pc.repository.IRepositoryPc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employee")
public class PcController {
    @Autowired
    public IRepositoryPc pc;

    @GetMapping
    public ResponseEntity findAll(){
        List<PcModel> list = pc.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return  new ResponseEntity(list, HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity save(@Valid @RequestBody PcModel pcModel, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity("Lỗi rồi", HttpStatus.BAD_REQUEST);
        }
        pc.save(pcModel);
        return new ResponseEntity("Ok", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        Optional<PcModel> list = pc.findById(id);
        if (!list.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        pc.deleteById(id);
        return new ResponseEntity("OK",HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(@Valid @PathVariable Long id, @RequestBody PcModel pcModel, BindingResult bindingResult){
        Optional<PcModel> list = pc.findById(id);
        if (bindingResult.hasErrors()){
            return new ResponseEntity("Lỗi rồi", HttpStatus.BAD_REQUEST);
        }
        if (!list.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        pcModel.setId(id);
        pc.save(pcModel);
        return new ResponseEntity("OK", HttpStatus.OK);
    }

    @GetMapping("/findByName")
    public ResponseEntity findByName (@RequestParam String name) {
        return new ResponseEntity(pc.findAllByNameContaining(name), HttpStatus.OK);
    }
    @GetMapping("/findByManufacturer")
    public ResponseEntity findByManufacturer (@RequestParam String manufacturer) {
        return new ResponseEntity(pc.findAllByManufacturersContaining(manufacturer), HttpStatus.OK);
    }
    @GetMapping("/findByType")
    public ResponseEntity findByType (@RequestParam String type) {
        return new ResponseEntity(pc.findAllByTypeContaining(type), HttpStatus.OK);
    }


}
