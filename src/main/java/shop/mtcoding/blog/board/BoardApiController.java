package shop.mtcoding.blog.board;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class BoardApiController {
    private final BoardRepository boardRepository;

    @GetMapping("/api/boards/1")
    public ApiUtill<?> findAll() {
        List<Board> boardList = boardRepository.selectAll();
        return new ApiUtill<>();
    }
}
