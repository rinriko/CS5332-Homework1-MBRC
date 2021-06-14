# Table of Contents
1. [Message Buffer and Response Connector (MBRC)](#mbrc)
2. [Install Python to Local machine](#install-python)
3. [Download project to Local machine](#download-project)
4. [How to run this program](#how-to-run)

Note: 
- Follow step 2 if Apache NetBeans 12.4 is not installed on the local machine. 
- Follow step 3 and 4 if project has not been downloaded to the local machine.

----------------------------------------------------
## <a name="mbrc"></a> Message Buffer and Response Connector (MBRC)

This is a multi-threaded program for communicating messages between a sender object thread and a receiver object thread using a "message buffer and response connector (MBRC)" object in an object-oriented language (Java, C++, or C#).

The figure below depicts the MBRC class, followed by the detailed specifications of operations. The MBRC class provides the send(), receive(), and reply() operations and has a message buffer and a response buffer where each buffer can hold only one message. 

There are two separate objects, a sender object, and a receiver object, each having its thread in multi-threaded programming and communicate with each other. They are concurrently running to communicate messages synchronously. The sender object can send the next message to the receiver object only after receiving the response of the previous message from the receiver object. 

Also, the message Content of each message from a sender object to a receiver object requires a digital signature (non-repudiation security service). In contrast, each response from the receiver object to the sender object requires a message digest (integrity security service).

![alt text](https://github.com/rinriko/CS5332-Homework1-MBRC/blob/main/image/image1.JPG?raw=true)

![alt text](https://github.com/rinriko/CS5332-Homework1-MBRC/blob/main/image/image2.JPG?raw=true)

----------------------------------------------------
## <a name="install-python"></a> Install Apache NetBeans 12.4 to Local machine
1. Go to this [link](https://netbeans.apache.org/download/nb124/nb124.html).
2. Download Apache NetBeans 12.4 for your Operating System (e.g., Mac OS X, Windows)
3. Install Apache NetBeans 12.4 by following this [link](https://www3.ntu.edu.sg/home/ehchua/programming/howto/netbeans_howto.html).
----------------------------------------------------
## <a name="download-project"></a> Download project to Local machine
### First method (clone repository)
1. Open Command Prompt or Terminal. [More information](https://www.groovypost.com/howto/open-command-window-terminal-window-specific-folder-windows-mac-linux/).
2. Change the current working directory to the location where you want to download the project.
3. Clone repository:
```sh
$ git clone https://github.com/rinriko/CS5332-Homework1-MBRC.git
```
Note: If Git is not installed, please complete the following [instructions](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git). Or follow the [second method](#second-method) instructions.

### <a name="second-method"></a> Second method (download project)
1. Go to https://github.com/rinriko/N-Queens.
2. Above the list of files, click &#8595; Code.
3. Click "Download ZIP".
4. Save the zip file into a convenient location on your PC and start working on it.
5. Extract the ZIP file.

----------------------------------------------------

## <a name="how-to-run"></a> How to run this program
1.  Launch the NetBeans IDE.
    - On Microsoft Windows systems, you can use the NetBeans IDE item in the Start menu.
    - On Solaris OS and Linux systems, you execute the IDE launcher script by navigating to the IDE's bin directory and typing ./netbeans.
    - On Mac OS X systems, click the NetBeans IDE application icon.
2.  In the NetBeans IDE, choose File | Open Project
3.  Select the project file that your colon from the Github
4.  Run the Program
    - From the IDE's menu bar, choose Run | Run Main Project.
    - The next figure shows what you should now see.
