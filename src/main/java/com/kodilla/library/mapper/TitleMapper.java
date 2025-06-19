package com.kodilla.library.mapper;

import com.kodilla.library.domain.Title;
import com.kodilla.library.domain.TitleDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TitleMapper {

    public Title mapToTitle(final TitleDto dto) {
        return new Title(
                dto.getId(),
                dto.getTitle(),
                dto.getAuthor(),
                dto.getPublicationYear()
        );
    }

    public TitleDto mapToTitleDto(final Title title) {
        return new TitleDto(
                title.getId(),
                title.getTitle(),
                title.getAuthor(),
                title.getPublicationYear()
        );
    }

    public List<TitleDto> mapToTitleDtoList(final List<Title> titleList) {
        return titleList.stream()
                .map(this::mapToTitleDto)
                .toList();
    }
}
