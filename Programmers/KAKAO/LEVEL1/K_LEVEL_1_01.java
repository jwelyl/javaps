import java.util.*;

class Solution {
    Set<String> reportLog = new HashSet<String>();  //  신고 기록 from to (from이 to를 신고)
    Map<String, Integer> user = new HashMap<String, Integer>(); //  user 이름, user index
    Map<String, Integer> reportCnt = new HashMap<String, Integer>();    //  user 이름, 신고 당한 횟수
    Map<String, ArrayList<String> > reporters = new HashMap<String, ArrayList<String> >();  // user 이름, 신고한 사람들 


    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = {};  //  각 user가 메일 받은 횟수

        for(int i = 0; i < id_list.length; i++) {
            user.put(id_list[i], i);        //  user 추가
            reportCnt.put(id_list[i], 0);   //  신고당한 횟수 0 
            reporters.put(id_list[i], new ArrayList<String>()); //  신고한 사람 
        }
        answer = new int[id_list.length];
        Arrays.fill(answer, 0);

        for(int i = 0; i < report.length; i++) {
            String[] str = report[i].split(" ");
            String from = str[0];   //  신고한 사람
            String to = str[1];     //  신고당한 사람

            if(!reportLog.contains(report[i])) {    //  동일한 신고가 없을 경우
                reportLog.add(report[i]);   //  신고 기록에 추가
                reportCnt.put(to, reportCnt.get(to) + 1);   //  to가 신고당한 횟수 1 증가
                reporters.get(to).add(from);    //  to를 신고한 사람에 from을 추가
            }
        }

        for(int i = 0; i < id_list.length; i++) {
            String name = id_list[i];
            if(reportCnt.get(name) >= k) {  //  신고 횟수가 k 이상일 경우
                for(int j = 0; j < reporters.get(name).size(); j++) {
                    String reporter = reporters.get(name).get(j);   //  신고자
                    int idx = user.get(reporter);   //  신고자 번호

                    answer[idx] += 1;   //  신고자가 받은 메일 횟수 1 증가
                }
            }
        }

        return answer;
    }
}