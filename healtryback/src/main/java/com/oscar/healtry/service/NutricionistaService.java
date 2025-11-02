package com.oscar.healtry.service;

import com.oscar.healtry.dto.nutricionista.NutricionistaPerfilDTO;
import com.oscar.healtry.dto.nutricionista.NutricionistaResumenDTO;

public interface NutricionistaService {
    NutricionistaPerfilDTO obtenerPerfil(Long nutricionistaId);
    NutricionistaPerfilDTO actualizarPerfil(Long nutricionistaId, NutricionistaPerfilDTO perfilDTO);
    NutricionistaResumenDTO obtenerResumen(Long nutricionistaId);
}
