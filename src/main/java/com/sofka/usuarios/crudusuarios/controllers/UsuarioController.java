package com.sofka.usuarios.crudusuarios.controllers;

import com.sofka.usuarios.crudusuarios.models.UsuarioModel;
import com.sofka.usuarios.crudusuarios.services.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.temporal.Temporal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping()
    public ResponseEntity<List<UsuarioModel>> obtenerUsuarios(){
        return ResponseEntity.ok(usuarioService.obtenerUsuarios());
    }

    @PostMapping()
    public ResponseEntity<UsuarioModel> guardarUsuario(@RequestBody UsuarioModel usuario){
        UsuarioModel temporal = usuarioService.guardarUsuario(usuario);

        try {
            return ResponseEntity.created(new URI("/usuario/"+temporal.getId())).body(temporal);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<UsuarioModel>> obtenerUsuarioPorId(@PathVariable("id") Long id){
        Optional<UsuarioModel> temporal = usuarioService.obtenerPorId(id);

        try {
            return ResponseEntity.ok(temporal);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/query")
    public ResponseEntity<List<UsuarioModel>> obtenerUsuarioPorPrioridad(@RequestParam("prioridad") Integer prioridad){
        return ResponseEntity.ok(usuarioService.obtenerPorPrioridad(prioridad));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> eliminarPorId(@PathVariable("id") Long id){
        boolean ok = usuarioService.eliminarUsuario(id);
        if(ok)
            return ResponseEntity.ok("Usuario eliminado correctamente");
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No pudo ser eliminado el usuario con id " + id);
    }
}
