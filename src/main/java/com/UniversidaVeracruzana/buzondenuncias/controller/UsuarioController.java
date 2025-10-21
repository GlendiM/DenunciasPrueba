package com.UniversidaVeracruzana.buzondenuncias.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.UniversidaVeracruzana.buzondenuncias.dto.LoginRequestDTO;
import com.UniversidaVeracruzana.buzondenuncias.dto.UsuarioRequestDTO;
import com.UniversidaVeracruzana.buzondenuncias.dto.UsuarioResponseDTO;
import com.UniversidaVeracruzana.buzondenuncias.service.UsuarioService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // desarrollo local
public class UsuarioController {

    private final UsuarioService usuarioService;

    // GET - obetener todos los usuarios
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> getAllUsuarios(){
        try{
            List<UsuarioResponseDTO> usuarios = usuarioService.findAll();
            return ResponseEntity.ok(usuarios);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    // GET - obtener usuario por id
    @GetMapping("{id}")
    public ResponseEntity<UsuarioResponseDTO> getUsuarioById(@PathVariable Integer Id){
        try{
            Optional<UsuarioResponseDTO> usuario = usuarioService.findById(Id);
            return usuario.map(ResponseEntity::ok)
                            .orElse(ResponseEntity.notFound().build());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    // POST - crear un nuevo usuario
    @PostMapping
    public ResponseEntity<?> crearUsuario(@RequestBody UsuarioRequestDTO usuarioRequestDTO){
        try{
            UsuarioResponseDTO nuevoUsuario = usuarioService.create(usuarioRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Interno del Servidor");
        }
    }

    // POST - LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO){
        try{
            Optional<UsuarioResponseDTO> usuario = usuarioService.login(loginRequestDTO);
            if(usuario.isPresent()){
                return ResponseEntity.ok(usuario.get());
            }else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales Inválidas");
            }
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Interno del Servidor");
        }
    }

    // PUT - Actualizar usuario
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUsuario(@PathVariable Integer Id, @RequestBody UsuarioRequestDTO usuarioRequestDTO){
        try{
            UsuarioResponseDTO usuarioActualizado = usuarioService.update(Id, usuarioRequestDTO);
            return ResponseEntity.ok(usuarioActualizado);
        }catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Interno del Servidor");
        }
    }

    //DELETE - Eliminar Usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Integer id){
        try{
            usuarioService.deleteById(id);
            return ResponseEntity.ok().body("Usuario eliminado correctamente...");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error del servidor");
        }
    }

    // GET - buscar por correo
    @GetMapping("/correo/{correo}")
    public ResponseEntity<?> getUsuarioByCorreo(@PathVariable String correo){
        try{
            Optional<UsuarioResponseDTO> usuario = usuarioService.findByCorreo(correo);
            return usuario.map(ResponseEntity::ok)
                            .orElse(ResponseEntity.notFound().build());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // GET - Buscar Por Matricula
    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<?> getUsuarioByMatricula(@PathVariable String matricula){
        try{
            Optional<UsuarioResponseDTO> usuario = usuarioService.findByMatricula(matricula);
            return usuario.map(ResponseEntity::ok)
                            .orElse(ResponseEntity.notFound().build());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
