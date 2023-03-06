package com.api.controller;

import com.api.assembler.PartidaInputDissassembler;
import com.api.assembler.PartidaModelAssembler;
import com.api.dto.PartidaDTO;
import com.api.dto.input.PartidaInput;
import com.domain.exception.NegocioException;
import com.domain.exception.PartidaNaoEncontradoException;
import com.domain.model.Partida;
import com.domain.repository.PartidaRepository;
import com.domain.service.CadastroPartidaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value= "/partidas")
@Tag(name = "Partidas", description = "Gerencia Pardidas")
public class PartidaController {

    @Autowired
    private PartidaInputDissassembler partidaInputDisassembler;

    @Autowired
    private PartidaRepository partidaRepository;

    @Autowired
    private PartidaModelAssembler partidaModelAssembler;

    @Autowired
    private CadastroPartidaService cadastroPartidaService;

    @GetMapping
    @Operation(summary= "Listar Partidas")
    public List<PartidaDTO> listar() {
        List<Partida> partidas = partidaRepository.findAll();
        return partidaModelAssembler.toCollectionModel(partidas);
    }

    @GetMapping("/{partidaId}")
    @Operation(summary= "Listar Partida por ID")
    public PartidaDTO buscar(@PathVariable Long partidaId) {
        Partida partida = cadastroPartidaService.buscarOuFalhar(partidaId);
        return partidaModelAssembler.toModel(partida);
    }

    @PostMapping
    @Operation(summary= "Adicionar Partida")
    @ResponseStatus(HttpStatus.CREATED)
    public PartidaDTO adicionar(@RequestBody @Valid PartidaInput partidaInput) {
        Partida partida = partidaInputDisassembler.toDomainObject(partidaInput);

        partida = cadastroPartidaService.salvar(partida);

        return partidaModelAssembler.toModel(partida);
    }

    @PutMapping("/{partidaId}")
    @Operation(summary= "Atualizar Partida por ID")
    public PartidaDTO atualizar(@PathVariable Long partidaId,
                             @RequestBody @Valid PartidaInput partidaInput) {
        try {
            Partida partidaAtual = cadastroPartidaService.buscarOuFalhar(partidaId);

            partidaInputDisassembler.copyToDomainObject(partidaInput, partidaAtual);

            partidaAtual = cadastroPartidaService.salvar(partidaAtual);

            return partidaModelAssembler.toModel(partidaAtual);
        } catch (PartidaNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{partidaId}")
    @Operation(summary= "Deletar Partida por ID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long partidaId) {
        cadastroPartidaService.excluir(partidaId);
    }

}
