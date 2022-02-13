package ru.mtven.springdemo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.mtven.springdemo.model.Users;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends PagingAndSortingRepository<Users, Long> {

    Optional<Users> findByLogin(String login);
}
