package com.lucas.atividade_db.controllers;


import com.lucas.atividade_db.dtos.UserRecordDto;
import com.lucas.atividade_db.models.UserModel;
import com.lucas.atividade_db.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/usuarios")
    public ResponseEntity <UserModel> saveUser(@RequestBody @Valid UserRecordDto userRecordDto) {
        var userModel = new UserModel();
        BeanUtils.copyProperties(userRecordDto, userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(userModel));
    }
    @GetMapping("/usuarios")
    public ResponseEntity<List<UserModel>> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
    }

    @GetMapping("/usuarios/{cpf}")
    public ResponseEntity<Object> getOneUser(@PathVariable(value="cpf") Long cpf){
        Optional<UserModel> userO = userRepository.findByCpf(cpf);
        if(userO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario nao encontrato.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userO.get());
    }

    @PutMapping("/usuarios/{cpf}")
    public ResponseEntity<Object> updateUser (@PathVariable(value="cpf") Long cpf,
                                                @RequestBody @Valid UserRecordDto userRecordDto) {
        Optional<UserModel> userO = userRepository.findByCpf(cpf);
        if(userO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario nao encontrado.");
        }
        var userModel = userO.get();
        BeanUtils.copyProperties(userRecordDto, userModel, "cpf");
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.save(userModel));
    }

    @DeleteMapping("/usuarios/{cpf}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value="cpf") Long cpf) {
        Optional<UserModel> userO = userRepository.findByCpf(cpf);
        if(userO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario nao encontrado");
        }
        userRepository.delete(userO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuario deletado com sucesso.");
    }
}
