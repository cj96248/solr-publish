## Solr with solrJ
This project uses solrJ to publish data to solr database.
The default env is:
- JDK1.8 or above
- Solr6.0
- Eclipse 4.5
- solr-solrj-6.0.0.jar
- dom4j-1.6.1.jar
- fastjson-1.2.7.jar

### Insert XML file data into solr database
1. Parse the XML file use SAXReader
2. Convert the XML data to JavaBean, and create the bean list
3. Create Solr client
4. Traverse the java bean list,and insert it into solr 

### Insert JSON file data into solr database
1. Parse the JSON file use fastjson
2. Convert the JSON data to a java bean list
3. Create Solr client
4. Traverse the java bean list,and insert it into solr 
