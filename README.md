# Playhills Admission Work
## Setup
Zum Aufsetzen mus folgendes lokal auf dem Computer gehostet werden:
* Spigot 1.11.2+
* MongoDB
* Node.js
### Spigot
Das Plugin kann [hier](https://github.com/Emonadeo/playhills-plugin/releases) heruntergeladen werden. Auszuwählen ist die `PHA-X.X.X.jar`.
Diese ist das ganz normale Plugin. Die MongoDB Driver sind enthalten.
### MongoDB
Die Datenbank muss lokal beliebig gehostet werden. Zum testen ist der [Community Server](https://www.mongodb.com/download-center?jmp=homepage#community)
empfehlenswert. (Ich verwendete Version 3.4.5)
### Node.js
[Node.js](https://nodejs.org/en/download/) muss hierfür auf dem Computer installiert sein.
Zunächst muss der Sourcecode vom Repository [playhills-web](https://github.com/Emonadeo/playhills-web) heruntergeladen werden.
Nach dem Entpacken IM Ordner über die Eingabeaufforderung den Befehl `node index.js` ausführen, damit der Server gestartet wird.
## Anwendung
Auf dem Spigot Server werden die Stats automatisch in MongoDB übernommen. Diese können visuell im Browser
im zuvor heruntergeladenen `playhills-web/docs/index.html` angesehen werden. Die Stats aktualisieren sich automatisch alle 3 Sekunden
dabei muss mindestens der MongoDB und Node.js Server laufen. Zur optimalen Probe können in Minecraft Stats absichtlich
durch sterben, Tiere töten und laufen verändert werden, während die Website geöffnet ist und alle drei Server laufen
