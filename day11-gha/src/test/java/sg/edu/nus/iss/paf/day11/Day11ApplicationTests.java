package sg.edu.nus.iss.paf.day11;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.nus.iss.paf.day11.model.Comment;
import sg.edu.nus.iss.paf.day11.model.Game;
import sg.edu.nus.iss.paf.day11.repo.GameRepository;

@SpringBootTest
class Day11ApplicationTests {

	@Autowired
	private GameRepository gRepo;

	@Test
	void shouldReturnAGame() {
		Optional<Game> opt = gRepo.getGameByGameId(10);
		Assertions.assertTrue(opt.isPresent(), "gid = 10");
	}

	@Test
	void shouldReturnEmpty() {
		Optional<Game> opt = gRepo.getGameByGameId(-1);
		Assertions.assertFalse(opt.isPresent(), "gid = -1");
	}

	@Test
	void shouldReturnComments() {
		List<Comment> cList = gRepo.getCommentByGameId(10, 10, 0);
		Assertions.assertTrue(cList.size() == 10, "gid = 10, 10 comments");
	}

	@Test
	void shouldBeNonEqualComments() {
		List<Comment> cSingle = gRepo.getCommentByGameId(10, 1, 0);
		List<Comment> cSingle2 = gRepo.getCommentByGameId(10, 1, 1);
		Assertions.assertFalse(cSingle.get(0).equals(cSingle2.get(0)));
	}

	@Test
	void shouldReturnEmptyList() {
		List<Comment> cL1 = gRepo.getCommentByGameId(-1, 10, 0);
		List<Comment> cL2 = gRepo.getCommentByGameId(10, 0, 0);
		Assertions.assertTrue(cL1.isEmpty());
		Assertions.assertTrue(cL2.isEmpty());
	}
}
