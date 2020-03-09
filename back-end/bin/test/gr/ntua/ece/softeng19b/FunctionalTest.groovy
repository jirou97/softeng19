package gr.ntua.ece.softeng19b


import gr.ntua.ece.softeng19b.client.RestAPI
import gr.ntua.ece.softeng19b.client.ImportResult
import  org.restlet.resource.ResourceException
import java.nio.file.Path
import java.nio.file.Paths
import java.time.LocalDate
import gr.ntua.ece.softeng19b.client.Format
import gr.ntua.ece.softeng19b.data.model.User
import gr.ntua.ece.softeng19b.data.model.*
import gr.ntua.ece.softeng19b.client.ServerResponseException
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Stepwise

@Stepwise
class FunctionalTest extends Specification{
    //private static final String IGNORED = System.setProperty("IGNORE_SSL_ERRORS", "true")

    @Shared RestAPI caller1 = new RestAPI()
    @Shared RestAPI caller2 = new RestAPI()
    @Shared String pathFor10DaysATL = "C:\\Users\\user\\Desktop\\ActualTotalLoad-10days.csv"
    @Shared String pathFor10DaysDATL = "C:\\Users\\user\\Desktop\\DayAheadTotalLoadForecast-10days.csv"
    @Shared String pathFor10DaysAGPT = "C:\\Users\\user\\Desktop\\AggregatedGenerationPerType-10days.csv"
    def "T01. Health check status is OKs"() {
        given:
        String status = caller1.healthCheck()

        expect:
        status == "OK"

    }
    
/*
   def "T02. The database is reset successfully"() {
        given:
        String status = caller1.resetDatabase()

        expect:
        status == "OK"
    }
*/
    def "T03. Admin logs in successfully"() {
        given:
        String token = caller1.login("admin","321nimda")

        expect:
        caller1.isLoggedIn()
        
    }
    def "T04. Admin creates a new user"() {
        given:
        User user = caller1.addUser("newuser","anemail@you.com","somepassword",100)
        
        expect:
        user.getUsername() == "newuser" &&
        user.getEmail() == "anemail@you.com" &&
        user.getRequestsPerDayQuota() == 100

    }
    
    def "T05. Admin sees user"(){
        given:
        User user = caller1.getUser("newuser")

        expect:
        user.getUsername() == "newuser" &&
        user.getEmail() =="anemail@you.com" &&
        user.getRequestsPerDayQuota() == 100 &&
        user.getPassword() != "somepassword"
    }

    def "T06. User logs in"() {
        given:
        String token =caller2.login("newuser","somepassword")

        expect:
        caller2.isLoggedIn()
    }
    /*
    
    def "T07. Admin uploads an DayAheadTotalLoad dataset"() {
        given:

        String csv = pathFor10DaysDATL
        
        ImportResult result = caller2.importFile("DayAheadTotalLoadForecast",Path.of(csv))

        expect:
        result.getTotalRecordsInFile() == 40079 &&
        result.getTotalRecordsImported() == 40078 &&
        result.getTotalRecordsInDatabase() == 40078
    }

    def "T08. Admin uploads an ActualTotalLoad dataset"() {
        given:
        //String csv = "C:\\Users\\user\\Desktop\\Test.csv"
        String csv = pathFor10DaysATL
        //String csv = "C:\\Users\\user\\Desktop\\ActualTotalLoad-3months.csv"
        
        ImportResult result = caller2.importFile("ActualTotalLoad",Path.of(csv))

        expect:
        //result.getTotalRecordsInFile() == 218 &&
        //result.getTotalRecordsImported() == 217 &&
        //result.getTotalRecordsInDatabase() == 217
        result.getTotalRecordsInFile() == 38681 &&
        result.getTotalRecordsImported() == 38680 &&
        result.getTotalRecordsInDatabase() == 38680
        
        //result.getTotalRecordsInFile() == 387198 &&
        //result.getTotalRecordsImported() == 387197 &&
        //result.getTotalRecordsInDatabase() == 387197
    }

    def "T09. Admin uploads another ActualTotalLoad dataset to check if it overrides"() {
        given:
        String csv = "C:\\Users\\user\\Desktop\\Test.csv"
        
        ImportResult result = caller2.importFile("ActualTotalLoad",Path.of(csv))

        expect:
        result.getTotalRecordsInFile() == 3 &&
        result.getTotalRecordsImported() == 0 &&
        result.getTotalRecordsInDatabase() == 38680
    }
    def "T10. Admin uploads an AggregatedGenerationPerType dataset"() {
        given:

        String csv = pathFor10DaysAGPT
        
        ImportResult result = caller2.importFile("AggregatedGenerationPerType",Path.of(csv))

        expect:
        result.getTotalRecordsInFile() == 330770 &&
        result.getTotalRecordsImported() == 330769 &&
        result.getTotalRecordsInDatabase() == 330769
    }
    
    */
    def "T11. User retrieves ActualTotalLoad tuple for 2018-01-01 for Greece"() {
        given :
        List<ATLRecordForSpecificDay> records = caller2.getActualTotalLoad(
            "Greece",
            "PT60M",
            LocalDate.of(2018, 1, 1),
            Format.JSON
        )

        expect:
        records.size() == 24
    }
    def "T12. User retrieves ActualTotalLoad tuple for 2018-01 for Greece"() {
        given :
        List<ATLRecordForSpecificMonth> records = caller2.getActualTotalLoadForMonth(
            "Greece",
            "PT60M",
            LocalDate.of(2018, 1, 1),
            Format.JSON
        )

        expect:
        records.size() == 10
    }

    def "T13. User retrieves ActualTotalLoad tuple for 2018 for Greece"() {
        given :
        List<ATLRecordForSpecificYear> records = caller2.getActualTotalLoadForYear(
            "Greece",
            "PT60M",
            LocalDate.of(2018, 1, 1),
            Format.JSON
        )

        expect:
        records.size() == 1
    }
    def "T14. User retrieves DayAheadTotalLoad For tuple for 2018-01-01 for France"() {
        given :
        List<DATLFRecordForSpecificDay> records = caller2.getDayAheadTotalLoad(
            "France",
            "PT60M",
            LocalDate.of(2018, 1, 1),
            Format.JSON
        )

        expect:
        records.size() == 24
    }

    def "T15. User retrieves DayAheadTotalLoadForMonth For tuple for 2018-01 for France"() {
        given :
        List<DATLFRecordForSpecificMonth> records = caller2.getDayAheadTotalLoadForMonth(
            "France",
            "PT60M",
            LocalDate.of(2018, 1, 1),
            Format.JSON
        )

        expect:
        records.size() == 10
    }
    def "T16. User retrieves DayAheadTotalLoadForYear For tuple for 2018 for France"() {
        given :
        List<DATLFRecordForSpecificYear> records = caller2.getDayAheadTotalLoadForYear(
            "France",
            "PT60M",
            LocalDate.of(2018,1,1),
            Format.JSON
        )

        expect:
        records.size() == 1
    }

    def "T17. User retrieves AllTypes AggregatedGenerationPerType For tuple for 2018-01-06 for Austria"() {
        //PT15M
        //Austia
        given :
        List<AGPTRecordForSpecificDay> records = caller2.getAggregatedGenerationPerType(
            "Austria",
            "PT15M",
            LocalDate.of(2018, 1, 6),
            "AllTypes",
            Format.JSON
        )

        expect:
        records.size() == 1248
    }
    
    def "T18. User retrieves AllTypes AggregatedGenerationPerTypeForMonth For tuple for 2018-01-06 for Austria"() {
        //PT15M
        //Austia
        given :
        List<AGPTRecordForSpecificMonth> records = caller2.getAggregatedGenerationPerTypeForMonth(
            "Austria",
            "PT15M",
            LocalDate.of(2018, 1, 1),
            "AllTypes",
            Format.JSON
        )

        expect:
        records.size() == 130
    }
    def "T19. User retrieves AllTypes AggregatedGenerationPerTypeForYear For tuple for 2018-01-06 for Austria"() {
        //PT15M
        //Austia
        given :
        List<AGPTRecordForSpecificYear> records = caller2.getAggregatedGenerationPerTypeForYear(
            "Austria",
            "PT15M",
            LocalDate.of(2018, 1, 1),
            "AllTypes",
            Format.JSON
        )

        expect:
        records.size() == 13
    }
    
    def "T20. User retrieves AggregatedGenerationPerType For tuple for 2018-01-06 for Austria and Fossil Gas"() {
        //PT15M
        //Austia
        given :
        List<AGPTRecordForSpecificDay> records = caller2.getAggregatedGenerationPerType(
            "Austria",
            "PT15M",
            LocalDate.of(2018, 1, 6),
            "Fossil Gas",
            Format.JSON
        )

        expect:
        records.size() == 96
    }
    def "T21. User retrieves AggregatedGenerationPerType For tuple for 2018-01-06 for Austria"() {
        //PT15M
        //Austia
        given :
        List<AGPTRecordForSpecificDay> records = caller2.getAggregatedGenerationPerType(
            "Austria",
            "PT15M",
            LocalDate.of(2018, 1, 6),
            "Other",
            Format.JSON
        )

        expect:
        records.size() == 96
    }
    def "T22. User retrieves AggregatedGenerationPerTypeForMonth For tuple for 2018-01-06 for Austria"() {
        //PT15M
        //Austia
        given :
        List<AGPTRecordForSpecificMonth> records = caller2.getAggregatedGenerationPerTypeForMonth(
            "Austria",
            "PT15M",
            LocalDate.of(2018, 1, 1),
            "Other",
            Format.JSON
        )

        expect:
        records.size() == 10
    }
    def "T23. User retrieves AggregatedGenerationPerTypeForYear For tuple for 2018-01-06 for Austria"() {
        //PT15M
        //Austia
        given :
        List<AGPTRecordForSpecificYear> records = caller2.getAggregatedGenerationPerTypeForYear(
            "Austria",
            "PT15M",
            LocalDate.of(2018, 1, 1),
            "Other",
            Format.JSON
        )

        expect:
        records.size() == 1
    }
    
    def "T24. User retrieves ForecastvsActual tuple for 2018-01-04 for Greece"() {
        given :
        List<AvFRecordForSpecificDay> records = caller2.getActualvsForecast(
            "Greece",
            "PT60M",
            LocalDate.of(2018, 1, 4),
            Format.JSON
        )

        expect:
        records.size() == 24
    }
    
    def "T25. User retrieves ForecastvsActual tuple for 2018-01 for Greece"() {
        given :
        List<AvFRecordForSpecificMonth> records = caller2.getActualvsForecastForMonth(
            "Greece",
            "PT60M",
            LocalDate.of(2018, 1, 1),
            Format.JSON
        )

        expect:
        records.size() == 1
    }

    def "T26. User retrieves ForecastvsActual tuple for 2018 for Greece"() {
        given :
        List<AvFRecordForSpecificYear> records = caller2.getActualvsForecastForYear(
            "Greece",
            "PT60M",
            LocalDate.of(2018, 1, 1),
            Format.JSON
        )

        expect:
        records.size() == 1
    }
     def "T27. Admin limits the quota of the new user"() {
        given:
        User user = caller1.updateUser(new User("newuser","anotheremail@you.gr",1))
        //User user1 = caller1.updateUser("newuser","anotheremail@you.gr",1)

        expect:
        user.getEmail() == "anotheremail@you.gr" &&
        user.getRequestsPerDayQuota() == 1
    }
    
    

    def "T28. User cannot read ActualTotalLoad tuple for 2018-01-02 due to quota limit"() {
        when :
        List<ATLRecordForSpecificDay> records = caller2.getActualTotalLoad(
            "Greece",
            "PT60M",
            LocalDate.of(2018, 1, 2),
            Format.JSON
        )
        
        then:
        ServerResponseException exception = thrown()
        exception.getStatusCode() == 402
    }
    

    def "T29. Admin updates the quota of the new user again"() {
        given:
        User user = caller1.updateUser(new User("newuser","anemail@you.com",100))

        expect:
        user.getRequestsPerDayQuota() == 100
    }

    
    def "T30. User retrieves ActualTotalLoad tuple for 2018-01-02"() {
        given :
        List<ATLRecordForSpecificDay> records = caller2.getActualTotalLoad(
            "Greece",
            "PT60M",
            LocalDate.of(2018, 1, 2),
            Format.JSON
        )

        expect:
        records.size() == 24
    }

    
    def "T31. User and Admin logs out"() {
        given :
        caller2.logout()
        caller1.logout()
        expect:
        !caller2.isLoggedIn() &&
        !caller1.isLoggedIn()
    }
    
    def "T32. N Logins"(){
        setup:
        RestAPI admin0 = new RestAPI()
        RestAPI admin1 = new RestAPI()
        RestAPI admin2 = new RestAPI()
        RestAPI admin3 = new RestAPI()
        RestAPI admin4 = new RestAPI()
        RestAPI admin5 = new RestAPI()
        RestAPI admin6 = new RestAPI()
        RestAPI admin7 = new RestAPI()
        RestAPI admin8 = new RestAPI()
        RestAPI admin9 = new RestAPI()
        RestAPI admin10 = new RestAPI()
        RestAPI admin11 = new RestAPI()
        RestAPI admin12 = new RestAPI()
        RestAPI admin13 = new RestAPI()
        RestAPI admin14 = new RestAPI()
        RestAPI admin15 = new RestAPI()
        RestAPI admin16 = new RestAPI()
        RestAPI admin17 = new RestAPI()
        RestAPI admin18 = new RestAPI()
        RestAPI admin19 = new RestAPI()
        RestAPI admin20 = new RestAPI()
        RestAPI admin21 = new RestAPI()
        RestAPI admin22 = new RestAPI()
        RestAPI admin23 = new RestAPI()
        RestAPI admin24 = new RestAPI()
        RestAPI admin25 = new RestAPI()
        RestAPI admin26 = new RestAPI()
        RestAPI admin27 = new RestAPI()
        RestAPI admin28 = new RestAPI()
        RestAPI admin29 = new RestAPI()
        RestAPI admin30 = new RestAPI()
        RestAPI admin31 = new RestAPI()
        RestAPI admin32 = new RestAPI()
        RestAPI admin33 = new RestAPI()
        RestAPI admin34 = new RestAPI()
        RestAPI admin35 = new RestAPI()
        RestAPI admin36 = new RestAPI()
        RestAPI admin37 = new RestAPI()
        RestAPI admin38 = new RestAPI()
        RestAPI admin39 = new RestAPI()
        RestAPI admin40 = new RestAPI()
        RestAPI admin41 = new RestAPI()
        RestAPI admin42 = new RestAPI()
        RestAPI admin43 = new RestAPI()
        RestAPI admin44 = new RestAPI()
        RestAPI admin45 = new RestAPI()
        RestAPI admin46 = new RestAPI()
        RestAPI admin47 = new RestAPI()
        RestAPI admin48 = new RestAPI()
        RestAPI admin49 = new RestAPI()
        RestAPI admin50 = new RestAPI()
        admin0.login("admin","321nimda")
        User x1 = admin0.addUser( "newuser1","anemail1@you.com","some1password",100)
        User x2 =  admin0.addUser( "newuser2","anemail2@you.com","some2password",100)
        User x3 =  admin0.addUser( "newuser3","anemail3@you.com","some3password",100)
        User x4 =  admin0.addUser( "newuser4","anemail4@you.com","some4password",100)
        User x5 =  admin0.addUser( "newuser5","anemail5@you.com","some5password",100)
        User x6 =  admin0.addUser( "newuser6","anemail6@you.com","some6password",100)
        User x7 =  admin0.addUser( "newuser7","anemail7@you.com","some7password",100)
        User x8 =  admin0.addUser( "newuser8","anemail8@you.com","some8password",100)
        User x9 =  admin0.addUser( "newuser9","anemail9@you.com","some9password",100)
        User x10 =  admin0.addUser( "newuser10","anemail10@you.com","some10password",100)
        User x11 =  admin0.addUser( "newuser11","anemail11@you.com","some11password",100)
        User x12 =  admin0.addUser( "newuser12","anemail12@you.com","some12password",100)
        User x13 =  admin0.addUser( "newuser13","anemail13@you.com","some13password",100)
        User x14 =  admin0.addUser( "newuser14","anemail14@you.com","some14password",100)
        User x15 =  admin0.addUser( "newuser15","anemail15@you.com","some15password",100)
        User x16 =  admin0.addUser( "newuser16","anemail16@you.com","some16password",100)
        User x17 =  admin0.addUser( "newuser17","anemail17@you.com","some17password",100)
        User x18 =  admin0.addUser( "newuser18","anemail18@you.com","some18password",100)
        User x19 =  admin0.addUser( "newuser19","anemail19@you.com","some19password",100)
        User x20 =  admin0.addUser( "newuser20","anemail20@you.com","some20password",100)
        User x21 =  admin0.addUser( "newuser21","anemail21@you.com","some21password",100)
        User x22 =  admin0.addUser( "newuser22","anemail22@you.com","some22password",100)
        User x23 =  admin0.addUser( "newuser23","anemail23@you.com","some23password",100)
        User x24 =  admin0.addUser( "newuser24","anemail24@you.com","some24password",100)
        User x25 =  admin0.addUser( "newuser25","anemail25@you.com","some25password",100)
        User x26 =  admin0.addUser( "newuser26","anemail26@you.com","some26password",100)
        User x27 =  admin0.addUser( "newuser27","anemail27@you.com","some27password",100)
        User x28 =  admin0.addUser( "newuser28","anemail28@you.com","some28password",100)
        User x29 =  admin0.addUser( "newuser29","anemail29@you.com","some29password",100)
        User x30 =  admin0.addUser( "newuser30","anemail30@you.com","some30password",100)
        User x31 =  admin0.addUser( "newuser31","anemail31@you.com","some31password",100)
        User x32 =  admin0.addUser( "newuser32","anemail32@you.com","some32password",100)
        User x33 =  admin0.addUser( "newuser33","anemail33@you.com","some33password",100)
        User x34 =  admin0.addUser( "newuser34","anemail34@you.com","some34password",100)
        User x35 =  admin0.addUser( "newuser35","anemail35@you.com","some35password",100)
        User x36 =  admin0.addUser( "newuser36","anemail36@you.com","some36password",100)
        User x37 =  admin0.addUser( "newuser37","anemail37@you.com","some37password",100)
        User x38 =  admin0.addUser( "newuser38","anemail38@you.com","some38password",100)
        User x39 =  admin0.addUser( "newuser39","anemail39@you.com","some39password",100)
        User x40 =  admin0.addUser( "newuser40","anemail40@you.com","some40password",100)
        User x41 =  admin0.addUser( "newuser41","anemail41@you.com","some41password",100)
        User x42 =  admin0.addUser( "newuser42","anemail42@you.com","some42password",100)
        User x43 =  admin0.addUser( "newuser43","anemail43@you.com","some43password",100)
        User x44 =  admin0.addUser( "newuser44","anemail44@you.com","some44password",100)
        User x45 =  admin0.addUser( "newuser45","anemail45@you.com","some45password",100)
        User x46 =  admin0.addUser( "newuser46","anemail46@you.com","some46password",100)
        User x47 =  admin0.addUser( "newuser47","anemail47@you.com","some47password",100)
        User x48 =  admin0.addUser( "newuser48","anemail48@you.com","some48password",100)
        User x49 =  admin0.addUser( "newuser49","anemail49@you.com","some49password",100)
        User x50 =  admin0.addUser( "newuser50","anemail50@you.com","some50password",100)
        when :
         admin1.login("newuser1","some1password")
         admin2.login("newuser2","some2password")
         admin3.login("newuser3","some3password")
         admin4.login("newuser4","some4password")
         admin5.login("newuser5","some5password")
         admin6.login("newuser6","some6password")
         admin7.login("newuser7","some7password")
         admin8.login("newuser8","some8password")
         admin9.login("newuser9","some9password")
         admin10.login("newuser10","some10password")
         admin11.login("newuser11","some11password")
         admin12.login("newuser12","some12password")
         admin13.login("newuser13","some13password")
         admin14.login("newuser14","some14password")
         admin15.login("newuser15","some15password")
         admin16.login("newuser16","some16password")
         admin17.login("newuser17","some17password")
         admin18.login("newuser18","some18password")
         admin19.login("newuser19","some19password")
         admin20.login("newuser20","some20password")
         admin21.login("newuser21","some21password")
         admin22.login("newuser22","some22password")
         admin23.login("newuser23","some23password")
         admin24.login("newuser24","some24password")
         admin25.login("newuser25","some25password")
         admin26.login("newuser26","some26password")
         admin27.login("newuser27","some27password")
         admin28.login("newuser28","some28password")
         admin29.login("newuser29","some29password")
         admin30.login("newuser30","some30password")
         admin31.login("newuser31","some31password")
         admin32.login("newuser32","some32password")
         admin33.login("newuser33","some33password")
         admin34.login("newuser34","some34password")
         admin35.login("newuser35","some35password")
         admin36.login("newuser36","some36password")
         admin37.login("newuser37","some37password")
         admin38.login("newuser38","some38password")
         admin39.login("newuser39","some39password")
         admin40.login("newuser40","some40password")
         admin41.login("newuser41","some41password")
         admin42.login("newuser42","some42password")
         admin43.login("newuser43","some43password")
         admin44.login("newuser44","some44password")
         admin45.login("newuser45","some45password")
         admin46.login("newuser46","some46password")
         admin47.login("newuser47","some47password")
         admin48.login("newuser48","some48password")
         admin49.login("newuser49","some49password")
         admin50.login("newuser50","some50password")
        
         admin0.logout()
         admin1.logout()
         admin2.logout()
         admin3.logout()
         admin4.logout()
         admin5.logout()
         admin6.logout()
         admin7.logout()
         admin8.logout()
         admin9.logout()
         admin10.logout()
         admin11.logout()
         admin12.logout()
         admin13.logout()
         admin14.logout()
         admin15.logout()
         admin16.logout()
         admin17.logout()
         admin18.logout()
         admin19.logout()
         admin20.logout()
         admin21.logout()
         admin22.logout()
         admin23.logout()
         admin24.logout()
         admin25.logout()
         admin26.logout()
         admin27.logout()
         admin28.logout()
         admin29.logout()
         admin30.logout()
         admin31.logout()
         admin32.logout()
         admin33.logout()
         admin34.logout()
         admin35.logout()
         admin36.logout()
         admin37.logout()
         admin38.logout()
         admin39.logout()
         admin40.logout()
         admin41.logout()
         admin42.logout()
         admin43.logout()
         admin44.logout()
         admin45.logout()
         admin46.logout()
         admin47.logout()
         admin48.logout()
         admin49.logout()
         admin50.logout()
    then :
    noExceptionThrown()
    }
    
    
}