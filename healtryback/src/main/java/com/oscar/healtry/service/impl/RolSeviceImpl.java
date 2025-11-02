package com.oscar.healtry.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oscar.healtry.dto.admin.RolDTO;
import com.oscar.healtry.repository.RolRepository;
import com.oscar.healtry.service.RolService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RolSeviceImpl implements RolService {
	private final RolRepository rolRepository;

	@Override
	public List<RolDTO> listarTodos() {
		log.debug("ENTRADA listarTodosDTO()");

		List<RolDTO> lista = rolRepository.findAll().stream().map(RolService::mapToDto).toList();

		log.debug("SALIDA listarTodosDTO -> {}", lista);
		return lista;
	}
}
