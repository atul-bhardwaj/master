# master


GoEuroTest.jar is the final jar to be used for testing. 

Following are the things to be noted. 

1. Provided application has been developed and tested on java-7.
2. Third party jars used are log4j, jackson and javacsv.
3. Only 1 word city is expected as argument. Any space between city name and no word at all will result into error.
4. Logs are created in GoEuroTestLogs folder located at application level.
5. CityName_Timestamp.csv will be created for every successful run at application level. 
6. If there are no suggestions available for the given, a blank file will still be created.