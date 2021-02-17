package com.swedbank.academy.springlesson.group;

import com.swedbank.academy.springlesson.group.exception.GroupExistsException;
import com.swedbank.academy.springlesson.group.exception.GroupNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("api/groups")
public class GroupController {

    private static final Logger log = LoggerFactory.getLogger(GroupController.class);
    private GroupService groupService;
    @Autowired
    public GroupController( GroupService groupService){
        this.groupService = groupService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Group>> getGroups(){
        List<Group> list = groupService.getAll();
        return new ResponseEntity<List<Group>>(list, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Group> getGroupById(@PathVariable final long id) {

        try {
            Group group = groupService.findById(id);
            return new ResponseEntity<Group>(group, HttpStatus.OK);
        } catch (GroupNotFoundException ex) {
            log.error("On retrieveGroup", ex);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Group> addGroup(@RequestBody final Group group) {

        try {
            Group newGroup = groupService.save(group);
            // Return link to the new object
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(newGroup.getId()).toUri();
            return ResponseEntity.created(location).build();
        } catch (GroupExistsException ex) {
            log.error("On newGroup", ex);
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateGroup(@PathVariable final long id, @RequestBody final Group group) {

        try {
            Group existingGroup = groupService.findById(id);
            group.setId(existingGroup.getId());
            groupService.update(group);
            return ResponseEntity.ok().build();
        } catch (GroupNotFoundException ex) {
            log.error("On updateGroup", ex);
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteGroup(@PathVariable final long id) {

        try {
            groupService.delete(id);
            return ResponseEntity.ok().build();
        } catch (GroupNotFoundException ex) {
            log.error("On deleteGroup", ex);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAllGroups() {
        groupService.deleteAll();
        return ResponseEntity.ok().build();
    }

}

