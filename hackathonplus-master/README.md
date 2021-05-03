# hackathonplus
Code Repository For RedHat Hackathon
------------------------------------------------------------------------------------------------------
activity tracker :
------------------------------------------------------------------------------------------------------
before running the project make sure to have a mongodb set up with dbname 'healthplannerdb'
execute beow commands in mongo terminal to create a collection counter which will act as a sequence generater/counter
       db.createCollection("counter")
       db.counter.insert ({_id:"user_id",seq_value : 0})

******************************************************************************************************
endpoint and sample requests:
*****************************************************************************************************
GET  : http://localhost:8080/healthplanner/patient/all  -- returns all patient records in db

POST   :   http://localhost:8080/healthplanner/patient/register  -- to register a user
sample input:
{
        "name" : {
                "firstName" : "Mili",
                "lastName" : "M"
        },
        "gender" : "female",
        "age" : 38,
        "height" : 167,
        "weight" : 63,
	"city"  : "Bangalore"
}

POST  : http://localhost:8080/healthplanner/patient/calorie?id={patientid}&exercise={exercise}
for eg: patientid = 3
        exercise = run 4 km, walk 1 hour etc..
**********************************************************************************************************
