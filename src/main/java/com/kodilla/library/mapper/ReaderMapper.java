package com.kodilla.library.mapper;

import com.kodilla.library.domain.Reader;
import com.kodilla.library.domain.ReaderDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderMapper {

    public Reader mapToReader(final ReaderDto dto) {
        return new Reader(
                dto.getId(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getAccCreated()
        );
    }

    public ReaderDto mapToReaderDto(final Reader reader) {
        return new ReaderDto(
                reader.getId(),
                reader.getFirstName(),
                reader.getLastName(),
                reader.getAccCreated()
        );
    }

    public List<ReaderDto> mapToReaderDtoList(final List<Reader> readerList) {
        return readerList.stream()
                .map(this::mapToReaderDto)
                .toList();
    }
}
