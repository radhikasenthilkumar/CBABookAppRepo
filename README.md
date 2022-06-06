# CBABookAppRepo
Created a Book system Demo Application for CBA.

<h1> Application Features Description</h1>
<div>
<ul>
<li>JDK 18</li>
<li>Maven Build</li>
<li>Spring boot web – (For exposing REST API’s)</li>
<li>Spring boot JPA – (For CRUD operations)</li>
<li>Spring boot Test – (For creating unit test cases)</li>
<li>Spring boot Validation – ( For native & Custom bean validation)</li>
<li>Java MySQL Connector – (for accessing MySQL database)</li>
<li>LAMBOG – (For avoid boiler plate code)</li>
<li>GitHub – (For version control )</li>
<li>MySQL – For database</li>
</ul>
</div>

<h1> Java Class Description</h1>
<div>
<ul>
<li>controller/BookController.java - Exposes rest API's for the library</li>
<li>model/Book. java - Entity/Bean class for Book</li>
<li>validation/ISBNValidation. java - Custom Bean Annotation for ISBN number</li>
<li>validation/ISBNValidator.java - Custom validation for ISBN number</li>
<li>exception/GlobalExceptionHandler .java - Custom global error handling for Rest API's</li>
<li>exception/ErrorResponse. java - Model for custom error handling</li>
<li>exception/CustomBookException .java - Custom excepion calss for handling common exceptions of book entity</li>
<li>repoistory/BookRepository.java - Native ORM for CRUD operations</li>
<li>repoistory/BookRepositoryCustom.java - Interface for Custom ORM</li>
<li>repoistory/BookRepositoryImpl.java - Implementation for Custom ORM</li>
<li>service/BookServiceImpl. java.  - Controller for exposing API’s</li>
<li>BookSystemApplication.java. - Spring boot App initialiser</li>
<li>/resources/application.properties- Application configuration file</li>
<li>/test/controllerBookControllerTest.java - Unit test cases for REST API’s contoller methods</li>
<li>/pom.xml - Maven Build file</li>
</ul>
</div>
