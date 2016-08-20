# XML Hammer Library

_This library splits source XML file into multiple XML files pivoting on a particular node in the source XML._

## Examples:

### Input:
```
<Company>
	<Employees>
		<Employee>
			<FirstName>George</FirstName>
			<LastName>Michael</LastName>
		</Employee>
		<Employee>
			<FirstName>Maeby</FirstName>
			<LastName>Funke</LastName>
		</Employee>
	</Employees>
</Company>
```

### Output:
```
<Company>
	<Employees>
		<Employee>
			<FirstName>George</FirstName>
			<LastName>Michael</LastName>
		</Employee>
	</Employees>
</Company>
```

```
<Company>
	<Employees>
		<Employee>
			<FirstName>Maeby</FirstName>
            <LastName>Funke</LastName>
		</Employee>
	</Employees>
</Company>
```

## Parameters

* File Name: Mandatory. The XML file that needs to be split.
* Pivot Element Name: Mandatory. The element name from the XML file on which child elements will be split.
* Source Directory: Optional. The directory from the file system where the xml files are present. 
                    If this parameter is absent, application sets the source directory to current directory.
* Input Directory:  Optional. The directory for the Input File. this is considered to be relative to the source Directory.
                    If this parameter is absent, application sets the input directory to current directory.
* Output Directory: Optional. The directory for the Output Files. this is considered to be relative to the source Directory.
                    If this parameter is absent, application sets the output directory to current directory.

#### Examples:

* java -jar xmlhammer-1.0-SNAPSHOT.jar Company.xml Employees "" In/ Out/
* java -jar xmlhammer-1.0-SNAPSHOT.jar Company.xml Employees MyHomeDirectory In/ Out/
* java -jar xmlhammer-1.0-SNAPSHOT.jar Company.xml


## Things to Remember:

* Parameters must be passed in order as mentioned above.
* Optional Parameters follow all or none pattern, if any one is passed, all needs to be passed. Empty string is a valid value for the optional parameters.
* SplitterTest can be used to set arguments and run the program as a test in case of any errors with the executable.

## TODO List:

* Handle exceptions instead throws clauses.
* Stronger input parameter validations along with named parameters.
* Relaxation on the input argument rules.
* Support for customizing the output file names.
* Enrich unit tests add have better coverage.

## Wish List:

* Schema Validation Algorithms
* Adding a feature to merge different XML files from a directory to one composite xml.
* Apache Camel support to move source files to archival directories and stream output files to target directory.
* Support for different XML parsers.

## Support:

* Create an GitHub issue for any issues/requests.