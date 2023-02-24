package com.api.controller;


import com.api.assembler.CampeonatoModelAssembler;
import com.api.dto.CampeonatoDTO;
import com.domain.model.Campeonato;
import com.domain.repository.CampeonatoRepository;
import com.domain.repository.service.CadastroCampeonatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value= "/campeonatos")
public class CampeonatoController {

    @Autowired
    private CampeonatoRepository campeonatoRepository;

    @Autowired
    private CadastroCampeonatoService cadastroCampeonatoService;

    @Autowired
    private CampeonatoModelAssembler campeonatoModelAssembler;

    @GetMapping
    public List<CampeonatoDTO> listar() {
        List<Campeonato> campeonatos = campeonatoRepository.findAll();
        return campeonatoModelAssembler.toCollectionModel(campeonatos);
    }

    @GetMapping("/{campeonatoId}")
    public CampeonatoDTO buscar(@PathVariable Long campeonatoId) {
        Campeonato campeonato = cadastroCampeonatoService.buscarOuFalhar(campeonatoId);
        return campeonatoModelAssembler.toModel(campeonato);
    }

}
