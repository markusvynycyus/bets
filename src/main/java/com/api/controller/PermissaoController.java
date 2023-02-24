package com.api.controller;

import com.api.assembler.PermissaoInputDisassembler;
import com.api.assembler.PermissaoModelAssembler;
import com.api.dto.PermissaoDTO;
import com.domain.model.Permissao;
import com.domain.repository.PermissaoRepository;
import com.domain.repository.service.CadastroPermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/permissoes")
public class PermissaoController {

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Autowired
    private CadastroPermissaoService cadastroPermissaoService;
    @Autowired
    private PermissaoModelAssembler permissaoModelAssembler;

    @Autowired
    private PermissaoInputDisassembler permissaoInputDisassembler;

    @GetMapping
    public List<PermissaoDTO> listar() {
        List<Permissao> permissaos = permissaoRepository.findAll();
        return permissaoModelAssembler.toCollectionModel(permissaos);
    }

    @GetMapping("/{permissaoId}")
    public PermissaoDTO buscar(@PathVariable Long permissaoId) {
        Permissao permissao = cadastroPermissaoService.buscarOuFalhar(permissaoId);
        return permissaoModelAssembler.toModel(permissao);
    }
}
