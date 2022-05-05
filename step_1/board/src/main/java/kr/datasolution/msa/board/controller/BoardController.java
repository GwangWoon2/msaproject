package kr.datasolution.msa.board.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import kr.datasolution.msa.board.dto.BoardDto;
import kr.datasolution.msa.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 게시물 관련 처리 Controller Layer
 */
@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    /** 게시물 관련 처리 Service Layer 연결 */
    private final BoardService boardService;

    /**
     * 게시물 목록 조회 화면 이동
     * @return 게시물 목록 조회 화면 경로
     */

    @Operation(description = "목록조회")
    @GetMapping("")
    public List<BoardDto> getViewBoardMain() {

        return boardService.getBoardList();
    }

    /**
     * 게시물 상세 조회 화면 이동
     * @param id 게시물 ID
     * @return 게시물 상세 조회 화면 경로
     */
    @Operation(description = "게시물 상세조회")
    @GetMapping("{id}")
    public BoardDto getViewBoard(
            @Parameter @PathVariable("id") int id) {
        return boardService.getBoard(id);
    }


    /**
     * 게시물 등록 처리
     * @param boardDto 게시물 등록 데이터
     * @return 게시물 상세 조회 화면 호출
     */

    @Operation(description = "게시물 등록")
    @PostMapping("")
    public int addBoard(@RequestBody BoardDto boardDto) {
        return boardService.addBoard(boardDto);
    }

    /**
     * 게시물 수정 처리
     * @param boardDto 게시물 수정 데이터
     * @return 게시물 상세 조회 화면 호출
     */
    @Operation(description = "게시물 수정")
    @PutMapping("{id}")
    public int modBoard(
            @Parameter@PathVariable("id") int id, @RequestBody BoardDto boardDto) {
        boardDto.setId(id);
        return boardService.modBoard(boardDto);
    }

    /**
     * 게시물 삭제 처리
     * @param id 삭제 대상 게시물 ID
     * @return 게시물 목록 조회 화면 호출
     */

    @Operation(description = "게시물 삭제")
    @DeleteMapping("{id}")
    public int removeBoard(
            @Parameter@PathVariable("id") int id) {
        return boardService.removeBoard(id);
    }
}


