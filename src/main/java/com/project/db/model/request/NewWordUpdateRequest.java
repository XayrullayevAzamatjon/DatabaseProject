package com.project.db.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.db.utils.Part_of_speech;

public record NewWordUpdateRequest(
        @JsonProperty(namespace = "word_id") String wordId,
        @JsonProperty(namespace = "written_form") String writtenForm,
        @JsonProperty(namespace = "defination") String defination,
        @JsonProperty(namespace = "part_of_speech") Part_of_speech partOfSpeech) {
}