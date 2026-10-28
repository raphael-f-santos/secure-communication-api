package br.com.raphaelsantos.auth_service.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import br.com.raphaelsantos.auth_service.entity.AppUser;


public interface UserRepository extends CrudRepository<AppUser, Long>{
    Optional<AppUser> findByUsername(String username);
}