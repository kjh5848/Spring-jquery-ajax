package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.awt.desktop.PreferencesEvent;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardApiContoller {

    private final BoardRepository boardRepository;

    @DeleteMapping("/api/boards/{id}")
    public ApiUtil<?> deleteById(@PathVariable Integer id,HttpServletResponse response) {
        Board board = boardRepository.selectOne(id);
        if (board == null) {
//            response.setStatus(404);
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
