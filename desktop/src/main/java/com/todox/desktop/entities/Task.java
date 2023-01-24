package com.todox.desktop.entities;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class Task {
    private Long id;

    private String content;

    @SerializedName("removed")
    private boolean isRemoved;

    @SerializedName("completed")
    private boolean isCompleted;
}
