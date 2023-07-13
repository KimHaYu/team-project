package service;

import dao.StadiumDAO;
import model.Stadium;

import java.util.List;

//야구장 비즈니스 로직
public class StadiumService {

    private final StadiumDAO stadiumDAO;

    public StadiumService(StadiumDAO stadiumDAO) {
        this.stadiumDAO = stadiumDAO;
    }

    public int createStadium(String stadiumName) {
        return stadiumDAO.insert(stadiumName);
    }

    public List<Stadium> getAllStadium() {
        return stadiumDAO.selectAll();
    }
}
