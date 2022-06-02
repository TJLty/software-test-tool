talk_time = input()
violate_times = input()

if not talk_time.isdigit() or not violate_times.isdigit():
    print("input error")
    exit()

talk_time = int(talk_time)
violate_times = int(violate_times)

if talk_time < 0:
    print("talk_time error")
    exit()

if violate_times < 0 or violate_times > 11:
    print("violate times error")
    exit()
cost = 0
if talk_time >= 0 and talk_time <= 60:
    if violate_times <= 1:
        cost = 25 + 0.15 * talk_time * 0.99
    else:
        cost = 25 + 0.15 * talk_time
elif talk_time > 60 and talk_time <= 120:
    if violate_times <= 2:
        cost = 25 + 0.15 * talk_time * 0.985
    else:
        cost = 25 + 0.15 * talk_time
elif talk_time > 120 and talk_time <= 180:
    if violate_times <= 3:
        cost = 25 + 0.15 * talk_time * 0.98
    else:
        cost = 25 + 0.15 * talk_time
elif talk_time > 180 and talk_time <= 300:
    if violate_times <= 3:
        cost = 25 + 0.15 * talk_time * 0.975
    else:
        cost = 25 + 0.15 * talk_time
elif talk_time > 300:
    if violate_times <= 6:
        cost = 25 + 0.15 * talk_time * 0.97
    else:
        cost = 25 + 0.15 * talk_time

cost = ((int(cost * 1000) + 5) // 10) / 100

if not cost - int(cost):
    cost = int(cost)
print(cost)
