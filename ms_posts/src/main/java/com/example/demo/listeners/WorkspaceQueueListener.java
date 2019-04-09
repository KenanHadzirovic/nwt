package com.example.demo.listeners;

import com.example.demo.IPostRepository;
import com.example.demo.Post;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class WorkspaceQueueListener {

    @Autowired
    IPostRepository _repository;

    @RabbitListener(queues = "#{workspaceQueue.name}")
    public void removePosts(String message) throws ParseException {

        Long id = Long.parseLong(message);

        _repository.removeByWorkspaceId(id);
    }
}
