package org.crustproject.taskmanagement.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTaskRequest {
    private String title;
    private String description;
}
