package com.oscar.healtry.service;

import java.util.List;

import com.oscar.healtry.dto.admin.UsuarioCreateDTO;
import com.oscar.healtry.dto.admin.UsuarioDTO;
import com.oscar.healtry.dto.admin.UsuarioUpdateDTO;

public interface UsuarioService {
    UsuarioDTO crearUsuarioDTO(UsuarioCreateDTO dto);
    UsuarioDTO actualizarUsuarioDTO(Integer id, UsuarioUpdateDTO dto);
    UsuarioDTO cambiarEstadoDTO(Integer id, boolean activo);
    UsuarioDTO asignarRolDTO(Integer id, Integer idRol);
    UsuarioDTO buscarDTOPorId(Integer id);
    List<UsuarioDTO> listarTodosDTO();
}
