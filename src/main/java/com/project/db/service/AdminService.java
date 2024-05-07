package com.project.db.service;

import com.project.db.entity.NewWord;
import com.project.db.model.response.NewWordResponse;
import com.project.db.repositpry.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    private final EntryRepository entryRepository;
    private final UserRepository userRepository;
    private final NewWordRepository newWordRepository;
    private final RelationsRepository relationsRepository;
    private final SenseRepository senseRepository;
    private final SynsetRepository synsetRepository;

    public AdminService(EntryRepository entryRepository, UserRepository userRepository,
                        NewWordRepository newWordRepository, RelationsRepository relationsRepository,
                        SenseRepository senseRepository, SynsetRepository synsetRepository) {
        this.entryRepository = entryRepository;
        this.userRepository = userRepository;
        this.newWordRepository = newWordRepository;
        this.relationsRepository = relationsRepository;
        this.senseRepository = senseRepository;
        this.synsetRepository = synsetRepository;
    }

//    public List<NewWordResponse> allRequestedNewWords(){
//        return newWordRepository.findAllRequestedNewWords()
//                .stream()
//                .map(this::NewWord2NewWordResponse)
//                .toList();
//    }

    public NewWordResponse NewWord2NewWordResponse(NewWord newWord){
        return new NewWordResponse(
                newWord.getWord_Id(), newWord.getUser().getId(), newWord.getWritten_form(),
                newWord.getPart_of_speech(), newWord.getDefinition(), newWord.getStatus(),
                newWord.getCreated_date(), newWord.getConfirmed_date()
        );
    }
}
