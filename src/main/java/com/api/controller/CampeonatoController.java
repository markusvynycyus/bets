package com.api.controller;


import com.api.assembler.CampeonatoInputDisassembler;
import com.api.assembler.CampeonatoModelAssembler;
import com.api.dto.CampeonatoDTO;
import com.api.dto.TimeDTO;
import com.api.dto.input.CampeonatoInput;
import com.api.dto.input.TimeInput;
import com.domain.exception.CampeonatoNaoEncontradoException;
import com.domain.exception.NegocioException;
import com.domain.exception.TimeNaoEncontradoException;
import com.domain.model.Campeonato;
import com.domain.model.Time;
import com.domain.repository.CampeonatoRepository;
import com.domain.service.CadastroCampeonatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @Autowired
    private CampeonatoInputDisassembler campeonatoInputDisassembler;

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

    @PutMapping("/{campeonatoId}")
    public CampeonatoDTO atualizar(@PathVariable Long campeonatoId,
                             @RequestBody @Valid CampeonatoInput campeonatoInput) {
        try {
            Campeonato campeonatoAtual = cadastroCampeonatoService.buscarOuFalhar(campeonatoId);

            campeonatoInputDisassembler.copyToDomainObject(campeonatoInput, campeonatoAtual);

            campeonatoAtual = cadastroCampeonatoService.salvar(campeonatoAtual);

            return campeonatoModelAssembler.toModel(campeonatoAtual);
        } catch (CampeonatoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{campeonatoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long campeonatoId) {
        cadastroCampeonatoService.excluir(campeonatoId);
    }


}
