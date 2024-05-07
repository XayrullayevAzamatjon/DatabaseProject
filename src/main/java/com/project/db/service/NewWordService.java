package com.project.db.service;

import com.project.db.entity.NewWord;
import com.project.db.entity.User;
import com.project.db.model.request.NewWordCreateRequest;
import com.project.db.model.request.NewWordUpdateRequest;
import com.project.db.model.response.NewWordResponse;
import com.project.db.repositpry.NewWordRepository;
import com.project.db.repositpry.UserRepository;
import com.project.db.utils.Statuus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class NewWordService {
    private final NewWordRepository newWordRepository;
    private final UserRepository userRepository;

    public NewWordService(NewWordRepository newWordRepository, UserRepository userRepository) {
        this.newWordRepository = newWordRepository;
        this.userRepository = userRepository;
    }

    public NewWord findById(String Id){
        return newWordRepository.findById(Id).orElseThrow(
                () -> new RuntimeException("User not found with id " + Id)
                );
    }

    public List<NewWordResponse> findAll(){
        return newWordRepository
                .findAll()
                .stream()
                .map(this::NewWord2NewWordResponse)
                .toList();
    }

    public NewWordResponse addNewWord(NewWordCreateRequest newWordCreateRequest){

        NewWord newWord = NewWordCreateRequest2NewWord(newWordCreateRequest);
        newWordRepository.save(newWord);
        return NewWord2NewWordResponse(newWord);
    }

    public NewWordResponse updateNewWord(NewWordUpdateRequest newWordUpdateRequest){
        NewWord byId = findById(newWordUpdateRequest.word_id());
        byId.setWritten_form(newWordUpdateRequest.written_form());
        byId.setPart_of_speech(newWordUpdateRequest.part_of_speech());
        return NewWord2NewWordResponse(byId);
    }


    public NewWordResponse NewWord2NewWordResponse(NewWord newWord){
        return new NewWordResponse(
                newWord.getWord_Id(), newWord.getUser().getId(), newWord.getWritten_form(),
                newWord.getPart_of_speech(), newWord.getStatus(),
                newWord.getCreated_date(), newWord.getConfirmed_date()
        );
    }

    public NewWord NewWordCreateRequest2NewWord (NewWordCreateRequest newWordCreateRequest){
        UUID uuid = UUID.randomUUID();
        LocalDateTime time = LocalDateTime.now();
        NewWord newWord = new NewWord();
        newWord.setWord_Id(uuid.toString());
        User user = userRepository.findById(
                        newWordCreateRequest.userId())
                .orElseThrow(
                        () -> new RuntimeException("User not found with id " + newWordCreateRequest.userId()
                        )
                );
        newWord.setUser(user);
        newWord.setWritten_form(newWordCreateRequest.writtenForm());
        newWord.setPart_of_speech(newWordCreateRequest.partOfSpeech());
        newWord.setStatus(Statuus.REQUESTED);
        newWord.setCreated_date(time);
        newWord.setConfirmed_date(time);
        return newWord;
    }
}
