
def find_fraudulent_activity(logs, threshold):
    user_count = defaultdict(int)

    for entry in logs:

        users = set(entry[:2])

        for user in users:
            user_count[user] += 1
    return sorted(k for (k, v) in user_count)


logs = [
       ["345366", "89921", 45],
       ["029323", "38239", 23],
       ["38239", "2345366", 15],
       ["029323", "38239", 77],
       ["345366", "38239", 23],
       ["029323", "345366", 13],
       ["38239", "38239", 23]
       ]

print(find_fraudulent_activity(logs, 3)