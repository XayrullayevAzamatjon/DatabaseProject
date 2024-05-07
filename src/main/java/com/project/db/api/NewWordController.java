package com.project.db.api;

import com.project.db.entity.NewWord;
import com.project.db.model.request.NewWordCreateRequest;
import com.project.db.model.request.NewWordUpdateRequest;
import com.project.db.model.response.NewWordResponse;
import com.project.db.service.NewWordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/new-words")
public class NewWordController {
    private final NewWordService newWordService;

    public NewWordController(NewWordService newWordService) {
        this.newWordService = newWordService;
    }

    @GetMapping("{id}")
    public NewWordResponse findById(@PathVariable String id){
        NewWord byId = newWordService.findById(id);
        return newWordService.NewWord2NewWordResponse(byId);
    }

    @GetMapping()
    public List<NewWordResponse> findAllNewWord(){
        return newWordService.findAll();
    }

    @PostMapping()
    public NewWordResponse addNewWord(@RequestBody NewWordCreateRequest newWordCreateRequest){
        return newWordService.addNewWord(newWordCreateRequest);
    }

    @PutMapping("/update")
    public NewWordResponse updateNewWord(@RequestBody NewWordUpdateRequest newWordUpdateRequest){
        return newWordService.updateNewWord(newWordUpdateRequest);
    }


}
