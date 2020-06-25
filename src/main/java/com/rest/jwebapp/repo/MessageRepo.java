package com.rest.jwebapp.repo;

import com.rest.jwebapp.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MessageRepo extends JpaRepository<Message, Long> {
}
