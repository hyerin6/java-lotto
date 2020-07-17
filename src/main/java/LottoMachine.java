import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import domain.Lotto;
import domain.Rank;
import domain.WinningLotto;

public class LottoMachine {

	public static List<Lotto> buyLotto(int n) {
		List<Lotto> lottoList = new ArrayList<>();

		for(int i = 0; i < n; ++i) {
			lottoList.add(createLotto());
		}

		return lottoList;
	}

	private static Lotto createLotto() {
		List<Integer> lotto = new ArrayList<>();
		int size = 0;

		while(size < 6) {
			addLotto(lotto);
			size = lotto.size();
		}

		Collections.sort(lotto);
		return new Lotto(lotto);
	}

	private static void addLotto(List<Integer> lotto) {
		int num = generateLottoNum();

		if(!lotto.contains(num)) {
			lotto.add(num);
		}
	}

	private static int generateLottoNum() {
		Random random = new Random();
		return random.nextInt(45)+1;
	}

	public static Map<Integer, Integer> matchCount(List<Rank> ranks) {
		Map<Integer, Integer> countMap = new HashMap<>();
		int size = ranks.size();

		for(int i = 0; i < size; ++i) {
			count(countMap, ranks.get(i));
		}

		return countMap;
	}

	private static void count(Map<Integer, Integer> countMap, Rank rank) {
		int winningMoney = rank.getWinningMoney();

		if(!countMap.containsKey(winningMoney)) {
			countMap.put(winningMoney, 1);
			return;
		}

		countMap.put(winningMoney, countMap.get(winningMoney)+1);
	}

	public static List<Rank> match(List<Lotto> lottoList, WinningLotto winningLotto) {
		List<Rank> ranks = new ArrayList<>();
		int size = lottoList.size();

		for(int i = 0; i < size; ++i) {
			ranks.add(winningLotto.match(lottoList.get(i)));
		}

		return ranks;
	}

	public static double getProfit(List<Rank> ranks) {
		int profit = 0;
		int size = ranks.size();

		for(int i = 0; i < size; ++i) {
			profit += ranks.get(i).getWinningMoney();
		}

		return profit;
	}

	public static void printProfit(List<Rank> ranks, int money) {
		System.out.println("총 수익률은 " + getProfit(ranks)/money + "입니다.");
	}

	public static int getWinningCount(Map<Integer, Integer> countMap, int winningMoney) {
		return countMap.get(winningMoney) == null ? 0 : countMap.get(winningMoney);
	}

	public static void printStatistics(Map<Integer, Integer> countMap) {
		Rank[] ranks = Rank.values();

		System.out.println("\n당첨 통계\n" + "----------");
		for(int i = 4; i >= 0; --i) {
			System.out.printf("%d개 일치 (%d원) - %d개\n",
				ranks[i].getCountOfMatch(), ranks[i].getWinningMoney(),
				LottoMachine.getWinningCount(countMap, ranks[i].getWinningMoney()));
		}
	}

}
