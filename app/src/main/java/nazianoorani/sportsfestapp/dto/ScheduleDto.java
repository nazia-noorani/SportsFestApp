package nazianoorani.sportsfestapp.dto;

/**
 * Created by nazianoorani on 17/05/16.
 */
public class ScheduleDto {

    String event;
    String nameTeamA;
    String nameTeamB;
    String matchDate;
    String matchTime;
    String teamAChestURL;
    String teamBChestURL;





    //TODO  getter and setter
    //Also for Lat and longitud




    public String getTeamAChestURL() {
        return teamAChestURL;
    }

    public void setTeamAChestURL(String teamAChestURL) {
        this.teamAChestURL = teamAChestURL;
    }

    public String getTeamBChestURL() {
        return teamBChestURL;
    }

    public void setTeamBChestURL(String teamBChestURL) {
        this.teamBChestURL = teamBChestURL;
    }

    public String getNameTeamA() {
        return nameTeamA;
    }

    public void setNameTeamA(String nameTeamA) {
        this.nameTeamA = nameTeamA;
    }

    public String getNameTeamB() {
        return nameTeamB;
    }

    public void setNameTeamB(String nameTeamB) {
        this.nameTeamB = nameTeamB;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    public String getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(String matchTime) {
        this.matchTime = matchTime;
    }



}
