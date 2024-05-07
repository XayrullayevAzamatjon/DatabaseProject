package com.project.db.model.request;

import com.project.db.utils.Part_of_speech;
import com.fasterxml.jackson.annotation.JsonProperty;


public record NewWordCreateRequest(
        @JsonProperty("user_id")
        String userId,
        @JsonProperty("written_form")
        String writtenForm,
        @JsonProperty("definition")
        String definition,
        @JsonProperty("part_of_speech")
        Part_of_speech partOfSpeech) {

}
