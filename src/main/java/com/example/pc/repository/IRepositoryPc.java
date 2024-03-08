package com.example.pc.repository;

import com.example.pc.model.PcModel;
import com.example.pc.model.PcModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRepositoryPc extends JpaRepository<PcModel,Long> {
    List<PcModel> findAllByNameContaining(String string);
    List<PcModel> findAllByManufacturersContaining(String manufacturers);
    List<PcModel> findAllByTypeContaining(String type);


}
