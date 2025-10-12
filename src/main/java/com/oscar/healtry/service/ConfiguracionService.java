package com.oscar.healtry.service;

import java.util.List;

import com.oscar.healtry.dto.admin.ConfiguracionCreateDTO;
import com.oscar.healtry.dto.admin.ConfiguracionDTO;

public interface ConfiguracionService {
    ConfiguracionDTO guardarDTO(ConfiguracionCreateDTO dto);
    void eliminarPorClave(String clave);
    ConfiguracionDTO obtenerPorClaveDTO(String clave);
    List<ConfiguracionDTO> listarTodasDTO();
}
