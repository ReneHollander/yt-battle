Willkommen beim offiziellen YouTube-Battle Plugin
-------------------------------------------------

**Download der letzten Version**: [YT-Battle 1.9.0 für Craftbukkit 1.7.10-R0.1 || DEV BUILD 711aa376211726a77bb01988cf05286bfd9bf901](http://files.rene8888.at/yt-battle/yt-battle-latest-build-711aa376211726a77bb01988cf05286bfd9bf901.zip)

##Informationen

###Den Server konfigurieren
####Installation:
Verwendet wird die Craftbukkit Version `1.7.10-R0.1`. Das Plugin muss mitsamt dem `lib` Ordner in den `plugins` Ordner verschoben werden. Am sichersten ist es, wenn vorher alle anderen Plugins wie Permission System, World Edit, Essentials usw entfernt werden. Sonst kann es zu Problemen kommen!  
Dann kann schon der Server gestartet werden. Die Konfiguration erfolgt nun INGAME:

1. Zuerst muss der Spawn Punkt mit dem Kommando `/battle spawn` gesetzt werden.
2. Durch einen Fehler in der momentanen Version von WorldEdit, können wir den Border nicht automatisch generieren. Als Border Material wird Bedrock empfohlen.
3. Damit jeder Spieler im Richtigen Team ist, muss jeder Spieler folgendes Kommando ausführen: `/battle join <farbe>`. Folgende Team Farben sind verfügbar: `white, yellow, green, cyan, purple, blue, red, black`
4. Sind alle Spieler in den Richtigen Teams, so kann das Spiel mit `/battle start` gestartet werden. Ab jetzt läuft das Spiel.

####Befehle im Spielverlauf
Um eine neue Wolle zu erhalten, wird das Kommando `/battle life` verwendet.

###Die Offiziellen Regeln nach [Rathamoon](https://www.youtube.com/user/RathamoonLP)
1. Nether ist aus!
2. Friedensphase und After-Kill-Schutzzeit:
	1. Die Friedenphase beträgt 30 Minuten und wird durch das Plugin realisiert (keinem ist es erlaubt in dieser Phase aggressive Handlungen gegenüber anderen Teams zu begehen. (z.B. kein Angreifen, keine Kisten öffnen ect.)
	2. Die After-Kill-Schutzzeit beträgt 15 Minuten. In dieser Zeit kann der unter Schutzstehende nicht angegriffen werden. ein Plündern seiner Kisten ist allerdings erlaubt. Es gilt, wenn man einen Spieler Schaden zufügen kann, ist die Schutzzeit abgelaufen. Sollte allerdings die Schutzzeit noch nicht abgelaufen sein, muss dieses der betroffene Spieler der Leitung melden. Hier wird im Einzelfall durch die am Kampf nicht beteiligte Leitung entschieden. Sollte die gesamte Leitung am Kampf beteiligt gewesen sein, zählt das Plugin, auch wenn es fehlerhaft sein sollte. 
	3. Die Schutzzeit erlischt bei einer aggressiven Handlung gegebenüber anderen Teams. (Wolle abbauen, Angreifen)
	4. Ein Run auf seine gedroppten Items ist erlaubt und führt nicht zur Beendung der Schutzzeit. 
3. Wollblöcke als Leben. (überwiegend durch Plugin gehandhabt)
	1. Jedes Team bekommt zum Start 10 Leben in Form von Wollblöcken
	2. Jeder Spieler muss 1 Wollblock im Inventar haben und nach der Friedensphase 1 Wollblock in der Welt platziert haben.
	3. Das platzieren der Wolle in der Welt wird durch das Plugin gesteuert. folgendes gilt aber in jedem Fall: 
	4. Der Wollblock muss in alle Richtungen 2 Blöcke Abstand frei haben und darf auch nicht nachträglich zugebaut werden.  
	5. Der Wollblock muss über der Erde platziert werden. (Nicht in Höhlen, nicht in Schluchten, nicht Unterwasser ect.)
	6. Die Wolle darf nicht weiter als 3 Blöcke vom Boden entfernt sein, wobei als Boden das gilt, was die Mehrheit als solchen ansieht! (Also nicht im fliegendem Luftschloss und auch nicht auf den einen Erdblock der durch den Mapgenerator fliegend irgendwo platziert wurde)
	7. Eigene Wolle darf nicht abgebaut werden
4. Weltgröße: 
	1. Die Welt wird durch eine Badrock-Wand begrenzt. Der Radius variiert je nach Teilnehmer Anzahl zwischen 350 und 550 Blöcken.
	2. Sollten Lücken im Bedrock Rand durch irgendwelche Umstände entstehen, so ist es dem Spieler nicht erlaubt durch diese Lücken irgendwelche Handlungen zu vollziehen. (z.B. kein Durchgehen, kein Abbauen auf der andern Seite, keine Tiere rüberlocken ect.)

##Regeln zur Nutzung
Solltest du das Plugin verwenden und Videos darüber machen, musst du mit einem Direktlink in der Beschreibung oder dergleichen auf diese Seite (`https://github.com/Rene8888/yt-battle`) verweisen. Das muss jeder tun der Mitspielt! Kein adf.ly, bit.ly oder dergleichen. Änderungen am Code (wie in der Lizenz beschrieben) müssen Öffentlich zugänglich sein. Außerdem sollst du Änderungen nicht verkaufen oder irgendwie anders daran Geld verdienen. Monetarisierung der Videos ist okay, aber das Plugin darf nicht verkauft werden!

##Mitwirken
####Pull Requests
Du hättest gerne eine Änderung vorgenommen? Oder hast du einen Bug gefunden den du selber fixen kannst? Nice!  
Damit das gut Funktioniert, bitte ich dich, den Eclipse Code Formatter zu verwenden! Beim Line Wrapping in den Einstellungen bitte die Maximale Zeichenlänge auf 300 Zeichen stellen!  
Auch bitte ich dich, alles gut zu testen und zu dokumentieren! Jetzt kannst du deinen Pull Request ausführen. Wenn alles passt wird der Request nach maximal 1-2 Tagen angenommen. Die Änderung wird dann bei der nächsten Version im Download inkludiert. Sollte es sich um einen Major Bugfix handeln, wird sofort ein neuer Download bereitgestellt.
 
####Issues
Solltest du einen Fehler finden, kannst du ihn ganz einfach reporten:

1. Navigiere zur [Issue Page](https://github.com/Rene8888/yt-battle/issues)
2. Überprüfe ob das Problem nicht bereits reportet wurde
3. Erstelle ein neues Problem (`New Issue` Button)
4. Überlege dir einen sinnvollen Titel
5. Eine Ausführliche Beschreibung und/oder Crash Reports über PasteBin sind ein muss!
6. Damit dein Problem eingeordnet werden kann sollte ein passendes Label ausgewählt werden!
	1. Schwerer Fehler der sofort gefixt werden muss: `major bug`
	2. Fehler der das Spiel kaum beeinträchtigt aber gefixt werden sollte: `bug`
	3. Leichter Fehler wie falsche Anzeige usw: `minor bug`
7. Abwarten bis sich jemand Meldet...

##Lizenz
Verwendet wird die General Public License in der Version 3. Eine Kopie der Lizenz befindet sich im Repository.
