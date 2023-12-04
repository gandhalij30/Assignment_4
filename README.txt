---- Coupling ----
	
	-It is very common in software development in java one layer makes use of another layer.
	-While doing that the layer which is using another layer is known as dependent
	-The layer which is getting used which is called as dependency
	-If dependency needs to be changed that affect dependent it leads to common software problem known as tight coupling
	-tight coupling is bad must be avoided
	-In traditional approach dependent is responsible for create all of dependencies which leads to tight coupling
	-Exact reverse approach of this is dependent is no more responsibility for more responsibility for object creation
	of it dependency some external mechanism will create both the objects and pass dependency to its dependent
	-This known as "Dependency Injection"
	-Spring gives you dependency injection
	-In other words two layers must be loosely coupled from each other
	-In order to do this one layer must only be aware of and communicate with other layer using Interfaces
	-In implementation it is known as "coding to Interfaces"
	
	-This is the exact reason why spring framework was written
	-The core principle of spring is to avoid tight coupling among the layers
	-in order to achieve loose coupling using Spring framework bring as following things
		1. Spring takes the responsibility of creating object of dependency and dependent both
		   it is done by a spring object known as Spring Container
		   Spring Container is also known as IoC Container (Inversion of Control)
		   these object been called as "Beans"
		   hence Spring Container is also known as "Bean Container"
		   
		2. Spring container object can configured by couple of ways
		   i) XML : this traditional old way of configuring Spring Container it still valid and supported by Spring
		            but it is not used in these days
		            
		            Root tag tag of XML in <beans>.
		            One <beans> tag can have multiple <bean> tag.
		            Every bean in Spring Container has  unique ID associated with it.
		            bean will be identified by using that unique ID.
		            
		            Writing XML file is altogether a different syntax, as XML is different language.
		     		Writing XML is namespaces is not only difficult but also error prone.
		     		Values in the XML attributes are written in "", hence if there are any typos they wont be caught
		     		until runtime.
		     		Hence it becomes difficult for java developers to write XML configuration file.
		     		To exactly avoid all these issues, second way is evolved which is annotation configuration
		   ii)Annotation :
		   			This new, popular and common approach this is the most common approach today
					The Annotation was added in Spring 2.5 and above.
					
				Steps to use Annotation:
					1. Annotate classes using stereo type Annotation to register class as Spring Bean
					Following are stereo type Annotation:
						@Component	: This is a generic annotation use to mark many classes as spring bean.
						@Repository	: This is written on top of dao classes, hence dao classes are also known as
									  repository classes.
						@Service	: This is written on top of service classes
						@Controller	: This is written on top of controller classes
					above annotations are equivalent of <bean> tag in XML
					all above annotation are disable by default in order to enable we have to use following steps:
						1. Write following tag to enable these annotation
							<context:component-scan base-package="in.co.vwits.sms"></context>
							Above tag tell spring container to search classes mark with stereo type annotation.
							In the package mention and all of its sub packages
							Best practice is to mention project base package name
							
						2. To used above tag we have to add necessary name space (context name space)
						
					Dealing with XML and XML namespaces is not only defcult but also error prone task, hence
					new approach is evolved known as Java configuration (Zero XML)
						Step 1: To write a configuration class
						step 2: Annotate the class with @Configuration
						Step 3: Annotate the class with @ComponentScan
						Step 4: Instenciate the Spring Container using AnnotationConfigApplicationContext
						
					@Autowired is used to do auto wiring which cane be used on top of Field, Constructor, Setter
					
Steps to use Spring
	Step 1: Add appropriate dependency
	Step 2: Create XML configuration file (applicationContext.xml)
			Note : Name of the XML file can be any thing you like but applicationContext is the recommended name
			       and use by developer community
	Step 3: Create Spring container object (defers based on how are we configuring Spring container object)
	Step 4: Write appropriate configuration in applicationContext.xml file
	
Types of Dependency Injection
	1. Constructor Injection
		<constructor-arg ref="studentDao"></constructor-arg>
	2. Setter Injection / Property Injection
		<property name="dao" ref="studentDao"></property>
		
*** List of Important Annotation:
	1. @Component
	2. @Service
	3. @Repository
	4. @Autowired
	5. @Configuration
	6. @ComponentScan
	7. @Controller
	8. @Bean
	
