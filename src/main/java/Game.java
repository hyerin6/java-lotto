import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import domain.Lotto;
import domain.Rank;
import domain.WinningLotto;

public class Game {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("구입금액을 입력해 주세요.");
		StringTokenizer t = new StringTokenizer(br.readLine());
		int money = Integer.parseInt(t.nextToken());

		int n = money / 1000;
		List<Lotto> lottoList = LottoMachine.buyLotto(n);

		System.out.println("\n" + n + "개를 구매했습니다.");
		for(int i = 0; i < n; ++i) {
			System.out.println(lottoList.get(i).getNumbers());
		}

		System.out.println("\n" + "지난 주 당첨 번호를 입력해 주세요.");
		t = new StringTokenizer(br.readLine(), ", ");

		List<Integer> winingNum = new ArrayList<>();
		for(int i = 0; i < 6; ++i) {
			winingNum.add(Integer.parseInt(t.nextToken()));
		}

		System.out.println("보너스 볼을 입력해 주세요.");
		t = new StringTokenizer(br.readLine());
		int bonus = Integer.parseInt(t.nextToken());

		WinningLotto winningLotto = new WinningLotto(new Lotto(winingNum), bonus);

		List<Rank> ranks = LottoMachine.match(lottoList, winningLotto);

		Map<Integer, Integer> countMap = LottoMachine.matchCount(ranks);

		LottoMachine.printStatistics(countMap);

		LottoMachine.printProfit(ranks, money);

	}

}
