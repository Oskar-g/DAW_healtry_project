package com.oscar.healtry.service;

import java.util.List;

import com.oscar.healtry.dto.admin.UsuarioCrearDTO;
import com.oscar.healtry.dto.admin.UsuarioDTO;
import com.oscar.healtry.dto.admin.UsuarioUpdateDTO;

public interface UsuarioService {
    UsuarioDTO crearUsuarioDTO(UsuarioCrearDTO dto);
    UsuarioDTO actualizarUsuarioDTO(Integer id, UsuarioUpdateDTO dto);
    UsuarioDTO cambiarEstadoDTO(Integer id, boolean activo);
    UsuarioDTO asignarRol(Integer id, Integer idRol);
    UsuarioDTO buscarPorId(Integer id);
    List<UsuarioDTO> listarTodos();
}
