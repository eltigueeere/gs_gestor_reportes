# App en spring para correr en un servidor WAS IBM Liberty

# Install linux
sudo dnf search openjdk 
sudo dnf install java-1.8.0-openjdk -y 
sudo alternatives --config java

# gs_gestor_reportes


# Install ORACLE
mvn install:install-file -Dfile=ojdbc8.jar -DgroupId=com.oracle -DartifactId=ojdbc8 -Dversion=19.3 -Dpackaging=jar

# Run 
- mvn clean; mvn install; mvn cobertura:cobertura