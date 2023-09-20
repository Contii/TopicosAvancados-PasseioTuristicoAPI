package br.edu.utfpr.todo.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ToDo extends BasicEntity {
    private String title;
    private String description;
    private boolean done = false;
    private LocalDateTime date;
}
