package br.edu.ifpr.foz.projeto_spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import br.edu.ifpr.foz.projeto_spring.models.Task;
import br.edu.ifpr.foz.projeto_spring.models.TaskStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private List<Task> tasks = new ArrayList<>();

    public TaskController() {
        tasks.add(new Task(1L, "Task1", "Descrição 1", LocalDate.now()));
        tasks.add(new Task(2L, "Task2", "Descrição 2", LocalDate.now()));
        tasks.add(new Task(3L, "Task3", "Descrição 3", LocalDate.now()));
    }

    @GetMapping({"", "/", "/tasks"})
    public String listTasks(Model model) {
        model.addAttribute("tasks", tasks);
        return "task-list";
    }

    @GetMapping("/create")
    public String createTaskForm(Model model) {
        model.addAttribute("taskStatuses", TaskStatus.values());  // Envia os status disponíveis para o formulário
        return "task-create";
    }

    @PostMapping("/create")
    public String createTask(@RequestParam String titulo,
                             @RequestParam String descricao,
                             @RequestParam String data,
                             @RequestParam TaskStatus status) {
        Long newId = tasks.size() + 1L;
        LocalDate date = LocalDate.parse(data);
        Task newTask = new Task(newId, titulo, descricao, date);
        newTask.setStatus(status);  // Garante que o status será atribuído corretamente
        tasks.add(newTask);
        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    public String editTaskForm(@PathVariable Long id, Model model) {
        Task task = tasks.stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
        model.addAttribute("task", task);
        model.addAttribute("taskStatuses", TaskStatus.values());  // Adiciona os status para edição
        return "task-edit";
    }

    @PostMapping("/edit")
    public String editTask(@RequestParam Long id,
                           @RequestParam String titulo,
                           @RequestParam String descricao,
                           @RequestParam String data,
                           @RequestParam TaskStatus status) {
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                task.setTitulo(titulo);
                task.setDescricao(descricao);
                task.setDate(LocalDate.parse(data));
                task.setStatus(status);
                break;
            }
        }
        return "redirect:/tasks";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        tasks.removeIf(task -> task.getId().equals(id));
        return "redirect:/tasks";
    }

    @GetMapping("/{id}")
    public String viewTask(@PathVariable Long id, Model model) {
        Task task = tasks.stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
        model.addAttribute("task", task);
        return "task-view";  // Nova página para exibição da task
    }

    @GetMapping("/em-andamento")
    public String listTasksInProgress(Model model) {
        List<Task> emAndamento = tasks.stream()
                                      .filter(t -> t.getStatus() == TaskStatus.EM_ANDAMENTO)
                                      .toList();
        model.addAttribute("tasks", emAndamento);
        return "task-list";
    }
}
