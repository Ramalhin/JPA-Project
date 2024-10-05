package com.jpaproject.controller;

import com.jpaproject.model.Aluno;
import com.jpaproject.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public List<Aluno> getAllAlunos() {
        return alunoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getAlunoById(@PathVariable Long id) {
        Aluno aluno = alunoService.findById(id);
        return aluno != null ? ResponseEntity.ok(aluno) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Aluno createAluno(@RequestBody Aluno aluno) {
        return alunoService.save(aluno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> updateAluno(@PathVariable Long id, @RequestBody Aluno aluno) {
        Aluno existingAluno = alunoService.findById(id);
        if (existingAluno != null) {
            aluno.setId(id);
            return ResponseEntity.ok(alunoService.save(aluno));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluno(@PathVariable Long id) {
        alunoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
