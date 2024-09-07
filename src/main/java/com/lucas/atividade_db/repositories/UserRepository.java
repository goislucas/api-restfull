package com.lucas.atividade_db.repositories;

import com.lucas.atividade_db.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findByCpf(Long cpf);
}
