package com.javatech.lab8.dtos;

import java.io.Serializable;
import java.util.List;

public class DocumentDTO implements Serializable {
    Long id;
    String name;
    String type;
    String content;
    List<DocumentAuthorDTO> authors;
    List<DocumentReferenceDTO> references;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<DocumentAuthorDTO> getAuthors() {
        return authors;
    }

    public void setAuthors(List<DocumentAuthorDTO> authors) {
        this.authors = authors;
    }

    public List<DocumentReferenceDTO> getReferences() {
        return references;
    }

    public void setReferences(List<DocumentReferenceDTO> references) {
        this.references = references;
    }
}
