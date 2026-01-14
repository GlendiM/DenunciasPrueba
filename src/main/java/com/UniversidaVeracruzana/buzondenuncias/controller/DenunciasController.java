package com.UniversidaVeracruzana.buzondenuncias.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.UniversidaVeracruzana.buzondenuncias.dto.DenunciaRequestDTO;
import com.UniversidaVeracruzana.buzondenuncias.dto.DenunciaResponseDTO;
import com.UniversidaVeracruzana.buzondenuncias.enums.EstadoDenuncia;
import com.UniversidaVeracruzana.buzondenuncias.service.DenunciasService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/denuncias")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DenunciasController {

    private final DenunciasService denunciasService;

    @GetMapping
    public ResponseEntity<List<DenunciaResponseDTO>> getAllDenuncias() {
        try{
            List<DenunciaResponseDTO> denuncias = denunciasService.findAll();
            return ResponseEntity.ok(denuncias);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DenunciaResponseDTO> getDenunciaById(@PathVariable Integer Id){
        try{
            Optional<DenunciaResponseDTO> denuncia = denunciasService.findById(Id);
            return denuncia.map(ResponseEntity::ok)
                            .orElse(ResponseEntity.notFound().build());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createDenuncia(@RequestBody DenunciaRequestDTO denunciaRequestDTO){
        try{
            DenunciaResponseDTO nuevaDenuncia = denunciasService.create(denunciaRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaDenuncia);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<?> updateEstadoDenuncia(
        @PathVariable Integer id,
        @RequestParam EstadoDenuncia nuevoEstado){
            try{
                DenunciaResponseDTO denunciaActualizada= denunciasService.updateEstado(id, nuevoEstado);
                return ResponseEntity.ok(denunciaActualizada);
            }catch (Exception e){
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
    
    @GetMapping("/denunciante/{denuncianteId}")
    public ResponseEntity<List<DenunciaResponseDTO>> getDenunciasByDenunciante(@PathVariable Integer denuncianteId){
        try{
            List<DenunciaResponseDTO> denuncias = denunciasService.findByDenunciante(denuncianteId);
            return ResponseEntity.ok(denuncias);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<DenunciaResponseDTO>> getDenunciasByEstado(@PathVariable EstadoDenuncia estado){
        try{
            List<DenunciaResponseDTO> denuncias = denunciasService.findByEstado(estado);
            return ResponseEntity.ok(denuncias);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/entidad/{entidadId}")
    public ResponseEntity<List<DenunciaResponseDTO>> getDenunciasByEntidad(@PathVariable Integer entidadId){
        try{
            List<DenunciaResponseDTO> denuncias = denunciasService.findByEntidadAcademica(entidadId);
            return ResponseEntity.ok(denuncias);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    //hacer pruebas y continuar con los demas controller
}
