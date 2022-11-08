package controller;

import models.Player;
import service.PlayerService;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * @author Laurentiu
 */
@Named("playerBean")
@RequestScoped
public class PlayerController implements Serializable {

    private Long id;
    private String name;
    private int age;
    private String position;
    private String result;
    private long teamId;
    private String transactionResult;
    private boolean playerPositionCriteria;
    private boolean ageFrameCriteria;

    private String playerPosition;
    private String ageFrame;

    private List<Player> criteriaSearchResult;

    @Inject
    private PlayerService playerService;

    public List<Player> getPlayers() {
        return this.playerService.getPlayers();
    }

    public void addPlayer() {
        try {
            playerService.addPlayer(new Player(name, age, position, teamId));
            this.result = "Player added succesully";
        } catch (Exception e) {
            this.result = "Player adding failed";
        }
    }

    public void removePlayerById() {
        try {
            playerService.removePlayer(id);
            this.result = "Player removed succesully";
        } catch (Exception e) {
            this.result = "Player removing failed";
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    public void searchForPlayerByCriteria() {
        transactionResult = "Searched succesfully!";
        System.out.println(this.ageFrame);
        System.out.println(this.playerPosition);
        if (criteriaSearchResult == null) {
            criteriaSearchResult = playerService.searchByCriteria(playerPositionCriteria, playerPosition, ageFrameCriteria, ageFrame);
            System.out.println("SIZE " + criteriaSearchResult.size() + "----------------------");
        }
    }

    public String getTransactionResult() {
        return transactionResult;
    }

    public boolean isPlayerPositionCriteria() {
        return playerPositionCriteria;
    }

    public void setPlayerPositionCriteria(boolean playerPositionCriteria) {
        this.playerPositionCriteria = playerPositionCriteria;
    }

    public String getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(String playerPosition) {
        this.playerPosition = playerPosition;
    }

    public boolean isAgeFrameCriteria() {
        return ageFrameCriteria;
    }

    public void setAgeFrameCriteria(boolean ageFrameCriteria) {
        this.ageFrameCriteria = ageFrameCriteria;
    }

    public String getAgeFrame() {
        return ageFrame;
    }

    public void setAgeFrame(String ageFrame) {
        this.ageFrame = ageFrame;
    }

    public List<Player> getCriteriaSearchResults() {
        if (criteriaSearchResult == null) {
            criteriaSearchResult = new ArrayList<>();
        }
        return criteriaSearchResult;
    }

    public void setCriteriaSearchResult(List<Player> criteriaSearchResult) {
        this.criteriaSearchResult = criteriaSearchResult;
    }
}
