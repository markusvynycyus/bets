package com.api.controller;

import com.api.assembler.GrupoInputDisassembler;
import com.api.assembler.GrupoModelAssembler;
import com.api.dto.GrupoDTO;
import com.api.dto.input.GrupoInput;
import com.domain.model.Grupo;
import com.domain.repository.GrupoRepository;
import com.domain.service.CadastroGrupoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/grupos")
@Tag(name = "Grupos", description = "Gerencia Grupos")
public class GrupoController {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private CadastroGrupoService cadastroGrupoService;

    @Autowired
    private GrupoModelAssembler grupoModelAssembler;

    @Autowired
    private GrupoInputDisassembler grupoInputDisassembler;


    @GetMapping
    @Operation(summary= "Listar Grupos")
    public List<GrupoDTO> listar() {
        List<Grupo> todosGrupos = grupoRepository.findAll();

        return grupoModelAssembler.toCollectionModel(todosGrupos);
    }

    @GetMapping("/{grupoId}")
    @Operation(summary= "Listar Grupo por ID")
    public GrupoDTO buscar(@PathVariable Long grupoId) {
        Grupo grupo = cadastroGrupoService.buscarOuFalhar(grupoId);

        return grupoModelAssembler.toModel(grupo);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary= "Adicionar Time")
    public GrupoDTO adicionar(@RequestBody @Valid GrupoInput grupoInput) {
        Grupo grupo = grupoInputDisassembler.toDomainObject(grupoInput);

        grupo = cadastroGrupoService.salvar(grupo);

        return grupoModelAssembler.toModel(grupo);
    }

    @PutMapping("/{grupoId}")
    @Operation(summary= "Atualizar Grupo por ID")
    public GrupoDTO atualizar(@PathVariable Long grupoId,
                                @RequestBody @Valid GrupoInput grupoInput) {
        Grupo grupoAtual = cadastroGrupoService.buscarOuFalhar(grupoId);

        grupoInputDisassembler.copyToDomainObject(grupoInput, grupoAtual);

        grupoAtual = cadastroGrupoService.salvar(grupoAtual);

        return grupoModelAssembler.toModel(grupoAtual);
    }

    @DeleteMapping("/{grupoId}")
    @Operation(summary= "Deletar Grupo por ID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long grupoId) {
        cadastroGrupoService.excluir(grupoId);
    }


}
