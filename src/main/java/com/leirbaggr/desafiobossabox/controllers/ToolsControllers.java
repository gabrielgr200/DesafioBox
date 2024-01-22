package com.leirbaggr.desafiobossabox.controllers;


import com.leirbaggr.desafiobossabox.dto.ToolsRequest;
import com.leirbaggr.desafiobossabox.dto.ToolsResponse;
import com.leirbaggr.desafiobossabox.entity.Tools;
import com.leirbaggr.desafiobossabox.services.ToolsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/tools")
public class ToolsControllers {
    @Autowired
    private ToolsService toolsService;

    @GetMapping
    public ResponseEntity findAll() {
        List<Tools> allTools = toolsService.getAllTools();
        return ResponseEntity.ok().body(allTools);
    }

    @GetMapping("/findByTag")
    public ResponseEntity findToolsByTags(@RequestParam String tags) {
        List<Tools> toolsList = toolsService.findTollByTag(tags);
        return ResponseEntity.ok().body(toolsList);
    }

    @PostMapping
    public ResponseEntity saveTools(@RequestBody ToolsRequest toolsRequest) {
        Tools toolsToBeSaved = toolsRequest.toModel();
        ToolsResponse toolsResponse = toolsService.saveTools(toolsToBeSaved);

        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(toolsResponse.id())
                .toUri();

        return ResponseEntity.created(headerLocation).body(toolsResponse);
    }

    @DeleteMapping
    public ResponseEntity deleteTools(@RequestParam Long id) {
        toolsService.deleteToolsById(id);
        return ResponseEntity.ok().body("Tools deleted");
    }
}
