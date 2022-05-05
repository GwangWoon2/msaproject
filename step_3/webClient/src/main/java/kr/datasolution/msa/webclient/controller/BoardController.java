package kr.datasolution.msa.webclient.controller;

import kr.datasolution.msa.webclient.dto.BoardDto;
import kr.datasolution.msa.webclient.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


/**
 * 게시물 관련 처리 Controller Layer
 */
@Controller
@RequestMapping("/webclient")
@RequiredArgsConstructor
public class BoardController {

    /** 게시물 관련 처리 Service Layer 연결 */
    private final BoardService boardService;

    /**
     * 게시물 목록 조회 화면 이동
     * @return 게시물 목록 조회 화면 경로
     */
    @GetMapping("")
    public String getViewBoardMain(ModelMap map) {
        map.put("list", boardService.getBoardList());
        return "main";
    }

    /**
     * 게시물 상세 조회 화면 이동
     * @param id 게시물 ID
     * @param map View 로 전달할 ModelMap 객체
     * @return 게시물 상세 조회 화면 경로
     */
    @GetMapping("{id}")
    public String getViewBoard(@PathVariable("id") int id,ModelMap map) {
        map.addAttribute("info",boardService.getBoard(id));
        return "info";
    }

    /**
     * 게시물 등록 화면 이동
     * @return 게시물 등록 화면 경로
     */
    @GetMapping("new")
    public String getViewBoardNew() {
        return "new";
    }

    /**
     * 게시물 수정 화면 이동
     * @param id 게시물 ID
     * @param map View 로 전달할 ModelMap 객체
     * @return 게시물 수정 화면 경로
     */
    @GetMapping("{id}/edit")
    public String getViewBoardEdit(
            @PathVariable("id") int id,
            ModelMap map) {
        map.put("info", boardService.getBoard(id));
        return "edit";
    }

    /**
     * 게시물 등록 처리
     * @param boardDto 게시물 등록 데이터
     * @param map View 로 전달할 ModelMap 객체
     * @return 게시물 상세 조회 화면 호출
     */
    @PostMapping("")
    public String addBoard(
            @ModelAttribute BoardDto boardDto) {
        boardService.addBoard(boardDto);
        return "redirect:/webclient";
    }

    /**
     * 게시물 수정 처리
     * @param boardDto 게시물 수정 데이터
     * @param map View 로 전달할 ModelMap 객체
     * @return 게시물 상세 조회 화면 호출
     */
    @PutMapping("{id}")
    public String modBoard(
            @PathVariable("id") int id,
            @ModelAttribute BoardDto boardDto) {
        boardDto.setId(id);
        boardService.modBoard(boardDto);
        return "redirect:/webclient/" + id;
    }

    /**
     * 게시물 삭제 처리
     * @param id 삭제 대상 게시물 ID
     * @param map View 로 전달할 ModelMap 객체
     * @return 게시물 목록 조회 화면 호출
     */
    @DeleteMapping("{id}")
    public String removeBoard(
            @PathVariable("id") int id,
            ModelMap map) {
        boardService.removeBoard(id);
        return "redirect:/webclient";
    }
}
