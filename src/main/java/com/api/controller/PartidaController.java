package com.api.controller;

import com.api.assembler.PartidaModelAssembler;
import com.api.dto.PartidaDTO;
import com.domain.model.Partida;
import com.domain.repository.PartidaRepository;
import com.domain.service.CadastroPartidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value= "/partidas")
public class PartidaController {


    @Autowired
    private PartidaRepository partidaRepository;

    @Autowired
    private PartidaModelAssembler partidaModelAssembler;

    @Autowired
    private CadastroPartidaService cadastroPartidaService;

    @GetMapping
    public List<PartidaDTO> listar() {
        List<Partida> partidas = partidaRepository.findAll();
        return partidaModelAssembler.toCollectionModel(partidas);
    }

    @GetMapping("/{partidaId}")
    public PartidaDTO buscar(@PathVariable Long partidaId) {
        Partida partida = cadastroPartidaService.buscarOuFalhar(partidaId);
        return partidaModelAssembler.toModel(partida);
    }
}
