package kr.datasolution.msa.board.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 게시물 DTO CLASS
 */
@Getter
@Setter
public class BoardDto {
    /** 게시물 ID */
    private int id;

    /** 제목 */
    private String title;

    /** 내용 */
    private String contents;

    /** 등록일시 */
    private LocalDateTime regDt;

    /** 수정일시 */
    private LocalDateTime updDt;
}
