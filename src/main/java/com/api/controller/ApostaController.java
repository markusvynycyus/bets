package com.api.controller;


import com.api.assembler.ApostaModelAssembler;
import com.api.dto.ApostaDTO;
import com.domain.model.Aposta;
import com.domain.repository.ApostaRepository;
import com.domain.service.CadastroApostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value= "/apostas")
public class ApostaController {

    @Autowired
    private ApostaRepository apostaRepository;
    @Autowired
    private ApostaModelAssembler apostaModelAssembler;
    @Autowired
    private CadastroApostaService cadastroApostaService;


    @GetMapping
    public List<ApostaDTO> listar() {
        List<Aposta> apostas = apostaRepository.findAll();
        return apostaModelAssembler.toCollectionModel(apostas);
    }

    @GetMapping("/{apostaId}")
    public ApostaDTO buscar(@PathVariable Long apostaId) {
        Aposta aposta = cadastroApostaService.buscarOuFalhar(apostaId);
        return apostaModelAssembler.toModel(aposta);
    }
}
