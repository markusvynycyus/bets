package com.api.controller;

import com.api.assembler.TimeInputDisassembler;
import com.api.assembler.TimeModelAssembler;
import com.api.dto.TimeDTO;
import com.api.dto.input.TimeInput;
import com.domain.exception.NegocioException;
import com.domain.exception.TimeNaoEncontradoException;
import com.domain.model.Time;
import com.domain.repository.TimeRepository;
import com.domain.service.CadastroTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value= "/times")
public class TimeController {
    @Autowired
    private TimeRepository timeRepository;
    @Autowired
    private CadastroTimeService cadastroTimeService;

    @Autowired
    private TimeModelAssembler timeModelAssembler;

    @Autowired
    private TimeInputDisassembler timeInputDisassembler;

    @GetMapping
    public List<TimeDTO> listar() {
        List<Time> times = timeRepository.findAll();
        return timeModelAssembler.toCollectionModel(times);
    }

    @GetMapping("/{timeId}")
    public TimeDTO buscar(@PathVariable Long timeId) {
        Time time = cadastroTimeService.buscarOuFalhar(timeId);
        return timeModelAssembler.toModel(time);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TimeDTO adicionar(@RequestBody @Valid TimeInput timeInput) {
        Time time = timeInputDisassembler.toDomainObject(timeInput);

        time = cadastroTimeService.salvar(time);

        return timeModelAssembler.toModel(time);
    }

    @PutMapping("/{timeId}")
    public TimeDTO atualizar(@PathVariable Long timeId,
                                 @RequestBody @Valid TimeInput timeInput) {
        try {
            Time timeAtual = cadastroTimeService.buscarOuFalhar(timeId);

            timeInputDisassembler.copyToDomainObject(timeInput, timeAtual);

            timeAtual = cadastroTimeService.salvar(timeAtual);

            return timeModelAssembler.toModel(timeAtual);
        } catch (TimeNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{timeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long timeId) {
        cadastroTimeService.excluir(timeId);
    }


}
