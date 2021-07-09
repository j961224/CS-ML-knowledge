# 1. 이상치 탐지
: 이상값을 찾는 것으로 다른 데이터와 많이 떨어진 데이터를 의미한다.

우선 잘못된 값이라면 제거하거나 수정하고 잘못된 값이 아니라면 우선은 그대로 둔다.


## Quantile을 이용한 이상값 탐지
: 보통 값이 data<quantile1-1.5 X IQR 이거나 data>quantile3+1.5 X IQR에 속한다면 이상값으로 탐지한다.

~~~
import numpy as np

print("표준편차")
print(np.std(df['duration(m)']))

print("1사분면")
print(df['duration(m)'].quantile(0.25)) # 1사분면
print("2사분면")
print(df['duration(m)'].quantile(0.5)) # 2사분면
print("3사분면")
print(df['duration(m)'].quantile(0.75)) # 3사분면


IQR = df['duration(m)'].quantile(0.75)-df['duration(m)'].quantile(0.25)

#Outlier 탐색
searchdf = df[(df['duration(m)']<df['duration(m)'].quantile(0.25)-1.5*IQR) | (df['duration(m)']>df['duration(m)'].quantile(0.75)+1.5*IQR)]
#존재하지 않는다.
print(searchdf)

표준편차
176.57922364259682
1사분면
28.25
2사분면
191.5
3사분면
353.25
Empty DataFrame
Columns: [ID, timestamp, duration(m)]
Index: []
~~~

