package com.prakar.jira.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Meta {
    private LocalDateTime timestamp;
    private String path;
    private Long page;
    private Long size;
    private Long total;

    public Meta(String path){
        this.timestamp = LocalDateTime.now();
        this.path = path;
    }

    public Meta(String path,Long page,Long size,Long total){
        this.timestamp = LocalDateTime.now();
        this.path = path;
        this.page=page;
        this.size = size;
        this.total = total;
    }
}
