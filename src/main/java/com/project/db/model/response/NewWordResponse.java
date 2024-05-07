package com.project.db.model.response;

import com.project.db.utils.Part_of_speech;
import com.project.db.utils.Statuus;

import java.time.LocalDateTime;

public record NewWordResponse(String word_Id, String user_id, String written_form,
                              Part_of_speech part_of_speech, Statuus Status,
                              LocalDateTime created_date, LocalDateTime confirmed_date) {
}
