package sg.edu.nus.iss.paf.day11.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.paf.day11.model.Comment;
import sg.edu.nus.iss.paf.day11.model.Game;

import static sg.edu.nus.iss.paf.day11.repo.Queries.SQL_SELECT_GAME_BY_GID;
import static sg.edu.nus.iss.paf.day11.repo.Queries.SQL_SELECT_COMMENT_BY_CID;
import static sg.edu.nus.iss.paf.day11.repo.Queries.SQL_SELECT_COMMENT_BY_GID;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class GameRepository {
    
    @Autowired
    private JdbcTemplate template;


    public Optional<Game> getGameByGameId(Integer queryGid) {
        final SqlRowSet rs = template.queryForRowSet(
            SQL_SELECT_GAME_BY_GID, queryGid
        );
        if (!rs.next()) {
            return Optional.empty();
        }
        Game g = Game.create(rs);
        return Optional.of(g);
    }

    public Optional<Comment> getCommentByCommentId(String queryCid) {
        final SqlRowSet rs = template.queryForRowSet(
            SQL_SELECT_COMMENT_BY_CID, queryCid
        );
        if (!rs.next()) {
            return Optional.empty();
        }
        Comment c = Comment.create(rs);
        return Optional.of(c);
    }

    public List<Comment> getCommentByGameId(Integer gid) {
        return getCommentByGameId(gid, Integer.MAX_VALUE, 0);
    }

    public List<Comment> getCommentByGameId(Integer gid, Integer limit) {
        return getCommentByGameId(gid, limit, 0);
    }

    public List<Comment> getCommentByGameId(Integer gid, Integer limit, Integer offset) {
        List<Comment> cList = new ArrayList<>();

        final SqlRowSet rs = template.queryForRowSet(SQL_SELECT_COMMENT_BY_GID, gid, limit, offset);
        while (rs.next()) {
            cList.add(Comment.create(rs));
        }
        return cList;
    }
}
