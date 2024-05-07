package com.project.db.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.db.utils.PartOfSpeech;


public record NewWordCreateRequest(
        @JsonProperty("user_id")
        String userId,
        @JsonProperty("written_form")
        String writtenForm,
        @JsonProperty("definition")
        String definition,
        @JsonProperty("part_of_speech")
        PartOfSpeech partOfSpeech) {

}
