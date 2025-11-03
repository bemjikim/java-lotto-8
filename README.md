# Lotto

## 기능 요구 사항

---

- 로또 번호의 숫자 범위는 1~45까지이다.

- 1개의 로또를 발행할 때 중복되지 않는 6개의 숫자를 뽑는다.

- 당첨 번호 추첨 시 중복되지 않는 숫자 6개와 보너스 번호 1개를 뽑는다.

- 당첨은 1등부터 5등까지 있다. 당첨 기준과 금액은 아래와 같다.

  - 1등: 6개 번호 일치 / 2,000,000,000원
  
  - 2등: 5개 번호 + 보너스 번호 일치 / 30,000,000원
  
  - 3등: 5개 번호 일치 / 1,500,000원
  
  - 4등: 4개 번호 일치 / 50,000원
  
  - 5등: 3개 번호 일치 / 5,000원


- 로또 구입 금액을 입력하면 구입 금액에 해당하는 만큼 로또를 발행해야 한다.

- 로또 1장의 가격은 1,000원이다.

- 당첨 번호와 보너스 번호를 입력받는다.

- 사용자가 구매한 로또 번호와 당첨 번호를 비교하여 당첨 내역 및 수익률을 출력하고 로또 게임을 종료한다.

- 사용자가 잘못된 값을 입력할 경우 IllegalArgumentException을 발생시키고, "[ERROR]"로 시작하는 에러 메시지를 출력 후 그 부분부터 입력을 다시 받는다.

- Exception이 아닌 IllegalArgumentException, IllegalStateException 등과 같은 명확한 유형을 처리한다.


## 기능 상세 구현을 위한 생각 공간

---

### 0. 구입 금액 입력

- camp.nextstep.edu.missionutils.Console의 readLine()을 활용

        - Exception Case:
             - 빈 문자열일 경우 [view에서 처리]
             - 숫자가 아닌 문자일 경우 [view에서 처리]
             - 1000원으로 나누어 떨어지지 않는 경우

        - Exception Case가 발생시 재귀문을 활용하여 다시 입력받을 수 있도록 한다!


### 1. 로또 번호 생성

- camp.nextstep.edu.missionutils.Randoms의 pickUniqueNumbersInRange(1, 45, 6)를 활용
        
        - TDD: 중복되지 않는 숫자 6개가 1~45 사이에서 정상적으로 생성되는지 10000 case Test
        
        - Exception Case: 없음

- 생성 후 Lotto클래스에서 인스턴스 생성 후 등록할 때, sort도 해준다.
### 2. 당첨 번호 && 보너스 번호 입력

- camp.nextstep.edu.missionutils.Console의 readLine()을 활용
- **','** 구분자로 구분된 문자열을 입력 받음
  
        - Exception Case: 빈 문자열인지 확인 [view에서 처리]

        - Exception Case가 발생시 재귀문을 활용하여 다시 입력받을 수 있도록 한다!
- ',' 구분자를 기준으로 문자열을 분리 후 List\<Integer> 형식으로 저장
        
        - split()을 담당하는 클래스 or 메서드를 만들어야 한다! -> 메서드로 했음..!
        
        - TDD: 1, 2, 3, 4, 5, 6를 입력했을 때 -> [1, 2, 3, 4, 5, 6]을 return하는지 확인      

        - Exception Case: 
               - split 이후 결과에서 값이 숫자가 아닌 문자일 경우
                  ex) 1, 2, 3, 4, 5, a

             
             - 구분자의 형식이 잘못 될 경우
                ex) 1,, 2, 3, 4, 5, 6
                    , 1, 2, 3, 4, 5, 6

             - 당첨 번호의 숫자가 1보다 작고 45보다 큰 경우

        - Exception Case가 발생시 재귀문을 활용하여 다시 입력받을 수 있도록 한다!
- 보너스 번호를 입력 받음
 
        - Exception Case:
             - 빈 문자열일 경우 [view에서 처리]
             - 숫자가 아닌 문자일 경우 [view에서 처리]
             - 숫자가 1보다 작고 45보다 큰 경우

        - Exception Case가 발생시 재귀문을 활용하여 다시 입력받을 수 있도록 한다!

### 3. 로또 번호와 당첨 번호랑 비교

- 로또 번호와 당첨 번호를 비교한다 (당첨번호 객체와 로또 번호 객체를 비교했다!)
    
        List<WinningLotto> winningLottoNumber = findWinningLotto();
        List<Lotto> purchaseLottoNumbers = purchaseLottoService.findPurchaseLotto();
        List<Integer> compareResult = new ArrayList<>();

        for (WinningLotto winningLotto : winningLottoNumber) {
            purchaseLottoNumbers.forEach(lotto -> {
                int matchCount = winningLotto.countMatchedNumbers(lotto);
                compareResult.add(matchCount);
            });
        }

        - TDD: 임의의 로또 번호와 임의의 당첨 번호를 선정한 후 비교 테스트



### 4. 로또 당첨 출력 

- enums 파일을 따로 관리해서 출력문을 활용
  - matchCount, Message, Prize를 선언해서 enum에서 바로 수익률을 구할 수 있도록 변환함!
        
            NUMBER_OF_BUY_OUTPUT_MESSAGE(-1, "%d개를 구매했습니다.", 0),
            RESULT_STATISTICS_OUTPUT_MESSAGE(-2, "당첨 통계\n---", 0),
            THREE_MATCH_OUTPUT_MESSAGE(3, "3개 일치 (5,000원) - %d개", 5_000),
            FOUR_MATCH_OUTPUT_MESSAGE(4, "4개 일치 (50,000원) - %d개", 50_000),
            FIVE_MATCH_OUTPUT_MESSAGE(5, "5개 일치 (1,500,000원) - %d개", 1_500_000),
            FIVE_AND_BONUS_MATCH_OUTPUT_MESSAGE(51, "5개 일치, 보너스 볼 일치 (30,000,000원) - %d개", 30_000_000),
            SIX_MATCH_OUTPUT_MESSAGE(6, "6개 일치 (2,000,000,000원) - %d개", 2_000_000_000),
            RESULT_OUTPUT_MESSAGE(-3, "총 수익률은 %.1f%%입니다.", 0);
        
            private final int matchCount;
            private final String message;
            private final long prize;
        
            private LottoOutputMessage(int matchCount, String message, long prize) {
                this.matchCount = matchCount;
                this.message = message;
                this.prize = prize;
            }

  - 출력할 때 원하는 출력문을 구분하기 위해 MatchCount변수를 활용했다!

            if (this.matchCount <= 0) {
              return 0;
              }
            
            이러면 message를 구분하기 쉬워짐!
### ++ 더 생각해봐야할 것들

- 랜덤한 값으로 이루어진 로또 번호들을 어디에 저장하지?

        - Repository 패키지 생성으로 해결!!!
            - DB 역할을 대신 해줄 수 있음!
            - Repository <-> Service <-> Domain 으로 정보 교환을 자유롭게 할 수 있도록 구현!