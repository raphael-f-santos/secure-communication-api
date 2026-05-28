package br.com.raphaelsantos.auth_service.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.raphaelsantos.auth_service.entity.AppUser;


@Repository
public interface UserRepository extends CrudRepository<AppUser, Long>{
    Optional<AppUser> findByUsername(String username);
}