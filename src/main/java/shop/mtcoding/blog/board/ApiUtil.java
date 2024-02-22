package shop.mtcoding.blog.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.autoconfigure.AutoConfiguration;

@Data
public class ApiUtil<T> {
    private Integer status; // 200,400, 404, 405
    private String msg; //실패시 - > 정확한 메세지
    private T body;

    public ApiUtil(T body) {
        this.status = 200;
        this.msg = "성공";
        this.body = body;
    }

    public ApiUtil(Integer status, String msg, T body) {
        this.status = status;
        this.msg = msg;
        this.body = null;
    }

    //    public static <T> ApiUtil<?> (T body){
//        return new ApiUtil<T>(200, "성공", body);
//    }

}
