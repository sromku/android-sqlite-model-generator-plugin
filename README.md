SQLite Model Generator
=====================================
Eclipse plugin - Generate SQLite model for Android based on JSON schema file

## Usage of the plugin
#### 1. Install the plugin
Update site: `http://romkuapps.com/Apps/SQLite/update`

#### 2. Create JSON file that describes your sqlite schema.
* Schema has `name` and tables
* `table` has `name`, `description` and columns
* `column` has `name` and `columnType`
* `columnType` can be `TEXT` or `INTEGER` only

**Example:** 
Two tables: `STUDENT` and `COURSE`
``` json
{
  "name": "SQL_PRIVATE_DB",
  "tables": [
    {
      "name": "STUDENT",
      "description": "Student details",
      "columns": [
        {
          "name": "FIRSTNAME",
          "columnType": "TEXT"
        },
        {
          "name": "LASTNAME",
          "columnType": "TEXT"
        },
        {
          "name": "AGE",
          "columnType": "INTEGER"
        }
      ]
    },
    {
      "name": "COURSE",
      "description": "Course details",
      "columns": [
        {
          "name": "NAME",
          "columnType": "TEXT"
        },
        {
          "name": "COURSENUMBER",
          "columnType": "INTEGER"
        },
        {
          "name": "DESCRIPTION",
          "columnType": "TEXT"
        },
        {
          "name": "POINTS",
          "columnType": "INTEGER"
        }
      ]
    }
  ]
}
```
#### 3. Right click on the file -> 'Generate SQLite Model...' ![Screenshot](http://romkuapps.com/Apps/Refs/right_click.png)
![Screenshot](http://romkuapps.com/Apps/Refs/project_right_click.png)


#### 4. Refresh the project
![Screenshot](http://romkuapps.com/Apps/Refs/project_after.png)

## Generated Code

#### Entities
Each table is converted to Java entity and is generated under 'entities' package
```
| - entities
```

#### Actions
Each entity can be creates, updated, deleted or retrieved from the database. The support for CRUD actions is located under 'actions' package
```
  | - actions
	  | - create
	  | - delete
	  | - read
	  | - update
```
#### Model
The entry point for all operations above SQLite database are done via Model.java class, which is located under 'model' package
```
	| - model	
```

## Usage 

#### Create
For each defined table in the JSON file you can create a new entity which will be saved in the SQLite table

``` java
	Student student = new Student();
	student.setFirstname("John");
	student.setLastname("Smith");
	student.setAge(30);
		
	Model model = Model.getInstance(context);
	model.createStudent(student);
```

#### Retrieve
For each defined table in the JSON file you can retrieve all entities from that SQLite table

``` java
	Model model = Model.getInstance(context);
	List<Student> students = model.readStudent();
```

#### Update
For each defined table in the JSON file you can update existing entity from the SQLite table

``` java
	student.setAge(31);
	
	Model model = Model.getInstance(context);
	model.updateStudent(student);
```

#### Delete
For each defined table in the JSON file you can delete existing entity from the SQLite table

``` java
	Model model = Model.getInstance(context);
	model.deleteStudent(student);
```

## How it works
* The first time the singleton instance of the model is created `Model model = Model.getInstance(context)`, all the SQLite database content is loaded into the memory.
* Each of CRUD commands works directly with the cached data and SQLite database. For example, `model.update...(Object object)` will update the entity in the memory and then update the raw in the database. Thus, the database is always synced. 
