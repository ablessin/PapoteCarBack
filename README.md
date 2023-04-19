# api-rest-GreenGo

memo: "dans headers { Authorization: 'Bearer token' }
pour tous les url exepté http://localhost:8080/api/greenGo/v1/auth/* et http://localhost:8080/api/greenGo/v1/trajet/read et http://localhost:8080/api/greenGo/v1/trajet/search/*"

rappel: "il est necessaire d'avoir des id existant sinon cela ne fonctionnera pas"

// create user (method post)

http://localhost:8080/api/greenGo/v1/auth/signup

body:
{
"username": "admin22",
"email": "admin22gmail.com",
"password": "123azer",
"role": "user",
"firstName": "admin",
"surname": "admin",
"gender": "male"
}

/////////////////////////////////////////////////////////////////////////////////////////

// login (method post)

http://localhost:8080/api/greenGo/v1/auth/signin

body:
{
"username": "admin2",
"password": "123azer",
"role": "user"
}

/////////////////////////////////////////////////////////////////////////////////////////

// create trajet (method post)

http://localhost:8080/api/greenGo/v1/trajet/create

body:
{
"name": "nomDuTrajet",
"driver": { "id": userId },
"placeMax": integer
}

/////////////////////////////////////////////////////////////////////////////////////////

// read all trajet (method get)

http://localhost:8080/api/greenGo/v1/trajet/read

/////////////////////////////////////////////////////////////////////////////////////////

// read trajet by user (method get)

http://localhost:8080/api/greenGo/v1/trajet/read/user/userId

/////////////////////////////////////////////////////////////////////////////////////////

// read trajet by id (method get)

http://localhost:8080/api/greenGo/v1/trajet/read/trajetId

/////////////////////////////////////////////////////////////////////////////////////////

// read trajet by place (method get)

http://localhost:8080/api/greenGo/v1/trajet/search/type de champs/value
exemple:
http://localhost:8080/api/greenGo/v1/trajet/search/city/nantes
http://localhost:8080/api/greenGo/v1/trajet/search/region/normandie

/////////////////////////////////////////////////////////////////////////////////////////

// create chat (method post)

http://localhost:8080/api/greenGo/v1/chat/create

body:
{
"trajet": { "id": trajetId }
}

/////////////////////////////////////////////////////////////////////////////////////////

// read chat by trajet (method get)

http://localhost:8080/api/greenGo/v1/chat/read/trajet/trajetId

/////////////////////////////////////////////////////////////////////////////////////////

// create message (method post)

http://localhost:8080/api/greenGo/v1/message/create

body:
{
"chat": { "id": chatId },
"user": { "id": userId }
}

/////////////////////////////////////////////////////////////////////////////////////////

// read message by chat (method get)

http://localhost:8080/api/greenGo/v1/message/read/chat/chatId

/////////////////////////////////////////////////////////////////////////////////////////

// create place (method post)

http://localhost:8080/api/greenGo/v1/place/create

body:
{
"city": "nantes",
"adress": "rue  test",
"number": integer, (optional)
"departement": "Loire-Atlantique",
"region": "Pays de la Loire"
}

/////////////////////////////////////////////////////////////////////////////////////////

// read place by chat (method get)

http://localhost:8080/api/greenGo/v1/place/search/type de champs/value
exemple:
http://localhost:8080/api/greenGo/v1/place/search/city/nantes
http://localhost:8080/api/greenGo/v1/place/search/region/normandie

all place:
http://localhost:8080/api/greenGo/v1/place/search/vide/vide


/////////////////////////////////////////////////////////////////////////////////////////

// create step (method post)

http://localhost:8080/api/greenGo/v1/step/create

body:
{
"place": { "id": placeId },
"trajet": { "id": trajetId },
"position": integer
}

/////////////////////////////////////////////////////////////////////////////////////////

// read step by trajet (method get)

http://localhost:8080/api/greenGo/v1/step/read/trajet/trajetId

/////////////////////////////////////////////////////////////////////////////////////////

// read step by best search (method get)

http://localhost:8080/api/greenGo/v1/step/read/bestSearch

/////////////////////////////////////////////////////////////////////////////////////////

// read step by place (method get)

http://localhost:8080/api/greenGo/v1/step/read/placeId

/////////////////////////////////////////////////////////////////////////////////////////

// create objectPassager (method post)

http://localhost:8080/api/greenGo/v1/objectPassager/create

body:
{
"user": { "id": userId },
"trajet": { "id": trajetId },
"start": { "id": placeId },
"end": { "id": placeId },
"isValided: false (mettre a false car le conducteur doit valider)
}

/////////////////////////////////////////////////////////////////////////////////////////

// read objectPassager by trajet (method get)

http://localhost:8080/api/greenGo/v1/objectPassager/read/trajet/trajetId

/////////////////////////////////////////////////////////////////////////////////////////

// validate objectPassager (method put)

http://localhost:8080/api/greenGo/v1/objectPassager/accepted/id

/////////////////////////////////////////////////////////////////////////////////////////

// read notifications by user (method get)

http://localhost:8080/api/greenGo/v1/notifications/read/user/userId

/////////////////////////////////////////////////////////////////////////////////////////

// suppression passager d'un trajet (method delete)

http://localhost:8080/api/greenGo/v1/objectPassager/delete/id
