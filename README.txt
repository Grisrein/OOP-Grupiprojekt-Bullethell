Takistusrada

Autorid: Mikk Raid & Gris-Markus Reinesberg
-------------------------------------------------------

Programm: Tegu on takistuserajaga, kus mängija peab eemale hoidma takistustest ja jääma ellu nii kaua kui võimalik.

Kasutajajuhend:
Liikumine toimub nooltenuppudega.

Klassid
-----------------

Peaklass: Seadistab mängu akna, paneb mängu tööle
Meetodid: main - käivitab mängu
------------------
Mangija: Sisaldab mängija liikumismehaanikat
Meetodid: uuenda() - uuendab mängija asukohta ja piirab mängijal ekraanist välja liikumast
	  klahviVajutus() - registreerib mängija klahvi inputi.
	  saadaPiirid() - Saadab, mängija piirid, et kontrollida kas on kokkupõrget takistusega
------------------
Takistus: Haldab takistuste liikumist
Meetodid: uuenda() - liigutab takistusi paremalt ekraani poolt vasakule
	  joonista() - loob takistuse graafilise osa
	  saadaPiirid() - Saadab, takistuse piirid, et kontrollida kas on kokkupõrget mängijaga
------------------
Mangupaneel: Loob mängupaneeli, taimeri, haldab takistuste tekkimist ja mängija/takistuste interaktsioone (kogemata kujunenud main)
Meetodid: uuendaAeg() - Loeb palju aega on möödunud mängu algusest (skoor)
	  uuendaMangu() - uuendab mängu hetkeseisu (mängija ja takistuste asukohad, kokkupõrke kontroll)
	  haldaTakistusi() - tegeleb takistuste tekkimistega, muudab nende loomise kiirust
	  kontrolliKokkuporkeid() - kontrollib kas mängija ja takistus puutuvad üksteist
	  joonista() - loob mängija ja takistuste graafilise poole
------------------

Protsess
--------------------------------------------------
Kuna alustasime ilma githubita siis oli protsess väga aeglane. Saime luua programmi 1 inimene korraga. Lõpu poole tõime kasutusse Githubi, tegi protsessi palju kiiremaks.
Samuti tegime terve algse programmi ühte java faili ja siis hiljem eraldasime nad, selle tõttu on ka mõned Klassid väga sassis üksteisega (nt Mangupaneel).

Panused
--------------------------------------------------
Mikk (20h) - Algne programm. Mängija, takistused, graafiline liides
Gris (15h) - Programmide puhastamine ja edasi töötlemine, taimer, githubiga tegelemine

Mured
--------------------------------------------------
1. Me tegime juba graafilise liidesega programmi, oli tsipa raske aru Saada kuidas need asjad tõlgivad
2. Meil ei küsita mängijalt midagi (saab küll vajelda, et programm küsib liikumis inputi)

Hinnang
--------------------------------------------------
Gris: Esimese programmi kohta ütleks, et tuli väga korralik. Järgmine kord tahaks mängudisaini poolist rohkem arendada ja korrektsemad klassid luua
Mikk:

Testimine
--------------------------------------------------
Tavapärane testimine. Panime mängu tööle vaatasime kas töötab nii kuidas tahame ja saime sealt edasi muuta asju täpsemaks