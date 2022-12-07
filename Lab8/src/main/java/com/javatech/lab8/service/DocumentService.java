package com.javatech.lab8.service;

import com.javatech.lab8.dtos.*;
import com.javatech.lab8.entity.Document;
import com.javatech.lab8.entity.User;
import com.javatech.lab8.exceptions.*;
import com.javatech.lab8.repository.AccountRepository;
import com.javatech.lab8.repository.DocumentRepository;

import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

public class DocumentService {

    @Inject
    protected DocumentRepository documentRepository;

    @Inject
    protected AccountRepository accountRepository;


    public DocumentDTO get(Long documentId) {

        Document document = documentRepository.findById(Document.class, documentId);

        if (document != null) {

            DocumentDTO documentDTO = new DocumentDTO();
            List<DocumentAuthorDTO> documentAuthorsDTO = new ArrayList<>();
            List<DocumentReferenceDTO> documentReferencesDTO = new ArrayList<>();

            for (User user : document.getAuthors()) {
                DocumentAuthorDTO authorDTO = new DocumentAuthorDTO();

                authorDTO.setId(user.getId());
                authorDTO.setName(user.getName());

                documentAuthorsDTO.add(authorDTO);
            }

            for (Document reference : document.getBibliography()) {
                DocumentReferenceDTO referenceDTO = new DocumentReferenceDTO();

                referenceDTO.setId(reference.getId());
                referenceDTO.setName(reference.getName());

                documentReferencesDTO.add(referenceDTO);
            }

            documentDTO.setContent(document.getContent());
            documentDTO.setName(document.getName());
            documentDTO.setId(document.getId());
            documentDTO.setAuthors(documentAuthorsDTO);
            documentDTO.setReferences(documentReferencesDTO);

            return documentDTO;
        } else {
            throw new DocumentNotFoundException();
        }

    }

    public List<DocumentDTO> gets() {

        List<DocumentDTO> documentsDTO = new ArrayList<>();

        for (Document document : documentRepository.getAll()) {

            DocumentDTO documentDTO = new DocumentDTO();
            List<DocumentAuthorDTO> documentAuthorsDTO = new ArrayList<>();
            List<DocumentReferenceDTO> documentReferencesDTO = new ArrayList<>();

            for (User author : document.getAuthors()) {
                DocumentAuthorDTO documentAuthorDTO = new DocumentAuthorDTO();

                documentAuthorDTO.setName(author.getName());
                documentAuthorDTO.setId(author.getId());

                documentAuthorsDTO.add(documentAuthorDTO);
            }

            for (Document reference : document.getBibliography()) {
                DocumentReferenceDTO documentReferenceDTO = new DocumentReferenceDTO();

                documentReferenceDTO.setName(reference.getName());
                documentReferenceDTO.setId(reference.getId());

                documentReferencesDTO.add(documentReferenceDTO);
            }

            documentDTO.setId(document.getId());
            documentDTO.setType(document.getType());
            documentDTO.setName(document.getName());
            documentDTO.setContent(document.getContent());
            documentDTO.setAuthors(documentAuthorsDTO);
            documentDTO.setReferences(documentReferencesDTO);

            documentsDTO.add(documentDTO);

        }
        return documentsDTO;

    }


    public void create(DocumentAddDTO addDocument, Long accountId) {

        User user = accountRepository.findById(User.class, accountId);
        Document document = new Document(addDocument.getName(), addDocument.getName(), addDocument.getType());

        document.setAuthors(Collections.singletonList(user));
        documentRepository.save(document);
    }

    public void remove(Long documentId, Long accountId) {

        Document document = documentRepository.findById(Document.class, documentId);

        if (document != null) {
            User author = null;

            for (User user : document.getAuthors()) {
                if (user.getId().equals(accountId)) {
                    author = user;
                }
            }

            if (author != null) {
                documentRepository.deleteById(Document.class, documentId);
            } else {
                throw new DocumentInvalidOwnershipException();
            }
        } else {
            throw new DocumentNotFoundException();
        }

    }

    public void removeAuthorFromDocument(Long documentId, Long accountId, Long authorId) {

        Document document = documentRepository.findById(Document.class, documentId);

        if (document != null) {
            User author = null;

            for (User user : document.getAuthors()) {
                if (user.getId().equals(accountId)) {
                    author = user;
                }
            }

            if (author != null) {
                List<User> authors = document.getAuthors();

                User userToRemove = null;

                for (User user : authors) {
                    if (user.getId().equals(authorId)) {
                        userToRemove = user;
                    }
                }

                if (userToRemove != null) {

                    List<User> documentAuthors = document.getAuthors();
                    documentAuthors.remove(userToRemove);
                    document.setAuthors(documentAuthors);


                    documentRepository.save(document);
                } else {
                    throw new DocumentAuthorNotFoundException();
                }
            } else {
                throw new DocumentInvalidOwnershipException();
            }
        } else {
            throw new DocumentNotFoundException();
        }
    }

    public List<DocumentDTO> getPersonal(Long accountId) {

        List<DocumentDTO> documentsDTO = new ArrayList<>();

        for (Document document : documentRepository.getAll()) {
            User author = document.getAuthors()
                    .stream()
                    .filter(a -> a.getId().equals(accountId))
                    .findFirst()
                    .orElse(null);

            if (author != null) {
                DocumentDTO documentDTO = new DocumentDTO();

                documentDTO.setContent(document.getContent());
                documentDTO.setType(document.getType());
                documentDTO.setName(document.getName());
                documentDTO.setId(document.getId());

                List<DocumentAuthorDTO> authorsDTO = new ArrayList<>();

                for (User user : document.getAuthors()) {
                    DocumentAuthorDTO documentAccountDTO = new DocumentAuthorDTO();

                    documentAccountDTO.setId(user.getId());
                    documentAccountDTO.setName(user.getName());

                    authorsDTO.add(documentAccountDTO);
                }
                List<DocumentReferenceDTO> referencesDTO = new ArrayList<>();

                for (Document reference : document.getBibliography()) {
                    DocumentReferenceDTO documentReferenceDTO = new DocumentReferenceDTO();

                    documentReferenceDTO.setId(document.getId());
                    documentReferenceDTO.setName(document.getName());

                    referencesDTO.add(documentReferenceDTO);

                }


                documentDTO.setAuthors(authorsDTO);
                documentDTO.setReferences(referencesDTO);

                documentsDTO.add(documentDTO);
            }

        }

        return documentsDTO;
    }


    public void addAuthorToDocument(Long documentId, Long authorId, Long accountId) {

        Document document = documentRepository.findById(Document.class, documentId);

        if (document != null) {

            User author = null;

            for (User user : document.getAuthors()) {
                if (user.getId().equals(accountId)) {
                    author = user;
                }
            }

            if (author != null) {

                User authorUser = accountRepository.findById(User.class, authorId);

                if (authorUser != null) {
                    List<User> authors = document.getAuthors();

                    if (!authors.contains(authorUser)) {

                        authors.add(authorUser);
                        document.setAuthors(authors);
                        documentRepository.save(document);

                    } else {
                        throw new DocumentAuthorAlreadyExistsException();
                    }

                } else {
                    throw new DocumentAuthorNotFoundException();
                }
            } else {
                throw new DocumentInvalidOwnershipException();
            }
        } else {
            throw new DocumentNotFoundException();
        }

    }

    public void addReferenceToDocument(Long documentId, Long accountId, DocumentAddReferenceDTO referenceToAdd) {
        Document document = documentRepository.findById(Document.class, documentId);

        if (document != null) {

            User author = null;

            for (User user : document.getAuthors()) {
                if (user.getId().equals(accountId)) {
                    author = user;
                }
            }

            if (author == null) {
                throw new DocumentInvalidOwnershipException();
            }

            List<Document> references = document.getBibliography();

            Document reference = documentRepository.findById(Document.class, referenceToAdd.getDocumentId());

            if (reference == null) {
                throw new DocumentReferenceNotFoundException();
            }

            for (Document entry : references) {
                if (entry.getId().equals(reference.getId())) {
                    throw new DocumentReferenceAlreadyExistsException();
                }
            }
            Map<Long, List<Long>> globalReferencesGraphInit = new HashMap<>();
            List<Document> allDocuments = documentRepository.getAll();

            List<Long> currentDocumentRefs = document.getBibliography()
                    .stream()
                    .map(Document::getId)
                    .collect(Collectors.toList());


            currentDocumentRefs.add(reference.getId());
            globalReferencesGraphInit.put(document.getId(), currentDocumentRefs);

            System.out.println("Global Refs Init:" + globalReferencesGraphInit);

            allDocuments.removeIf(doc -> doc.getId().equals(document.getId()));


            for (Document entry : allDocuments) {
                List<Long> entryRefs = entry.getBibliography()
                        .stream()
                        .map(Document::getId)
                        .collect(Collectors.toList());

                globalReferencesGraphInit.put(entry.getId(), entryRefs);
            }
            List<Document> documentExistingRefs = document.getBibliography();

            documentExistingRefs.add(reference);
            document.setBibliography(documentExistingRefs);

            documentRepository.save(document);
        } else {
            throw new DocumentNotFoundException();
        }
    }

    public void removeReferenceFromDocument(Long documentId, Long referenceId, Long accountId) {
        Document document = documentRepository.findById(Document.class, documentId);

        if (document != null) {

            User author = null;

            for (User user : document.getAuthors()) {
                if (user.getId().equals(accountId)) {
                    author = user;
                }
            }

            if (author != null) {
                Document reference = null;

                for (Document referee : document.getBibliography()) {
                    if (referee.getId().equals(referenceId)) {
                        reference = referee;
                    }
                }
                if (reference != null) {
                    List<Document> actualReferences = document.getBibliography();
                    actualReferences.remove(reference);
                    document.setBibliography(actualReferences);
                    documentRepository.save(document);
                } else {
                    throw new DocumentReferenceNotFoundException();
                }
            } else {
                throw new DocumentInvalidOwnershipException();
            }

        } else {
            throw new DocumentNotFoundException();
        }
    }

    public List<DocumentReferenceDTO> getReferencesOfDocument(Long documentId, Long userId) {

        Document document = documentRepository.findById(Document.class, documentId);

        if (document != null) {
            List<DocumentReferenceDTO> referencesDTO = new ArrayList<>();
            for (Document reference : document.getBibliography()) {
                DocumentReferenceDTO documentReferenceDTO = new DocumentReferenceDTO();

                documentReferenceDTO.setName(reference.getName());
                documentReferenceDTO.setId(reference.getId());

                referencesDTO.add(documentReferenceDTO);
            }
            return referencesDTO;
        } else {
            throw new DocumentNotFoundException();
        }
    }


}
