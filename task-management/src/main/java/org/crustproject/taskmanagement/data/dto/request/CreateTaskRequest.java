package org.crustproject.taskmanagement.data.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTaskRequest {
    private String title;
    private String description;
}
