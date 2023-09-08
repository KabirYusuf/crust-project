package org.crustproject.taskmanagement;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "org.crustproject.**")
public class TaskConfig {
}
