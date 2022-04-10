package sg.edu.nus.iss.paf.day11.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import sg.edu.nus.iss.paf.day11.model.Comment;
import sg.edu.nus.iss.paf.day11.model.Game;
import sg.edu.nus.iss.paf.day11.repo.GameRepository;

@Service
public class GameService {

    @Autowired
    private GameRepository gRepo;

    public Optional<JsonObject> getGameJson(Integer gid, Integer limit, Integer offset) {
        JsonObjectBuilder gViewBuilder = Json.createObjectBuilder();

        Optional<Game> g = gRepo.getGameByGameId(gid);
        
        if (g.isEmpty()) {
            return Optional.empty();
        }

        List<Comment> cL = gRepo.getCommentByGameId(gid, limit, offset);
        JsonArrayBuilder cLinks = Json.createArrayBuilder();

        cL.stream()
            .filter(v -> v != null)
            .forEach(v -> {
                cLinks.add("/comment/" + v.getCid());
            });
        
        gViewBuilder.add("game", g.get().toJson());
        gViewBuilder.add("comments", cLinks.build());

        return Optional.of(gViewBuilder.build());
    }

    public Optional<JsonObject> getCommentJson(String cid) {
        Optional<Comment> c = gRepo.getCommentByCommentId(cid);
        if (c.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(c.get().toJson());
    }
    
}
