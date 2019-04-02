/*
Autor: Felipe Alves Ivanaga
email: felipe.alves.ivanaga@gmail.com
*/

package br.com.ivanaga.TaskApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
