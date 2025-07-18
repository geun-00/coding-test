# [Gold I] 끝말잇기 - 2320 

[문제 링크](https://www.acmicpc.net/problem/2320) 

### 성능 요약

메모리: 19584 KB, 시간: 156 ms

### 분류

비트마스킹, 다이나믹 프로그래밍, 비트필드를 이용한 다이나믹 프로그래밍

### 제출 일자

2025년 7월 10일 19:10:33

### 문제 설명

<p>백승환와 이석원은 한 팀이 되어 English 끝말대회에 출전했다. 앞단어의 마지막 글자가 뒷단어의 처음 글자와 같도록 단어를 차례대로 늘어놓는 게임 말이다. 단어는 주어지는 사전에 나와 있는 단어만 사용해야 하며 (영혼이 맑아질 만한 사실을 한 가지 가르쳐 주자면) 사전의 단어들은 모두 모음(A, E, I, O, U)으로만 이루어져있다는 것이다. 단어의 시작은 어떤 단어이든지 상관이 없고 같은 단어가 두 번 이상 사용되면 안 되며 게임에 사용된 단어의 길이의 합이 그 팀의 점수가 된다.</p>

<p>점수가 최대가 되도록 끝말잇기 규칙에 맞게 단어를 늘어놓는 프로그램을 만들어 승환이와 석원이를 도와주도록 하자.</p>

### 입력 

 <p>첫 줄에 사전에 포함된 단어 개수 N이 입력된다. (1 ≤ N ≤ 16)</p>

<p>두 번째 줄부터 N+1번째 줄까지 사전에 포함된 단어들이 한 줄에 하나씩 입력된다. 단어는 대문자 A, E, I, O, U로만 이루어져 있고 하나의 단어는 그 길이가 100을 넘지 않는다. 같은 단어가 두 번 주어지지 않는다.</p>

### 출력 

 <p>한 줄에 단어를 잘 늘어 놨을 때 얻을 수 있는 최대 점수를 출력한다.</p>

