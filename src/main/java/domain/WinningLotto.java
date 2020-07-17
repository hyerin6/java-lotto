package domain;

/**
 * 당첨 번호를 담당하는 객체
 */
public class WinningLotto {
    private final Lotto lotto;
    private final int bonusNo;

    public WinningLotto(Lotto lotto, int bonusNo) {
        this.lotto = lotto;
        this.bonusNo = bonusNo;
    }

    public Rank match(Lotto userLotto) {
        int countOfMatch = 0;

        for(int i = 0; i < 6; ++i) {
            if(userLotto.getNumbers().contains(this.lotto.getNumbers().get(i))) {
                countOfMatch++;
            }
        }

        return Rank.valueOf(countOfMatch, userLotto.getNumbers().contains(this.bonusNo));
    }

}
