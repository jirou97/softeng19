# CLI client
cd cli-client
gradlew installDist
cd \build\install\energy_TEAM\bin
Και κάθε εντολή τρέχεται με την μορφή 
	π.χ. energy_TEAM ActualTotalLoad --area=Greece --date=2018-01-01 --timeres=PT60M
	
	Μια απλή σειρά κλήσεων εμφανίζεται παρακάτω :
	-Login Admin and make a new user with quota=6
		energy_TEAM Login --username=admin --passw=321nimda
		energy_TEAM Admin --newuser=anotheruser --passw=anotherpassword --email=a@a.gr --quota=6
		energy_TEAM Logout
	-Login as the new made user and make some calls
		energy_TEAM Login --username=anotheruser --passw=anotherpassword
		energy_TEAM ActualTotalLoad --area=Greece --timeres=PT60M --date=2018-01-01	//(200)
		energy_TEAM ActualTotalLoad --area=Greece --timeres=PT40M --date=2018-01-01	//CLI error 
		energy_TEAM ActualTotalLoad --area=gReEcE --timeres=PT60M --date=2018-01-01	//(400) Bad AreaName
		energy_TEAM ActualTotalLoad --area=Greece --timeres=PT41M --date=2018-12-01	//(403) No data fetched

		energy_TEAM ActualTotalLoad --area=Greece --timeres=PT60M --month=2018-01	//(200)
		energy_TEAM ActualTotalLoad --area=Greece --timeres=PT60M --year=2018		//(200)
		energy_TEAM ActualTotalLoad --area="IT-Centre-South BZ" --timeres=PT60M --year=2018	//encoding works just fine

	-quota should be equal to 1 so next call will be an out of quota exception
		energy_TEAM ActualTotalLoad --area=France --timeres=PT60M --date=2018-01-01	//(402) Out Of quota
		energy_TEAM Logout
	
	-Admin Logs in again updates quotas and sees the user's status
		energy_TEAM Login --username=admin --passw=321nimda
		energy_TEAM Admin --moduser=anotheruser --quota=10
		energy_TEAM Admin --userstatus=anotheruser 
		energy_TEAM Logout
	
	-User logs in again and uploads a file
		energy_TEAM Login --username=anotheruser --passw=anotherpassword
		energy_TEAM Admin --userstatus=anotheruser 						//(401) not authorized
		energy_TEAM Admin --newdata=ActualTotalLoad --source="C:/path/to/file/to/upload.csv"		//200
		energy_TEAM Logout
	
ΦΑΚΕΛΟΙ:<br/>
- cli-client\src\main\java\gr\ntua\ece\softeng19b\cli<br/>
		Περιέχει όλα τα endpoints και τα arguements τους για το cli.

Intended contents:
- Command line interface (CLI).
