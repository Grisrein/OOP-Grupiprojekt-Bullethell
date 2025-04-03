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
Meetodid: 
------------------

Protsess
--------------------------------------------------

Panused
--------------------------------------------------

Mured
--------------------------------------------------

Hinnang
--------------------------------------------------

Testimine
--------------------------------------------------