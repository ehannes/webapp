import os

# Testing the Person resource
url = 'http://localhost:8080/webbapp_frontend/rs/'
resource = 'person'
LIMIT = 10

for x in xrange(0, LIMIT):
    user_string = ("username=user" + str(x) + "&email=user" + str(x) + "@user"
                   + str(x) + ".com" + "&password=pAssWORd!" + str(x))
    adduser = 'curl --data ' + '"' + user_string + '"' + ' '
    os.system(adduser + url + resource)
