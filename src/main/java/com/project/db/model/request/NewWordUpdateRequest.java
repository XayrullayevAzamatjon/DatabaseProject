package com.project.db.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.db.utils.PartOfSpeech;

public record NewWordUpdateRequest(
        @JsonProperty(namespace = "word_id") String wordId,
        @JsonProperty(namespace = "written_form") String writtenForm,
        @JsonProperty(namespace = "part_of_speech") PartOfSpeech partOfSpeech,
        @JsonProperty("definition") String definition) {
}