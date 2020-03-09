# Back-end

- cd /back-end
- gradlew apprun #Για να τρεξει ο server
- gradlew build  #Για build ολου του server + tests
- gradlew test   #Μονο για tests του back-end

ΦΑΚΕΛΟΙ:<br/>
- back-end\src\main\java\gr\ntua\ece\softeng19b\api\representation<br/> 
		Περιέχει Representation για JsonUser,JsonMap,JsonImportResult καθώς και interfaces για παραγωγη Representation των δεδομένων
- back-end\src\main\java\gr\ntua\ece\softeng19b\api\resource<br/>
		Περιέχει τα Rest calls που γίνονται ( get , put και post ) καθώς και την διαχείρηση σφαλμάτων πριν η ερώτηση φτάσει στην βάση
- back-end\src\main\java\gr\ntua\ece\softeng19b\conf<br/>
		Configurations και αρχικοποίηση λιστών δεδομένων για ProductionType και AreaName με κλήση στην βάση.
- back-end\src\main\java\gr\ntua\ece\softeng19b\data<br/>
		Κλήσεις στην βάση για όλα τα endpoints.
- back-end\src\test\groovy\gr\ntua\ece\softeng19b\FunctionalTest.groovy<br/>
		Functional Tests για το back-end.


Intended contents:
- Code-testing: Back-end functional tests.
- Code-testing: RESTful API.
- Code-testing: Πηγαίος κώδικας εφαρμογής για εισαγωγή, διαχείριση και πρόσβαση σε πρωτογενή δεδομένα (backend).
