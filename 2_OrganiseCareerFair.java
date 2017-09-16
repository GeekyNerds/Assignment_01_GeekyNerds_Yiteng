/*Scenario:  2. Organise a career fair(Suppose you are the organiser)

2.1 Set up a questionaire website to collect student's preferred companies, career fair open dates, how many students willing to come  
2.2 According to the questionaire result, invite companies, decide career fair open dates
2.3 According to the invitation result, reserve room that meets company & student's need
2.4 Set up a career fair website for student to see career fair information detail, register check-in ticket and receive update
2.5 According to the registration result, decide whether further action is needed, such as "if a larger room is needed or not?"
2.6 Collect students and companies feedbacks about this career fair.

*/

//Identify Objects and Behaviors://

 Object : Organisor
 Attribute : name, emailAddr, phoneNumber, username, password
 Behaviors : loginAccount(), setUpWebsite(), sendEmail(), reserveRoom()
  
 Object : Student
 Attribute : studentName, studentEmailAddr, phoneNumber, username, password
 Behaviors : loginAccount(), answerQuestionaire(), registerCareerFair(), giveFeedback(), sendEmail()
  
 Object : Company
 Attribute : companyName, location, companyEmailAddr, jobTitle
 Behaviors : respondInvitation(), giveFeedback(), sendEmail()
  
 Object : QuestionaireWebsite
 Attribute : url
 Behaviors : authorize()
  
 Object : Questionaire
 Attribute : Question[] question
 Behaviors : 
   
 Object : CareerFairWebsite
 Attribute : url
 Behaviors : authorize()
  
 Object : LoginConfirmation
 Attribute : welcomeMessage
 Behaviors : 
  
 Object : QuestionaireResult
 Attribute : answeredMessage
 Behaviors : 
  
 Object : InvitationResult
 Attribute : boolean 
 Behaviors : 
  
 Object : RegistrationResult
 Attribute : registrationAmount
 Behaviors : 
 

//Sequence of Flow - Invoke Objects with Behaviors//
 
 Organisor yiteng
 Student student
 Company company
 QuestionaireWebsite questionwebsite
 Questionaire questionaire
 CareerFairWebsite careerwebsite
 LoginConfirmation loginconfirmation
 QuestionaireResult questionaireresult
 InvitationResult invitationresult
 RegistrationResult registrationresult
 
 //2.1 Set up a questionaire website to collect student's preferred companies, career fair open dates, how many students willing to come
 yiteng.setUpWebsite() -> Questionaire (input: question[0], question[1], ...)
 yiteng.sendEmail() -> studentEmailAddr
 student.loginAccount() -> username, password -> questionairewebsite: authorize & return LoginConfirmation object
 student.answerQuestionaire() -> companyName, preferredOpenDate, jobTitle -> questionwebsite: return QuestionaireResult object
 
 //2.2 According to the questionaire result, invite companies, decide career fair open dates
 yiteng.sendEmail() -> Invitation (input: companyEmailAddr, time, location, message) -> company: return InvitationResult object
 
 //2.3 According to the invitation result, reserve room that meets company & student's need
 yiteng.reserveRoom()
  
 //2.4 Set up a career fair website for student to see career fair information detail, register check-in ticket and receive update
 yiteng.setUpWebsite() -> openTime, fairLocation, companyName, jobTitle
 student.registerCareerFair() -> studentName, studentEmailAddr, phoneNumber -> careerwebsite: return RegistrationResult object
 
 //2.5 According to the registration result, decide whether further action is needed, such as "if a larger room is needed or not?"
 if(registration amount > room capacity)
 yiteng.reserveRoom()
 end
 
 //2.6 Collect students and companies feedbacks about this career fair
 student.giveFeedback()
 company.giveFeedback()
  
  
