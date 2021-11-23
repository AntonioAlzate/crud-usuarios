package com.sofka.usuarios.crudusuarios.services.usuario;

import com.sofka.usuarios.crudusuarios.models.UsuarioModel;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<UsuarioModel> obtenerUsuarios();
    UsuarioModel guardarUsuario(UsuarioModel usuario);
    Optional<UsuarioModel> obtenerPorId(Long id);
    List<UsuarioModel> obtenerPorPrioridad(Integer prioridad);
    boolean eliminarUsuario(Long id);
}
