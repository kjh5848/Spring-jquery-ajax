package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;

    public List<Board> selectAll(){
        Query query = em.createNativeQuery("select * from board_tb order by id desc", Board.class);
        List<Board> boardList = query.getResultList(); // 못찾으면 빈 컬렉션을 준다 (크기=0)
        return boardList;
    }

    public Board selectOne(int id){
        Query query = em.createNativeQuery("select * from board_tb where id = ?", Board.class);
        query.setParameter(1, id);

        try {
            Board board = (Board) query.getSingleResult();
            return board;
        } catch (Exception e) {
            return null;
        }

    }

    @Transactional
    public void insert(BoardRequest.WriteDTO requestDTO){
        Query query = em.createNativeQuery("insert into board_tb(title, content, author) values(?, ?, ?)");
        query.setParameter(1, requestDTO.getTitle());
        query.setParameter(2, requestDTO.getContent());
        query.setParameter(3, requestDTO.getAuthor());
        query.executeUpdate();
    }

    @Transactional
    public void deleteById(Integer id) {
        String a = """
                delete from board_tb where id = ?;
                """;
        Query query = em.createNativeQuery(a);
        query.setParameter(1, id);
        query.executeUpdate();
    }

    @Transactional
    public void update(BoardRequest.UpdateDTO requestDTO, int id) {
        String a = """
                update board_tb set author = ?, title= ?, content =?  where id = ?  ;
                """;

        Query query = em.createNativeQuery(a);
        query.setParameter(1, requestDTO.getAuthor());
        query.setParameter(2, requestDTO.getTitle());
        query.setParameter(3, requestDTO.getContent());
        query.setParameter(4, id);
        query.executeUpdate();

    }
}
