package com.nwt.users.bll;

import com.nwt.users.bll.interfaces.WorkspaceService;
import com.nwt.users.entities.Workspace;
import com.nwt.users.repository.interfaces.WorkspaceRepository;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;

@Service
public class WorkspaceServiceImpl implements WorkspaceService {
    @Autowired
    WorkspaceRepository _repository;

    @Autowired
    RabbitTemplate _rabbitTemplate;

    @Autowired
    Queue _workspaceQueue;


    public boolean remove(long id) throws Exception {

        Optional<Workspace> optDbWorkspace = _repository.findById(id);

        if (!optDbWorkspace.isPresent()) {
            throw new Exception("Workspace not found by provided id");
        }

        Workspace db = optDbWorkspace.get();

        db.setDeleted(true);

        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
        dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
        SimpleDateFormat dateFormatLocal = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");

        db.setModifiedDate(dateFormatLocal.parse( dateFormatGmt.format(new Date())));

        _repository.save(db);

        _rabbitTemplate.convertAndSend(_workspaceQueue.getName(), id);

        return true;
    }
}