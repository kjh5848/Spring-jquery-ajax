package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardApiContoller {

    private final BoardRepository boardRepository;

    @PutMapping("/api/boards/{id}")
    public ApiUtil<?> read(@PathVariable int id,@RequestBody BoardRequest.ReadDTO requestDTO) {
        boardRepository.update(requestDTO,id);

        return new ApiUtil<>(null);
    }

    @PostMapping("/api/boards")
    public ApiUtil<?> write(@RequestBody BoardRequest.WriteDTO requestDTO) {
        boardRepository.insert(requestDTO);
        return new ApiUtil<>(null);
    }


    @DeleteMapping("/api/boards/{id}")
    public ApiUtil<?> deleteById(@PathVariable Integer id, HttpServletResponse response) {

        Board board = boardRepository.selectOne(id);
        if (board == null) {
            response.setStatus(404);
            return new ApiUtil<>(404, "해당 게시글을 찾을 수 없습니다.", board);
        }
        boardRepository.deleteById(id);
        return new ApiUtil<>(null);
    }


    @GetMapping("/api/boards")
    public ApiUtil<?> findAll(HttpServletResponse response) {

        List<Board> boardList = boardRepository.selectAll();
        return new ApiUtil<>(boardList);//MessageConverter
    }
}
