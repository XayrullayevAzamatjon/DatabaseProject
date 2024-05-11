package com.project.db.service;

import com.project.db.entity.*;
import com.project.db.error.NotFoundException;
import com.project.db.model.response.NewWordResponse;
import com.project.db.repository.*;
import com.project.db.utils.Status;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AdminService {
    private final NewWordRepository newWordRepository;
    private final EntryRepository entryRepository;
    private final RelationsRepository relationsRepository;
    private final SenseRepository senseRepository;
    private final SynsetRepository synsetRepository;
    private final UserRepository userRepository;


    public AdminService(NewWordRepository newWordRepository, EntryRepository entryRepository, RelationsRepository relationsRepository, SenseRepository senseRepository, SynsetRepository synsetRepository, UserRepository userRepository) {
        this.newWordRepository = newWordRepository;
        this.entryRepository = entryRepository;
        this.relationsRepository = relationsRepository;
        this.senseRepository = senseRepository;
        this.synsetRepository = synsetRepository;
        this.userRepository = userRepository;
    }

    public List<NewWordResponse> findAllRequestedWords(){
        return  newWordRepository.findAllRequestedWords().stream().map(this::NewWord2NewWordResponse).toList();
    }

    public List<NewWordResponse> searchNewWords(String query){
        return newWordRepository
                .findByWrittenFormContainingIgnoreCase(query)
                .stream()
                .map(this::NewWord2NewWordResponse)
                .toList();
    }
    public List<NewWordResponse> findByStatus(Status status){
        return newWordRepository
                .findAllByStatus(status)
                .stream()
                .map(this::NewWord2NewWordResponse)
                .toList();
    }


    public NewWordResponse NewWord2NewWordResponse(NewWord newWord){
        return new NewWordResponse(
                newWord.getWordId(), newWord.getUser().getId(), newWord.getWrittenForm(),
                newWord.getPartOfSpeech(), newWord.getDefinition(),newWord.getStatus(),
                newWord.getCreatedDate(), newWord.getConfirmedDate()
        );
    }

    public Status deniedNewWOrd(String newWordId){
        NewWord newWord = newWordRepository
                .findById(newWordId)
                .orElseThrow(
                        ()->new NotFoundException("Word with id " + newWordId + " not found")
                );
        LocalDateTime time = LocalDateTime.now();
        newWord.setStatus(Status.DENIED);
        newWord.setConfirmedDate(time);
        newWordRepository.save(newWord);
        return newWord.getStatus();
    }

    @Transactional
    public Status confirmNewWord(String newWordId){
        NewWord newWord = newWordRepository.findById(newWordId)
                .orElseThrow(
                        ()->new NotFoundException("Word with id " + newWordId + " not found")
                );
        newWord.setStatus(Status.CONFIRMED);
        newWordRepository.save(newWord);

        Entry entry = new Entry();
        UUID uuid1 = UUID.randomUUID();
        entry.setEntryId(uuid1.toString());
        entry.setWrittenForm(newWord.getWrittenForm());
        entry.setPartOfSpeech(newWord.getPartOfSpeech());
        entryRepository.save(entry);

        Synset synset = new Synset();
        UUID uuid2 = UUID.randomUUID();
        synset.setSynsetId(uuid2.toString());
        synset.setPartOfSpeech(newWord.getPartOfSpeech());
        synset.setDefinition(newWord.getDefinition());
        synsetRepository.save(synset);

        User user = newWord.getUser();
        Sense sense = new Sense();
        UUID uuid3 = UUID.randomUUID();
        sense.setSenseId(uuid3.toString());
        sense.setEntry(entry);
        sense.setSynset(synset);
        sense.getUserSet().add(user);
        senseRepository.save(sense);

        user.getUserSense().add(sense);
        userRepository.save(user);

        return newWord.getStatus();
    }
}
