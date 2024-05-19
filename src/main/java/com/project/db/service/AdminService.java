package com.project.db.service;

import com.project.db.entity.*;
import com.project.db.error.NotFoundException;
import com.project.db.model.request.RelationRequest;
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
//TODO Entry should not be return as a response . Create Entry Response instead
    public List<Entry> searchNewWords(String query){
        return entryRepository
                .findByWrittenFormContainingIgnoreCase(query)
                .stream()
                .toList();
    }

    public String generateEntryId() {
        Integer maxNumericId = entryRepository.findMaxNumericId();
        int nextNumericId = (maxNumericId != null) ? maxNumericId + 1 : 0;
        return "w" + nextNumericId;
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
        newWord.setStatus(Status.DENIED);
        newWordRepository.save(newWord);
        return newWord.getStatus();
    }

    @Transactional
    public Status confirmNewWord(String newWordId, RelationRequest request) {
        // Fetch the NewWord entity
        NewWord newWord = newWordRepository.findById(newWordId)
                .orElseThrow(() -> new NotFoundException("Word with id " + newWordId + " not found"));

        // Update status and confirmation date
        newWord.setStatus(Status.CONFIRMED);
        newWord.setConfirmedDate(LocalDateTime.now());
        newWordRepository.save(newWord);

        // Create a new Entry entity
        Entry entry = new Entry();
        entry.setEntryId(UUID.randomUUID().toString());
        entry.setWrittenForm(newWord.getWrittenForm());
        entry.setPartOfSpeech(newWord.getPartOfSpeech());
        Entry savedEntry = entryRepository.save(entry);

        // Create a new Synset entity
        Synset synset = new Synset();
        synset.setSynsetId(UUID.randomUUID().toString());
        synset.setPartOfSpeech(newWord.getPartOfSpeech());
        synset.setDefinition(newWord.getDefinition());
        Synset savedSynset = synsetRepository.save(synset);

        // Create a new Sense entity
        User user = newWord.getUser();
        Sense sense = new Sense();
        sense.setSenseId(UUID.randomUUID().toString());
        sense.setEntry(savedEntry);
        sense.setSynset(savedSynset);
        sense.getUserSet().add(user);
        Sense savedSense = senseRepository.save(sense);

        // Update user senses
        user.getUserSense().add(savedSense);
        userRepository.save(user);

        // Create relations between synsets
        Relations relations = new Relations();
        relations.setRelType(request.relType());
        relations.setSynset(savedSynset);  // Set the source synset
        Synset targetSynset = synsetRepository.findById(request.targetSynsetId())
                .orElseThrow(() -> new NotFoundException("Synset with id " + request.targetSynsetId() + " not found"));
        relations.setTargetSynset(targetSynset);  // Set the target synset

        // Debugging statements to ensure synset and targetSynset are not null
        if (relations.getSynset() == null) {
            throw new RuntimeException("Source synset is null.");
        }
        if (relations.getTargetSynset() == null) {
            throw new RuntimeException("Target synset is null.");
        }

        relationsRepository.save(relations);

        // Return the status of the new word
        return newWord.getStatus();
    }


}
