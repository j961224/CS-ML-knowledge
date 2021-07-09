# 0. DateTime 사용

- Datatime 형태로 바꾸기
: 데이터가 str이나 int로 되어 있는 경우 pandas의 to_datetime을 이용해 column 전체를 datetime으로 만들 수 있다.

(추가로 sort_values(by=컬럼명,ascending=[True:오름차순, False=내림차순])으로 가능하다.)

~~~
df["timestamp"]=pd.to_datetime(df["timestamp"],format="%Y-%m-%d %H:%M:%S")
print(df)

   ID           timestamp  duration(m)
0   6 2014-03-13 01:30:00            0
1   3 2014-03-30 15:30:00            0
2   1 2014-04-13 12:30:00            0
3   2 2014-04-15 14:30:00            0
4   4 2014-04-16 11:37:00            0
5   5 2014-05-01 12:00:00            0
~~~

~~~
df = df.sort_values(by=["duration(m)"],ascending=[False])
df

ID	timestamp	duration(m)
1	3	2014-03-30 15:30:00	422.0
5	5	2014-05-01 12:00:00	360.0
2	1	2014-04-13 12:30:00	333.0
3	2	2014-04-15 14:30:00	50.0
4	4	2014-04-16 11:37:00	21.0
0	6	2014-03-13 01:30:00	0.0
~~~


- Datetime의 시간들끼리 차수 구하기
: datetime type으로 되어 있는 것끼리 빼면 두 시간의 차이를 구할 수 있다. 지목하고 있는 index가 0이라면 shift()명령어로 index 1을 가져올 수 있다.

(추가로 astype('timedelta64[h]')로 뺀 시간을 분 단위로 표현할 수 있다.)

~~~
df1["duration(m)"]=(df1.timestamp-df1.timestamp.shift()).astype('timedelta64[h]').fillna(0)
print(df1)

            timestamp  duration(m)
0 2014-03-13 01:30:00          0.0
1 2014-03-30 15:30:00        422.0
2 2014-04-13 12:30:00        333.0
3 2014-04-15 14:30:00         50.0
4 2014-04-16 11:37:00         21.0
5 2014-05-01 12:00:00        360.0
~~~
