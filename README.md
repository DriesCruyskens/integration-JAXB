# integration-JAXB

dit is een hulpmiddel voor iedereen om XML aan te maken van data. Om XML om te zette naar data moet er nog toevegoed worden

Dit voorbeeld bevat alle messageklassen voor een UserMessage, deze kan gekopieerd en aangepast worden voor alle andere messages

Elke UserMessage bestaat uit een header, userstructure (wordt datastructure) en footer klasse. Deze bevatten op hun beurt de data die in de XML moet terrechtkomen.

Voor andere Message-klassen aan te maken moeten enkel de UserMessage-klasse en UserStructure-klasse worden gekopieerd en omgevord. Van klasse 'Footer' & 'Header' blijft er maar 1 bestaan.

Als iemand andere klasse aanmaken kunnen die op deze repository worden toegevoegd voor iedereen om te gebruiken!

benodigdheden:  

JAXB: https://mvnrepository.com/artifact/javax.xml.bind/jaxb-api/2.3.0  
Commons codec (voor sha1 checksum): https://mvnrepository.com/artifact/commons-codec/commons-codec/1.10
