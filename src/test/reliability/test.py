import requests
from datetime import datetime

startTime = datetime.now()
x = 0
faile = 0
while x <= 500:
    response = requests.get("http://152.96.56.38:8080/User/Matches/1")
    print(x, ": ", response.status_code)
    if response.status_code != 200:
        faile += 1
    x +=1

print(datetime.now() - startTime)
print(faile)
