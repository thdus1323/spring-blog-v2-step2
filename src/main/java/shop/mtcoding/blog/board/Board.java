package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;


@Data
@Table(name = "board_tb")
@Entity
@NoArgsConstructor // 기본 생성자
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    private String username;

    //영속객체에 넣었을 때 시간 자동  생성
    @CreationTimestamp
    private Timestamp createdAt;

    //필드들이 무조건 초기화된 상태로 만들어짐(NoArgsConstructor도 있으니까)
    public Board(String title, String content, String username) {
        this.title = title;
        this.content = content;
        this.username = username;
    }
}
