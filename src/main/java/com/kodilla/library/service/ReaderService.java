package com.kodilla.library.service;

import com.kodilla.library.domain.Reader;
import com.kodilla.library.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReaderService {

    private final ReaderRepository repository;

    public List<Reader> getAllReaders() {
        return repository.findAll();
    }

    public Optional<Reader> getReaderById(Long id) {
        return repository.findById(id);
    }

    public Reader saveReader(Reader reader) {
        return repository.save(reader);
    }

    public void deleteReader(Long id) {
        repository.deleteById(id);
    }
}
