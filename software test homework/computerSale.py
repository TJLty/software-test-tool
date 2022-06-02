def checkValue(computer, display, external):
    if computer < 0 or computer > 70:
        return False, "computer error"
    elif display < 0 or display > 80:
        return False, "display error"
    elif external < 0 or external > 90:
        return False, "external error"
    elif computer == 0 or external == 0 or display == 0:
        return False, "you did not sell a full computer"
    else:
        return True, None


def computeValue(computer, display, external):
    total_value = computer * 25 + display * 30 + external * 45
    # if total_value <= 1000:
    #     return total_value * 0.1
    # elif total_value > 1000 and total_value <= 1800:
    #     return total_value * 0.15
    # elif total_value > 1800:
    #     return total_value * 0.2
    return total_value


computer = input()
display = input()
external = input()

if not computer.isdigit() or not display.isdigit or not external.isdigit():
    print("input error")
    exit()

computer = int(computer)
display = int(display)
external = int(external)

check_ans, check_message = checkValue(computer, display, external)

if not check_ans:
    print(check_message)
    exit()

print(computeValue(computer, display, external))
