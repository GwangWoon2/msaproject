package kr.datasolution.msa.webclient.service;


import kr.datasolution.msa.webclient.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 게시물 관련 처리 Service Layer
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {

    private final WebClient webClient = WebClient.builder().baseUrl("http://localhost:8081/board").build();

    /**
     * 전체 게시물 리스트 조회
     * @return 전체 게시물 리스트
     */
    public List<BoardDto> getBoardList() {

        return webClient.get().uri("").retrieve().bodyToFlux(BoardDto.class).toStream().collect(Collectors.toList());
    }

    /**
     * 지정 게시물 상세 조회
     * @param id 게시물 ID
     * @return 지정 게시물
     */
    public  BoardDto getBoard(int id) {
        return webClient.get()
                .uri("/"+id)
                .retrieve()
                .bodyToMono(BoardDto.class)
                .block();
    }

    /**
     * 게시물 등록 처리
     * @param boardDto 게시물 등록 데이터
     */
    public void addBoard(BoardDto boardDto) {
        webClient.post()
                .uri("/")
                .body(Mono.just(boardDto),BoardDto.class)
                .retrieve()
                .bodyToMono(void.class)
                .block();
    }

    /**
     * 게시물 수정 처리
     * @param boardDto 게시물 수정 데이터
     */
    public void modBoard(BoardDto boardDto) {
        webClient.put()
                .uri("/"+boardDto.getId())
                .body(Mono.just(boardDto),BoardDto.class)
                .retrieve()
                .bodyToMono(void.class)
                .block();
    }

    /**
     * 게시물 삭제 처리
     * @param id 게시물 ID
     */
    public void removeBoard(int id) {
        webClient.delete()
                .uri("/"+id)
                .retrieve()
                .bodyToMono(void.class)
                .block();

    }
}
