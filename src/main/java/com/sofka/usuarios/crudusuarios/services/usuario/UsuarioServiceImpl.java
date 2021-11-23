package com.sofka.usuarios.crudusuarios.services.usuario;

import com.sofka.usuarios.crudusuarios.models.UsuarioModel;
import com.sofka.usuarios.crudusuarios.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public List<UsuarioModel> obtenerUsuarios(){
        return (List<UsuarioModel>) usuarioRepository.findAll();
    }

    @Override
    public UsuarioModel guardarUsuario(UsuarioModel usuario){
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<UsuarioModel> obtenerPorId(Long id){
        return usuarioRepository.findById(id);
    }

    @Override
    public List<UsuarioModel> obtenerPorPrioridad(Integer prioridad){
        return usuarioRepository.findByPrioridad(prioridad);
    }

    @Override
    public boolean eliminarUsuario(Long id){
        try {
            usuarioRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
