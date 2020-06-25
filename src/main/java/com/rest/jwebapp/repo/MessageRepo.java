package com.rest.jwebapp.repo;

import com.rest.jwebapp.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message, Long> {
}
