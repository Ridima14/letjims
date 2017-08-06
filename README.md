# letjims
College android application started in the end of july 2017

A college android app for JEMTEC GREATER NOIDA.

Targeted users:Faculty members of the college and all students of every batch and every year.

 Using Firebase(Google) cloud services for backend.
Functionalities to be impleted for two bases of users:

#Faculty of college

1)Login screen with firebase authentication

2)Menu page with horizontalscrollView,each section contains the classes associated with the logged in faculty(Hard coded as of now).
       
       a)NotifyStudents which posts notification set by faculty
       b)Query interface which is basically a chat interface with querries from students of a particular class '
         and answers from the faculty.
       c)ActivityFeed having every activity completed 
       d)SubmitMaterial section where faculty can submit images etc for students aid or notification.
      
#Students

1)Login

2)Homepage with data pulled from college and university website(either using jsoup library for static sections of webpage or
   webApi json object)
 
 3)Menupage with listview containing data from firebase database on notifications etc.
 
 4)Horizontalscrollview contains subjects according to their course(Hard coded for each class)which is connected to the
  particular faculty.
 
 5)Fire query to respective faculty
 
 6)Add notification with request.
 
 7)Events page(jsoup library but fetching of data is still a vague concept)
 
 
 
 Keep coding keep slaying.
      
