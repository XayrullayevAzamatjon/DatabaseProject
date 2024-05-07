package com.project.db.model.request;

import com.project.db.utils.Part_of_speech;

public record NewWordUpdateRequest(String word_id, String written_form,
                                   Part_of_speech part_of_speech) {
}
