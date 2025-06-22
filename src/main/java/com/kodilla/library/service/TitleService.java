package com.kodilla.library.service;

import com.kodilla.library.domain.Title;
import com.kodilla.library.repository.TitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TitleService {

    private final TitleRepository titleRepository;

    public List<Title> getAllTitles() {
        return titleRepository.findAll();
    }

    public Title saveTitle(Title title) {
        return titleRepository.save(title);
    }

    public Title getTitleById(Long id) {
        return titleRepository.findById(id).orElseThrow(() -> new RuntimeException("Title not found"));
    }
}
