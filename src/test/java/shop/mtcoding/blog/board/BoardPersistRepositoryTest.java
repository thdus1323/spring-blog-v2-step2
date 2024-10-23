package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@Import(BoardPersistRepository.class)
class BoardPersistRepositoryTest {

    @Autowired //DI
    private BoardPersistRepository boardPersistRepository;

    @PersistenceContext //이 방법이 더 권장
    private EntityManager em;


    @Test
    void save_test() {
        //given
        Board board = new Board("제목5", "내용5", "ssar");

        //when
        boardPersistRepository.save(board);
        System.out.println("save_test : " + board);

        //then
    }

    @Test
    void findAll_test(){
        //given

        //when
        List<Board> boardList = boardPersistRepository.findAll();

        //then
        System.out.println("findAll_test/size : " + boardList.size());
        System.out.println("findAll_test/username : " + boardList.get(2).getUsername());

        //org.assertj.core.api
        assertThat(boardList.size()).isEqualTo(4);
        assertThat(boardList.get(2).getUsername()).isEqualTo("ssar");
    }

    @Test
    public void findById_test(){
        //given
        int id = 1;

        //when
        Board board = boardPersistRepository.findById(id);
        em.clear();
        boardPersistRepository.findById(id);
        System.out.println("findById_test " + board);

        //then
        //org.assertj.core.api
        assertThat(board.getTitle()).isEqualTo("제목1");
        assertThat(board.getContent()).isEqualTo("내용1");
    }

    @Test
    public void deleteByIdV2_test(){
        int id = 1;

        boardPersistRepository.deleteByIdV2(id);

        em.flush();

        Board board = boardPersistRepository.findById(id);
        System.out.println("findById_test " + board);
    }
}