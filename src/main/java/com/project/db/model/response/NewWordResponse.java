package com.project.db.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.db.utils.PartOfSpeech;
import com.project.db.utils.Status;

import java.time.LocalDateTime;

public record NewWordResponse(
        @JsonProperty(namespace = "word_id") String wordId,
        @JsonProperty(namespace = "user_id") String userId,
        @JsonProperty(namespace = "written_form") String writtenForm,
        @JsonProperty(namespace = "part_of_speech") PartOfSpeech partOfSpeech,
        @JsonProperty(namespace = "status") Status status,
        @JsonProperty(namespace = "created_date") LocalDateTime createdDate,
        @JsonProperty(namespace = "confirmed_date") LocalDateTime confirmedDate) {
}
