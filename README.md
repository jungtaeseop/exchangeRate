
# 환율계산기 프로젝트

##  사용 기술
* spring boot, java 11, thymeleaf

## 요구 사항
* 송금국가는 미국으로 고정입니다. 통화는 미국달러(USD)입니다.
* 수취국가는 한국, 일본, 필리핀 세 군데 중 하나를 select box로 선택합니다. 각각 통화는 KRW, JPY, PHP 입니다.
* 수취국가를 선택하면 아래 환율이 바뀌어나타나야 합니다. 
* 송금액을 USD로 입력하고 Submit을 누르면 아래 다음과 같이 수취금액이 KRW, JPY, PHP 중 하나로 계산되어서 나와야 합니다.
* 환율과 수취금액은 소숫점 2째자리까지, 3자리 이상 되면 콤마를 가운데 찍어 보여줍니다.
* 환율 정보는 https://currencylayer.com/의 무료 서비스를 이용해 실시간으로 가져오면 된다.
* 매번 수취국가를 선택/변경할 때마다 API로 서버에 요청을 해서 환율정보를 가져옵니다.
* Submit을 누르면 선택된 수취국가와 그 환율, 그리고 송금액을 가지고 수취금액을 계산해서 하단에 보여주면 됩니다.
* 수취금액을 입력하지 않거나, 0보다 작은 금액이거나 10,000 USD보다 큰 금액, 혹은 바른 숫자가 아니라면 “송금액이 바르지 않습니다"라는 에러 메시지를 보여줍니다. 메시지는 팝업, 혹은 하단에 빨간 글씨로 나타나면 됩니다.

## 시연 화면
<img src="https://user-images.githubusercontent.com/69243090/167093509-d6381f91-c49b-4db4-bb33-046edc97a93a.png"  width="700" height="370">
![image](https://user-images.githubusercontent.com/69243090/167093632-a9e6b895-c151-41b9-8560-06f777bfff14.png)
![image](https://user-images.githubusercontent.com/69243090/167093722-7cb7a7a9-e845-478a-b32d-4207bde59d7b.png)
