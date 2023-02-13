#**Backend programmering inlämningsuppgift 2**

**Realtids chat med REST endpoints.**

*Individuellt projekt med Web sockets och Spring REST*

#*Rest CRUD & Sockets*

Implementera CRUD operationerna via ett REST API med realtidsuppdateringar via socket kanaler. 
Observera att du förväntas använda databaslösningar i uppgiften där det är lämpligt.

#**HEADER PARAMETRAR ANVÄNDS för att aktivera chat (key, value).**

##**Uppdragsbeskrivning**

Låta användare ansluta till en permanent chatt-kanal där deltagare kan skicka förslag på chatt-träffar.
Denna uppgift delas in i två områden. Ett REST API med endpoints som hanterar kommunikationen för att annonsera nya chattrum (träffar) 
samt för att ta bort en skapad chatträff. En annons består av ett id och en titel.

###REST API endpoints

1. [GET] /channels/ ← Hämtar en lista över annonserade kanaler
2. [POST] - /channels/ ← skapar en ny kanal som annonseras i den permanenta chatt-kanalen.
3. [DELETE] /channels/{id} ← tar bort en annonserad kanal

Studerande väljer hur deltagare av borttagna kanaler hanteras. Det är ok att låta de vara kvar efter att annonsen är borttagen.

###Socket endpoints

1. /sub/channels/ ← en socket för den permanenta chatt-kanalen. Här skickas annonser som har skapats via POST förfrågan.
2. /sub/chat/ ← en (eller flera) socket där du ansluter mot en specifik kanal (via id-värde) och lyssnar på nya meddelanden från kanalen samt kan skicka tillbaka svar via samma socket. ***

*** HandshakeHeaders kan användas för att skicka med initierade värden i en socket, ex. kanalens id-värde. 
