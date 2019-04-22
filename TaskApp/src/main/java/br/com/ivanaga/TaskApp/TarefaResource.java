/*
Autor: Felipe Alves Ivanaga
email: felipe.alves.ivanaga@gmail.com
*/

package br.com.ivanaga.TaskApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TarefaResource {

    @Autowired
    private TarefaRepository tarefaRepository;

    @GetMapping("/Tarefas")
    public List<Tarefa> consultaTarefa() {
        tarefaRepository.findAll();
        return tarefaRepository.findAll();
    }

    @GetMapping("/Tarefas/{id}")
    public Tarefa consultaTarefa(@PathVariable Long id) {
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);

        if (!tarefa.isPresent())
            throw new RuntimeException("id-" + id);

        return tarefa.get();
    }

    @PostMapping("/Tarefas")
    public Tarefa createTarefa(@RequestBody Tarefa tarefa) {
        Optional<Tarefa> novaTarefa = tarefaRepository.findById(tarefa.getId());

        if (novaTarefa.isPresent())
            throw new RuntimeException("id-" + tarefa.getId());

        tarefaRepository.save(tarefa);
        return tarefa;
    }

    @PutMapping
    public ResponseEntity<Object> updateTarefa(@RequestBody Tarefa tarefa, @PathVariable long id) {
        Optional<Tarefa> tarefaOptional = tarefaRepository.findById(id);

        if (!tarefaOptional.isPresent())
            tarefa.setId(id);
        tarefaRepository.save(tarefa);
        return ResponseEntity.noContent().build();

    }
}
