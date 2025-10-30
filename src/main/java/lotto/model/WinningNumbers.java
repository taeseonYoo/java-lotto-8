package lotto.model;

public class WinningNumbers {
    private final Lotto winningLotto;
    private final BonusNumber bonusNumber;

    public WinningNumbers(Lotto winningLotto, BonusNumber bonusNumber) {
        validateBonusNumberIsUnique(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumberIsUnique(Lotto winningLotto, BonusNumber bonusNumber) {
        if (winningLotto.contains(bonusNumber.number())) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 동일할 수 없습니다.");
        }
    }

    public int countMatchingNumbers(Lotto lotto) {
        return (int) lotto.getNumbers().stream()
                .filter(winningLotto::contains)
                .count();
    }

    public boolean isMatchBonus(Lotto lotto) {
        return lotto.contains(bonusNumber.number());
    }
}
