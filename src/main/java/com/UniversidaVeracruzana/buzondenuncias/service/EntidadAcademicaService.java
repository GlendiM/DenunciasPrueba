package com.UniversidaVeracruzana.buzondenuncias.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.UniversidaVeracruzana.buzondenuncias.model.EntidadAcademica;
import com.UniversidaVeracruzana.buzondenuncias.repository.EntidadAcademicaRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class EntidadAcademicaService {
    
    private final EntidadAcademicaRepository entidadAcademicaRepository;

    @Transactional(readOnly = true)
    public List<EntidadAcademica> findAll(){
        return entidadAcademicaRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Optional<EntidadAcademica> finById(Integer id){
        return entidadAcademicaRepository.findById(id);
    }

    // guardar o actualizar una entidad academica
    public EntidadAcademica save(EntidadAcademica entidadAcademica){
        return entidadAcademicaRepository.save(entidadAcademica);
    }

    public void deleteById(Integer id){
        entidadAcademicaRepository.deleteById(id);
    }







}
