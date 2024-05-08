package com.project.db.service;

import com.project.db.entity.NewWord;
import com.project.db.model.response.NewWordResponse;
import com.project.db.repository.NewWordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    private final NewWordRepository newWordRepository;

    public AdminService(NewWordRepository newWordRepository) {
        this.newWordRepository = newWordRepository;
    }

    public List<NewWordResponse> findAllRequestedWords(){
        return  newWordRepository.findAllRequestedWords().stream().map(this::NewWord2NewWordResponse).toList();
    }


    public NewWordResponse NewWord2NewWordResponse(NewWord newWord){
        return new NewWordResponse(
                newWord.getWordId(), newWord.getUser().getId(), newWord.getWrittenForm(),
                newWord.getPartOfSpeech(), newWord.getDefinition(),newWord.getStatus(),
                newWord.getCreatedDate(), newWord.getConfirmedDate()
        );
    }

}
