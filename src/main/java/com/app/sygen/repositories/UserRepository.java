package com.app.sygen.repositories;

import org.springframework.stereotype.Repository;

import com.app.sygen.entities.User;

@Repository
public interface UserRepository extends AppRepository<User, Long>
{
	User findByLoginAndPassword(String login, String password);
    User findByLogin(String login);
}
