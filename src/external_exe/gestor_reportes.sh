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

move_and_backup() {
    # inicia move_and_backup
    rm /u01/Telcel/DATA/GestorReportes/move_and_backup.txt
    ls  $( find /u01/Telcel/REPORTS/BESGestorReports/done -type f -name $1 ) > /u01/Telcel/DATA/GestorReportes/move_and_backup.txt
}

mv_wy() {
    #inicia mv_wy
    ##chmod 775 /u01/Telcel/REPORTS/BESGestorReports/send/ -R;
    mkdir /u01/Telcel/DATA/GestorReportes/send_backup;
    mkdir /u01/Telcel/DATA/GestorReportes/send_backup/$2;
    mkdir /u01/Telcel/DATA/GestorReportes/send;
    cp -p $1 /u01/Telcel/DATA/GestorReportes/send;
    mv $1 /u01/Telcel/DATA/GestorReportes/send_backup/$2;
    echo "Fin post move_and_backup"
}

download_files() {
    # inicia download_files
    rm /u01/Telcel/DATA/GestorReportes/download_file.txt
    ls  $( find /u01/Telcel/REPORTS/BESGestorReports/done -type f -name $1 ) > /u01/Telcel/DATA/GestorReportes/download_file.txt
}

send_ftp() {
    
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
    move_and_backup $2
elif [ $1 = "mv_wy" ]
then
    mv_wy $2 $3 
elif [ $1 = "send_ftp" ]
then
    send_ftp $2 $3
else
    echo "no es parametro"
fi

