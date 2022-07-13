import java.util.*;

class Solution {
    final int OUT = 1439;                           //  23:59
    List<String> carList = new ArrayList<>();       //  차량 번호 목록
    Map<String, Integer> lastIn = new HashMap<>();          //  차량 번호별 마지막 들어온 시간
    Map<String, Integer> totalParkTime = new HashMap<>();   //  차량 번호별 총 주차 시간

    int timeToMin(String time) {    //  HH:MM 꼴의 시간 문자열을 분 단위 정수로 변환
        String[] tmp = time.split(":");
        int hour = Integer.parseInt(tmp[0]);
        int min = Integer.parseInt(tmp[1]);

        return (hour * 60 + min);
    }

    public int[] solution(int[] fees, String[] records) {
        int[] answer = {};
        int N;                      //  차량 개수
        int baseTime = fees[0];     //  기본 시간
        int baseFee = fees[1];      //  기본 요금
        int unitTime = fees[2];     //  단위 시간
        int unitFee = fees[3];      //  단위 요금

        for(String record : records) {
            String[] str = record.split(" ");
            int ioTime = timeToMin(str[0]); //  차가 들어가거나 나온 시각(분)
            String carNum = str[1]; //  차량 번호
            String io = str[2]; //  차량 출입기록(IN : 들어옴, OUT : 나감)
            // System.out.println("record = " + record);
            // System.out.println("record = " + ioTime + " " + carNum + " " + io);

            if(io.equals("IN")) {    //  차량이 들어올 경우
                // System.out.println("IN!!");
                if(!totalParkTime.containsKey(carNum)) {  //  처음 들어오는 차량의 경우
                    totalParkTime.put(carNum, 0);   //  총 주차 시간 0으로 설정
                    carList.add(carNum);    //  차량 목록에 추가
                    // System.out.println("새로 들어온 차 번호 = " + carNum);
                }
                lastIn.put(carNum, ioTime); //  차량 마지막에 들어온 시간 갱신
            } else {    //  차량이 나갈 경우
                // System.out.println("OUT!!");
                int inTime = lastIn.get(carNum);    //  나간 차량이 마지막에 들어온 시각
                int duration = ioTime - inTime;     //  마지막 주차 시간
                totalParkTime.put(carNum, totalParkTime.get(carNum) + duration);    //  총 주차시간 증가

                lastIn.remove(carNum);  //  나간 차량 제거
            }
        }
        N = carList.size();

        for(String car : carList) {
            if(lastIn.containsKey(car)) {   //  23:59까지 안나갔을 경우
                int inTime = lastIn.get(car);
                int duration = OUT - inTime;    //  23:59에 나간 것으로 처리
                totalParkTime.put(car, totalParkTime.get(car) + duration);
            }
        }

        answer = new int[N];
        Collections.sort(carList);
        for(int i = 0; i < N; i++) {
            int total = totalParkTime.get(carList.get(i));  //  총 주차 시간
            int totalFee;   //  총 주차 요금

            if(total <= baseTime)
                totalFee = baseFee;
            else 
                totalFee = baseFee + (int)(Math.ceil((double)(total - baseTime) / unitTime)) * unitFee;
            
            answer[i] = totalFee;   
        }

        return answer;
    }
}