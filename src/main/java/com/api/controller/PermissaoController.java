package com.api.controller;

import com.api.assembler.PermissaoInputDisassembler;
import com.api.assembler.PermissaoModelAssembler;
import com.api.dto.PermissaoDTO;
import com.api.dto.TimeDTO;
import com.api.dto.input.PermissaoInput;
import com.api.dto.input.TimeInput;
import com.domain.exception.NegocioException;
import com.domain.exception.PermissaoNaoEncontradaException;
import com.domain.exception.TimeNaoEncontradoException;
import com.domain.model.Permissao;
import com.domain.model.Time;
import com.domain.repository.PermissaoRepository;
import com.domain.service.CadastroPermissaoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @Operation(summary= "Listar Permissoões")
    public List<PermissaoDTO> listar() {
        List<Permissao> permissaos = permissaoRepository.findAll();
        return permissaoModelAssembler.toCollectionModel(permissaos);
    }

    @GetMapping("/{permissaoId}")
    @Operation(summary= "Listar Permissao por ID")
    public PermissaoDTO buscar(@PathVariable Long permissaoId) {
        Permissao permissao = cadastroPermissaoService.buscarOuFalhar(permissaoId);
        return permissaoModelAssembler.toModel(permissao);
    }

    @PostMapping
    @Operation(summary= "Adicionar Permissao")
    @ResponseStatus(HttpStatus.CREATED)
    public PermissaoDTO adicionar(@RequestBody @Valid PermissaoInput permissaoInput) {
        Permissao permissao = permissaoInputDisassembler.toDomainObject(permissaoInput);

        permissao = cadastroPermissaoService.salvar(permissao);

        return permissaoModelAssembler.toModel(permissao);
    }

    @PutMapping("/{permissaoId}")
    @Operation(summary= "Atualizar Permissao por ID")
    public PermissaoDTO atualizar(@PathVariable Long permissaoId,
                             @RequestBody @Valid PermissaoInput permissaoInput) {
        try {
            Permissao permissaoAtual = cadastroPermissaoService.buscarOuFalhar(permissaoId);

            permissaoInputDisassembler.copyToDomainObject(permissaoInput, permissaoAtual);

            permissaoAtual = cadastroPermissaoService.salvar(permissaoAtual);

            return permissaoModelAssembler.toModel(permissaoAtual);
        } catch (PermissaoNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{permissaoId}")
    @Operation(summary= "Deletar Permissao por ID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long permissaoId) {
        cadastroPermissaoService.excluir(permissaoId);
    }



}
