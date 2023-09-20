package br.edu.utfpr.todo.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToDoDTO {
    private String title;
    private String description;
    private boolean done = false;
    private LocalDateTime date;
}
