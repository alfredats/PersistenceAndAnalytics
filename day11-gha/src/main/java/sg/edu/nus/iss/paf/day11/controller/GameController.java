package sg.edu.nus.iss.paf.day11.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.JsonObject;
import sg.edu.nus.iss.paf.day11.service.GameService;

@RestController
@RequestMapping(path="/", produces = MediaType.APPLICATION_JSON_VALUE)
public class GameController {
    private static final Logger logger = LoggerFactory.getLogger(GameController.class);

    @Autowired
    private GameService gSvc;

    @GetMapping(path = "/game/{gid}")
    ResponseEntity<String> gameResource(
        @PathVariable String gid,
        @RequestParam(defaultValue = "10") String limit,
        @RequestParam(defaultValue = "0") String offset
    ) {
        Optional<JsonObject> gView = 
            gSvc.getGameJson(
                Integer.parseInt(gid), 
                Integer.parseInt(limit), 
                Integer.parseInt(offset)
            );

        if (gView.isEmpty()) {
            return ResponseEntity.status(404).body("{'error': 'Not Found'}");
        }

        return ResponseEntity.ok(gView.get().toString());
    }

    @GetMapping(path="/comment/{cid}")
    ResponseEntity<String> commentResource(
        @PathVariable String cid
    ) {
        Optional<JsonObject> cView = gSvc.getCommentJson(cid);

        if (cView.isEmpty())
            return ResponseEntity.status(400).body("{}");

        return ResponseEntity.ok(cView.get().toString());
    }

    
}
