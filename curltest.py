import os

# Testing the Person resource
url = 'http://localhost:8080/webbapp_frontend/rs/'
resource = 'person'

for x in xrange(0, 10):
    user_string = ("username=user" + str(x) + "&email=user" + str(x) + "@user"
                   + str(x) + ".com" + "&password=pAssWORd!" + str(x))
    adduser = 'curl --data ' + '"' + user_string + '"' + ' '
    os.system(adduser + url + resource)
