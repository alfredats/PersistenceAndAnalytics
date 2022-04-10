package sg.edu.nus.iss.paf.day11.model;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Comment {
    private String cid;
    private String user;
    private Integer rating;
    private String cText;
    private Integer gid;

    public static Comment create(SqlRowSet rs) {
        Comment c = new Comment();
        c.setCid(rs.getString("c_id"));
        c.setUser(rs.getString("user"));
        c.setRating(rs.getInt("rating"));
        c.setCText(rs.getString("c_text"));
        c.setGid(rs.getInt("gid"));
        return c;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                    .add("c_id", getCid())
                    .add("user", getUser())
                    .add("rating", getRating())
                    .add("c_text", getCText())
                    .add("gid", getGid())
                    .build();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Comment) {
            Comment c = (Comment) o;
            return getCid().equals(c.getCid())
                && getUser().equals(c.getUser())
                && getRating().equals(c.getRating())
                && getCText().equals(c.getCText())
                && getGid().equals(c.getGid());
        }
        return false;
    }

    /**
     * @return String return the cid
     */
    public String getCid() {
        return cid;
    }

    /**
     * @param cid the cid to set
     */
    public void setCid(String cid) {
        this.cid = cid;
    }

    /**
     * @return String return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return Integer return the rating
     */
    public Integer getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(Integer rating) {
        this.rating = rating;
    }

    /**
     * @return String return the cText
     */
    public String getCText() {
        return cText;
    }

    /**
     * @param cText the cText to set
     */
    public void setCText(String cText) {
        this.cText = cText;
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

}
