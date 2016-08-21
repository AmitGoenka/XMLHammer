# XML Hammer Library

![alt tag](http://images.clipartpanda.com/hammer-clipart-4TbKxXBAc.gif)

_<B>This library supports XML split and merge operations.</B>_

SPLIT operation splits a source XML file into multiple XML fragment files pivoting on a particular XML node.
MERGE operation merges multiple XML fragmented files into a single XML file pivoting on a particular XML node.

## Examples

### Split Operation:

#### Input:
Company.xml
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

#### Output:
Employee-1.xml
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
Employee-2.xml
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

### Merge Operation:

Reverse of Split operation.
Merging Employee-1.xml and Employee.xml will yield Employees.xml with the content same as Company.xml.

## Basic Usage

* <B>`-m`</B> : Merge Mode
    * Mandatory for Merge operation.
    * This is a qualifier parameter and does not need a corresponding value.
* <B>`-s`</B> : Split Mode
    * Mandatory for Split operation.
    * This is a qualifier parameter and does not need a corresponding value.
* `-h` : Home Directory
    * Optional
    * The home directory from where input and output directories are referenced.
    * In the absence of this parameter, current directory is used as home directory.
* `-i` : Input Directory
    * Optional
    * <B>This is relative to home directory.</B>
    * The input directory where source files are located.
    * In the absence of this parameter, home directory is used as input directory.
* `-o` : Output Directory (Relative to source directory)
    * Optional
    * <B>This is relative to home directory.</B>
    * The output directory where output files will be written.
    * In the absence of this parameter, home directory is used as output directory.
* `-p` : Pivot Element Name
    * Mandatory
    * The element name from the XML file on which child elements will be split/merged.
    * Example: 'Employees'.
* `-n` : File Name
    * Mandatory for Split operation. Does not apply to Merge operation.
    * The input xml file that need to be split.
* `-f` : File Name Prefix
    * Mandatory for Merge operation. Does not apply to Split operation.
    * This is the common prefix of the file names which needs to be merged.
    * The name of the files that need to be merged must follow the pattern: Prefix-${index}.xml.
    * Examples: Employee-1.xml, Employee-2.xml, Employee-3.xml.
* `-x` : Start Index
    * Optional. Does not apply to Split operation.
    * Starting Index of the files that need to be merged.
    * In the absence of this parameter, default base value 1 is used.
    * Example: If there are four files present in input directory of the pattern (Employee-${index}.xml), 
                a start index of 3 will apply merge operation on files starting from (Employee-3.xml) only
                and leave the files with preceding (1, 2) indices.
* `-y` : End Index
    * Optional. Does not apply to Split operation.
    * End Index of the files that need to be merged.
    * In the absence of this parameter, max index in the input directory is used.
    * Example: If there are four files present in input directory of the pattern (Employee-${index}.xml), 
                an end index of 2 will apply merge operation on files up to (Employee-2.xml) only
                and leave the files with following (3, 4) indices.
* JVM Heap Arguments:
    * On large operations, JVM might throw `OutOfMemoryException` due to insufficient heap space.
    * In such situations, it is recommended to pass heap size parameters in the execution command
    * The JVM heap arguments should be the preceding arguments in the argument list. Example (java -Xmx512m -jar ...).
    * Example arguments: -Xms512m -Xmx1024m.

### Examples:

* java -jar xmlhammer-1.0.jar -s -h C:/Users/me -i In -o Out -n Company.xml -p Employees
* java -jar xmlhammer-1.0.jar -s -h C:/Users/me -n Company.xml -p Employees
* java -jar xmlhammer-1.0.jar -m -h C:/Users/me -i Out -o In -p Employees -f Employee -x 1 -y 2
* java -jar xmlhammer-1.0.jar -m -h C:/Users/me -i Out -o In -p Employees -f Employee
* java -jar xmlhammer-1.0.jar -m -h C:/Users/me -i -p Employees -f Employee -x 1 -y 2
* java -jar xmlhammer-1.0.jar -m -h C:/Users/me -i -p Employees -f Employee -x 5 -y 10
* java -jar xmlhammer-1.0.jar -m -h C:/Users/me -i -p Employees -f Employee -x 5
* java -Xms512m -Xmx1024m -jar xmlhammer-1.0.jar -m -h C:/Users/me -i Out -o In -p Employees -f Employee -x 1 -y 2

## Installation:

`mvn package`

## Troubleshooting:

* `SplitterTest` can be used to set arguments and run as test in case of any errors with the executable.
* `MergerTest` can be used to set arguments and run as test in case of any errors with the executable.

## Going Forward:

* Support for customizing the output file names.
* Enrich unit tests and have better coverage.
* Schema Validation Algorithms
* Apache Camel support to move source files to archival directories and stream output files to target directory.
* Support for different XML parsers.

## Support:

* Create an GitHub issue for any issues/requests.