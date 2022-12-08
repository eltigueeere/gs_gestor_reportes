#! /bin/bash
# Script by la base dev

#Description parameters
#list_folders 
#download_folders
#download_files 
#move_and_backup


list_folders() {
    #inicia list folders
    #rm /u01/Telcel/DATA/GestorReportes/list_folders.txt 
    ls /u01/Telcel/REPORTS/BESGestorReports/done > /u01/Telcel/DATA/GestorReportes/list_folders.txt
}

download_folders() {
    # inicia inicia Download folders
    ls -p /u01/Telcel/REPORTS/BESGestorReports/done/$1/* | grep -v / > /u01/Telcel/DATA/GestorReportes/download_folders.txt

}

download_files() {
    # inicia download_files
    rm /u01/Telcel/DATA/GestorReportes/download_file.txt
    ls  $( find /u01/Telcel/REPORTS/BESGestorReports/done -type f -name $1 ) > /u01/Telcel/DATA/GestorReportes/download_file.txt
}

if [ $1 = "list_folders" ]
then
    list_folders
elif [ $1 = "download_folders" ]
then
    download_folders $2
elif [ $1 = "download_files" ]
then
    download_files $2
elif [ $1 = "move_and_backup" ]
then
    echo "inicia move and backup"
else
    echo "no es parametro"
fi
