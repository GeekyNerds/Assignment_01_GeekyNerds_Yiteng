Hi, Gorgeous Serena & Cute Harsha, 

GiveAssignmentToStudents:

TA serena
TA harsha
Student student
Platform github
TextEditor texteditor

serena.signUpGithub -> github: authorize and login
harsha.signUpGithub -> github: authorize and login
student.signUpGithub -> github: authorize and login

serena.gitClone -> github: clone download the neu-sep17 repository
harsha.gitClone -> github: clone download the neu-sep17 repository
student.gitClone -> github: clone download the neu-sep17 repository
 
serena.editAssignment -> texteditor: input the assignment
harsha.editAssignment -> texteditor: input the assignment

serena.gitPush -> github: add, commit, push the assignment
harsha.gitPush -> github: add, commit, push the assignment


student.gitPull -> github: pull the latest version of assignment
student.feedback -> student: feedback 0. siva-level hard 1. so-so 2. piece of cake
 

if feedback.isAvailable

 Loop
      if student.feedback: 1. so-so or 2. piece of cake
         student.finishAssignment
         break
      end
      
      if student.feedback: 0. siva-level hard
         serena.editAssignment -> texteditor: input an easier assignment
         harsha.editAssignment -> texteditor: input an easier assignment
         serena.gitPush -> github: add, commit, push the assignment
         harsha.gitPush -> github: add, commit, push the assignment
         student.gitPull -> github: pull the latest version of assignment
         student.feedback
      end
      
      
      else
         student.finishAssignment
      end
 End


else
   student.finishAssignment
end

serena.markAssignment
harsha.markAssignment

		
