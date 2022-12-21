#!/bin/bash 

#!/bin/bash

# The 3 variables below store server and login details
HOST="10.119.160.156"
USER="svsapcc"
PASSWORD="MimTlcl17+"


# $1 is the first argument to the script
# We are using it as upload directory path
# If it is '.', file is uploaded to current directory.
DESTINATION="/u01/Telcel/test"


# Rest of the arguments are a list of files to be uploaded.
# ${@:2} is an array of arguments without first one.
ALL_FILES="/u01/Telcel/REPORTS/BESGestorReports/done/1KQ/20220531/BES_R011KQPRB_20220611091453.txt"


# FTP login and upload is explained in paragraph below
sftp -inv $HOST <<EOF
put MimTlcl17+
user $USER $PASSWORD
cd $DESTINATION
mput $ALL_FILES
bye
EOF


# sshpass -p MimTlcl17+ sftp svsapcc@10.119.160.156