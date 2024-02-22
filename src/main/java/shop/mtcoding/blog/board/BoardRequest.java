package shop.mtcoding.blog.board;

import lombok.Data;
import org.springframework.web.bind.annotation.DeleteMapping;

public class BoardRequest {

    @Data
    public static class WriteDTO {
        private String title;
        private String content;
        private String author;
    }

    @Data
    public static class ReadDTO {
        private String title;
        private String content;
        private String author;

    }
}
