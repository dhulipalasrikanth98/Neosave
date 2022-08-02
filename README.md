# Neosave
step 1:
mvn clean install command for creating the jar in the target folder
step 2 : run command from location where .jar is present - java -jar jarName.jar the jar is running

port used 8081

Sample Request and Response

Success
http://localhost:8081/neosave/user/create?name=srikanth&email=d.v.srikanth@gmail.com&addressPinCode=533106

{
    "id": 25
}

Failure:
{
    "status": 500,
    "message": "provide the required details"
}

Success

http://localhost:8081/neosave/user/11

{
    "stateName": "Andhra Pradesh",
    "name": "ABCD",
    "email": "ABCD@gmail.com",
    "addressPinCode": "533107"
}


