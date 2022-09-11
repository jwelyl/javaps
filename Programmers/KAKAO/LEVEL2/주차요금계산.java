import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 
 * @author koreii
 * @see https://school.programmers.co.kr/learn/courses/30/lessons/92341
 * @difficuly LEVEL2
 * @performance 97.5MB   7.25ms
 * @category # 해쉬맵, 구현
 * @memo # HashMap으로 차량 번호에 따라 들어오고 나가는 것을 관리해야 함. 일단 모든 차량의 누적 주차시간부터 구한 후 비용을 계산해야 함.
 * @etc # 번거롭더라도 최대한 풀어써서 비용 계산에서 실수 없도록 하는게 좋을듯.
 * 
 */

class Solution {
	int baseMin, baseFee, unitMin, unitFee;
	int OUTTIME = timeToMin("23:59");					//	23:59 출차 시간
	Map<String, Integer> carIOTime = new HashMap<>();	//	차가 들어오거나 나간 시간
	Map<String, Integer> carParkTime = new HashMap<>();		//	차의 누적 주차 시간
	
	List<Car> carList = new ArrayList<>();
	
    public int[] solution(int[] fees, String[] records) {
        int[] answer = {};
        
        baseMin = fees[0];	//	기본 주차시간
        baseFee = fees[1];	//	기본 주차비용
        unitMin = fees[2];	//	추가 주차시간
        unitFee = fees[3];	//	추가 주차비용
        
        for(int i = 0; i < records.length; i++) {
        	String time = records[i].substring(0, 5);
        	int min = timeToMin(time);
        	String carNum = records[i].substring(6, 10);
        	
        	if(!carIOTime.containsKey(carNum)) {	//	주차장에 처음 들어오거나 다시 들어오는 경우
        		carIOTime.put(carNum, min);	//	차 들어온 시간 기록
        		if(!carParkTime.containsKey(carNum)) //	차가 처음 들어온 경우
        			carParkTime.put(carNum, 0);	//	누적 주차시간 0으로 초기화
        	}
        	else {	//	주차장에서 나가는 경우
        		int inTime = carIOTime.get(carNum);	//	해당 차가 들어왔던 시간
        		int outTime = min;					//	해당 차가 나간 시간
        		carParkTime.put(carNum, carParkTime.get(carNum) + outTime - inTime);	//	차 누적 주차시간 추가
        		carIOTime.remove(carNum);	//	차 제거
        	}
        }
        
        //	아직 출차 안한 차들(23:59에 일괄 퇴차 처리)
        Set<Entry<String, Integer>> entrySet = carIOTime.entrySet();
        for(Entry<String, Integer> entry: entrySet) {
        	String carNum = entry.getKey();
        	int inTime = entry.getValue();
        	carParkTime.put(carNum, carParkTime.get(carNum) + OUTTIME - inTime);
        }
        
        entrySet = carParkTime.entrySet();	//	주차 비용 계산
        for(Entry<String, Integer> entry: entrySet) {
        	String carNum = entry.getKey();	//	차량 번호
        	int parkTime = entry.getValue();	//	차량 누적 주차시간
        	int parkFee = baseFee;	//	일단 기본비용
        	
        	if(parkTime > baseMin) {	//	기본 주차시간보다 더 주차했을 경우
        		int overUnit = (int)Math.ceil((double)(parkTime - baseMin) / unitMin);
        		parkFee += overUnit * unitFee;	//	추가 주차 비용
        	}
        	carList.add(new Car(carNum, parkFee));
        }
        
        int idx = 0;
        Collections.sort(carList);
        answer = new int[carList.size()];
        for(Car car : carList)
        	answer[idx++] = car.fee;
        return answer;
    }
    
    //	HH:MM이 00:00으로부터 몇 분 떨어져있는지 구함
    int timeToMin(String time) {
    	return Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3, 5));
    }
    
    static class Car implements Comparable<Car> {
    	String carNum;
    	int fee;
    	
    	Car(String carNum, int fee) {
    		this.carNum = carNum;
    		this.fee = fee;
    	}
    	
		@Override
		public int compareTo(Car o) {
			// TODO Auto-generated method stub
			return this.carNum.compareTo(o.carNum);
		}

		@Override
		public String toString() {
			return "Car [carNum=" + carNum + ", fee=" + fee + "]";
		}
		
		
    }
}

public class 주차요금계산 {
	public static void main(String[] args) {
		Solution sol = new Solution();
		int[] fees = {180, 5000, 10, 600};
		String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
		int[] result = sol.solution(fees, records);
		System.out.println(Arrays.toString(result));
	
		sol = new Solution();
		fees = new int[]{120, 0, 60, 591};
		records = new String[]{"16:00 3961 IN","16:00 0202 IN","18:00 3961 OUT","18:00 0202 OUT","23:58 3961 IN"};
		result = sol.solution(fees, records);
		System.out.println(Arrays.toString(result));
		
		sol = new Solution();
		fees = new int[]{1, 461, 1, 10};
		records = new String[]{"00:00 1234 IN"};
		result = sol.solution(fees, records);
		System.out.println(Arrays.toString(result));
	}
}