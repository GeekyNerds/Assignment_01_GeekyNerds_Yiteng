/*Scenario:  4. Design a code sharing platform

4.1 be able to authorize user login
4.2 be able to clone download an existing repository into local working directory(workspace)
4.3 be able to add local changes into stage(temporary storage area)
4.4 be able to commit all the local changes from stage into local repo branch
4.5 be able to push local repo branch into the remote repo branch
4.6 be able to pull to get the latest version of repo
4.7 be able to show difference between repo branches
4.8 be able to show difference between workspace and stage
4.9 be able to save all the change operations history into one place for track and management
4.10 be able to add collaborator into repo
4.11 be able to show user's contribution history
4.12 be able to create repository
4.13 be able to show comment

*/

//Identify Object and Behaviors://

Object : CodeSharingPlatform
Attribute : url, platformName, username, password
Behaviors : authorizeUserLogin(), clone(), add(), commit(), push(), pull()
            showBranchDifference(), showWorkAndStageDifference(), saveChangesHistory()
            addCollaborator(), showContributionHistory(), createRepository(), showComment()

           
public class CodeSharingPlatform{
     string url;
     string platformName;
     string username;
     string password;
     
     //authorize user login//
     boolean authorizeUserLogin(){
     
     }
     
     //clone download an existing repo into local working directory(workspace)//
     void clone(){
     
     }
     
     //add local changes into stage (temporary storage area)//
     void add(){
     
     }
     
     //commit all the local changes from stage into local repo branch//
     void commit(){
     
     }
     
     //push local repo branch into the remote repo branch//
     void push(){
     
     }
     
     //pull to get the latest version of repo//
     void pull(){
     
     }
     
     //show difference between repo branches//
     string showBranchDifference(){
    
     }
     
     //show difference between workspace and stage//
     string showWorkAndStageDifference(){
    
     }
     
     //save all the change operations history into one place for track and management//
     string saveChangesHistory(){
    
     }
     
     //add collaborator into repo//
     void addCollaborator(){
     
     }
     
     //show user's contribution history//
     string showContributionHistory(){
     
     }
     
     //create repository
     void createRepository(){
     
     }
     
     //show comment
     string showComment(){
     
     }
    
}
