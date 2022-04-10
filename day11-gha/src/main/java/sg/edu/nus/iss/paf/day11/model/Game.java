package sg.edu.nus.iss.paf.day11.model;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Game {
    private Integer gid;
    private String name;
    private Integer year;
    private Integer ranking;
    private Integer usersRated;
    private String url;
    private String image;
    
    public static Game create(SqlRowSet rs) {
        Game g = new Game();

        g.setGid(rs.getInt("gid"));
        g.setYear(rs.getInt("year"));
        g.setRanking(rs.getInt("ranking"));
        g.setUsersRated(rs.getInt("users_rated"));
        g.setName(rs.getString("name"));
        g.setUrl(rs.getString("url"));
        g.setImage(rs.getString("image"));

        return g;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("gid", getGid())
                .add("name", getName())
                .add("year", getYear())
                .add("ranking", getRanking())
                .add("usersRated", getUsersRated())
                .add("url", getUrl())
                .add("image", getImage())
                .build();
                
    }

    /**
     * @return Integer return the gid
     */
    public Integer getGid() {
        return gid;
    }

    /**
     * @param gid the gid to set
     */
    public void setGid(Integer gid) {
        this.gid = gid;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Integer return the year
     */
    public Integer getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * @return Integer return the usersRated
     */
    public Integer getUsersRated() {
        return usersRated;
    }

    /**
     * @param usersRated the usersRated to set
     */
    public void setUsersRated(Integer usersRated) {
        this.usersRated = usersRated;
    }

    /**
     * @return String return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return String return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }


    /**
     * @return Integer return the ranking
     */
    public Integer getRanking() {
        return ranking;
    }

    /**
     * @param ranking the ranking to set
     */
    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

}
