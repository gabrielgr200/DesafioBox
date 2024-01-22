package com.leirbaggr.desafiobossabox.services;

import com.leirbaggr.desafiobossabox.dto.ToolsResponse;
import com.leirbaggr.desafiobossabox.entity.Tools;
import com.leirbaggr.desafiobossabox.repository.ToolsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToolsService {

    private final ToolsRepository toolsRepository;

    public ToolsService(ToolsRepository toolsRepository) {
        this.toolsRepository = toolsRepository;
    }

    public List<Tools> getAllTools() {
        List<Tools> toolsList = toolsRepository.findAll();
        return toolsList;
    }

    public List<Tools> findTollByTag(String tags) {
        List<Tools> toolsList = toolsRepository.buscar(tags);
        return toolsList;
    }

    public ToolsResponse saveTools(Tools tools) {
        Tools toolsSaved = toolsRepository.save(tools);
        ToolsResponse toolsResponse = new ToolsResponse(
                toolsSaved.getId(),
                toolsSaved.getTitle(),
                toolsSaved.getLink(),
                toolsSaved.getDescription(),
                toolsSaved.getTags()
        );
        return toolsResponse;
    }

    public void deleteToolsById(Long id) {
        toolsRepository.deleteById(id);
    }
}
