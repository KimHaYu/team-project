import dao.OutPlayerDAO;
import dao.PlayerDAO;
import dao.StadiumDAO;
import dao.TeamDAO;
import db.DBconnection;
import dto.OutPlayerRespDTO;
import dto.TeamRespDTO;
import model.Outplayer;
import model.Player;
import model.Stadium;
import service.OutPlayerService;
import service.PlayerService;
import service.StadiumService;
import service.TeamService;

import java.sql.Connection;
import java.time.format.DateTimeFormatter;
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
            System.out.println("==================================");
            System.out.println("1. 야구장 등록");
            System.out.println("2. 전체 야구장 목록 보기");
            System.out.println("3. 팀 등록");
            System.out.println("4. 전체 팀 목록");
            System.out.println("5. 선수 등록");
            System.out.println("6. 팀별 선수 목록");
            System.out.println("7. 선수 퇴출 등록");
            System.out.println("8. 선수 퇴출 목록");
            System.out.println("==================================");
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
                for (Stadium stadium : stadiums) {
                    System.out.println("Stadium Name: " + stadium.getStadiumId());
                    System.out.println("Location: " + stadium.getStadiumName());
                    System.out.println("Capacity: " + stadium.getStadiumCreated_at());
                    System.out.println();
                }

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
                for (TeamRespDTO teamRespDTO : teamRespDTOList) {
                    System.out.println("Team ID: " + teamRespDTO.getTeamId());
                    System.out.println("Team Name: " + teamRespDTO.getTeamName());
                    System.out.println();
                }

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

                for (Player player : playerDAO.getPlayerList()) {
                    if (player.getTeamId() == teamId) {
                        System.out.println("Player Name: " + player.getName());
                        System.out.println("Player Position: " + player.getPosition());
                        System.out.println("Player Team: " + player.getTeamId());
                        System.out.println();
                    }
                }
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
                for (OutPlayerRespDTO outPlayerRespDTO : outPlayerRespDTOList) {
                    if (outPlayerRespDTO.getDay() != null) {
                        System.out.printf("%d %s %s %s %s%n",
                                outPlayerRespDTO.getPlayerId(),
                                outPlayerRespDTO.getPlayerName(),
                                outPlayerRespDTO.getPlayerPosition(),
                                outPlayerRespDTO.getReason(),
                                (outPlayerRespDTO.getDay() == null ? null : outPlayerRespDTO.getDay().format(DateTimeFormatter.ISO_DATE))
                        );
                    }
                }

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
