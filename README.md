ofr
===
Introduction:

Every day in newspaper or other communication media we see the news of people who need funding for bearing their medical expenses or for other issues. Even every year we see people raising fund for people who are struck by natural disaster. It is sometime very difficult to raise fund as convincing people and collecting money is a hard process. What if we have a system where people can register and ask for help and also people can donate money without hesitating if their money is properly spent.

Scope:

The  scope  of  this  project will be limited to those people who are looking for raising fund for some issue like, raising fund for bearing medical expenses or for helping people who are affected by natural causes like flood, earthquake, cyclone or any other natural disasters. People can register to the system and request for an issue. After verifying the issue admin will publish the issue. People who are interested (both registered and anonymous user) can donate and collect receipts.


Users and their roles:

The following describes the various users of the app and their respective roles:

Anonymous user:

Can view list of issues
Can donate
Get receipt of donation

Registered user:

Can view list of issues
Can request a issue to admin
Can donate
Can update his profile
Can view his/her contribution
Can view his/her issues
Can invite people for donation via email
Get receipt of donation

Admin:

views list of issues
can activate and close an issue

Use cases of the system:

The following describes the use case of the system:

Registration (1st):
Users  will  be  provided with a form and by correctly filling out the form the user will be registered to the app and will be assigned a unique id.

Login (1st):
Users  will  be  provided  with  a form with two fields: email and password. By filling the form with  registered  email  and  password  the  user  can  have  access  to features  of  the app according to the role (admin or user).

Requesting Issue (1st):
Authorized  users  can  request issue  using  an  interface   which  will  contain  the  following attributes: title, help for, description, needed money.

Activating Issue (1st):
Only admin can activate an issue. Before activating an issue admin will run background checking for the validity of the issue.

Closing Issue (1st):
An issue will be closed automatically if required money is collected. And admin will also be able to close an issue manually.

Recent Issue (1st):
Ten recent issue will be visible in the user home page.

Donation (1st):
Both anonymous and registered users can donate money using paypal and download receipt in pdf format.


Sending Invitation for Donation:
Registered user can send invitation for donation to a friend via email address.

View User Contribution:
Registered user can view the events he/she contributed with the amount of contribution

View User Issue (1st):
Registered user can also view the issues he/she created.

Personal Profile:
The  registered user  can  update  their  personal  information  using  this  feature.

Search Issue:
A  user  can  search  an  issue .

Tech Spec:
● JSF 2.0
● PrimeFaces 3.5
● Weld 1.0
● EJB 3.1
● JPA 2.0
● Glassfish 3.x
● Maven 3.x
● Intellij Idea 12.x
● MySql Database
