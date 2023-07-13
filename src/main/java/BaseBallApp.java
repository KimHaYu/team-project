import dao.OutPlayerDAO;
import dao.PlayerDAO;
import dao.StadiumDAO;
import dao.TeamDAO;
import db.DBconnection;
import dto.OutPlayerRespDTO;
import dto.TeamRespDTO;
import model.Stadium;
import service.OutPlayerService;
import service.PlayerService;
import service.StadiumService;
import service.TeamService;

import java.sql.Connection;
import java.util.*;
import java.util.stream.Collectors;

public class BaseBallApp {

    private static final Scanner sc = new Scanner(System.in);
    private static final String USER_INPUT_PREFIX = ">> ";

    public static void main(String[] args) {
        Connection con = DBconnection.getInstance();

        // 객체 생성 시작
        StadiumDAO stadiumDAO = new StadiumDAO(con);
        TeamDAO teamDAO = new TeamDAO(con);
        PlayerDAO playerDAO = new PlayerDAO(con);
        OutPlayerDAO outPlayerDAO = new OutPlayerDAO(con);

        StadiumService stadiumService = new StadiumService(stadiumDAO);
        TeamService teamService = new TeamService(teamDAO);
        PlayerService playerService = new PlayerService(playerDAO);
        OutPlayerService outPlayerService = new OutPlayerService(con, outPlayerDAO, playerDAO);
        // 객체 생성 완료


        // 사용자 입력 시작
        // 종료 or 오류 전까지 계속 입력 가능
        while (true) {
            System.out.println("어떤 기능을 요청하시겠습니까?");

            // input[0] -> 야구장등록;
            // input[1] -> name=잠실야구장;
            String[] input = userInput().split("\\?");
            String command = input[0];
            Map<String, String> params = new HashMap<>();
            // 오류 방지. 야구장 목록 같은 경우는 input[1]이 없음!!
            if (input.length >= 2) {
                // teamId=1&name=이대호&position=1루수 ->를 & 단위로 자름
                String[] split = input[1].split("&");
                // split[0] -> teamId=1 //// split[1] -> name=이대호 //// split[2] -> position=1루수
                // 각 문자열을 = 단위로 또 잘라서 teamId는 변수 이름으로, 1은 값으로 사용할 것!!!
                params = Arrays.stream(split)
                        .map(s -> s.split("="))
                        .collect(Collectors.toMap(strings -> strings[0], strings -> strings[1]));
            }

            // OK
            if (command.equals("야구장등록")) {
                String stadiumName = params.get("name");

                stadiumService.createStadium(stadiumName);
                System.out.println("성공!");
            }
            // OK
            else if (command.equals("야구장목록")) {
                List<Stadium> stadiums = stadiumService.getAllStadium();
                System.out.println(stadiums);
            }
            // OK
            else if (command.equals("팀등록")) {
                int stadiumId = Integer.parseInt(params.get("stadiumId"));
                String teamName = params.get("name");

                teamService.createTeam(stadiumId, teamName);
                //System.out.println("성공!");
            }
            // OK
            else if (command.equals("팀목록")) {
                List<TeamRespDTO> teamRespDTOList = teamService.getAllTeam();
                System.out.println(teamRespDTOList);
            }
            // OK
            else if (command.equals("선수등록")) {
                int teamId = Integer.parseInt(params.get("teamId"));
                String playerName = params.get("name");
                String position = params.get("position");

                playerService.createPlayer(teamId, playerName, position);
                System.out.println("성공!");
            }
            //
            else if (command.equals("선수목록")) {
                int teamId = Integer.parseInt(params.get("teamId"));
                // TODO 구현
            }
            // OK
            else if (command.equals("퇴출등록")) {
                int playerId = Integer.parseInt(params.get("playerId"));
                String reason = params.get("reason");

                outPlayerService.createOutPlayer(playerId, reason);
                System.out.println("성공!");;
            }
            //
            else if (command.equals("퇴출목록")) {
                // left outer join
                // TODO 구현
                List<OutPlayerRespDTO> outPlayerRespDTOList = outPlayerService.getAllPlayersWithReason();

                System.out.println(outPlayerRespDTOList);
            }
            //
            else if (command.equals("포지션별목록")) {

            } else if (command.equals("종료")) {
                System.out.println("EXIT...");
                break;
            } else {
                System.out.println("ERROR!!");
            }

        }
    }

    private static String userInput() {
        System.out.print(USER_INPUT_PREFIX);
        String input = sc.next();
        return input;
    }
}
