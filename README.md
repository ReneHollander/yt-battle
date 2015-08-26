Das offizielle YouTube-Battle Plugin
-------------------------------------------------

### Download
**Latest Build**: [YT-Battle 2.0.0 for SPIGOT-1.8.8-R0.1-SNAPSHOT | Wed 26 Aug 2015 02:12:39 PM CEST](http://files.rene8888.at/yt-battle/yt-battle-v2.0.0-SPIGOT-1.8.8-R0.1-SNAPSHOT.jar)  
##Informationen

### Autoren
* Rene8888:
	- Website: [rene8888.at](http://rene8888.at/)
	- YouTube: [Rene8004](https://www.youtube.com/user/Rene8004/)
	- Skype: simpsons_fan2
	- Twitter: [Rene8004](https://twitter.com/Rene8004)
* EXSolo:
	- Website: [exsoloscript.com](http://exsoloscript.com/)
	- YouTube: [EXSoloScript](https://www.youtube.com/user/EXSoloScript)
	- Skype: exsoloyt

### Den Server konfigurieren
#### Installation:
Verwendet wird Spigot mit der Version `1.8.8-R0.1-SNAPSHOT`. Nähere Informationen wie du Spigot 1.8 bekommst findest du hier: [Bukkit, CraftBukkit & Spigot 1.8 - Server Instructions](http://www.spigotmc.org/threads/bukkit-craftbukkit-spigot-1-8.36598/). Das Plugin wie jedes andere in den `plugins/` Ordner kopieren. Kompatibilität besteht zu folgenden Plugins: Essentials, WorldEdit, WorldGuard, TerrainControl, SuperVanish.

#### Konfigurationsdatei
Im Konfigurationsordner `YT-Battle` kann die `config.yml` angepasst werden. Die Namen der einzelnen Einstellungen sollten selbsterklärend sein.

#### Konfiguration (Ingame):
1. Zuerst muss der Spawn Punkt mit dem Kommando `/battle spawn` gesetzt werden.
2. Danach sollte die Welt mit WorldBorder vorgeneriert und mit WorldEdit eine Bedrock Wand erstellt werden.
3. Damit jeder Spieler im Richtigen Team ist, muss jeder Spieler folgendes Kommando ausführen: `/battle join <farbe>`. Folgende Team Farben sind verfügbar: `white, yellow, green, cyan, purple, blue, red, magenta, orange, gray`.
4. Sind alle Spieler in den Richtigen Teams, so kann das Spiel mit `/battle start <grace period (min)>` gestartet werden. Ab jetzt läuft das Spiel.

#### Befehle im Spielverlauf
Um eine neue Wolle zu erhalten, wird das Kommando `/battle life` verwendet.

#### Spielstand speichern und wiederherstellen
Das Spiel kann mit `/battle pause` pausiert werden. Anzumerken ist, Spieler können sich nachwievor bewegen, Truhen öffnen, gegenseitig schlagen. Es werden nur Timer wie Invincibility, WoolPlace gestoppt. Nachdem `/battle pause` ausgeführt wurde kann der Server gestoppt werden. Wenn vor einem `/stop` oder `/reload` nicht pausiert wurde wird dies automatisch gemacht.

Im Konfigurationsordner `YT-Battle` befindet sich das Savefile. Dieses kann auf einen anderen Server mit der selben Plugin Version kopiert werden. Wenn man vorsichtig ist, kann man es auch mit einem Texteditor bearbeiten.

Wenn das Spiel wieder losgehen soll, kann mit `/battle resume` das Spiel fortgesetzt werden. Dies muss nachdem der Server gestoppt, reloaded oder vorher `/battle pause` ausgeführt wurde immer passieren.

Mit `/battle reset` kann das Savefile gelöscht werden. Anzumerken ist, dass hierbei auch der Server reloaded wird.

### Die Offiziellen Regeln nach [Rathamoon](https://www.youtube.com/user/RathamoonLP)
(Stand 26.08.2015)
1. Nether ist aus!
2. Teambildung:
	1. Teilnehmer tragen sich selber mit einer Selbsteinschätzung ein (1=Gut / 2= nicht so gut)
	2. Die Liste der PVP(1) bekommt durch random.org eine Reihenfolge.
 	3. nach dieser Reihenfolge wählen sich die PVP(1) jeweils einen PVP(2)
3. Friedensphase und After-Kill-Schutzzeit:
	1. Die Friedenphase beträgt 15 Minuten und wird durch das Plugin realisiert (keinem ist es erlaubt in dieser Phase aggressive Handlungen gegenüber anderen Teams zu begehen. (z.B. kein Angreifen, keine Kisten öffnen ect.)
	2. Die After-Kill-Schutzzeit beträgt 15 Minuten. In dieser Zeit kann der unter Schutzstehende nicht angegriffen werden. ein Plündern seiner Kisten ist allerdings erlaubt. Es gilt, wenn man einen Spieler Schaden zufügen kann, ist die Schutzzeit abgelaufen. Sollte allerdings die Schutzzeit noch nicht abgelaufen sein, muss dieses der betroffene Spieler der Leitung melden. Hier wird im Einzelfall durch die am Kampf nicht beteiligte Leitung entschieden. Sollte die gesamte Leitung am Kampf beteiligt gewesen sein, zählt das Plugin, auch wenn es fehlerhaft sein sollte.
	3. Die Schutzzeit erlischt bei einer aggressiven Handlung gegebenüber anderen Teams. (Wolle abbauen, Angreifen)
	4. Ein Run auf seine gedroppten Items ist erlaubt und führt zur Beendung der Schutzzeit.
4. Wollblöcke als Leben. (überwiegend durch Plugin gehandhabt)
	1. Jedes Team bekommt zum Start 10 Leben in Form von Wollblöcken
 	2. Jeder Spieler muss 1 Wollblock im Inventar haben und nach der Friedensphase 1 Wollblock in der Welt platziert haben.
 	3. Das platzieren der Wolle in der Welt wird durch das Plugin gesteuert. folgendes gilt aber in jedem Fall:
 	4. Der Wollblock muss in alle Richtungen 2 Blöcke Abstand frei haben und darf auch nicht nachträglich zugebaut werden.  
 	5. Der Wollblock muss über der Erde platziert werden. (Nicht in Höhlen, nicht in Schluchten, nicht Unterwasser ect.)
 	6. Die Wolle darf nicht weiter als 3 Blöcke vom Boden entfernt sein, wobei als Boden das gilt, was die Mehrheit als solchen ansieht! (Also nicht im fliegendem Luftschloss und auch nicht auf den einen Erdblock der durch den Mapgenerator fliegend irgendwo platziert wurde)
 	7. Eigene Wolle darf nicht abgebaut werden
5. Weltgröße:
	1.  Die Welt wird durch eine Badrock-Wand begrenzt. Der Radius variiert je nach Teilnehmer Anzahl zwischen 350 und 550 Blöcken.
 	2. Sollten Lücken im Bedrock Rand durch irgendwelche Umstände entstehen, so ist es dem Spieler nicht erlaubt durch diese Lücken irgendwelche Handlungen zu vollziehen. (z.B. kein Durchgehen, kein Abbauen auf der andern Seite, keine Tiere rüberlocken ect.)
6. Finalkampf:
Wenn nur noch 2 Teams leben verabreden sich diese zu einem finalen Kampf der über Sieg oder Niederlage entscheidet. Die derzeitige Lebensanzahl (Wolle) spielt keine Rolle.
Im Finalkampf ist bis auf eine Ausnahme alles erfarmte erlaubt. Die Ausnahme hier betrifft die Bögen. Im Finalkampf sind nur Bögen ohne jegliche Verzauberung erlaubt!
7. Mods:
Es sind keine nachinstallierten Mods erlaubt. (Außnahme Optifine da hierdurch die Microlags behoben werden)
8. Verzauberungstische
Die Map wird von den Admins so ausgesucht, das für alle genug Zuckerrohr vorhanden sein sollte.
Sollte dennoch ein Team nicht die Möglichkeit haben einen Verzauberungstisch zu craften, so wird ab dem zweiten Aufnahmetermin ein Verzauberungstisch gestellt.


## Regeln zur Nutzung
Solltest du das Plugin verwenden und Videos darüber machen, musst du mit einem Direktlink in der Beschreibung oder dergleichen auf diese Seite (`https://github.com/Rene8888/yt-battle`) oder auf den verkürzten Link (`http://bit.ly/battleplugin`) verweisen. Das muss jeder tun der Mitspielt! Änderungen am Code (wie in der Lizenz beschrieben) müssen Öffentlich zugänglich sein. Außerdem sollst du Änderungen nicht verkaufen oder irgendwie anders daran Geld verdienen. Monetarisierung der Videos ist okay, aber das Plugin darf nicht verkauft werden!

## Arbeitsumgebung aufsetzen

Das Plugin basiert auf folgenden Template: https://github.com/ReneHollander/empty-spigot-plugin. Dort gibt es auch Informationen zur Verwendung.

## Mitwirken
#### Pull Requests
Du hättest gerne eine Änderung vorgenommen? Oder hast du einen Bug gefunden den du selber fixen kannst? Nice!  
Damit das gut Funktioniert, bitte ich dich, den Eclipse Code Formatter zu verwenden! Beim Line Wrapping in den Einstellungen bitte die Maximale Zeichenlänge auf 300 Zeichen stellen!  
Auch bitte ich dich, alles gut zu testen und zu dokumentieren! Jetzt kannst du deinen Pull Request ausführen. Wenn alles passt wird der Request nach maximal 1-2 Tagen angenommen. Die Änderung wird dann bei der nächsten Version im Download inkludiert. Sollte es sich um einen Major Bugfix handeln, wird sofort ein neuer Download bereitgestellt.

#### Issues
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

## Lizenz
Verwendet wird die General Public License in der Version 3. Eine Kopie der Lizenz befindet sich im Repository.
