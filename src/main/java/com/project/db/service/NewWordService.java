package com.project.db.service;

import com.project.db.entity.NewWord;
import com.project.db.entity.User;
import com.project.db.error.AlreadyExistsException;
import com.project.db.error.NotFoundException;
import com.project.db.model.request.NewWordCreateRequest;
import com.project.db.model.request.NewWordUpdateRequest;
import com.project.db.model.response.NewWordResponse;
import com.project.db.repository.EntryRepository;
import com.project.db.repository.NewWordRepository;
import com.project.db.repository.UserRepository;
import com.project.db.utils.Status;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class NewWordService {
    private final NewWordRepository newWordRepository;
    private final UserRepository userRepository;
    private final EntryRepository entryRepository;

    public NewWordService(NewWordRepository newWordRepository, UserRepository userRepository, EntryRepository entryRepository) {
        this.newWordRepository = newWordRepository;
        this.userRepository = userRepository;
        this.entryRepository = entryRepository;
    }

    public NewWord findById(String Id){
        return newWordRepository.findById(Id).orElseThrow(
                () -> new NotFoundException("User not found with id " + Id)
                );
    }

    public List<NewWordResponse> findAll(){
        return newWordRepository
                .findAll()
                .stream()
                .map(this::NewWord2NewWordResponse)
                .toList();
    }

    public NewWordResponse addNewWord(NewWordCreateRequest request){

        //Check word is already exist
        if(entryRepository.existsByWrittenFormAndPartOfSpeech(request.writtenForm(),request.partOfSpeech())){
            throw new AlreadyExistsException("Word with written form ["+request.writtenForm()+"] already exist");
        }
        if (newWordRepository.existsByWrittenFormAndPartOfSpeech(request.writtenForm(),request.partOfSpeech())){
            NewWord word = newWordRepository.findByWrittenForm(request.writtenForm());
            Status status = word.getStatus();
            if (status.equals(Status.DENIED)){
                throw new RuntimeException("Word with written form ["+request.writtenForm()+"] already denied");
            }
            if (status.equals(Status.REQUESTED)){
                throw new AlreadyExistsException("Word with written form ["+request.writtenForm()+"] already PENDING to confirm");
            }
        }
        NewWord newWord = NewWordCreateRequest2NewWord(request);
        newWordRepository.save(newWord);
        return NewWord2NewWordResponse(newWord);
    }

    public NewWordResponse updateNewWord(NewWordUpdateRequest request){
        NewWord newWord = findById(request.wordId());
        newWord.setWrittenForm(request.writtenForm());
        newWord.setPartOfSpeech(request.partOfSpeech());
        newWord.setDefinition(request.definition());
        return NewWord2NewWordResponse(newWord);
    }

    public NewWordResponse NewWord2NewWordResponse(NewWord newWord){
        return new NewWordResponse(
                newWord.getWordId(), newWord.getUser().getId(), newWord.getWrittenForm(),
                newWord.getPartOfSpeech(), newWord.getDefinition(),newWord.getStatus(),
                newWord.getCreatedDate(), newWord.getConfirmedDate()
        );
    }

    public NewWord NewWordCreateRequest2NewWord (NewWordCreateRequest request){
        UUID uuid = UUID.randomUUID();
        LocalDateTime time = LocalDateTime.now();
        NewWord newWord = new NewWord();
        newWord.setWordId(uuid.toString());
        User user = userRepository.findById(
                        request.userId())
                .orElseThrow(
                        () -> new NotFoundException("User not found with id " + request.userId()
                        )
                );
        newWord.setUser(user);
        newWord.setWrittenForm(request.writtenForm());
        newWord.setPartOfSpeech(request.partOfSpeech());
        newWord.setStatus(Status.REQUESTED);
        newWord.setCreatedDate(time);
        newWord.setDefinition(request.definition());
        return newWord;
    }
}
