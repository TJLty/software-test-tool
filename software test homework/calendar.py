c = input()


def checkLeapYear(value):
    if value % 4 == 0 and value % 100:
        return 29
    elif value%400==0:
        return 29
    else:
        return 28


def checkMonth(year, month):
    single_month = [1, 3, 5, 7, 8, 10, 12]
    double_month = [4, 6, 9, 11]
    if month in single_month:
        return 31
    elif month == 2:
        return checkLeapYear(year)
    elif month in double_month:
        return 30
    else:
        return -1


if c.isdigit() == False:
    print("input error")
else:
    c = int(c)
    year = c // 10000
    month = (c % 10000) // 100
    day = (c % 100)

    if year > 2100 or year < 1900:
        print("year error")
        exit()

    max_day = checkMonth(year, month)
    if max_day == -1:
        print(month)
        print("month error")
        exit()
    if day <= 0:
        print("day error")
        exit()
    if day < max_day:
        day = day + 1
    elif day == max_day:
        month = month + 1
        day = 1
    else:
        print("day error")
        exit()
    if month == 13:
        year = year + 1
        month = 1

    print(year * 10000 + month * 100 + day)
